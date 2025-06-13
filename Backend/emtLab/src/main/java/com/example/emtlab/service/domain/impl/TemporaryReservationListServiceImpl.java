package com.example.emtlab.service.domain.impl;

import com.example.emtlab.model.domain.Accommodation;
import com.example.emtlab.model.domain.TemporaryReservationList;
import com.example.emtlab.model.domain.User;
import com.example.emtlab.model.enumerations.TemporaryListStatus;
import com.example.emtlab.model.exceptions.AccommodationAlreadyInException;
import com.example.emtlab.model.exceptions.AccommodationIsAlreadyRentedException;
import com.example.emtlab.repository.AccommodationRepository;
import com.example.emtlab.repository.TemporaryReservationListRepository;
import com.example.emtlab.repository.UserRepository;
import com.example.emtlab.service.domain.TemporaryReservationListService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemporaryReservationListServiceImpl implements TemporaryReservationListService {

    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;

    private final TemporaryReservationListRepository temporaryReservationListRepository;

    public TemporaryReservationListServiceImpl(UserRepository userRepository, AccommodationRepository accommodationRepository, TemporaryReservationListRepository temporaryReservationListRepository) {
        this.userRepository = userRepository;
        this.accommodationRepository = accommodationRepository;
        this.temporaryReservationListRepository = temporaryReservationListRepository;
    }

    @Override
    public Optional<TemporaryReservationList> addAccommodationToTemporaryList(String username, Long accommodationId) {

        Optional<User> user=userRepository.findByUsername(username);
        Optional<Accommodation> accommodation=accommodationRepository.findById(accommodationId);

        if(user.isPresent() && accommodation.isPresent()){

            TemporaryReservationList temporaryReservationList=null;

            for(TemporaryReservationList t : user.get().getTemporaryReservationLists()){
                if(t.getTemporaryListStatus()== TemporaryListStatus.CREATED){
                    temporaryReservationList=t;
                }
            }

            if (temporaryReservationList==null) return Optional.of(temporaryReservationListRepository.save(new TemporaryReservationList(user.get())));

            if (accommodation.get().getRented()){
                    throw new AccommodationIsAlreadyRentedException();
                }


            if(!temporaryReservationList.getAccommodations().stream().filter(p->p.getId().equals(accommodationId)).toList().isEmpty()){
                throw new AccommodationAlreadyInException(accommodationId, username);
            }
            temporaryReservationList.getAccommodations().add(accommodation.get());
            return Optional.of(temporaryReservationListRepository.save(temporaryReservationList));
        }

        return Optional.empty();
    }

//    @Override
//    public List<TemporaryReservationList> listAllTemporaryLists(Long listId) {
//
//        Optional<TemporaryReservationList> temporaryReservationList=temporaryReservationListRepository.findById(listId);
//        List<Accommodation> accommodations=null;
//
//        if(temporaryReservationList.isPresent()){
//            accommodations = temporaryReservationList.get().getAccommodations();
//        }
//
//        return accommodations;
//    }

    @Override
    public Optional<TemporaryReservationList> confirmReservations(String username) {

        Optional<User> user=userRepository.findByUsername(username);

        TemporaryReservationList temporaryReservationList=null;

        if(user.isPresent()) {
            for (TemporaryReservationList t : user.get().getTemporaryReservationLists()){
                if(t.getTemporaryListStatus()==TemporaryListStatus.CREATED){
                    temporaryReservationList=t;
                }
            }
//            if(temporaryReservationList==null){
//                return "The user has no temporary list with status CREATED";
//            }

            for(Accommodation accommodation: temporaryReservationList.getAccommodations()){
                accommodation.setRented(true);
                accommodationRepository.save(accommodation);
            }

            temporaryReservationList.setTemporaryListStatus(TemporaryListStatus.FINISHED);
            return Optional.of(temporaryReservationListRepository.save(temporaryReservationList));
        }
        return Optional.empty();
    }

    @Override
    public Optional<TemporaryReservationList> getActiveTemporaryList(String username) {
        User user=userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
        return Optional.of(temporaryReservationListRepository.findByUserAndTemporaryListStatus(user, TemporaryListStatus.CREATED).orElseGet(() -> temporaryReservationListRepository.save(new TemporaryReservationList(user))));

    }
}

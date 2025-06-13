package com.example.emtlab.service.domain.impl;

import com.example.emtlab.model.domain.Accommodation;
import com.example.emtlab.model.enumerations.AccommodationType;
import com.example.emtlab.model.views.AccommodationsPerHostView;
import com.example.emtlab.repository.AccommodationRepository;
import com.example.emtlab.repository.AccommodationsPerHostViewRepository;
import com.example.emtlab.repository.HostRepository;
import com.example.emtlab.service.domain.AccommodationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final AccommodationsPerHostViewRepository accommodationsPerHostViewRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostRepository hostRepository, AccommodationsPerHostViewRepository accommodationsPerHostViewRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.accommodationsPerHostViewRepository = accommodationsPerHostViewRepository;
    }


    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        Optional<Accommodation> accommodation=accommodationRepository.findById(id);
        return accommodation;
    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        if (accommodation.getHost() != null &&
                hostRepository.findById(accommodation.getHost().getId()).isPresent() &&
                accommodation.getCategory() != null &&
                Arrays.stream(AccommodationType.values()).toList().contains(accommodation.getCategory())) {
            return Optional.of(
                    accommodationRepository.save(new Accommodation(
                            accommodation.getName(), accommodation.getCategory(),
                            hostRepository.findById(accommodation.getHost().getId()).get(),
                            accommodation.getNumRooms()
                    )));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return accommodationRepository.findById(id).map(existingAccommodition->{
            if(accommodation.getName()!=null){
                existingAccommodition.setName(accommodation.getName());
            }
            if(accommodation.getCategory()!=null){
                existingAccommodition.setCategory(accommodation.getCategory());
            }
            if(accommodation.getHost()!=null){
                existingAccommodition.setHost(hostRepository.findById(accommodation.getHost().getId()).get());
            }
            if(accommodation.getNumRooms()!=null){
                existingAccommodition.setNumRooms(accommodation.getNumRooms());
            }
            return accommodationRepository.save(existingAccommodition);
        });
    }

    @Override
    public Optional<Accommodation> markAsRented(Long id) {
        Accommodation accommodation=accommodationRepository.findById(id).orElseThrow();
        accommodation.setRented(true);
        return Optional.of(accommodationRepository.save(accommodation));
    }

    @Override
    public void refreshMaterializedView() {
        accommodationsPerHostViewRepository.refreshMaterializedView();
    }

    @Override
    public List<AccommodationsPerHostView> getAccommodationsCountByHost() {
        return accommodationsPerHostViewRepository.findAll();
    }

    @Override
    public Page<Accommodation> findAll(Pageable pageable) {
        return accommodationRepository.findAll(pageable);
    }
}

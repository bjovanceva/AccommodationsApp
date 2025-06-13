package com.example.emtlab.config.init;

import com.example.emtlab.model.domain.Country;
import com.example.emtlab.model.domain.User;
import com.example.emtlab.model.enumerations.Role;
import com.example.emtlab.repository.AccommodationRepository;
import com.example.emtlab.repository.CountryRepository;
import com.example.emtlab.repository.HostRepository;
import com.example.emtlab.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final AccommodationRepository accommodationRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public DataInitializer(CountryRepository countryRepository, HostRepository hostRepository, AccommodationRepository accommodationRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.accommodationRepository = accommodationRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostConstruct
    public void init(){
        countryRepository.save(new Country("Macedonia", "Europa"));
        countryRepository.save(new Country("Italy", "Europa"));
        countryRepository.save(new Country("Canada", "North America"));
        countryRepository.save(new Country("Brazil", "South America"));
        countryRepository.save(new Country("China", "Asia"));

        userRepository.save(new User(
                "bj",
                passwordEncoder.encode("bj"),
                "Bojana",
                "Jovancheva",
                Role.ROLE_USER
        ));


//        Optional<Country> country1 = countryRepository.findById(Long.valueOf(1));
//        Optional<Country> country2 = countryRepository.findById(Long.valueOf(2));
//
//
//        hostRepository.save(new Host("Bojana", "Jovancheva", country1.get()));
//        hostRepository.save(new Host("Angela", "Jovancheva", country2.get()));
//        hostRepository.save(new Host("Ljupche", "Angelovski",country1.get()));
//
//        accommodationRepository.save(new Accommodation("Two birds", HOTEL, hostRepository.findAll().get(2), 35));
//        accommodationRepository.save(new Accommodation("Hotel", MOTEL, hostRepository.findAll().get(0), 100));
//        accommodationRepository.save(new Accommodation("Two birds", HOTEL, hostRepository.findAll().get(1), 45));

    }
}

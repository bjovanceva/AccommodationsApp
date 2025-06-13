package com.example.emtlab.service.domain.impl;

import com.example.emtlab.model.domain.Accommodation;
import com.example.emtlab.model.domain.Host;
import com.example.emtlab.model.projections.HostProjection;
import com.example.emtlab.model.views.HostsPerCountryView;
import com.example.emtlab.repository.AccommodationRepository;
import com.example.emtlab.repository.CountryRepository;
import com.example.emtlab.repository.HostRepository;
import com.example.emtlab.repository.HostsPerCountryViewRepository;
import com.example.emtlab.service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;
    private final AccommodationRepository accommodationRepository;

    private final HostsPerCountryViewRepository hostsPerCountryViewRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public HostServiceImpl(HostRepository hostRepository, CountryRepository countryRepository, AccommodationRepository accommodationRepository, HostsPerCountryViewRepository hostsPerCountryViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
        this.accommodationRepository = accommodationRepository;
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        Optional<Host> host=hostRepository.findById(id);
        return host;
    }

    @Override
    public Optional<Host> save(Host host) {
        Optional<Host> savedHost = Optional.empty();

        if(countryRepository.findById(host.getCountry().getId()).isPresent()){
            savedHost = Optional.of(hostRepository.save(new Host(
                    host.getName(),
                    host.getSurname(),
                    countryRepository.findById(host.getCountry().getId()).get()
            )));
            this.refreshMaterializedView();
//                    this.applicationEventPublisher.publishEvent(new HostCreatedEvent(host));


        }
        return savedHost;
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return hostRepository.findById(id).map(existingHost->{
            if(host.getName()!=null){
                existingHost.setName(host.getName());
            }
            if(host.getSurname()!=null){
                existingHost.setSurname(host.getSurname());
            }
            if(host.getCountry()!=null){
                existingHost.setCountry(countryRepository.findById(host.getCountry().getId()).get());
            }
//            return hostRepository.save(existingHost);

            Host updatedHost = hostRepository.save(existingHost);

            this.refreshMaterializedView();
//                        this.applicationEventPublisher.publishEvent(new HostCreatedEvent(host));

            return updatedHost;

        });
    }

    @Override
    public void deleteById(Long id) {
        List<Accommodation> accommodations=accommodationRepository.findAll();
        List<Host> hosts=hostRepository.findAll();
        Host new_host=null;
        for(Host host : hosts){
            if(!Objects.equals(host.getId(), id)) {new_host=host; break;}
        }
        for (Accommodation accommodation : accommodations) {
            if(accommodation.getHost().getId().equals(id)){
                if(new_host!=null) {
                    accommodation.setHost(new_host);
                    accommodationRepository.save(accommodation);
                }
                else{
                    accommodationRepository.deleteById(accommodation.getId());
                }
            }
        }
        hostRepository.deleteById(id);

    }

    @Override
    public void refreshMaterializedView() {
          hostsPerCountryViewRepository.refreshMaterializedView();
    }

    @Override
    public List<HostsPerCountryView> getHostsCountByCountry() {
        return hostsPerCountryViewRepository.findAll();
    }

    @Override
    public List<HostProjection> getAllHostsByNameAndSurname() {
        return hostRepository.findAllBy();
    }


}

package com.example.emtlab.service.application.impl;

import com.example.emtlab.dto.CreateHostDto;
import com.example.emtlab.dto.DisplayHostDto;
import com.example.emtlab.model.domain.Country;
import com.example.emtlab.model.projections.HostProjection;
import com.example.emtlab.model.views.HostsPerCountryView;
import com.example.emtlab.service.application.HostApplicationService;
import com.example.emtlab.service.domain.CountryService;
import com.example.emtlab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {

    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return DisplayHostDto.from(hostService.findAll());
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto createHostDto) {

        Optional<Country> country=countryService.findById(createHostDto.country());

        if(country.isPresent()){
            return hostService.save(createHostDto.toHost(country.get())).map(DisplayHostDto::from);
        }

        return Optional.empty();
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto createHostDto) {

        Optional<Country> country=countryService.findById(createHostDto.country());

        return hostService.update(id, createHostDto.toHost(country.get())).map(DisplayHostDto::from);
    }

    @Override
    public void deleteById(Long id) {
        hostService.deleteById(id);
    }

    @Override
    public List<HostsPerCountryView> getHostsCountByCountry() {
        return hostService.getHostsCountByCountry();
    }

    @Override
    public List<HostProjection> getAllHostsByNameAndSurname() {
        return hostService.getAllHostsByNameAndSurname();
    }
}

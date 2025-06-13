package com.example.emtlab.service.application.impl;

import com.example.emtlab.dto.CreateCountryDto;
import com.example.emtlab.dto.DisplayCountyDto;
import com.example.emtlab.service.application.CountryApplicationService;
import com.example.emtlab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<DisplayCountyDto> findAll() {
        return DisplayCountyDto.from(countryService.findAll());
    }

    @Override
    public Optional<DisplayCountyDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountyDto::from);
    }

    @Override
    public Optional<DisplayCountyDto> save(CreateCountryDto countryDto) {
        return countryService.save(countryDto.toCountry()).map(DisplayCountyDto::from);
    }

    @Override
    public Optional<DisplayCountyDto> update(Long id, CreateCountryDto countryDto) {
        return countryService.update(id, countryDto.toCountry()).map(DisplayCountyDto::from);
    }

    @Override
    public void deleteById(Long id) {
         countryService.deleteById(id);
    }
}

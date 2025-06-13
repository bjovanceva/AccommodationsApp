package com.example.emtlab.service.application;

import com.example.emtlab.dto.CreateCountryDto;
import com.example.emtlab.dto.DisplayCountyDto;
import com.example.emtlab.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {

    List<DisplayCountyDto> findAll();

    Optional<DisplayCountyDto> findById(Long id);
    Optional<DisplayCountyDto> save(CreateCountryDto countryDto);
    Optional<DisplayCountyDto> update(Long id, CreateCountryDto countryDto);
    void deleteById(Long id);
}

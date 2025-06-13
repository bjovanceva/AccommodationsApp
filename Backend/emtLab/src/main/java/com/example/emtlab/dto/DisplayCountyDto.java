package com.example.emtlab.dto;

import com.example.emtlab.model.domain.Accommodation;
import com.example.emtlab.model.domain.Country;
import com.example.emtlab.model.domain.Host;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayCountyDto(Long id, String name, String continent) {

    public static DisplayCountyDto from(Country country){
        return new DisplayCountyDto(country.getId(), country.getName(), country.getContinent());
    }

    public static List<DisplayCountyDto> from(List<Country> countries) {
        return countries.stream().map(DisplayCountyDto::from).collect(Collectors.toList());
    }

    public Country toCountry() {
        return new Country(name, continent);
    }
}

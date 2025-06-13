package com.example.emtlab.dto;

import com.example.emtlab.model.domain.Accommodation;
import com.example.emtlab.model.domain.Host;
import com.example.emtlab.model.enumerations.AccommodationType;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccommodationWithAllDetailsDto(Long id, String name, AccommodationType category, DisplayHostWithAllDetailsDto host, Integer numRooms, boolean isRented) {


    public static DisplayAccommodationWithAllDetailsDto from(Accommodation accommodation){
        return new DisplayAccommodationWithAllDetailsDto(accommodation.getId(),
                accommodation.getName(), accommodation.getCategory(), DisplayHostWithAllDetailsDto.from(accommodation.getHost()), accommodation.getNumRooms(),
                accommodation.getRented());
    }

    public static List<DisplayAccommodationWithAllDetailsDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(DisplayAccommodationWithAllDetailsDto::from).collect(Collectors.toList());
    }

    public Accommodation toAccommodation(DisplayHostWithAllDetailsDto host) {
        return new Accommodation(name, category, host.toHost(host.country()), numRooms, isRented);
    }
}

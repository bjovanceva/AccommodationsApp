package com.example.emtlab.dto;

import com.example.emtlab.model.domain.Accommodation;
import com.example.emtlab.model.domain.Host;
import com.example.emtlab.model.enumerations.AccommodationType;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccommodationDto(Long id, String name, AccommodationType category, Long host, Integer numRooms, boolean isRented) {


    public static DisplayAccommodationDto from(Accommodation accommodation){
        return new DisplayAccommodationDto(accommodation.getId(),
                accommodation.getName(), accommodation.getCategory(), accommodation.getHost().getId(), accommodation.getNumRooms(),
                accommodation.getRented());
    }

    public static List<DisplayAccommodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(DisplayAccommodationDto::from).collect(Collectors.toList());
    }

    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, numRooms, isRented);
    }
}

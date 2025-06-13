package com.example.emtlab.dto;

import com.example.emtlab.model.domain.Accommodation;
import com.example.emtlab.model.domain.Host;
import com.example.emtlab.model.enumerations.AccommodationType;

import java.util.List;

public record CreateAccommodationDto(String name, AccommodationType category, Long host, Integer numRooms, boolean isRented) {

    public static CreateAccommodationDto from(Accommodation accommodation){
        return new CreateAccommodationDto(accommodation.getName(), accommodation.getCategory(),
                accommodation.getHost().getId(), accommodation.getNumRooms(), accommodation.getRented());

    }

    public Accommodation toAccomodation(Host host){
        return new Accommodation(name, category, host, numRooms, isRented);
    }

    public List<CreateAccommodationDto> from(List<Accommodation> accommodations){
        return accommodations.stream().map(CreateAccommodationDto::from).toList();
    }
}

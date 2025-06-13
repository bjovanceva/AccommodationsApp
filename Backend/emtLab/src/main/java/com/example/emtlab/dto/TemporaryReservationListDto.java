package com.example.emtlab.dto;

import com.example.emtlab.model.domain.TemporaryReservationList;
import com.example.emtlab.model.enumerations.TemporaryListStatus;

import java.util.List;

public record TemporaryReservationListDto(Long id, DisplayUserDto user, List<DisplayAccommodationDto> accommodations, TemporaryListStatus temporaryListStatus) {

    public static TemporaryReservationListDto from(TemporaryReservationList temporaryReservationList){
        return new TemporaryReservationListDto(temporaryReservationList.getId(),
                DisplayUserDto.from(temporaryReservationList.getUser()), DisplayAccommodationDto.from(temporaryReservationList.getAccommodations()), temporaryReservationList.getTemporaryListStatus());
    }
}

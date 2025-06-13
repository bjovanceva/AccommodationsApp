package com.example.emtlab.service.application;

import com.example.emtlab.dto.DisplayAccommodationDto;
import com.example.emtlab.dto.TemporaryReservationListDto;
import com.example.emtlab.model.domain.Accommodation;
import com.example.emtlab.model.domain.TemporaryReservationList;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationListApplicationService {

    Optional<TemporaryReservationListDto> addAccommodationToTemporaryList(String username, Long accommodationId);

    Optional<TemporaryReservationListDto> confirmReservations(String username);

    Optional<TemporaryReservationListDto> getActiveTemporaryList(String username);
}

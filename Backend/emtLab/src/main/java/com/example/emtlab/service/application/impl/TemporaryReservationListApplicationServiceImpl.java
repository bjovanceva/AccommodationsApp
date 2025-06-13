package com.example.emtlab.service.application.impl;

import com.example.emtlab.dto.DisplayAccommodationDto;
import com.example.emtlab.dto.TemporaryReservationListDto;
import com.example.emtlab.service.application.TemporaryReservationListApplicationService;
import com.example.emtlab.service.domain.TemporaryReservationListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemporaryReservationListApplicationServiceImpl implements TemporaryReservationListApplicationService {

    private final TemporaryReservationListService temporaryReservationListService;

    public TemporaryReservationListApplicationServiceImpl(TemporaryReservationListService temporaryReservationListService) {
        this.temporaryReservationListService = temporaryReservationListService;
    }


    @Override
    public Optional<TemporaryReservationListDto> addAccommodationToTemporaryList(String username, Long accommodationId) {
        return temporaryReservationListService.addAccommodationToTemporaryList(username, accommodationId).map(TemporaryReservationListDto::from);
    }

    @Override
    public Optional<TemporaryReservationListDto> confirmReservations(String username) {
        return temporaryReservationListService.confirmReservations(username).map(TemporaryReservationListDto::from);
    }

    @Override
    public Optional<TemporaryReservationListDto> getActiveTemporaryList(String username) {
        return temporaryReservationListService.getActiveTemporaryList(username).map(TemporaryReservationListDto::from);
    }
}

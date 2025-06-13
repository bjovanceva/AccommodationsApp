package com.example.emtlab.service.domain;

import com.example.emtlab.model.domain.Accommodation;
import com.example.emtlab.model.domain.TemporaryReservationList;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationListService {

    Optional<TemporaryReservationList> addAccommodationToTemporaryList(String username, Long accommodationId);

    Optional<TemporaryReservationList> confirmReservations(String username);

    Optional<TemporaryReservationList> getActiveTemporaryList(String username);
}

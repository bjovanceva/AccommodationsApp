package com.example.emtlab.repository;

import com.example.emtlab.model.domain.TemporaryReservationList;
import com.example.emtlab.model.domain.User;
import com.example.emtlab.model.enumerations.TemporaryListStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemporaryReservationListRepository extends JpaRepository<TemporaryReservationList, Long> {

    Optional<TemporaryReservationList> findByUserAndTemporaryListStatus(User user, TemporaryListStatus temporaryListStatus);
}

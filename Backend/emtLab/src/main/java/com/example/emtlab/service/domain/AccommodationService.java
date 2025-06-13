package com.example.emtlab.service.domain;

import com.example.emtlab.model.domain.Accommodation;
import com.example.emtlab.model.views.AccommodationsPerHostView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {

    List<Accommodation> findAll();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> save(Accommodation accommodation);
    void deleteById(Long id);
    Optional<Accommodation> update(Long id, Accommodation accommodation);
    Optional<Accommodation> markAsRented(Long id);
    void refreshMaterializedView();
    List<AccommodationsPerHostView> getAccommodationsCountByHost();
    Page<Accommodation> findAll(Pageable pageable);

}

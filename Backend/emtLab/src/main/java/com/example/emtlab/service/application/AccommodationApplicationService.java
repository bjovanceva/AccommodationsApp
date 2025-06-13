package com.example.emtlab.service.application;

import com.example.emtlab.dto.CreateAccommodationDto;
import com.example.emtlab.dto.DisplayAccommodationDto;
import com.example.emtlab.dto.DisplayAccommodationWithAllDetailsDto;
import com.example.emtlab.model.domain.Accommodation;
import com.example.emtlab.model.views.AccommodationsPerHostView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {

    List<DisplayAccommodationDto> findAll();
    Optional<DisplayAccommodationDto> findById(Long id);
//    Optional<DisplayAccommodationWithAllDetailsDto> findById(Long id);
    Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodationDto);
    void deleteById(Long id);
    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodationDto);
    Optional<DisplayAccommodationDto> markAsRented(Long id);

    List<AccommodationsPerHostView> getAccommodationsCountByHost();
    Page<DisplayAccommodationDto> findAll(Pageable pageable);
}

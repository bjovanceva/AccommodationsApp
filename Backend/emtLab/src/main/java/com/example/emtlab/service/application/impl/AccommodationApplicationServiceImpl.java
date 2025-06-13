package com.example.emtlab.service.application.impl;

import com.example.emtlab.dto.CreateAccommodationDto;
import com.example.emtlab.dto.DisplayAccommodationDto;
import com.example.emtlab.dto.DisplayAccommodationWithAllDetailsDto;
import com.example.emtlab.model.domain.Accommodation;
import com.example.emtlab.model.domain.Host;
import com.example.emtlab.model.views.AccommodationsPerHostView;
import com.example.emtlab.service.application.AccommodationApplicationService;
import com.example.emtlab.service.domain.AccommodationService;
import com.example.emtlab.service.domain.HostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {

    private final AccommodationService accommodationService;

    private final HostService hostService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return DisplayAccommodationDto.from(accommodationService.findAll());
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

//    @Override
//    public Optional<DisplayAccommodationWithAllDetailsDto> findById(Long id) {
//        return accommodationService.findById(id).map(DisplayAccommodationWithAllDetailsDto::from);
//    }

    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodationDto) {
        Optional<Host> host= hostService.findById(accommodationDto.host());

        if(host.isPresent()){
            return accommodationService.save(accommodationDto.toAccomodation(host.get())).map(DisplayAccommodationDto::from);
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
          accommodationService.deleteById(id);
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodationDto) {

        Optional<Host> host= hostService.findById(accommodationDto.host());


        return accommodationService.update(id, accommodationDto.toAccomodation(host.get())).map(DisplayAccommodationDto::from);

    }

    @Override
    public Optional<DisplayAccommodationDto> markAsRented(Long id) {
        return accommodationService.markAsRented(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public List<AccommodationsPerHostView> getAccommodationsCountByHost() {
        return accommodationService.getAccommodationsCountByHost();
    }

    @Override
    public Page<DisplayAccommodationDto> findAll(Pageable pageable) {
        return accommodationService.findAll(pageable).map(DisplayAccommodationDto::from);
    }
}

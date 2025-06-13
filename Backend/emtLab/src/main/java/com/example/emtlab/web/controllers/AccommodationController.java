package com.example.emtlab.web.controllers;

import com.example.emtlab.dto.CreateAccommodationDto;
import com.example.emtlab.dto.DisplayAccommodationDto;
import com.example.emtlab.dto.DisplayAccommodationWithAllDetailsDto;
import com.example.emtlab.model.domain.Accommodation;
import com.example.emtlab.model.views.AccommodationsPerHostView;
import com.example.emtlab.service.application.AccommodationApplicationService;
import com.example.emtlab.service.domain.AccommodationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
@Tag(name = "Accommodation API", description = "Endpoints for managing accommodations")
public class AccommodationController {

    private final AccommodationApplicationService accommodationApplicationService;


    public AccommodationController(AccommodationApplicationService accommodationApplicationService) {
        this.accommodationApplicationService = accommodationApplicationService;

    }

    @Operation(summary = "Get all accommodations", description = "Retrieves a list of all available accommodations")
    @GetMapping()
    public List<DisplayAccommodationDto> findAll(){
        return accommodationApplicationService.findAll();
    }


    @Operation(summary = "Get accommodation by id", description = "Finds an accommodation by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAccommodationDto> findById(@PathVariable Long id){
        return accommodationApplicationService.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Add a new accommodation", description = "Creates a new accommodation.")
    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDto> save(@RequestBody CreateAccommodationDto createAccommodationDto) {
        return accommodationApplicationService.save(createAccommodationDto)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @Operation(summary = "Update an existing accommodation", description = "Updates an accommodation by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAccommodationDto> update(@PathVariable Long id, @RequestBody CreateAccommodationDto createAccommodationDto) {
        return accommodationApplicationService.update(id, createAccommodationDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Delete an existing accommodation", description = "Deletes an accommodation by ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        if (accommodationApplicationService.findById(id).isPresent()) {
            accommodationApplicationService.deleteById(id);
            return ResponseEntity.ok("Accommodation deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Mark as rented an existing accommodation", description = "Marks as rented an accommodation by ID")
    @PutMapping("/markAsRented/{id}")
    public ResponseEntity<DisplayAccommodationDto> markAsRented(@PathVariable Long id){
        return accommodationApplicationService.markAsRented(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-host")
    public List<AccommodationsPerHostView> getAccommodationCountByHost() {
        return accommodationApplicationService.getAccommodationsCountByHost();
    }


    @GetMapping("/paginated")
    public ResponseEntity<Page<DisplayAccommodationDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(accommodationApplicationService.findAll(pageable));
    }



//    @Operation(summary = "Get accommodation by id with all details", description = "Finds an accommodation by its ID.")
//    @GetMapping("/{id}")
//    public ResponseEntity<DisplayAccommodationWithAllDetailsDto> findById(@PathVariable Long id){
//        return accommodationApplicationService.findById(id)
//                .map(c -> ResponseEntity.ok().body(c))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }


}

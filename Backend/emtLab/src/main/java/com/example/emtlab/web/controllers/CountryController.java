package com.example.emtlab.web.controllers;



import com.example.emtlab.dto.CreateCountryDto;
import com.example.emtlab.dto.DisplayCountyDto;
import com.example.emtlab.service.application.CountryApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Country API", description = "Endpoints for managing countries")
public class CountryController {

    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryService) {
        this.countryApplicationService = countryService;
    }


    @Operation(summary = "Get all countries", description = "Retrieves a list of all available countries")
    @GetMapping()
    public List<DisplayCountyDto> findAll(){
        return countryApplicationService.findAll();
    }

    @Operation(summary = "Get country by id", description = "Finds a country by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountyDto> findById(@PathVariable Long id){
        return countryApplicationService.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new country", description = "Creates a new country.")
    @PostMapping("/add")
    public ResponseEntity<DisplayCountyDto> save(@RequestBody CreateCountryDto createCountryDto) {
        return countryApplicationService.save(createCountryDto)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update an existing country", description = "Updates a country by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayCountyDto> update(@PathVariable Long id, @RequestBody CreateCountryDto createCountryDto) {
        return countryApplicationService.update(id, createCountryDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Delete an existing country", description = "Deletes a country by ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        if (countryApplicationService.findById(id).isPresent()) {
            countryApplicationService.deleteById(id);
            return ResponseEntity.ok("Country deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}


package com.example.emtlab.web.controllers;


import com.example.emtlab.dto.CreateHostDto;
import com.example.emtlab.dto.DisplayHostDto;
import com.example.emtlab.model.projections.HostProjection;
import com.example.emtlab.model.views.HostsPerCountryView;
import com.example.emtlab.service.application.HostApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Host API", description = "Endpoints for managing hosts")
public class HostController {

    private final HostApplicationService hostApplicationService;

    public HostController(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @Operation(summary = "Get all hosts", description = "Retrieves a list of all available hosts")
    @GetMapping()
    public List<DisplayHostDto> findAll(){
        return hostApplicationService.findAll();
    }

    @Operation(summary = "Get host by id", description = "Finds a host by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id){
        return hostApplicationService.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Add a new host", description = "Creates a new host.")
    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> save(@RequestBody CreateHostDto createHostDto) {
        return hostApplicationService.save(createHostDto)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @Operation(summary = "Update an existing host", description = "Updates a host by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayHostDto> update(@PathVariable Long id, @RequestBody CreateHostDto createHostDto) {
        return hostApplicationService.update(id, createHostDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    @Operation(summary = "Delete an existing host", description = "Deletes a host by ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        if (hostApplicationService.findById(id).isPresent()) {
            hostApplicationService.deleteById(id);
            return ResponseEntity.ok("Host deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/by-country")
    public List<HostsPerCountryView> getAccommodationCountByHost() {
        return hostApplicationService.getHostsCountByCountry();
    }


    @GetMapping("/names")
    public List<HostProjection> getHostNames() {
        return hostApplicationService.getAllHostsByNameAndSurname();
    }


}


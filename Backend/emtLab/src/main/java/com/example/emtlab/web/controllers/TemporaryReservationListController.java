package com.example.emtlab.web.controllers;

import com.example.emtlab.dto.TemporaryReservationListDto;
import com.example.emtlab.model.domain.User;
import com.example.emtlab.service.application.TemporaryReservationListApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/temporary-reservation-list")
@Tag(name = "Temporary-list API", description = "Endpoints for managing temporary list")
public class TemporaryReservationListController {

    private final TemporaryReservationListApplicationService temporaryReservationListService;

    public TemporaryReservationListController(TemporaryReservationListApplicationService temporaryReservationListService) {
        this.temporaryReservationListService = temporaryReservationListService;
    }


    @Operation(
            summary = "Get active temporary list",
            description = "Retrieves the active temporary list for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Temporary list retrieved successfully"
            ), @ApiResponse(responseCode = "404", description = "Temporary list not found")}
    )
    @GetMapping
    public ResponseEntity<TemporaryReservationListDto> getActiveTemporaryList(@AuthenticationPrincipal User user) {
        String username=user.getUsername();
        return temporaryReservationListService.getActiveTemporaryList(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Add accommodation to temporary list",
            description = "Adds an accommodation to the temporary list for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Accommodation added to shopping cart successfully"
            ), @ApiResponse(responseCode = "404", description = "Temporary list not found")}
    )
    @PostMapping("/add-accommodation/{id}")
    public ResponseEntity<TemporaryReservationListDto> addAccommodation(@PathVariable Long id,
                                                                     Authentication authentication) {

        try {
            User user = (User) authentication.getPrincipal();
            return temporaryReservationListService.addAccommodationToTemporaryList(user.getUsername(), id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }catch (RuntimeException runtimeException){
            return ResponseEntity.badRequest().build();
        }
    }


    @Operation(
            summary = "Confirm reservations",
            description = "Confirms reservations for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Reservation confirmed successfully"
            ), @ApiResponse(responseCode = "404", description = "Temporary list not found")}
    )
    @PostMapping("/confirmReservations")
    public ResponseEntity<TemporaryReservationListDto> confirmReservations(HttpServletRequest req){
        String username=req.getRemoteUser();
        return temporaryReservationListService.confirmReservations(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }





}

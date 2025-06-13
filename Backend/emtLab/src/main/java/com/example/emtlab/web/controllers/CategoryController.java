package com.example.emtlab.web.controllers;

import com.example.emtlab.model.enumerations.AccommodationType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @GetMapping
    public AccommodationType[] getAllCategories() {
        return AccommodationType.values();
    }
}

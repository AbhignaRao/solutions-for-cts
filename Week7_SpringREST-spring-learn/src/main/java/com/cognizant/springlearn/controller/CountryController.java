package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    // GET all countries
    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        LOGGER.info("getAllCountries");
        return countryService.getAllCountries();
    }

    // GET country by code
    @GetMapping("/countries/{code}")
    public ResponseEntity<Country> getCountry(@PathVariable String code) {
        LOGGER.info("getCountry code={}", code);
        try {
            Country country = countryService.getCountry(code);
            return ResponseEntity.ok(country);
        } catch (CountryNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST - add country
    @PostMapping("/countries")
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {
        LOGGER.info("addCountry");
        countryService.addCountry(country);
        return ResponseEntity.status(HttpStatus.CREATED).body(country);
    }

    // PUT - update country
    @PutMapping("/countries/{code}")
    public ResponseEntity<Country> updateCountry(@PathVariable String code,
                                                  @RequestBody Country country) {
        LOGGER.info("updateCountry code={}", code);
        try {
            countryService.updateCountry(code, country);
            return ResponseEntity.ok(country);
        } catch (CountryNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - delete country
    @DeleteMapping("/countries/{code}")
    public ResponseEntity<Void> deleteCountry(@PathVariable String code) {
        LOGGER.info("deleteCountry code={}", code);
        countryService.deleteCountry(code);
        return ResponseEntity.noContent().build();
    }
}
package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = 
        LoggerFactory.getLogger(OrmLearnApplication.class);
    private static CountryService countryService;

    public static void main(String[] args) throws CountryNotFoundException {
        ApplicationContext context = 
            SpringApplication.run(OrmLearnApplication.class, args);
        countryService = context.getBean(CountryService.class);
        LOGGER.info("Inside main");

        testGetAllCountries();
        testFindCountryByCode();
        testAddCountry();
        testUpdateCountry();
        testDeleteCountry();
        testFindByName();
    }

    // Handson 5
    private static void testGetAllCountries() {
        LOGGER.info("=== testGetAllCountries Start ===");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("=== testGetAllCountries End ===");
    }

    // Handson 6
    private static void testFindCountryByCode() throws CountryNotFoundException {
        LOGGER.info("=== testFindCountryByCode Start ===");
        Country country = countryService.findCountryByCode("IN");
        LOGGER.debug("Country: {}", country);
        LOGGER.info("=== testFindCountryByCode End ===");
    }

    // Handson 7
    private static void testAddCountry() throws CountryNotFoundException {
        LOGGER.info("=== testAddCountry Start ===");
        Country country = new Country();
        country.setCode("ZZ");
        country.setName("Test Country");
        countryService.addCountry(country);
        Country added = countryService.findCountryByCode("ZZ");
        LOGGER.debug("Added Country: {}", added);
        LOGGER.info("=== testAddCountry End ===");
    }

    // Handson 8
    private static void testUpdateCountry() throws CountryNotFoundException {
        LOGGER.info("=== testUpdateCountry Start ===");
        countryService.updateCountry("ZZ", "Updated Test Country");
        Country updated = countryService.findCountryByCode("ZZ");
        LOGGER.debug("Updated Country: {}", updated);
        LOGGER.info("=== testUpdateCountry End ===");
    }

    // Handson 9
    private static void testDeleteCountry() {
        LOGGER.info("=== testDeleteCountry Start ===");
        countryService.deleteCountry("ZZ");
        LOGGER.debug("Country ZZ deleted");
        LOGGER.info("=== testDeleteCountry End ===");
    }

    // Find by name
    private static void testFindByName() {
        LOGGER.info("=== testFindByName Start ===");
        List<Country> countries = countryService.findCountriesByName("Ind");
        LOGGER.debug("Countries with 'Ind': {}", countries);
        LOGGER.info("=== testFindByName End ===");
    }
}
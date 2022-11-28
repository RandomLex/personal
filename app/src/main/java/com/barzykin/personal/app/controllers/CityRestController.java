package com.barzykin.personal.app.controllers;

import com.barzykin.personal.app.repositories.CityRepository;
import com.barzykin.personal.model.City;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = "application/json")
@RequiredArgsConstructor
@Slf4j
public class CityRestController {

    private final CityRepository cityRepository;

    @GetMapping("/api/cities")
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @GetMapping("/api/cities/{id}")
    public ResponseEntity<City> getAllCities(@PathVariable Long id) {
        return ResponseEntity.of(cityRepository.find(id));
    }

    @PostMapping("/api/cities")
    public ResponseEntity<City> createCity(@RequestBody City city) {
        return ResponseEntity.ok(cityRepository.save(city));
    }

    @PutMapping("/api/cities/{id}")
    public ResponseEntity<City> getAllCities(@RequestBody City city, @PathVariable Long id) {
        if (!id.equals(city.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cityRepository.save(city));
    }

    @DeleteMapping("/api/cities/{id}")
    public ResponseEntity<City> deleteCity(@PathVariable Long id) {
        return ResponseEntity.of(cityRepository.remove(id));
    }

}

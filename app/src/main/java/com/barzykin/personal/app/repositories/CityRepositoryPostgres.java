package com.barzykin.personal.app.repositories;

import com.barzykin.personal.model.City;

import java.util.List;
import java.util.Optional;

public class CityRepositoryPostgres implements CityRepository {
    @Override
    public List<City> findAll() {
        return null;
    }

    @Override
    public Optional<City> find(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<City> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public City save(City employee) {
        return null;
    }

    @Override
    public Optional<City> remove(long id) {
        return Optional.empty();
    }
}

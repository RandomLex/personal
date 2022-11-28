package com.barzykin.personal.app.repositories;

import com.barzykin.personal.model.City;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CityRepositoryOrm implements CityRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<City> findAll() {
        return em.createQuery("from City", City.class).getResultList();
    }

    @Override
    public Optional<City> find(long id) {
        return Optional.ofNullable(em.find(City.class, id));
    }

    @Override
    public Optional<City> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public City save(City city) {
        if (city.getId() == null) {
            em.persist(city);
        } else {
            em.merge(city);
        }
        return city;
    }

    @Override
    public Optional<City> remove(long id) {
        Optional<City> city = find(id);
        if (city.isPresent()) {
            em.remove(city);
            return city;
        }
        return Optional.empty();
    }
}

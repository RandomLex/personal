package com.barzykin.personal.app.repositories;

import com.barzykin.personal.model.City;
import org.springframework.stereotype.Repository;

@Repository
public class CityRepositoryOrm extends AbstractRepositoryOrm<City> implements CityRepository {
    public CityRepositoryOrm() {
        tClass = City.class;
    }
}

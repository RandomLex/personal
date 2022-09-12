package com.barzykin.personal.app.repositories;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    List<T> findAll();

    Optional<T> find(long id);

    Optional<T> findByName(String name);

    T save(T t);

    Optional<T> remove(long id);
}

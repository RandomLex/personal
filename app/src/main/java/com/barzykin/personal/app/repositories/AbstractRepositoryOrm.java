package com.barzykin.personal.app.repositories;

import com.barzykin.personal.model.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public abstract class AbstractRepositoryOrm<T extends AbstractEntity> implements Repository<T> {
    protected Class<T> tClass;
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<T> findAll() {
        return em.createQuery("from " + tClass.getName(), tClass).getResultList();
    }

    @Override
    public Optional<T> find(long id) {
        return Optional.ofNullable(em.find(tClass, id));
    }

    @Override
    public Optional<T> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public T save(T t) {
        if (t.getId() == null) {
            em.persist(t);
        } else {
            em.merge(t);
        }
        return t;
    }

    @Override
    public Optional<T> remove(long id) {
        Optional<T> t = find(id);
        if (t.isPresent()) {
            em.remove(t);
            return t;
        }
        return Optional.empty();
    }
}

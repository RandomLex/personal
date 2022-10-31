package com.barzykin.personal.app.repositories;

import com.barzykin.personal.app.repositories.helpers.EntityManagerHelper;
import com.barzykin.personal.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.transaction.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class EmployeeRepositoryJpa implements EmployeeRepository {
    private final EntityManagerHelper helper;

    @Autowired
    public EmployeeRepositoryJpa(EntityManagerHelper helper) {
        this.helper = helper;
    }

    @Override
    public List<Employee> findAll() {
        EntityManager em = null;
        List<Employee> employees = new ArrayList<>();
        try {
            em = helper.getEntityManager();
            em.getTransaction().begin();


            TypedQuery<Employee> query = em.createQuery("from Employee", Employee.class);
            employees = query.getResultList();

            em.getTransaction().commit();
        } catch (Exception e) {
            safeRollback(em);
        } finally {
            safeClose(em);
        }
        return employees;
    }

    private static void safeRollback(EntityManager em) {
        EntityTransaction tx;
        if (em != null && (tx = em.getTransaction()) != null) {
            tx.rollback();
        }
    }

    private static void safeClose(EntityManager em) {
        if (em != null) {
            em.close();
        }
    }


    @Override
    public Optional<Employee> find(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Employee save(Employee employee) {
        return null;
    }

    @Override
    public Optional<Employee> remove(long id) {
        return Optional.empty();
    }
}

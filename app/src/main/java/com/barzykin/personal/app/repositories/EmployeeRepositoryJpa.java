package com.barzykin.personal.app.repositories;

import com.barzykin.personal.app.aop.JpaTransaction;
import com.barzykin.personal.app.repositories.helpers.EntityManagerHelper;
import com.barzykin.personal.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeRepositoryJpa implements EmployeeRepository {
    private final EntityManagerHelper helper;

    @Autowired
    public EmployeeRepositoryJpa(EntityManagerHelper helper) {
        this.helper = helper;
    }

    @Override
    @JpaTransaction
    public List<Employee> findAll() {
        return helper
                .getEntityManager()
                .createQuery("from Employee", Employee.class)
                .getResultList();
    }

    @Override
    @JpaTransaction
    public Optional<Employee> find(long id) {
        return Optional.ofNullable(helper.getEntityManager().find(Employee.class, id));
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Employee save(Employee employee) {
        EntityManager em = null;
        try {
            em = helper.getEntityManager();
            em.getTransaction().begin();

            if (employee.getId() == null) {
                em.persist(employee);
            } else {
                em.merge(employee);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            safeRollback(em);
        } finally {
            safeClose(em);
        }
        return employee;
    }

    @Override
    public Optional<Employee> remove(long id) {
        EntityManager em = null;
        Optional<Employee> employeeOpt = Optional.empty();
        try {
            em = helper.getEntityManager();
            em.getTransaction().begin();

            Employee employee = em.find(Employee.class, id);
            if (employee != null) {
                em.remove(employee);
                employeeOpt = Optional.of(employee);
            } else {
                employeeOpt = Optional.empty();
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            safeRollback(em);
        } finally {
            safeClose(em);
        }
        return employeeOpt;
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
}

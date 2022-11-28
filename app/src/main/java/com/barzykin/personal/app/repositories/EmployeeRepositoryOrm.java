package com.barzykin.personal.app.repositories;

import com.barzykin.personal.model.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryOrm implements EmployeeRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Employee> findAll() {
        return em.createQuery("from Employee", Employee.class).getResultList();
    }

    @Override
    public Optional<Employee> find(long id) {
        return Optional.ofNullable(em.find(Employee.class, id));
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            em.persist(employee);
        } else {
            em.merge(employee);
        }
        return employee;
    }

    @Override
    public Optional<Employee> remove(long id) {
        Optional<Employee> employee = find(id);
        if (employee.isPresent()) {
            em.remove(employee);
            return employee;
        }
        return Optional.empty();
    }
}

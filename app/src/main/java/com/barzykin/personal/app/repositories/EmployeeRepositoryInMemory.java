package com.barzykin.personal.app.repositories;

import com.barzykin.personal.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class EmployeeRepositoryInMemory implements EmployeeRepository {
    private volatile static EmployeeRepositoryInMemory instance;

    private Map<Long, Employee> map = new ConcurrentHashMap<>();

    private EmployeeRepositoryInMemory() {
        map.put(1L, new Employee("Alex", 45, null, Set.of(), 1000));
        map.put(2L, new Employee("Viktor", 34, null, Set.of(), 900));
    }

    public static EmployeeRepositoryInMemory getInstance() {
        if (instance == null) {
            synchronized (EmployeeRepositoryInMemory.class) {
                if (instance == null) {
                    instance = new EmployeeRepositoryInMemory();
                }
            }
        }
        return instance;
    }


    @Override
    public List<Employee> findAll() {
        if (map.isEmpty()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(map.values());
    }

    @Override
    public Optional<Employee> find(long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Employee save(Employee employee) {
        Long id = employee.getId();
        if (id == null) {
            id = generateId();
            employee.setId(id);
        }
        map.put(id, employee);
        return employee;
    }

    private long generateId() {
        long id;
        do {
            id = ThreadLocalRandom.current().nextLong(1, 1_000);
        } while (map.containsKey(id));
        return id;
    }

    @Override
    public Optional<Employee> remove(long id) {
        return Optional.ofNullable(map.remove(id));
    }
}

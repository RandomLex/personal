package com.barzykin.personal.app.repositories;

import com.barzykin.personal.app.constants.DbConstants;
import com.barzykin.personal.app.exception.ApplicationException;
import com.barzykin.personal.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.barzykin.personal.app.constants.DbConstants.*;
import static com.barzykin.personal.app.constants.DbConstants.ID;

public class EmployeeRepositoryPostgres implements EmployeeRepository {

    private final RepositoryDataSource dataSource;

    private static volatile EmployeeRepositoryPostgres instance;

    private EmployeeRepositoryPostgres(RepositoryDataSource dataSource) {
        this.dataSource = dataSource;

    }

    public static EmployeeRepositoryPostgres getInstance(RepositoryDataSource dataSource) {
        if (instance == null) {
            synchronized (EmployeeRepositoryPostgres.class) {
                if (instance == null) {
                    instance = new EmployeeRepositoryPostgres(dataSource);
                }
            }
        }
        return instance;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from employee");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getLong(ID));
                employee.setAge(rs.getInt(AGE));
                employee.setName(rs.getString(NAME));
                employee.setSalary(rs.getInt(SALARY));
                result.add(employee);
            }

        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
        return result;
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

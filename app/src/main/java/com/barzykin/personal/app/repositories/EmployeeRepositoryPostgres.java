package com.barzykin.personal.app.repositories;

import com.barzykin.personal.app.constants.DbConstants;
import com.barzykin.personal.app.exception.ApplicationException;
import com.barzykin.personal.model.Employee;
import com.barzykin.personal.model.Title;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.barzykin.personal.app.constants.DbConstants.*;
import static com.barzykin.personal.app.constants.DbConstants.AGE;
import static com.barzykin.personal.app.constants.DbConstants.ID;
import static com.barzykin.personal.app.constants.DbConstants.NAME;
import static com.barzykin.personal.app.constants.DbConstants.SALARY;
import static com.barzykin.personal.app.constants.DbConstants.T_ID;

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
             PreparedStatement ps = connection.prepareStatement(
                     "select e.id id, e.name, e.age age, t.id t_id, t.name title, e.salary salary " +
                             "from employee e " +
                             "join title t on e.title_id = t.id"
             );
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                result.add(new Employee()
                        .withId(rs.getLong(ID))
                        .withName(rs.getString(NAME))
                        .withAge(rs.getInt(AGE))
                        .withTitle(new Title()
                                .withId(rs.getLong(T_ID))
                                .withName(rs.getString(TITLE)))
                        .withSalary(rs.getInt(SALARY)));
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

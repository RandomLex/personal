package com.barzykin.personal.app.repositories;

import com.barzykin.personal.app.exception.ApplicationException;
import com.barzykin.personal.model.AbstractEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.barzykin.personal.app.constants.DbConstants.ID;

public abstract class AbstractRepository<T extends AbstractEntity> implements Repository<T> {

    protected RepositoryDataSource dataSource;

    protected abstract String selectAllRows();

    protected abstract String selectRowForId();

    protected abstract String insertRow();

    protected abstract String updateRow();

    protected abstract String deleteRow();

    protected abstract void setParametersForInsertSql(T model, PreparedStatement ps) throws SQLException;

    protected abstract void setParametersForUpdateSql(T model, PreparedStatement ps) throws SQLException;

    protected abstract Map<Long, T> resultSetToModel(ResultSet rs) throws SQLException;

    @Override
    public List<T> findAll() {
        Map<Long, T> employeeMap;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(selectAllRows());
             ResultSet rs = ps.executeQuery()) {
            employeeMap = resultSetToModel(rs);
        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
        return new ArrayList<>(employeeMap.values());
    }


    @Override
    public Optional<T> find(long id) {
        Map<Long, T> employeeMap;
        ResultSet rs = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(selectRowForId())) {
            ps.setLong(1, id);
            rs = ps.executeQuery();
            employeeMap = resultSetToModel(rs);
        } catch (SQLException e) {
            throw new ApplicationException(e);
        } finally {
            quietClose(rs);
        }
        return employeeMap.values().stream().findAny();
    }

    @Override
    public Optional<T> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public T save(T model) {

        return model.getId() == null ? insert(model) : update(model);
    }

    private T insert(T model) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(insertRow())) {
            setParametersForInsertSql(model, ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.setId(rs.getLong(ID));
            }
        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
        return model;
    }



    private T update(T model) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(updateRow())) {
            setParametersForUpdateSql(model, ps);

            if (ps.executeUpdate() > 0) {
                return model;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
    }


    @Override
    public Optional<T> remove(long id) {
        Optional<T> optionalEmployee = find(id);
        if (optionalEmployee.isEmpty()) {
            return Optional.empty();
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(deleteRow())) {
            ps.setLong(1, id);
            if (ps.executeUpdate() > 0) {
                return optionalEmployee;
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new ApplicationException(e);
        }
    }

    protected static void quietClose(AutoCloseable rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                // Nothing to do
            }
        }
    }
}

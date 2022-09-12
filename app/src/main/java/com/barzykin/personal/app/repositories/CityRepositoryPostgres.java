package com.barzykin.personal.app.repositories;

import com.barzykin.personal.model.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CityRepositoryPostgres extends AbstractRepository<City> implements CityRepository {
    private static volatile CityRepositoryPostgres instance;

    private CityRepositoryPostgres(RepositoryDataSource dataSource) {
        this.dataSource = dataSource;

    }

    public static CityRepositoryPostgres getInstance(RepositoryDataSource dataSource) {
        if (instance == null) {
            synchronized (CityRepositoryPostgres.class) {
                if (instance == null) {
                    instance = new CityRepositoryPostgres(dataSource);
                }
            }
        }
        return instance;
    }


    public static final String SELECT_ALL_FROM_CITY = "select * from city c";
    public static final String INSERT_INTO_CITY = "insert into city (name) values (?) returning id";
    public static final String UPDATE_CITY = "update city c set name = ?";
    public static final String DELETE_FROM_CITY = "delete from city c";
    public static final String FILTER_BY_ID = " where c.id = ?";

    @Override
    protected String selectAllRows() {
        return SELECT_ALL_FROM_CITY;
    }

    @Override
    protected String selectRowForId() {
        return SELECT_ALL_FROM_CITY + FILTER_BY_ID;
    }

    @Override
    protected String insertRow() {
        return INSERT_INTO_CITY;
    }

    @Override
    protected String updateRow() {
        return UPDATE_CITY + FILTER_BY_ID;
    }

    @Override
    protected String deleteRow() {
        return DELETE_FROM_CITY + FILTER_BY_ID;
    }

    @Override
    protected void setParametersForInsertSql(City model, PreparedStatement ps) throws SQLException {
        ps.setString(1, model.getName());
    }

    @Override
    protected void setParametersForUpdateSql(City model, PreparedStatement ps) throws SQLException {
        ps.setString(1, model.getName());
        ps.setLong(2, model.getId());
    }

    @Override
    protected Map<Long, City> resultSetToModel(ResultSet rs) throws SQLException {
        Map<Long, City> cityMap = new HashMap<>();
        while (rs.next()) {
            City city = new City();
            city.setId(rs.getLong("id"));
            city.setName(rs.getString("name"));
            cityMap.put(city.getId(), city);
        }
        return cityMap;
    }
}

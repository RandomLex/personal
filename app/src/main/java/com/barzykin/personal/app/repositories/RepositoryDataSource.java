package com.barzykin.personal.app.repositories;

import com.barzykin.personal.app.exception.ApplicationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RepositoryDataSource {
    private static volatile RepositoryDataSource instance;

    private String url;
    private String driver;
    private String user;
    private String password;

    private RepositoryDataSource(String url, String driver, String user, String password) {
        this.url = url;
        this.driver = driver;
        this.user = user;
        this.password = password;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new ApplicationException("Class " + driver + " in not found.", e);
        }

    }

    public static RepositoryDataSource getInstance(String url, String driver, String user, String password) {
        if (instance == null) {
            synchronized (RepositoryDataSource.class) {
                if (instance == null) {
                    instance = new RepositoryDataSource(url, driver, user, password);
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}

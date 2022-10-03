package com.barzykin.personal.app.repositories;

import com.barzykin.personal.app.exception.ApplicationException;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class RepositoryFactory {

    private static RepositoryDataSource dataSource;

    private static final RepositoryType TYPE;

    private static final String URL = "url";

    private static final String DRIVER = "driver";

    private static final String USER = "user";

    private static final String PASSWORD = "password";

    static {
        Properties applicationProperties = new Properties();
        try {
            applicationProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new ApplicationException(e);
        }
        String repositoryType = applicationProperties.getProperty("repository.type").toUpperCase(Locale.ROOT);

        TYPE = RepositoryType.valueOf(repositoryType);
        if (TYPE == RepositoryType.POSTGRES) {
            dataSource = RepositoryDataSource.getInstance(
                    applicationProperties.getProperty(URL),
                    applicationProperties.getProperty(DRIVER),
                    applicationProperties.getProperty(USER),
                    applicationProperties.getProperty(PASSWORD));
        }
    }

    private RepositoryFactory() {
        // preventing instance
    }

    public static EmployeeRepository getEmployeeRepository() {
        switch (TYPE) {
            case MEMORY:
                return EmployeeRepositoryInMemory.getInstance();
            case POSTGRES:
                return EmployeeRepositoryPostgres.getInstance(dataSource);
            case JPA:
                return EmployeeRepositoryJpa.getInstance();
            default:
                throw new ApplicationException("Property should be MEMORY or POSTGRES");
        }
    }

    public static CityRepositoryPostgres getCityRepository() {
        switch (TYPE) {

            case POSTGRES:
                return CityRepositoryPostgres.getInstance(dataSource);
            case MEMORY:
                default:
                throw new ApplicationException("Property should be MEMORY or POSTGRES");
        }
    }

}

package com.barzykin.personal.app.repositories;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class RepositoryFactory {

    private static final RepositoryType TYPE;

    static {
        Properties applicationProperties = new Properties();
        try {
            applicationProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String repositoryType = applicationProperties.getProperty("repository.type").toUpperCase(Locale.ROOT);

        TYPE = RepositoryType.valueOf(repositoryType);
    }

    public static EmployeeRepository getEmployeeRepository() {
        switch (TYPE) {
            case MEMORY:
                return EmployeeRepositoryInMemory.getInstance();
//            case POSTGRES:
//                return EmployeeRepositoryPostgres.getInstance();
            default:
                throw new RuntimeException("Property should be MEMORY, ANOTHER, or POSTGRES");
        }
    }

}

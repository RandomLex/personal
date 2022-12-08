package com.barzykin.personal.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
public class DbConfig {

    @Bean
    public TransactionTemplate transactionTemplate(@Autowired JpaTransactionManager transactionManager) {
        return new TransactionTemplate(transactionManager);
    }

    @Bean
    public JpaTransactionManager jpaTransactionManager(@Autowired LocalContainerEntityManagerFactoryBean factoryBean) {
        return new JpaTransactionManager(factoryBean.getObject());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean factoryBean(@Autowired Properties jpaProperties, @Autowired DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPersistenceUnitName("jpa-unit");
        entityManagerFactoryBean.setPackagesToScan("com.barzykin.personal.model");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;
    }

    @Bean
    public Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hbm2ddl.auto", "validate");
        properties.setProperty("current_session_context_class", "thread");
        properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        return properties;
    }

    @Bean
    public DataSource dataSource(
            @Value("${driver}") String driver,
            @Value("${url}") String url,
            @Value("${user}") String user,
            @Value("${password}") String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

}

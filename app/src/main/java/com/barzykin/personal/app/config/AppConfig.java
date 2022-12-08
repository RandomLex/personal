package com.barzykin.personal.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com")
@EnableTransactionManagement
//@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableJpaRepositories(
        basePackages = "com.barzykin.personal.app.repositories",
        entityManagerFactoryRef = "factoryBean",
        transactionManagerRef = "jpaTransactionManager")
public class AppConfig {

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(@Autowired ApplicationContext ctx) {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setApplicationContext(ctx);
        resolver.setPrefix("/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}

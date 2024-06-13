package com.wsb.libraryapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.ResourceBundle;

@Configuration
public class DataSourceConfig {

    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    @Bean
    @Profile("default")
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(resourceBundle.getString("spring.datasource.url"));
        dataSource.setUsername(resourceBundle.getString("spring.datasource.username"));
        dataSource.setPassword(resourceBundle.getString("spring.datasource.password"));
        return dataSource;
    }

    @Bean
    @Profile("test")
    public DataSource h2DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:librarydb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        return dataSource;
    }
}

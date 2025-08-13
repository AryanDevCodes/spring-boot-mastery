package org.annotations.patientreport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.DriverManager;

@Configuration
@ComponentScan(basePackages= "org.annotations.patientreport")
public class AppConfig {
    @Bean(name="driverManager", initMethod = "getConnection", destroyMethod = "getLoginTimeout")
    public DriverManagerDataSource datasource(){
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        datasource.setUrl("jdbc:mysql://localhost:3306/mini_project");
        datasource.setUsername("root");
        datasource.setPassword("8252");
        return datasource;
    }
}

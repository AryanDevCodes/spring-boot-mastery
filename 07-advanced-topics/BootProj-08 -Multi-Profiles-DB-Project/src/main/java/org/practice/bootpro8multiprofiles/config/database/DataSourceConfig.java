package org.practice.bootpro8multiprofiles.config.database;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
public class DataSourceConfig {

    private static final String ACTIVE_PROFILE = Objects.toString(System.getProperty("spring.profiles.active"), "default");

    @Bean
    @Primary  // Ensures Spring picks a default DataSource if no profile is active
    @Profile("dev")
    public DataSource devDataSource() {
        System.out.println("[✔] Active Profile: " + ACTIVE_PROFILE);
        System.out.println("[DEV] Using Development DataSource (H2)");

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:h2:mem:devdb");
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("devuser");
        dataSource.setPassword("");
        dataSource.setMaximumPoolSize(10);
        return dataSource;
    }

    @Bean
    @Primary  // Ensures it's picked if "prod" is active
    @Profile("prod")
    public DataSource prodDataSource() {
        System.out.println("[✔] Active Profile: " + ACTIVE_PROFILE);
        System.out.println("[PROD] Using Production DataSource (MySQL)");

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/proddb");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("8252");
        dataSource.setMaximumPoolSize(10);
        return dataSource;
    }

    @Bean
    @Primary
    @Profile("test")
    public DataSource testDataSource() {
        System.out.println("[✔] Active Profile: " + ACTIVE_PROFILE);
        System.out.println("[TEST] Using Test DataSource (H2)");

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:h2:mem:testdb");
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("testuser");
        dataSource.setPassword("");
        dataSource.setMaximumPoolSize(5);
        return dataSource;
    }



    @Bean
    @Primary
    @Profile("default")  // In case no profile is set
    public DataSource defaultDataSource( ) {
        System.out.println("[❌] No Active Profile Found! Using Default H2 Database.");

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:h2:mem:defaultdb");
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("defaultuser");
        dataSource.setPassword("");
        dataSource.setMaximumPoolSize(5);
        return dataSource;
    }
}

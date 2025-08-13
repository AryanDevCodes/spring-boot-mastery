package org.practice.bootpro8multiprofiles.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
public class ApplicationPropertiesConfig {
    private DatabaseProperties datasource;
    private ServerProperties server;

    @Getter
    @Setter
    public static class DatabaseProperties {
        private String url;
        private String username;
        private String password;
    }

    @Getter
    @Setter
    public static class ServerProperties {
        private int port;
        private String contextPath;
    }

    @Configuration
    @Profile("dev")
    public static class DevPropertiesConfig {
        // Dev-specific property overrides or additional configurations
    }

    @Configuration
    @Profile("prod")
    public static class ProdPropertiesConfig {
        // Prod-specific property overrides or additional configurations
    }
}

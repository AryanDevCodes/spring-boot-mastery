package org.practice.bootpro8multiprofiles.config.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class LoggingConfig {
    private static final Logger logger = LoggerFactory.getLogger(LoggingConfig.class);

    @Configuration
    @Profile("dev")
    public static class DevLoggingConfig {
        public DevLoggingConfig() {
            logger.info("Initializing Development Logging Configuration");
            System.setProperty("spring.profiles.active", "dev");
            System.setProperty("logging.level.org.springframework", "DEBUG");
        }
    }

    @Configuration
    @Profile("prod")
    public static class ProdLoggingConfig {
        public ProdLoggingConfig() {
            logger.info("Initializing Production Logging Configuration");
            System.setProperty("spring.profiles.active", "prod");
            System.setProperty("logging.level.org.springframework", "ERROR");
        }
    }
    @Configuration
    @Profile("test")
    public static class TestLoggingConfig {
        public TestLoggingConfig() {
            logger.info("Initializing Testing Logging Configuration");
            System.setProperty("spring.profiles.active", "prod");
            System.setProperty("logging.level.org.springframework", "INFO");
        }
    }
}

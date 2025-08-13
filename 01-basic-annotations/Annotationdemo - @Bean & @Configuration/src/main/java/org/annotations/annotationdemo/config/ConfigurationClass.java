package org.annotations.annotationdemo.config;

import org.annotations.annotationdemo.controller.GameController;
import org.annotations.annotationdemo.service.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for defining beans in the Spring application context.
 */
@Configuration
public class ConfigurationClass {

    /**
     * Defines a bean for GameService.
     *
     * @return a new instance of GameService.
     */
    @Bean
    public Game showGameService() {
        return new GameService();
    }

    /**
     * Defines a bean for GameService1.
     *
     * @return a new instance of GameService1.
     */
    @Bean
    public Game showGameService1() {
        return new GameService1();
    }

    /**
     * Defines a bean for GameService2.
     *
     * @return a new instance of GameService2.
     */
    @Bean
    public Game showGameService2() {
        return new GameService2();
    }

    /**
     * Defines a bean for GameService3.
     *
     * @return a new instance of GameService3.
     */
    @Bean
    public Game showGameService3() {
        return new GameService3();
    }

    /**
     * Defines a bean for GameController with initialization and destruction methods.
     *
     * @param game the Game bean to be injected.
     * @return a new instance of GameController.
     */
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public GameController showGameController(@Qualifier("showGameService2") Game game) {
        return new GameController(game);
    }
}

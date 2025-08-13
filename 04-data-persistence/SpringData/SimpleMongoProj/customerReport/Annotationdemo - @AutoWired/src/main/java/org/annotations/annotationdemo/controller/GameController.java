package org.annotations.annotationdemo.controller;
import org.annotations.annotationdemo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameController {

    @Autowired  // Field Injection
    GameService gameService;

/*  // Constructor Injection
    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }*/

/*
     // Setter Injection
    @Autowired
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }
*/

    public String showAnnotation() {
        return gameService.getGameServiceName();
    }
}

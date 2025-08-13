package org.annotations.annotationdemo.controller;
import org.annotations.annotationdemo.service.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameController {
    Game game;  // reference of Game Interface

  // Constructor Injection using Qualifier
    @Autowired
    public GameController( Game game) {
        this.game = game;
    }


    public String showAnnotation() {
        return game.getGameServiceName();
    }
}

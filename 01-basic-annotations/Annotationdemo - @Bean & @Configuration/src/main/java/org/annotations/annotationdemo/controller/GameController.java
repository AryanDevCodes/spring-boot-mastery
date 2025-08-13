package org.annotations.annotationdemo.controller;

import org.annotations.annotationdemo.service.Game;

public class GameController {
    Game game;  // reference of Game Interface

    public GameController(Game game) {
        this.game = game;
    }

    public String showAnnotation() {
        return game.getGameServiceName();
    }

    private void init() {
        System.out.println("Initializing GameController");
    }

    private void destroy() {
        System.out.println("Destroying GameController");
    }
}

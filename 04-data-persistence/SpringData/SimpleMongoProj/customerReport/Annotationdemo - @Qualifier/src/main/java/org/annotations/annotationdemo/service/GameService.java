package org.annotations.annotationdemo.service;

import org.springframework.stereotype.Component;

@Component
public class GameService implements Game{
    public String getGameServiceName() {
        return "Game Service - Uses @Qualifier annotation";
    }
}

package org.annotations.annotationdemo.service;

import org.springframework.stereotype.Component;

@Component("gameService1")
public class GameService1 implements Game{
    @Override
    public String getGameServiceName() {
        return "Another Bean Which is Specified By - @Qualifier - IN GameService1";
    }
}


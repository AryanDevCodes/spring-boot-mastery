package org.annotations.annotationdemo.service;

import org.springframework.stereotype.Component;

@Component("gameService2")
public class GameService2 implements Game{
    @Override
    public String getGameServiceName() {
        return "Another Bean Which is Specified By - @Qualifier - IN GameService2";
    }
}

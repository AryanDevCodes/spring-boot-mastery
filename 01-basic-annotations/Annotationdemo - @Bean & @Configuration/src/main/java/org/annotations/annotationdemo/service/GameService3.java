package org.annotations.annotationdemo.service;

import org.springframework.stereotype.Component;

@Component("gameService3")
public class GameService3 implements Game{
    @Override
    public String getGameServiceName() {
        return "Another Bean Which is Specified By - @Qualifier - IN GameService3 ";
    }
}

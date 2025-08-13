package org.annotations.annotationdemo.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("gameService1")
public class GameService1 implements Game{
    @Override
    public String getGameServiceName() {
        return "Another Bean Which is Specified By - @Qualifier - IN GameService1";
    }
}

@Component("gameService2")
class GameService2 implements Game{
    @Override
    public String getGameServiceName() {
        return "Another Bean Which is Specified By - @Qualifier - IN GameService2";
    }
}

@Component("gameService3")
@Primary
class GameService3 implements Game{
    @Override
    public String getGameServiceName() {
        return "Another Bean Which is Specified By - @Qualifier - IN GameService3  \n" +
                "Primary Bean - GameService3";
    }
}
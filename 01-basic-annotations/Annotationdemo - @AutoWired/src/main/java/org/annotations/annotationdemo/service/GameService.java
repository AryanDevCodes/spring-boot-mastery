package org.annotations.annotationdemo.service;

import org.springframework.stereotype.Component;

@Component
public class GameService {
    public String getGameServiceName() {
        return "Game Service - Uses @Autowired annotation";
    }
}

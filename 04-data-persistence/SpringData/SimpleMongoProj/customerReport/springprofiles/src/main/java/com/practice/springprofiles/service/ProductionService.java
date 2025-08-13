package com.practice.springprofiles.service;


import com.practice.springprofiles.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile("prod")
public class ProductionService {
    private final AppConfig appConfig;

    public String getConfigMessage() {
        System.out.println("In Production Environment");
        return "Prod : "+appConfig.getMessage();
    }
}

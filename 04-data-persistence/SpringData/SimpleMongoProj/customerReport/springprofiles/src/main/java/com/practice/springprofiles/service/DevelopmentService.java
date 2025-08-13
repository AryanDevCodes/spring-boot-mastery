package com.practice.springprofiles.service;

import com.practice.springprofiles.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile("dev")
public class DevelopmentService {
    private final AppConfig appConfig;

    public String getServiceMessage(){
        System.out.println("In Development Environment");
        return "Dev : "+appConfig.getMessage();
    }
}

package com.practice.springprofiles.controller;


import com.practice.springprofiles.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProfileController {
    private final AppConfig appConfig;

    @GetMapping("/config")
    public String getMessage(){
        return "Active Profile Config: " + appConfig.getMessage() + " (Version: " + appConfig.getVersion() + ")";
    }

}

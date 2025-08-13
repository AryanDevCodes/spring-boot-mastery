package com.practice.springprofiles.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Setter
@Getter
@ConfigurationProperties(prefix = "app")
@Component
@Data
public class AppConfig {

    private String message;
    private String version;

}

package org.practice.ConfigProps.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("company")
public class Company {
    @Value("${org.gt.name}")
    private String name;
    @Value("${org.gt.type}")
    private String type;
    @Value("${org.gt.location}")
    private String Location;

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", Location='" + Location + '\'' +
                '}';
    }
}

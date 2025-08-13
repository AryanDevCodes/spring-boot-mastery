package org.practice.ConfigProps.beans;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component("company1")
@ConfigurationProperties(prefix = "org.gt")
public class Company1 {
    private String name;
    private String type;
    private String default1 = "Default => Patna";

    @Override
    public String toString() {
        return "Company1{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", default1='" + default1 + '\'' +
                '}';
    }
}

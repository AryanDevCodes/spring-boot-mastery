package org.practice.bootproject6.beans;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component("comp")
public class Company {
    private String companyName;
    private String companyAddress;
    private int size;
}

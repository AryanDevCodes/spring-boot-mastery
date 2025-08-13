package org.practice.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeDTO implements Serializable {
    private Integer serialNO;
    private Integer EmployeeID;
    private String employeeName;
    private String jobTitle;
    private Double employeeSalary;
    private Integer departmentID;
    private Integer managerID;


}


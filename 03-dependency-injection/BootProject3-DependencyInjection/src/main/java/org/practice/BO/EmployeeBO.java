package org.practice.BO;

import lombok.Data;

//@Getter
//@Setter
@Data  // Doing work of both Getter & Setter
public class EmployeeBO {
    private Integer EmployeeID;
    private String employeeName;
    private String jobTitle;
    private Double employeeSalary;
    private Integer departmentID;
    private Integer managerID;
}
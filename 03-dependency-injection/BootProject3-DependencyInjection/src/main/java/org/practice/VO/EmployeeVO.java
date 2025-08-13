package org.practice.VO;

import lombok.Data;

@Data
public class EmployeeVO {
    private String serialNO;
    private Integer EmployeeID;
    private String employeeName;
    private String jobTitle;
    private String employeeSalary;
    private String departmentID;
    private String managerID;
}

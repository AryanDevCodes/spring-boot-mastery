package org.practice.DAO;

import org.practice.BO.EmployeeBO;

import java.util.List;

public interface EmployeeDAO_Interface {
    List<EmployeeBO> getEmployees(String Condition) throws Exception;

}

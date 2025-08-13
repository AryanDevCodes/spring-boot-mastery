package org.practice.service;

import org.practice.DTO.EmployeeDTO;

import java.util.List;

public interface EmployeeManagementServiceInter {

    List<EmployeeDTO> fetchEmployee(String[] designation) throws Exception;
}

package org.practice.service;

import org.practice.BO.EmployeeBO;
import org.practice.DAO.EmployeeDAO;
import org.practice.DTO.EmployeeDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeManagementServiceIMPL implements EmployeeManagementServiceInter {
    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeManagementServiceIMPL(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<EmployeeDTO> fetchEmployee(String[] designation) throws Exception {
        String condition = null;
        //convert designation[] into SQL IN clause condition (CLERK,MANAGER,SALESMAN)
        StringBuffer buffer = new StringBuffer("("); // use StringBuilder for WebApps
        for (int i = 0; i < designation.length; ++i) {
            if (i == designation.length - 1) {
                buffer.append("'").append(designation[i]).append("'");
            } else
                buffer.append("'").append(designation[i]).append("',");// for other than last element
        }
        // use DAO
        List<EmployeeBO> list = employeeDAO.getEmployees(condition);
        //convert ListBO to ListDTO
        List<EmployeeDTO> listDTO = new ArrayList<>();
        list.forEach(bo -> { // lamda Expression
            EmployeeDTO dto = new EmployeeDTO();
            //copy each BO class obj data to each DTO class obj data
            BeanUtils.copyProperties(bo, dto); // property names and type must match in both class bean
            dto.setSerialNO(listDTO.size() + 1);
            //add each obj of DTO class to List dtos
            listDTO.add(dto);
        });
        return listDTO;
    }
}

package org.practice.DAO;

import org.practice.BO.EmployeeBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAO implements EmployeeDAO_Interface {
    private static final String Get_EMPS_FROM_DS = "SELECT * FROM employee";
    private DataSource ds;

    @Autowired
    public EmployeeDAO(DataSource ds) {
    }

    @Override
    public List<EmployeeBO> getEmployees(String Condition) throws Exception {
        List<EmployeeBO> list = new ArrayList<EmployeeBO>();
        try {
            Connection con = ds.getConnection();
            // create statement obj
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(Get_EMPS_FROM_DS + Condition + "ORDER BY JOB");
            // convert RS object to List<BO> class obj
            EmployeeBO employeeBO = null;
            while (rs.next()) {
                // copy data from rs to BO class setter method
                employeeBO = new EmployeeBO();
                employeeBO.setEmployeeID(rs.getInt(1));
                employeeBO.setEmployeeName(rs.getString(2));
                employeeBO.setJobTitle(rs.getString(3));
                employeeBO.setEmployeeSalary(rs.getDouble(4));
                employeeBO.setDepartmentID(rs.getInt(5));
                employeeBO.setManagerID(rs.getInt(6));
                list.add(employeeBO);
            }
        } catch (SQLException se) {
            se.printStackTrace();
            throw se; // throw for exception propagation
        }
        return list;
    }
}

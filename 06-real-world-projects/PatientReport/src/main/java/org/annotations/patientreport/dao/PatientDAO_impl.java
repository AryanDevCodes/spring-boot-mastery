package org.annotations.patientreport.dao;

import org.annotations.patientreport.bo.PatientDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class PatientDAO_impl implements PatientDAO{
    private static final String INSERT_QUERY = "INSERT INTO corona_patient_details values(Patient_ID,?,?,?,?,?,?,?,?)";
    private final DataSource dataSource;
@Autowired
    public PatientDAO_impl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int insert(PatientDetail BO) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        int count ;
        try {
            con = dataSource.getConnection();
            pstmt = con.prepareStatement(INSERT_QUERY);
            pstmt.setString(1,BO.getPatient_name());
            pstmt.setString(2,BO.getPatient_addr());
            pstmt.setString(3,BO.getPatient_MobNumber());
            pstmt.setFloat(4,BO.getBill_per_day());
            pstmt.setFloat(5,BO.getNumber_of_days());
            pstmt.setFloat(6,BO.getTotalBillAmount());
            pstmt.setFloat(7,BO.getDiscount());
            pstmt.setFloat(8,BO.getNetBillAmount());
            count = pstmt.executeUpdate();
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            }catch(SQLException se){
                System.out.println(se.getMessage());
            }
            try {
                if (con != null) con.close();
            }catch (SQLException se){
                System.out.println(se.getMessage());
            }
        }

        return count;
    }
}

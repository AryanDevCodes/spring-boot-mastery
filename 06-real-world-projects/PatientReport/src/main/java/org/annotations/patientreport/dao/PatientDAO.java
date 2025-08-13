package org.annotations.patientreport.dao;

import org.annotations.patientreport.bo.PatientDetail;

public interface PatientDAO {
    int insert(PatientDetail BO) throws Exception;
}

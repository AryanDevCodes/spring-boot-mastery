package org.annotations.patientreport.service;

import org.annotations.patientreport.dto.PatientDTO;

public interface PatientServiceINT {
    String calculateNetBillAmount(PatientDTO dto) throws Exception;
}

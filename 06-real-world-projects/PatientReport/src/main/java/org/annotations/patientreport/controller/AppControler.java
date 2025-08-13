package org.annotations.patientreport.controller;

import org.annotations.patientreport.bo.PatientDetail;
import org.annotations.patientreport.dto.PatientDTO;
import org.annotations.patientreport.service.PatientServiceIMPL;
import org.annotations.patientreport.vo.PatientVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AppControler {
    private final PatientServiceIMPL patientService;
@Autowired
    public AppControler(PatientServiceIMPL patientService) {
        this.patientService = patientService;
    }
    public String processPatient(PatientVO patientVO) throws Exception {
        PatientDTO patientDTO = new PatientDTO();
        PatientDetail patientDetail = new PatientDetail();
        patientDTO.setPatient_name(patientVO.getPatient_name());
        patientDTO.setPatient_addr(patientVO.getPatient_addr());
        patientDTO.setPatient_MobNumber(String.valueOf(patientVO.getPatient_MobNumber()));
        patientDTO.setNumber_of_days(patientVO.getNumber_of_days());
        patientDTO.setBill_per_day(patientVO.getBill_per_day());
        patientDTO.setTotalBillAmount(patientDetail.getTotalBillAmount());
        patientDTO.setDiscount(patientDetail.getDiscount());
        patientDTO.setNetBillAmount(patientDetail.getNetBillAmount());
        return patientService.calculateNetBillAmount(patientDTO);
    }
}

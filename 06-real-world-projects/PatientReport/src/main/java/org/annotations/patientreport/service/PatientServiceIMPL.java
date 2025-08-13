package org.annotations.patientreport.service;

import org.annotations.patientreport.bo.PatientDetail;
import org.annotations.patientreport.dao.PatientDAO;
import org.annotations.patientreport.dto.PatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceIMPL implements PatientServiceINT{
    private final PatientDAO dao;

    @Autowired
    public PatientServiceIMPL(PatientDAO dao) {
        this.dao = dao;
    }

    @Override
    public String calculateNetBillAmount(PatientDTO dto) throws Exception {
        if (dto.getBill_per_day()==null){
            throw new IllegalArgumentException("Bill per day not found");
        }
        float totalBill = dto.getBill_per_day()*dto.getNumber_of_days();
        dto.setTotalBillAmount(totalBill);
        System.out.println("Calculated Total Bill = " + totalBill );
        float netBillAmount = totalBill;
        float discount = 0.0f;
        if(totalBill<=100000){
            discount = (totalBill)*0.01f;
            netBillAmount = totalBill-discount;
        } else if (totalBill>100000 && totalBill>=200000){
            discount = (totalBill)*0.01f;
            netBillAmount = totalBill-discount;
        }else System.out.println("NetBillAmount will be = "+netBillAmount);

        PatientDetail bo = new PatientDetail();
        bo.setPatient_name(dto.getPatient_name());
        bo.setPatient_addr(dto.getPatient_addr());
        bo.setPatient_MobNumber(String.valueOf(dto.getPatient_MobNumber()));
        bo.setBill_per_day(dto.getBill_per_day());
        bo.setNumber_of_days(dto.getNumber_of_days());
        bo.setTotalBillAmount(totalBill);
        bo.setDiscount(discount);
        bo.setNetBillAmount(netBillAmount);

        int count = dao.insert(bo);
        return count==1?"Patient Details Are Submitted :: TotalBillAmount==>"+totalBill:"Details Submission Failed";
    }
}

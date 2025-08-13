
package org.annotations.patientreport.dto;

public class PatientDTO {
    private String Patient_name;
    private String Patient_addr;
    private String Patient_MobNumber;
    private Integer number_of_days;
    private Float bill_per_day;
    private Float totalBillAmount;
    private Float netBillAmount;
    private Float discount;

    public String getPatient_name() {
        return Patient_name;
    }

    public void setPatient_name(String patient_name) {
        Patient_name = patient_name;
    }

    public String getPatient_addr() {
        return Patient_addr;
    }

    public void setPatient_addr(String patient_addr) {
        Patient_addr = patient_addr;
    }

    public String getPatient_MobNumber() {
        return Patient_MobNumber;
    }

    public void setPatient_MobNumber(String patient_MobNumber) {
        Patient_MobNumber = patient_MobNumber;
    }

    public Integer getNumber_of_days() {
        return number_of_days;
    }

    public void setNumber_of_days(Integer number_of_days) {
        this.number_of_days = number_of_days;
    }

    public Float getBill_per_day() {
        return bill_per_day;
    }

    public void setBill_per_day(Float bill_per_day) {
        this.bill_per_day = bill_per_day;
    }

    public Float getTotalBillAmount() {
        return totalBillAmount;
    }

    public void setTotalBillAmount(Float totalBillAmount) {
        this.totalBillAmount = totalBillAmount;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getNetBillAmount() {
        return netBillAmount;
    }

    public void setNetBillAmount(Float netBillAmount) {
        this.netBillAmount = netBillAmount;
    }

}

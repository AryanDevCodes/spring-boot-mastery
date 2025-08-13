package org.annotations.patientreport;

import org.annotations.patientreport.controller.AppControler;
import org.annotations.patientreport.vo.PatientVO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class PatientReportApplication {

    public static void main(String[] args) {
        try (var context = SpringApplication.run(PatientReportApplication.class, args)) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Patient Name :: ");
            String patientName = sc.nextLine();
            System.out.println("Patient Mobile Number :: ");
            String patientMobNum = sc.nextLine();
            System.out.println("Patient Address :: ");
            String patientAddress = sc.nextLine();
            System.out.println("Per day cost :: ");
            Float perDayCost = sc.nextFloat();
            System.out.println("Number of Days :: ");
            int number_of_days = sc.nextInt();
            PatientVO v = new PatientVO();
            v.setPatient_name(patientName);
            v.setPatient_MobNumber(patientMobNum);
            v.setPatient_addr(patientAddress);
            v.setBill_per_day(perDayCost);
            v.setNumber_of_days(number_of_days);
            AppControler controller = context.getBean(AppControler.class);
            String result = controller.processPatient(v);
            System.out.println(result);
            sc.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

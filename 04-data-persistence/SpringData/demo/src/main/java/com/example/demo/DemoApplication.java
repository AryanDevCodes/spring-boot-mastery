package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;

import com.example.demo.entity.CoronaVaccine;
import com.example.demo.service.CoronaVaccineServiceImpl;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		CoronaVaccineServiceImpl serviceImpl = context.getBean("vaccineMgmtService", CoronaVaccineServiceImpl.class);
		// try {
		// CoronaVaccine vaccine = new CoronaVaccine();
		// vaccine.setName("Covaxin");
		// vaccine.setCompany("Bharat BioTech");
		// vaccine.setPrice(7389.0);
		// vaccine.setRequiredDoesCount(2);
		// System.out.println("Vaccine Registered : " +
		// serviceImpl.registerCoronaVaccine(vaccine));
		// } catch (DataAccessException dae) {
		// dae.printStackTrace();
		// }
	}

}

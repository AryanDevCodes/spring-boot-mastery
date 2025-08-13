package com.example.demo.runners;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.CoronaVaccine;
import com.example.demo.service.CoronaVaccineServiceInt;

@Component
public class Savedata_Runner implements CommandLineRunner {
    private final CoronaVaccineServiceInt serviceInt;

    // Constructor-based dependency injection
    public Savedata_Runner(CoronaVaccineServiceInt serviceInt) {
        this.serviceInt = serviceInt;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create vaccine objects
        CoronaVaccine vaccine1 = new CoronaVaccine();
        vaccine1.setName("Sputnik V");
        vaccine1.setCompany("Russian");
        vaccine1.setPrice(7898.0);
        vaccine1.setRequiredDoesCount(2);

        CoronaVaccine vaccine2 = new CoronaVaccine();
        vaccine2.setName("Covaxin");
        vaccine2.setCompany("Bharat Biotech");
        vaccine2.setPrice(7389.0);
        vaccine2.setRequiredDoesCount(2);

        CoronaVaccine vaccine3 = new CoronaVaccine();
        vaccine3.setName("Pfizer");
        vaccine3.setCompany("Pfizer Inc.");
        vaccine3.setPrice(12000.0);
        vaccine3.setRequiredDoesCount(2);

        try {
            // Register vaccines in batch
            // Iterable<CoronaVaccine> registeredVaccines = serviceInt
            // .registerInBatch(List.of(vaccine1, vaccine2, vaccine3));

            // Iterable<CoronaVaccine> registeredVaccines = serviceInt
            // .registerInBatch(Arrays.asList(vaccine1, vaccine2, vaccine3));

            // // Print registered regNOs
            // System.out.println("The Registered RegNumbers are:");
            // registeredVaccines.forEach(vaccine ->
            // System.out.println(vaccine.getRegNO()));

            System.out.println("Total Number of Vaccine " + serviceInt.getVaccineCount());
            System.out.println("Availability of vaccine of given regnum " + serviceInt.checkVaccineAvaiability(252l));
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        }
    }
}

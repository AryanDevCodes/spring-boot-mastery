package com.example.demo.service;

import com.example.demo.entity.CoronaVaccine;
import com.example.demo.repo.CoronaVaccineRepoInt;
import org.springframework.stereotype.Service;

@Service("vaccineMgmtService")
public class CoronaVaccineServiceImpl implements CoronaVaccineServiceInt {
    private final CoronaVaccineRepoInt vaccineRepo;

    public CoronaVaccineServiceImpl(CoronaVaccineRepoInt repo) {
        this.vaccineRepo = repo;
    }

    @Override
    public String registerCoronaVaccine(CoronaVaccine coronaVaccine) {
        CoronaVaccine vaccine = vaccineRepo.save(coronaVaccine);
        return vaccine != null ? "Vaccine Registered Successfully with " + vaccine.getRegNO()
                : "Vaccine registration Failed";
    }

    @Override
    public Iterable<CoronaVaccine> registerInBatch(Iterable<CoronaVaccine> vaccines) {
        if (vaccines != null) {
            return vaccineRepo.saveAll(vaccines);
        } else
            throw new IllegalArgumentException("Invalid Data Type");
    }

    @Override
    public Long getVaccineCount() {
        return vaccineRepo.count();
    }

    @Override
    public Boolean checkVaccineAvaiability(Long regNo) {
        return vaccineRepo.existsById(regNo);
    }

}

package com.example.demo.service;

import com.example.demo.entity.CoronaVaccine;

public interface CoronaVaccineServiceInt {
    public String registerCoronaVaccine(CoronaVaccine coronaVaccine);

    public Iterable<CoronaVaccine> registerInBatch(Iterable<CoronaVaccine> vaccines);

    public Long getVaccineCount();

    public Boolean checkVaccineAvaiability(Long regNo);
}

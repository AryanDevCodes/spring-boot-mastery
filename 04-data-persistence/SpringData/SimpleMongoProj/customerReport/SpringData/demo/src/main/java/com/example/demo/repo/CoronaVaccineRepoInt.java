package com.example.demo.repo;

import com.example.demo.entity.CoronaVaccine;
import org.springframework.data.repository.CrudRepository;

public interface CoronaVaccineRepoInt extends CrudRepository<CoronaVaccine, Long> {
}

package com.manning.junitbook.repository;

import com.manning.junitbook.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query(value = "select c from Country c where c.name like 'A%'", nativeQuery = false)
    List<Country> getCountriesStartingWithA();
}

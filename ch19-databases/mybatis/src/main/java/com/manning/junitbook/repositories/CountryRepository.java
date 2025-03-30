package com.manning.junitbook.repositories;

import com.manning.junitbook.model.Country;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CountryRepository {
    List<Country> getCountriesStartingWithA();

    int save(Country country);

    void deleteAll();

    List<Country> findAll();
}

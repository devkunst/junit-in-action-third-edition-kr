package com.manning.junitbook.repositories;

import com.manning.junitbook.model.Country;
import com.manning.junitbook.service.CountryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static com.manning.junitbook.service.CountryService.COUNTRY_INIT_DATA;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@MybatisTest
@Import({CountryService.class})
class CountryRepositoryTest {


    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryService countryService;


    private List<Country> expectedCountryList = new ArrayList<>();
    private List<Country> expectedCountryListStartsWithA = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        countryService.init();
        initExpectedCountryLists();
    }

    @Test
    public void testCountryList() {
        List<Country> countryList = countryService.getAllCountries();
        assertNotNull(countryList);
        assertEquals(COUNTRY_INIT_DATA.length, countryList.size());
        for (int i = 0; i < expectedCountryList.size(); i++) {
            assertEquals(expectedCountryList.get(i), countryList.get(i));
        }
    }

    @Test
    public void testCountryListStartsWithA() {
        List<Country> countryList = countryService.getCountriesStartingWithA();
        assertNotNull(countryList);
        assertEquals(expectedCountryListStartsWithA.size(), countryList.size());
        for (int i = 0; i < expectedCountryListStartsWithA.size(); i++) {
            assertEquals(expectedCountryListStartsWithA.get(i), countryList.get(i));
        }
    }

    @AfterEach
    public void dropDown() {
        countryService.clear();
    }

    private void initExpectedCountryLists() {
        for (int i = 0; i < COUNTRY_INIT_DATA.length; i++) {
            String[] countryInitData = COUNTRY_INIT_DATA[i];
            Country country = new Country(countryInitData[0], countryInitData[1]);
            expectedCountryList.add(country);
            if (country.getName().startsWith("A")) {
                expectedCountryListStartsWithA.add(country);
            }
        }
    }

}
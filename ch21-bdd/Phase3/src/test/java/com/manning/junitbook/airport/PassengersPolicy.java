/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package com.manning.junitbook.airport;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PassengersPolicy {
    private Flight economyFlight;
    private Flight businessFlight;
    private Flight premiumFlight;
    private Passenger mike;
    private Passenger john;

    @Given("^이코노미 항공편에서$")
    public void 이코노미_항공편에서() throws Throwable {
        economyFlight = new EconomyFlight("1");
    }

    @When("^일반 승객은$")
    public void 일반_승객은() throws Throwable {
        mike = new Passenger("Mike", false);
    }

    @Then("^이코노미 항공편에서 추가가 가능하고 삭제도 가능하다$")
    public void 이코노미_항공편에서_추가가_가능하고_삭제도_가능하다() throws Throwable {
        assertAll("일반 승객은 이코노미 항공편에서 추가가 가능하고 삭제도 가능한지 검증",
                () -> assertEquals("1", economyFlight.getId()),
                () -> assertEquals(true, economyFlight.addPassenger(mike)),
                () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                () -> assertTrue(economyFlight.getPassengersSet().contains(mike)),
                () -> assertEquals(true, economyFlight.removePassenger(mike)),
                () -> assertEquals(0, economyFlight.getPassengersSet().size())
        );
    }

    @Then("^이코노미 항공편에 일반 승객을 중복해서 추가할 수 없다$")
    public void 이코노미_항공편에_일반_승객을_중복해서_추가할_수_없다() throws Throwable {
        for (int i = 0; i < 10; i++) {
            economyFlight.addPassenger(mike);
        }
        assertAll("이코노미 항공편에 일반 승객을 중복해서 추가할 수 없는지 검증",
                () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                () -> assertTrue(economyFlight.getPassengersSet().contains(mike)),
                () -> assertTrue(new ArrayList<>(economyFlight.getPassengersSet()).get(0).getName().equals("Mike"))
        );
    }

    @When("^VIP 승객은$")
    public void vip_승객은() throws Throwable {
        john = new Passenger("John", true);
    }

    @Then("^이코노미 항공편에서 추가가 가능하지만 삭제는 불가능하다$")
    public void 이코노미_항공편에서_추가가_가능하지만_삭제는_불가능하다() throws Throwable {
        assertAll("VIP 승객은 이코노미 항공편에서 추가가 가능하지만 삭제는 불가능한지 검증",
                () -> assertEquals("1", economyFlight.getId()),
                () -> assertEquals(true, economyFlight.addPassenger(john)),
                () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                () -> assertTrue(economyFlight.getPassengersSet().contains(john)),
                () -> assertEquals(false, economyFlight.removePassenger(john)),
                () -> assertEquals(1, economyFlight.getPassengersSet().size())
        );
    }

    @Then("^이코노미 항공편에 VIP 승객을 중복해서 추가할 수 없다$")
    public void 이코노미_항공편에_VIP_승객을_중복해서_추가할_수_없다() throws Throwable {
        for (int i = 0; i < 10; i++) {
            economyFlight.addPassenger(john);
        }

        assertAll("이코노미 항공편에 VIP 승객을 중복해서 추가할 수 없는지 검증",
                () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                () -> assertTrue(economyFlight.getPassengersSet().contains(john)),
                () -> assertTrue(new ArrayList<>(economyFlight.getPassengersSet()).get(0).getName().equals("John"))
        );
    }

    @Given("^비즈니스 항공편에서$")
    public void 비즈니스_항공편에서() throws Throwable {
        businessFlight = new BusinessFlight("2");
    }

    @Then("^비즈니스 항공편에서 추가가 불가능하고 삭제도 불가능하다$")
    public void 비즈니스_항공편에서_추가가_불가능하고_삭제도_불가능하다() throws Throwable {
        assertAll("일반 승객은 비즈니스 항공편에서 추가가 불가능하고 삭제도 불가능한지 검증",
                () -> assertEquals(false, businessFlight.addPassenger(mike)),
                () -> assertEquals(0, businessFlight.getPassengersSet().size()),
                () -> assertEquals(false, businessFlight.removePassenger(mike)),
                () -> assertEquals(0, businessFlight.getPassengersSet().size())
        );
    }

    @Then("^비즈니스 항공편에서 추가가 가능하지만 삭제는 불가능하다$")
    public void 비즈니스_항공편에서_추가가_가능하지만_삭제는_불가능하다() throws Throwable {
        assertAll("VIP 승객은 비즈니스 항공편에서 추가가 가능하지만 삭제는 불가능한지 검증",
                () -> assertEquals(true, businessFlight.addPassenger(john)),
                () -> assertEquals(1, businessFlight.getPassengersSet().size()),
                () -> assertEquals(false, businessFlight.removePassenger(john)),
                () -> assertEquals(1, businessFlight.getPassengersSet().size())
        );
    }

    @Then("^비즈니스 항공편에 VIP 승객을 중복해서 추가할 수 없다$")
    public void 비즈니스_항공편에_VIP_승객을_중복해서_추가할_수_없다() throws Throwable {
        for (int i = 0; i < 10; i++) {
            businessFlight.addPassenger(john);
        }

        assertAll("비즈니스 항공편에 VIP 승객을 중복해서 추가할 수 없는지 검증",
                () -> assertEquals(1, businessFlight.getPassengersSet().size()),
                () -> assertTrue(businessFlight.getPassengersSet().contains(john)),
                () -> assertTrue(new ArrayList<>(businessFlight.getPassengersSet()).get(0).getName().equals("John"))
        );
    }

    @Given("^프리미엄 항공편에서$")
    public void 프리미엄_항공편에서() throws Throwable {
        premiumFlight = new PremiumFlight("3");
    }

    @Then("^프리미엄 항공편에서 추가가 불가능하고 삭제도 불가능하다$")
    public void 프리미엄_항공편에서_추가가_불가능하고_삭제도_불가능하다() throws Throwable {
        assertAll("일반 승객은 프리미엄 항공편에서 추가가 불가능하고 삭제도 불가능한지 검증",
                () -> assertEquals(false, premiumFlight.addPassenger(mike)),
                () -> assertEquals(0, premiumFlight.getPassengersSet().size()),
                () -> assertEquals(false, premiumFlight.removePassenger(mike)),
                () -> assertEquals(0, premiumFlight.getPassengersSet().size())
        );
    }

    @Then("^프리미엄 항공편에서 추가가 가능하고 삭제도 가능하다$")
    public void 프리미엄_항공편에서_추가가_가능하고_삭제도_가능하다() throws Throwable {
        assertAll("VIP 승객은 프리미엄 항공편에서 추가가 가능하고 삭제도 가능한지 검증",
                () -> assertEquals(true, premiumFlight.addPassenger(john)),
                () -> assertEquals(1, premiumFlight.getPassengersSet().size()),
                () -> assertEquals(true, premiumFlight.removePassenger(john)),
                () -> assertEquals(0, premiumFlight.getPassengersSet().size())
        );
    }

    @Then("^프리미엄 항공편에 VIP 승객을 중복해서 추가할 수 없다$")
    public void 프리미엄_항공편에_VIP_승객을_중복해서_추가할_수_없다() throws Throwable {
        for (int i = 0; i < 10; i++) {
            premiumFlight.addPassenger(john);
        }

        assertAll("프리미엄 항공편에 VIP 승객을 중복해서 추가할 수 없는지 검증",
                () -> assertEquals(1, premiumFlight.getPassengersSet().size()),
                () -> assertTrue(premiumFlight.getPassengersSet().contains(john)),
                () -> assertTrue(new ArrayList<>(premiumFlight.getPassengersSet()).get(0).getName().equals("John"))
        );
    }
}

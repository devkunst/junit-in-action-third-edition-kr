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
package com.manning.junitbook.testpyramid.airport;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassengersPolicy {
    private Flight flight;
    private List<Passenger> regularPassengers = new ArrayList<>();
    private List<Passenger> vipPassengers = new ArrayList<>();
    private Flight anotherFlight = new Flight("AA7890", 48);

    @Given("^항공편명이 \"([^\"]*)\" 이고 좌석 수가 (\\d+) 인 항공편과 \"([^\"]*)\"에 정의되어 있는 승객 정보가 있는 상황에서$")
    public void 항공편명이_이고_좌석_수가_인_항공편과_에_정의되어_있는_승객_정보가_있는_상황에서(String flightNumber, int seats, String fileName) throws Throwable {
        flight = FlightBuilderUtil.buildFlightFromCsv(flightNumber, seats, "src/test/resources/" + fileName);
    }

    @When("^일반 승객은$")
    public void 일반_승객은() {
        for (Passenger passenger : flight.getPassengers()) {
            if (!passenger.isVip()) {
                regularPassengers.add(passenger);
            }
        }
    }

    @Then("^항공편에서 삭제할 수 있다$")
    public void 항공편에서_삭제할_수_있다() {
        for (Passenger passenger : regularPassengers) {
            assertTrue(flight.removePassenger(passenger));
        }
    }

    @Then("^다른 항공편에 추가할 수 있다$")
    public void 다른_항공편에_추가할_수_있다() {
        for (Passenger passenger : regularPassengers) {
            assertTrue(anotherFlight.addPassenger(passenger));
        }
    }

    @When("^VIP 승객은$")
    public void vip_승객은() {
        for (Passenger passenger : flight.getPassengers()) {
            if (passenger.isVip()) {
                vipPassengers.add(passenger);
            }
        }
    }

    @Then("^항공편에서 삭제할 수 없다$")
    public void 항공편에서_삭제할_수_없다() {
        for (Passenger passenger : vipPassengers) {
            assertFalse(flight.removePassenger(passenger));
        }
    }
}

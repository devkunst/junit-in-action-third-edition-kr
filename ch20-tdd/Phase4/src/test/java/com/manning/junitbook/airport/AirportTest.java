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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AirportTest {

    @DisplayName("Given 이코노미 항공편에서")
    @Nested
    class EconomyFlightTest {

        private Flight economyFlight;
        private Passenger mike;
        private Passenger james;

        @BeforeEach
        void setUp() {
            economyFlight = new EconomyFlight("1");
            mike = new Passenger("Mike", false);
            james = new Passenger("James", true);
        }

        @Nested
        @DisplayName("When 일반 승객은")
        class RegularPassenger {

            @Test
            @DisplayName("Then 이코노미 항공편에서 추가가 가능하고 삭제도 가능하다")
            public void testEconomyFlightRegularPassenger() {
                assertAll("일반 승객은 이코노미 항공편에서 추가가 가능하고 삭제도 가능한지 검증",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertEquals(true, economyFlight.addPassenger(mike)),
                        () -> assertEquals(1, economyFlight.getPassengersList().size()),
                        () -> assertEquals("Mike", economyFlight.getPassengersList().get(0).getName()),
                        () -> assertEquals(true, economyFlight.removePassenger(mike)),
                        () -> assertEquals(0, economyFlight.getPassengersList().size())
                );
            }
        }

        @Nested
        @DisplayName("When VIP 승객은")
        class VipPassenger {
            @Test
            @DisplayName("Then 이코노미 항공편에서 추가가 가능하지만 삭제는 불가능하다")
            public void testEconomyFlightVipPassenger() {
                assertAll("VIP 승객은 이코노미 항공편에서 추가가 가능하지만 삭제는 불가능한지 검증",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertEquals(true, economyFlight.addPassenger(james)),
                        () -> assertEquals(1, economyFlight.getPassengersList().size()),
                        () -> assertEquals("James", economyFlight.getPassengersList().get(0).getName()),
                        () -> assertEquals(false, economyFlight.removePassenger(james)),
                        () -> assertEquals(1, economyFlight.getPassengersList().size())
                );

            }
        }
    }

    @DisplayName("Given 비즈니스 항공편에서")
    @Nested
    class BusinessFlightTest {
        private Flight businessFlight;
        private Passenger mike;
        private Passenger james;

        @BeforeEach
        void setUp() {
            businessFlight = new BusinessFlight("2");
            mike = new Passenger("Mike", false);
            james = new Passenger("James", true);
        }

        @Nested
        @DisplayName("When 일반 승객은")
        class RegularPassenger {

            @Test
            @DisplayName("Then 비즈니스 항공편에서 추가가 불가능하고 삭제도 불가능하다")
            public void testBusinessFlightRegularPassenger() {
                assertAll("일반 승객은 비즈니스 항공편에서 추가가 불가능하고 삭제도 불가능한지 검증",
                        () -> assertEquals(false, businessFlight.addPassenger(mike)),
                        () -> assertEquals(0, businessFlight.getPassengersList().size()),
                        () -> assertEquals(false, businessFlight.removePassenger(mike)),
                        () -> assertEquals(0, businessFlight.getPassengersList().size())
                );
            }
        }

        @Nested
        @DisplayName("When VIP 승객은")
        class VipPassenger {

            @Test
            @DisplayName("Then 비즈니스 항공편에서 추가가 가능하지만 삭제는 불가능하다")
            public void testBusinessFlightVipPassenger() {
                assertAll("VIP 승객은 비즈니스 항공편에서 추가가 가능하지만 삭제는 불가능한지 검증",
                        () -> assertEquals(true, businessFlight.addPassenger(james)),
                        () -> assertEquals(1, businessFlight.getPassengersList().size()),
                        () -> assertEquals(false, businessFlight.removePassenger(james)),
                        () -> assertEquals(1, businessFlight.getPassengersList().size())
                );
            }
        }
    }

    @DisplayName("Given 프리미엄 항공편에서")
    @Nested
    class PremiumFlightTest {
        private Flight premiumFlight;
        private Passenger mike;
        private Passenger james;

        @BeforeEach
        void setUp() {
            premiumFlight = new PremiumFlight("3");
            mike = new Passenger("Mike", false);
            james = new Passenger("James", true);
        }

        @Nested
        @DisplayName("When 일반 승객은")
        class RegularPassenger {

            @Test
            @DisplayName("Then 프리미엄 항공편에서 추가가 불가능하고 삭제도 불가능하다")
            public void testPremiumFlightRegularPassenger() {
                assertAll("프리미엄 항공편에서 추가가 불가능하고 삭제도 불가능한지 검증",
                        () -> assertEquals(false, premiumFlight.addPassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengersList().size()),
                        () -> assertEquals(false, premiumFlight.removePassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengersList().size())
                );
            }
        }

        @Nested
        @DisplayName("When VIP 승객은")
        class VipPassenger {

            @Test
            @DisplayName("Then 프리미엄 항공편에서 추가가 가능하고 삭제도 가능하다")
            public void testPremiumFlightVipPassenger() {
                assertAll("VIP 승객은 프리미엄 항공편에서 추가가 가능하고 삭제도 가능한지 검증",
                        () -> assertEquals(true, premiumFlight.addPassenger(james)),
                        () -> assertEquals(1, premiumFlight.getPassengersList().size()),
                        () -> assertEquals(true, premiumFlight.removePassenger(james)),
                        () -> assertEquals(0, premiumFlight.getPassengersList().size())
                );
            }
        }
    }
}


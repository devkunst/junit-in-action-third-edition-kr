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

package com.manning.junitbook.ch12.disabled;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.manning.junitbook.ch12.lifecycle.SUT;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DisabledMethodsTest {
    private SUT systemUnderTest = new SUT("테스트 대상 시스템");

    @Test
    @Disabled
    void testUsualWork() {
        boolean canReceiveUsualWork = systemUnderTest.canReceiveUsualWork();

        assertTrue(canReceiveUsualWork);
    }

    @Test
    @Disabled("기능이 아직 개발 중")
    void testAdditionalWork() {
        boolean canReceiveAdditionalWork = systemUnderTest.canReceiveAdditionalWork();

        assertFalse(canReceiveAdditionalWork);
    }
}

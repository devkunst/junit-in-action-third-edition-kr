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

package com.manning.junitbook.ch15.browse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MultiBrowserSeleniumTest {

    private WebDriver driver;

    public static Collection<WebDriver> getBrowserVersions() {
        // 사용자의 환경에 따라 변경
//        System.setProperty("webdriver.gecko.driver", "D:\\tools\\webdriver\\geckodriver.exe");
//        System.setProperty("webdriver.chrome.driver", "D:\\tools\\webdriver\\chromedriver.exe");
//        System.setProperty("webdriver.edge.driver", "D:\\tools\\webdriver\\msedgedriver.exe");
        return Arrays.asList(new WebDriver[]{new ChromeDriver(), new FirefoxDriver(), new EdgeDriver()});
    }

    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    void testManningAccess(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.manning.com");
        assertThat(driver.getTitle(), is("Manning"));
    }

    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    void testGoogleAccess(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.google.com");
        assertThat(driver.getTitle(), is("Google"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

}

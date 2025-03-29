/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.manning.junitbook.ch15.htmlunit;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.htmlunit.CollectingAlertHandler;
import org.htmlunit.WebAssert;
import org.htmlunit.html.HtmlForm;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlSubmitInput;
import org.htmlunit.html.HtmlTextInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Demonstrates testing a form.
 */
public class FormTest extends ManagedWebClient {

    @Test
    public void testForm() throws IOException {
        HtmlPage page = webClient.getPage("file:src/main/webapp/formtest.html");
        HtmlForm form = page.getFormByName("validated_form");
        HtmlTextInput input = form.getInputByName("in_text");
        input.setValueAttribute("typing...");
        HtmlSubmitInput submitButton = form.getInputByName("submit");
        HtmlPage resultPage = submitButton.click();
        WebAssert.assertTitleEquals(resultPage, "Result");
    }

    @Test
    public void testFormAlert() throws IOException {
        CollectingAlertHandler alertHandler = new CollectingAlertHandler();
        webClient.setAlertHandler(alertHandler);
        //alternative code for the line above:
        // webClient.setAlertHandler((page, message) -> fail("JavaScript alert: " + message));

        HtmlPage page = webClient.getPage("file:src/main/webapp/formtest.html");
        HtmlForm form = page.getFormByName("validated_form");
        HtmlSubmitInput submitButton = form.getInputByName("submit");
        HtmlPage resultPage = submitButton.click();
        WebAssert.assertTitleEquals(resultPage, page.getTitleText());
        WebAssert.assertTextPresent(resultPage, page.asNormalizedText());

        List<String> collectedAlerts = alertHandler.getCollectedAlerts();
        List<String> expectedAlerts = Collections.singletonList("Please enter a value.");
        assertEquals(expectedAlerts, collectedAlerts);
    }

    @Test
    public void testFormNoAlert() throws IOException {
        CollectingAlertHandler alertHandler = new CollectingAlertHandler();
        webClient.setAlertHandler(alertHandler);
        HtmlPage page = webClient.getPage("file:src/main/webapp/formtest.html");
        HtmlForm form = page.getFormByName("validated_form");
        HtmlTextInput input = form.getInputByName("in_text");
        input.setValueAttribute("typing...");
        HtmlSubmitInput submitButton = form.getInputByName("submit");
        HtmlPage resultPage = submitButton.click();
        WebAssert.assertTitleEquals(resultPage, "Result");
        assertTrue(alertHandler.getCollectedAlerts().isEmpty(), "No alerts expected");
    }

}

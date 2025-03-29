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
package com.manning.junitbook.ch07;

import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.util.Callback;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A sample test case that demonstrates how to stub an HTTP server using Jetty as an embedded server.
 *
 * @version $Id$
 */
public class TestWebClient {

    private WebClient client = new WebClient();

    @BeforeAll
    public static void setUp() throws Exception {
        Server server = new Server(8081);

        ContextHandler contentOkContext = new ContextHandler(server, "/testGetContentOk");
        contentOkContext.setHandler(new TestGetContentOkHandler());

        ContextHandler contentErrorContext = new ContextHandler(server, "/testGetContentError");
        contentErrorContext.setHandler(new TestGetContentServerErrorHandler());

        ContextHandler contentNotFoundContext = new ContextHandler(server, "/testGetContentNotFound");
        contentNotFoundContext.setHandler(new TestGetContentNotFoundHandler());

        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(contentOkContext, contentErrorContext, contentNotFoundContext);

        server.setHandler(contexts);
        server.setStopAtShutdown(true);
        server.start();
    }

    @AfterAll
    public static void tearDown() {
        // Empty
    }

    @Test
    public void testGetContentOk() throws MalformedURLException {
        String workingContent = client.getContent(new URL("http://localhost:8081/testGetContentOk"));
        assertEquals("It works", workingContent);
    }

    /**
     * Handler to handle the good requests to the server.
     */
    private static class TestGetContentOkHandler extends AbstractHandler {

        @Override
        public boolean handle(Request request, Response response, Callback callback) throws Exception {
            response.setStatus(HttpServletResponse.SC_OK);
            try (OutputStream out = Response.asBufferedOutputStream(request, response)) {
                out.write("It works".getBytes(StandardCharsets.UTF_8));
            }
            return true;
        }
    }

    /**
     * Handler to handle bad requests to the server
     */
    private static class TestGetContentServerErrorHandler extends AbstractHandler {

        @Override
        public boolean handle(Request request, Response response, Callback callback) throws Exception {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            return false;
        }
    }

    /**
     * Handler to handle requests that request unavailable content.
     */
    private static class TestGetContentNotFoundHandler extends AbstractHandler {

        @Override
        public boolean handle(Request request, Response response, Callback callback) throws Exception {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return false;
        }
    }
}

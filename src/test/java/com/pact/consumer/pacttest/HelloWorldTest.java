/*
 * Copyright (c) 2017 Target Inc
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Target Inc and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Target Inc
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Target Inc
 */

package com.pact.consumer.pacttest;

import au.com.dius.pact.consumer.*;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.pact.consumer.models.Greeting;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class HelloWorldTest
{
    private final String INVENTORY_REMOVAL_CONSUMER = "Packslip";

    private String newInventoryRemovalJsonBody="";

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("test_provider", "localhost", 8080, this);

    @Pact(state = "test_state", provider ="test_provider" , consumer = "test_consumer") // will default to the provider name from mockProvider
    public RequestResponsePact createFragment(PactDslWithProvider builder) {
        return builder
                .given("test state")
                .uponReceiving("Test Consumer for Generating Pacts")
                .path("/hello-world")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("{\n" +
                        "    \"id\": 1,\n" +
                        "    \"content\": \"Welcome to PACT!\"\n" +
                        "}")
                .toPact();
    }

    @Test
    @PactVerification("test_provider")
    public void testPactForConsumer() {
        ResponseEntity<String> response =
                new RestTemplate().exchange(mockProvider.getUrl()+ "/hello-world",
                        HttpMethod.GET, null, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }


    private DslPart testPactforConsumer() {
        return new PactDslJsonBody()
                .numberType("id")
                .stringType("content")
                .asBody();
    }
}

package com.adhound.service;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.paypal.subscriptions.Plan;
import com.paypal.subscriptions.Plans;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PayPalTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGenerateInvoice() {}

    @Test
    void testGetSubscription() throws IOException {
        /**
         * .path("{userName}")
         * .resolveTemplate("userName", "janedoe")
         * .queryParam("chapter", "1")
         * // http://example.com/webapi/read/janedoe?chapter=1
         * Response response = myResource.request(...)        .get();
         */
        String token = "A21AAGryLhIse4qpFuExIgp1ygwy8tHv8IWKtSexU6fWNKbdjmaw82L_s7CM0srx-xqzjNT4ecfJmr8FEUNkLWjkoOJlqwUrw";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://api.sandbox.paypal.com/v1/billing/plans");

        //target.path("{plans}").resolveTemplate("plans", "P-51006304PY025420FLZZKVXA");

        target.queryParam("plan_id", "P-51006304PY025420FLZZKVXA");
        String response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + token).get(String.class);

        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Plans plans = mapper.readValue(response, Plans.class);
        Plan plan = plans.getPlan().iterator().next();

        assertEquals("Annual Subscription", plan.getName());
        //assertEquals(200.00, Float.parseFloat(plan.getBillingCycles().iterator().next().getPricingScheme().getFixedPrice().getValue()));

    }

}
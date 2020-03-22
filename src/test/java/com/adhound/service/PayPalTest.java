package com.adhound.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.paypal.subscriptions.Plan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PayPalTest implements PropertiesLoader {

    PayPal payPal;

    @BeforeEach
    void setUp() throws Exception {

        payPal = new PayPal();
        payPal.setAccessToken();
    }

    @Test
    void testGetAccessToken() {
        assertNotNull(payPal.getAccessToken());
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
        String token = "Bearer " + payPal.getAccessToken();
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://api.sandbox.paypal.com/v1/billing/plans").path("{plan}").resolveTemplate("plan", "P-51006304PY025420FLZZKVXA");

        String response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, token).get(String.class);

        ObjectMapper mapper = new ObjectMapper();

        Plan plan = mapper.readValue(response, Plan.class);

        assertEquals("Annual Subscription", plan.getName());
        assertEquals(200.00, Float.parseFloat(plan.getBillingCycles().iterator().next().getPricingScheme().getFixedPrice().getValue()));

    }

}
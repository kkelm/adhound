package com.adhound.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.paypal.subscriptions.Plan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.client.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import org.apache.commons.codec.binary.Base64;

import static org.junit.jupiter.api.Assertions.*;

class PayPalTest implements PropertiesLoader {

    Properties properties;

    @BeforeEach
    void setUp() throws Exception {

        properties = this.loadProperties("/adhound.properties");

        String tokenParameters = "grant_type=client_credentials";
        System.setProperty("https.protocols", "TLSv1.1,TLSv1.2,SSLv3,SSLv2Hello");
        URL url = new URL("https://api.sandbox.paypal.com/v1/oauth2/token");
        // Establishes a connection to PayPal
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        // Gets and formats the PayPal credentials to send to PayPal
        String credentials = properties.getProperty("clientId")+":"+properties.getProperty("secret");
        // Encodes the credentials before sending
        String encoded = Base64.encodeBase64String(credentials.getBytes());
        // Sets Header information
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Basic " + encoded);
        conn.setRequestProperty("Content-Length", Integer.toString(tokenParameters.length()));
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Accept-Language", "en_US");
        conn.setUseCaches(false);
        conn.setDoOutput(true);
        // Gets the response from PayPal
        DataOutputStream response = new DataOutputStream(conn.getOutputStream());
        response.write(tokenParameters.getBytes());
        response.close();
        // Reads the response from PayPal
        BufferedReader responseData = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String jsonString = responseData.readLine();
        responseData.close();

        // Maps JSON to a HashMap
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> jsonMap = new HashMap();
        jsonMap = mapper.readValue(jsonString, HashMap.class);

        String accessToken = jsonMap.get("access_token").toString();

        String test = "";
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
        String token = "Bearer A21AAEiyigQtRDf9tPQEXEG1nXywfLbrb1Jyrm-yDFJrzvBkadq0Ccwk9amnb3nz9acfr42ONJHvtfN2mN4lScPbOcd-GUinA";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://api.sandbox.paypal.com/v1/billing/plans").path("{plan}").resolveTemplate("plan", "P-51006304PY025420FLZZKVXA");

        //target.queryParam("plan_id", "P-51006304PY025420FLZZKVXA");
        String response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, token).get(String.class);


        ObjectMapper mapper = new ObjectMapper();

        //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //Plans plans = mapper.readValue(response, Plans.class);
        //Plan plan = plans.getPlan().iterator().next();
        Plan plan = mapper.readValue(response, Plan.class);

        assertEquals("Annual Subscription", plan.getName());
        //assertEquals(200.00, Float.parseFloat(plan.getBillingCycles().iterator().next().getPricingScheme().getFixedPrice().getValue()));

    }

}
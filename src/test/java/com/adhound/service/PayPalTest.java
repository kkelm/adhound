package com.adhound.service;

import com.adhound.entity.User;
import com.adhound.persistence.UserData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.paypal.subscriptions.Plan;
import com.paypal.subscriptions.Subscribe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PayPalTest implements PropertiesLoader {

    PayPal payPal;

    UserData userData;

    @BeforeEach
    void setUp() throws Exception {

        userData = new UserData();

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
    void testGetSubscription() {

        Plan plan = payPal.getPlan();
        assertEquals("Annual Subscription", plan.getName());
        assertEquals(200.00, Float.parseFloat(plan.getBillingCycles().iterator().next().getPricingScheme().getFixedPrice().getValue()));

    }

    @Test
    void testSubscribe() throws JsonProcessingException {

        User user = (User) userData.crud.getById(1);

        Plan plan = payPal.getPlan();

        JsonObject subscription = new JsonObject();
        subscription.addProperty("plan_id", plan.getId());
// Create Inner JSON Object
        JsonObject subscriber = new JsonObject();

        JsonObject name = new JsonObject();
        name.addProperty("given_name", user.getFirstName());
        name.addProperty("surname", user.getLastName());

        JsonObject shippingAddress = new JsonObject();

        JsonObject fullName = new JsonObject();
        fullName.addProperty("full_name", user.getFirstName() + " " + user.getLastName());

        JsonObject address = new JsonObject();
        address.addProperty("address_line_1", user.getAddress());
        address.addProperty("address_line_2", "");
        address.addProperty("admin_area_2", user.getCity());
        address.addProperty("admin_area_1", user.getState().getAbbreviation());
        address.addProperty("postal_code", user.getZipcode());

        shippingAddress.add("name", fullName);
        shippingAddress.add("address", address);

        subscriber.add("name", name);
        subscriber.addProperty("email_address", user.getEmail());
        subscriber.add("shipping_address", shippingAddress);

        subscription.add("subscriber", subscriber);


        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://api.sandbox.paypal.com/v1/billing/subscriptions");

        String response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, payPal.getAccessToken()).accept(MediaType.APPLICATION_JSON).post(Entity.json(subscription), String.class);

        ObjectMapper mapper = new ObjectMapper();

        Subscribe subscribe = mapper.readValue(response, Subscribe.class);

        String test = "";

    }

}
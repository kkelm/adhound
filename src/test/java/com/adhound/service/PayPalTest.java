package com.adhound.service;

import com.adhound.entity.User;
import com.adhound.persistence.UserData;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.paypal.subscriptions.Plan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testGetSubscription() {

        Plan plan = payPal.getPlan();
        assertEquals("Annual Subscription", plan.getName());
        assertEquals(200.00, Float.parseFloat(plan.getBillingCycles().iterator().next().getPricingScheme().getFixedPrice().getValue()));

    }

    @Test
    void testSubscribe() throws JsonProcessingException {

        User user = (User) userData.crud.getById(1);
        assertEquals("APPROVAL_PENDING", payPal.getSubscription(user).getStatus());
        assertEquals("approve", payPal.getSubscription(user).getLinks().get(0).getRel());

    }

}
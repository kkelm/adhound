package com.adhound.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.subscriptions.Plan;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

public class PayPal implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private String accessToken;
    Properties properties;

    public PayPal() {}

    public String getAccessToken() { return accessToken; }

    public void setAccessToken() {
        try {

            properties = this.loadProperties("/adhound.properties");
            // Parameters needed to get token
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

            accessToken = "Bearer " + jsonMap.get("access_token").toString();

        }
        catch (MalformedURLException urlException) {
            logger.error(urlException.getMessage());
            logger.error(urlException.getStackTrace());
        }
        catch (IOException ioException) {
            logger.error(ioException.getMessage());
            logger.error(ioException.getStackTrace());
        }
        catch (Exception exception) {
            logger.error(exception.getMessage());
            logger.error(exception.getStackTrace());
        }
    }

    public Plan getPlan()  {
        // Get subscription information
        /**
         * .path("{userName}")
         * .resolveTemplate("userName", "janedoe")
         * .queryParam("chapter", "1")
         * // http://example.com/webapi/read/janedoe?chapter=1
         * Response response = myResource.request(...)        .get();
         */
        Plan plan = new Plan();

        this.setAccessToken();

        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("https://api.sandbox.paypal.com/v1/billing/plans").path("{plan}").resolveTemplate("plan", "P-51006304PY025420FLZZKVXA");

            String response = target.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, this.getAccessToken()).get(String.class);

            ObjectMapper mapper = new ObjectMapper();

            plan = mapper.readValue(response, Plan.class);
        }
        catch (JsonProcessingException jsonException) {
            logger.error(jsonException.getMessage());
            logger.error(jsonException.getStackTrace());
        }


        return plan;
    }

}

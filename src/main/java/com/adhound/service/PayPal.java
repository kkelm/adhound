package com.adhound.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
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

            accessToken = jsonMap.get("access_token").toString();

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

}

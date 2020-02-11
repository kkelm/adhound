package com.adhound.persistence;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.ibatis.jdbc.ScriptRunner;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Database {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private Properties properties;

    private Connection connection;
    /**
     * Private constructor prevents instantiating this class anywhere else, and calls the loadProperties method.
     */
    public Database() {
        loadProperties();
    }
    /**
     * Runs a SQL file to create the AdHound database.
     */
    private void createDatabase() {

        ScriptRunner scriptRunner = new ScriptRunner(getConnection());

        try {

            Reader reader = new BufferedReader(new FileReader(("/adhound.sql")));

            scriptRunner.runScript(reader);

        } catch (FileNotFoundException e) {

            System.out.println("Database.createDatabase() : Cannot load the SQL file.");

        }

    }
    /**
     * Instantiates an object of the Properties class, and loads the database properties file.
     */
    private void loadProperties() {

        System.out.println("Properties File");

        logger.info("Properties File Loaded");

        properties = new Properties();

        try {

            properties.load(this.getClass().getResourceAsStream("/database.properties"));

        } catch (IOException e) {

            logger.info("Properties File Did Not Load: ", e);

            System.out.println("Database.loadProperties() : Cannot load the properties file.");

        }

    }
    /**
     * Gets the Connection object to the database
     * @return Connection object
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Makes a connection to the database using properties.
     * @throws SQLException
     * @throws ClassCastException
     */
    public void setConnection() throws SQLException, ClassCastException {

        if (connection != null) { return; }

        try {

            Class.forName(properties.getProperty("driver"));

        } catch (ClassNotFoundException e) {

            throw new ClassCastException("Database.setConnection() : MySQL Driver not found");

        }

        String url = properties.getProperty("url");

        connection = DriverManager.getConnection(url, properties.getProperty("username"), properties.getProperty("password"));

    }

    /**
     * Disconnects the connection to the database.
     */
    public void disconnect() {

        if (connection != null) {

            try {

                connection.close();

            } catch (SQLException e) {

                System.out.println("Cannot close connection" + e);

            }

        }

        connection = null;

    }

}

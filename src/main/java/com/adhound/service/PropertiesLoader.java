package com.adhound.service;

import java.io.*;
import java.util.*;

/**
 * This interface contains a default method that can be used anywhere a Properties
 * object is needed to be loaded.
 *
 * @author kkelm
 */
public interface PropertiesLoader {
    /**
     * This default method will load a properties file into a Properties instance
     * and return it.
     *
     * @param propertiesFilePath a path to a file on the java classpath list
     * @return a populated Properties instance or an empty Properties instance if the file path was not found.
     * @throws IOException The class Exception and its subclasses are a form of Throwable that indicates conditions that a reasonable application might want to catch.
     */
    default Properties loadProperties(String propertiesFilePath) throws IOException {

        Properties properties = new Properties();

        try {
            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
            throw ioException;
        }

        return properties;
    }
}

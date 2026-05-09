package com.jhs.httpserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.jhs.httpserver.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {

    /**
     * it will be a singleton
     * because we don't need more than one manager
     * which will be shared across everything in this project
     */
    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        if (myConfigurationManager == null)
            myConfigurationManager = new ConfigurationManager();
        return myConfigurationManager;
    }

    /**
     * we will load the cfg file by the path provided
     */
    public void loadConfigurationFile(String filePath)  {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        // read everything on the file
        int i;
        try {
            while ((i = fileReader.read()) != -1) {
                sb.append((char)i); // append to buffer
            } // once we get the str buffer becomes filled with the contents of the file, we make  the json node
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }
        JsonNode cfg = null;
        try {
            cfg = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error parsing the Configuration: ", e);
        }
        try {
            myCurrentConfiguration = Json.fromJson(cfg, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing the Configuration file, internal: ", e);
        }

    }

    /**
     * returns the current loaded config.
     */
    public Configuration getCurrentConfiguration() {
        if ( myCurrentConfiguration == null) {
            throw new HttpConfigurationException("No Current Configuration Set.");
        }
        return myCurrentConfiguration;
    }

}

package com.jhs.httpserver;

import com.jhs.httpserver.config.Configuration;
import com.jhs.httpserver.config.ConfigurationManager;

/**
 * driver class for the http server
 */

public class HttpServer {
    public static void main(String[] args) {
        System.out.println("Server starting...");

        // call the manager
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration cfg = ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("Using Port " + cfg.getPort());
        System.out.println("Using WebRoot " + cfg.getWebroot());


    }
}

package com.jhs.httpserver;

import com.jhs.httpserver.config.Configuration;
import com.jhs.httpserver.config.ConfigurationManager;
import com.jhs.httpserver.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * driver class for the http server
 */

public class HttpServer {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) {
        LOGGER.info("Server starting...");

        // call the manager
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration cfg = ConfigurationManager.getInstance().getCurrentConfiguration();
        LOGGER.info("Using Port " + cfg.getPort());
        LOGGER.info("Using WebRoot " + cfg.getWebroot());
        LOGGER.info("Local: http://localhost:" + cfg.getPort() + "/");

        /**
         * from this point on, the server needs to handle tcp connections so the browsers can connect to the server and retrieve the data,
         * and it needs to understand the http protocol
         *
         * we'll have 3 restrictions to our server when initially building it (deleted now)
         * 1. only one connection to our server allowed at a time
         * 2. the server will disregard what the browser sends to it
         * 3. we'll test on a hardcoded webpage
         *
         * in the future the server will handle multithreaded actions and it will handle http protocols <-- current
         */

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(cfg.getPort(), cfg.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
//            TODO handle later
        }

    }
}

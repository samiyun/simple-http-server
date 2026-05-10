package com.jhs.httpserver;

import com.jhs.httpserver.config.Configuration;
import com.jhs.httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


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

        /**
         * from this point on, the server needs to handle tcp connections so the browsers can connect to the server and retrieve the data,
         * and it needs to understand the http protocol
         *
         * we'll have 3 restrictions to our server when initially building it
         * 1. only one connection to our server allowed at a time
         * 2. the server will disregard what the browser sends to it
         * 3. we'll test on a hardcoded webpage
         *
         * in the future the server will handle multithreaded actions and it will handle http protocols
         */

        try {
            ServerSocket serverSocket = new ServerSocket(cfg.getPort());
            Socket socket = serverSocket.accept(); // prompts the socket listening to the port to accept any connect
            // when it gets a connection it'll return the socket

            // the program  will execute up until .accept, it contiues after a connection has been made
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            // we use the input stream to read the request from the browser,

            // TODO we would read - for now we will ignore this as our 3 initial restrictions state why.
            // TODO we would write - we'll only do this
            // define the page to send to the browser
            String html = "<html><head><title>Simple Java HTTP Server</title></head><body><h1>This page was served using this http server.</h1></body></html>";

            // we send two special characters - the carriage return and the line feed

            final String CRLF = "\r\n"; // typically uses bytes (we'll use this in the future (13, 10 - ascii)

            // the browser won't understand this html string, we have to wrap it in a http response
            String response =
                    "HTTP/1.1 200 OK" + CRLF +
                    "Content-Length: " + html.getBytes().length + CRLF +
                    CRLF +
                    html +
                    CRLF + CRLF;

            // Status line : HTTP VERSION RESPONSE_CODE RESPONSE_MESSAGE
            // Content-Length - header

            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

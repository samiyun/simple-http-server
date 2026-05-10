package com.jhs.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);

    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        // this'll do what the main method was doing on the main thread

        // to actually make it so the server can take more than 1 connection, we can use a while loop

        try {
            while (serverSocket.isBound() && !serverSocket.isClosed()) { // the server socket has a queue which is a problem, if a process took too long other processes in queue will take as long
                // we'll use threads to independently process these requests
                Socket socket = serverSocket.accept(); // prompts the socket listening to the port to accept any connect
                LOGGER.info("* Connection Accepted: " + socket.getInetAddress());
                HttpConnectionWorkerThread workerThread = new HttpConnectionWorkerThread(socket);
                workerThread.start();
            }
        } catch (IOException e) {
            LOGGER.info("Problem with setting socket: ", e);
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {}
            }
        }
    }
}
package com.jhs.httpserver.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class HttpParser {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);

    public void parseHttpRequest(InputStream inputStream) {
        // the method wont be static so we can make a parser for each of the threads we're working on, though we can use the same method for every thread
    }
}

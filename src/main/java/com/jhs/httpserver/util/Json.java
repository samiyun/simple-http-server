package com.jhs.httpserver.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class Json {
    /**
     * build our obj mapper
     */
    private static ObjectMapper myObjectMapper = new ObjectMapper();

    private static ObjectMapper defaultObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        // configure the mapper to the  desearalization option
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,  false);
        // blocks missing properties causing crashing
        return om;
    }
    // to parse an adjacent string to an adjacent node
    public static JsonNode parse(String jsonSrc) throws IOException {
        return myObjectMapper.readTree(jsonSrc);
    }
    // to move json node to cfg POJO class
    public static <A> A fromJson(JsonNode node, Class<A> classA) throws JsonProcessingException {
        // generic type

        // use obj mapper and use tree val
        return myObjectMapper.treeToValue(node, classA);
    }

    // to put cfg file into json node
    public static JsonNode toJson(Object obj) {
        return myObjectMapper.valueToTree(obj);
    }

    public static String strify(JsonNode node) throws JsonProcessingException {
        return generateJson(node, false);
    }

    public static String strifyPretty(JsonNode node) throws JsonProcessingException {
        return generateJson(node, true);
    }

    // create the writevalueasstring
    private static String generateJson(Object to, boolean pretty) throws JsonProcessingException {

        ObjectWriter objectWriter = myObjectMapper.writer();
        if (pretty) {
            // pretty to true puts it in pretty print
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT); // this
        }
        return objectWriter.writeValueAsString(to);
    }
}

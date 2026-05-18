package com.jhs.httpserver.http;

public enum HttpMethod {
    GET, HEAD;

    public static final int MAX_LENGTH;

    static {
        int tempMaxLength =  -1;
        for (HttpMethod method: values()) {
            // compare to see  if the length of the method name is bigger than the one stored at the temp varaible,
            // if it is assign it to the temp var to the method lenght
            if (method.name().length() > tempMaxLength) {
                tempMaxLength = method.name().length();
            }
        }
        // Set max len to temp val
        MAX_LENGTH = tempMaxLength;
    }
}

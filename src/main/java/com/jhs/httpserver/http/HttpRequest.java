package com.jhs.httpserver.http;
// we will have some things in this class at the package level so any  other class in this package can use this class

public class HttpRequest extends HttpMessage {
    private String method; // this is a token and it is case sensitive
    private String requestTarget;
    private String httpVersion;

    public HttpRequest() {
    }

    public String getMethod() {
        return method;
    }

    void setMethod(String method) {
        this.method = method;
    }
}

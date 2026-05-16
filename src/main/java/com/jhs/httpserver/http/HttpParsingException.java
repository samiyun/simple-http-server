package com.jhs.httpserver.http;

public class HttpParsingException extends Exception {
    private HttpStatusCode errorCode;

    public HttpParsingException(HttpStatusCode errorCode) {
        super(errorCode.message);
        this.errorCode = errorCode;
    }
    public HttpStatusCode getErrorCode() {
        return errorCode;
    }
}

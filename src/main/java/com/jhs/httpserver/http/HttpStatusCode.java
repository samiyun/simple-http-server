package com.jhs.httpserver.http;

public enum HttpStatusCode {
    /* CLIENT ERRORS */
    CLIENT_ERROR_400_BAD_REQUEST(400, "Bad Request"),
    CLIENT_ERROR_401_METHOD_NOT_ALLOWED(401, "Bad Request"),
    CLIENT_ERROR_414_BAD_REQUEST(414, "Bad Request"),

    /* SERVER ERRORS */
    CLIENT_ERROR_500_INTERNAL_SERVER_ERROR(500, "Bad Request"),
    CLIENT_ERROR_501_NOT_IMPLEMENTED(501, "Bad Request");


    public final int STATUS_CODE;
    public final String message;

    HttpStatusCode(int STATUS_CODE, String message) {
        this.STATUS_CODE = STATUS_CODE;
        this.message = message;
    }
}

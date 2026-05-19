package com.jhs.httpserver.http;
// we will have some things in this class at the package level so any  other class in this package can use this class

public class HttpRequest extends HttpMessage {
    private HttpMethod method; // this is a token and it is case sensitive
    private String requestTarget;
    private String httpVersion;

    public HttpRequest() {
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getRequestTarget() {
        return requestTarget;
    }

    void setMethod(String methodName) throws HttpParsingException {
        for (HttpMethod method: HttpMethod.values()){
            if (methodName.equals(method.name())) {
                this.method = method;
                return;
            }
        }
        throw new HttpParsingException(
          HttpStatusCode.CLIENT_ERROR_501_NOT_IMPLEMENTED
        );
    }

    public void setRequestTarget(String requestTarget) throws HttpParsingException {
        if (requestTarget == null || requestTarget.length() == 0) {
            throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_500_INTERNAL_SERVER_ERROR);
        }
        this.requestTarget = requestTarget;
    }
}

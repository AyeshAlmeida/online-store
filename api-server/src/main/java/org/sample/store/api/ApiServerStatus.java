package org.sample.store.api;

public enum  ApiServerStatus {
    SUCCESS("S1000"),
    CLIENT_ERROR("E2400"),
    SERVER_ERROR("E2500");

    private final String statusCode;

    ApiServerStatus(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}

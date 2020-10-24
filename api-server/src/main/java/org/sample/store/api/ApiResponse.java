package org.sample.store.api;

public class ApiResponse {
    private String status;
    private String statusDescription;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public static ApiResponse clientError() {
        ApiResponse response = new ApiResponse();
        response.setStatus(ApiServerStatus.CLIENT_ERROR.getStatusCode());
        response.setStatus("Bad request");
        return response;
    }

    public static ApiResponse serverError(String statusDescription) {
        ApiResponse response = new ApiResponse();
        response.setStatus(ApiServerStatus.SERVER_ERROR.getStatusCode());
        response.setStatus("Internal Server Error");
        return response;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApiResponse{");
        sb.append("status='").append(status).append('\'');
        sb.append(", statusDescription='").append(statusDescription).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

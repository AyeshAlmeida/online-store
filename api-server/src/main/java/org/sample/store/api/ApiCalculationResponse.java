package org.sample.store.api;

public class ApiCalculationResponse extends ApiResponse {
    private ProductCalculationData productDetails;
    private float total;

    public ProductCalculationData getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductCalculationData productDetails) {
        this.productDetails = productDetails;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public static ApiCalculationResponse success(ProductCalculationData productDetails, float total) {
        ApiCalculationResponse response = new ApiCalculationResponse();
        response.setStatus(ApiServerStatus.SUCCESS.getStatusCode());
        response.setStatusDescription("Success");
        response.setProductDetails(productDetails);
        response.setTotal(total);
        return response;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CalculationResponse{");
        sb.append("productDetails=").append(productDetails);
        sb.append(", total=").append(total);
        sb.append('}');
        return sb.toString();
    }
}

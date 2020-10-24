package org.sample.store.api;

import org.sample.store.repository.product.Product;

import java.util.List;

public class ProductDataResponse extends ApiResponse {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public static ProductDataResponse success(List<Product> products) {
        ProductDataResponse response = new ProductDataResponse();
        response.setStatus(ApiServerStatus.SUCCESS.getStatusCode());
        response.setStatusDescription("Success");
        response.setProducts(products);
        return response;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductDataResponse{");
        sb.append("products=").append(products);
        sb.append('}');
        return sb.toString();
    }
}

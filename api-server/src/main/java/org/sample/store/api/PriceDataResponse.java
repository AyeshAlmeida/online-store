package org.sample.store.api;

import java.util.List;

public class PriceDataResponse extends ApiResponse {
    private int totalPrices;
    private List<PriceData> prices;

    public int getTotalPrices() {
        return totalPrices;
    }

    public void setTotalPrices(int totalPrices) {
        this.totalPrices = totalPrices;
    }

    public List<PriceData> getPrices() {
        return prices;
    }

    public void setPrices(List<PriceData> prices) {
        this.prices = prices;
    }

    public static PriceDataResponse success(int totalPrices, List<PriceData> prices) {
        PriceDataResponse response = new PriceDataResponse();
        response.setStatus(ApiServerStatus.SUCCESS.getStatusCode());
        response.setStatusDescription("Success");
        response.setTotalPrices(totalPrices);
        response.setPrices(prices);
        return response;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PriceDataResponse{");
        sb.append("totalPrices=").append(totalPrices);
        sb.append(", prices=").append(prices);
        sb.append('}');
        return sb.toString();
    }
}

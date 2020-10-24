package org.sample.store.handler;

import org.sample.store.api.ApiResponse;
import org.sample.store.repository.product.Product;
import org.sample.store.service.CalculationDataRetrievalService;
import org.sample.store.service.ProductStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class UIRequestHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(UIRequestHandler.class);

    private final CalculationDataRetrievalService calculationDataRetrievalService;
    private final ProductStore store;

    @Autowired
    public UIRequestHandler(CalculationDataRetrievalService calculationDataRetrievalService,
                            ProductStore store) {
        this.calculationDataRetrievalService = calculationDataRetrievalService;
        this.store = store;
    }

    public Mono<ServerResponse> priceTable(ServerRequest request) {
        final int pageNumber = Integer.parseInt(request.pathVariable("pageNumber"));
        List<Product> products = store.getAllProducts();
        return calculationDataRetrievalService
                .retrievePriceData(pageNumber, products)
                .doOnNext(res -> {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Retried response [{}]", res);
                    }
                })
                .flatMap(res -> ServerResponse.ok().body(BodyInserters.fromValue(res)))
                .doOnError(err -> {
                    LOGGER.error("Error occurred while executing requests ", err);
                }).onErrorResume(err -> {
                    ApiResponse error = ApiResponse.serverError(err.getLocalizedMessage());
                    return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BodyInserters.fromValue(error));
                });
    }

    public Mono<ServerResponse> calculatePrice(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).build();
    }
}

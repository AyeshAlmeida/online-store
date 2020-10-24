package org.sample.store.route;

import org.sample.store.handler.UIRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UIRoutes {
    private final UIRequestHandler requestHandler;

    @Autowired
    public UIRoutes(UIRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> priceTableRoute() {
        return RouterFunctions.route(RequestPredicates.GET("/api-server/v1/prices/{pageNumber}"),
                requestHandler::priceTable);
    }

    @Bean
    public RouterFunction<ServerResponse> productsRoute() {
        return RouterFunctions.route(RequestPredicates.GET("/api-server/v1/products"),
                requestHandler::products);
    }

    @Bean
    public RouterFunction<ServerResponse> calculationRoute() {
        return RouterFunctions.route(RequestPredicates.POST("/api-server/v1/prices/calculate"),
                requestHandler::calculatePrice);
    }
}

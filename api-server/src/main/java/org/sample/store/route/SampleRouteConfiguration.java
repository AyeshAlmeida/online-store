package org.sample.store.route;

import org.sample.store.handler.SampleRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class SampleRouteConfiguration {
    private final SampleRequestHandler sampleRequestHandler;

    @Autowired
    public SampleRouteConfiguration(SampleRequestHandler sampleRequestHandler) {
        this.sampleRequestHandler = sampleRequestHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> sampleRoute() {
        return RouterFunctions.route(RequestPredicates.GET("/api-server/v1/sample"),
                sampleRequestHandler::hello);
    }
}

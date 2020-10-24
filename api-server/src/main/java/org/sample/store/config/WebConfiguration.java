package org.sample.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class WebConfiguration {
    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        Arrays.asList("PUT", "DELETE", "GET", "POST", "OPTIONS")
                .forEach(m -> config.addAllowedMethod(m));
        Arrays.asList("Content-Type", "Authorization", "X-Requested-With", "X-PTM-JWT", "Content-Length", "Accept", "Origin")
                .forEach(m -> config.addAllowedHeader(m));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}

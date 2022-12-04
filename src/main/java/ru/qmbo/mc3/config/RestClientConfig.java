package ru.qmbo.mc3.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestClientConfig
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 02.12.2022
 */
@Configuration
public class RestClientConfig {
    /**
     * Gets client.
     *
     * @param builder the builder
     * @return the client
     */
    @Bean
    public RestTemplate getClient(RestTemplateBuilder builder) {
        return builder.build();
    }
}

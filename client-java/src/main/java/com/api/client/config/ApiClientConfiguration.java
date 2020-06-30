package com.api.client.config;

import com.api.petstore.ApiClient;
import com.api.petstore.RFC3339DateFormat;
import com.api.petstore.client.PetsApi;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.DateFormat;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class ApiClientConfiguration {

    @Bean
    public WebClient.Builder loadBalancedWebClientBuilder(final List<WebClientCustomizer> customizers) {
        WebClient.Builder webclientBuilder = WebClient.builder();
        customizers.forEach(customizer -> customizer.customize(webclientBuilder));
        return webclientBuilder;
    }

    @Bean
    public ApiClient apiClient(WebClient.Builder webClientBuilder) {
        DateFormat dateFormat = createDefaultDateFormat();
        ApiClient apiClient = new ApiClient(webClientBuilder.build(), createDefaultObjectMapper(dateFormat), dateFormat);
        apiClient.setBasePath("http://petstore-server");
        return apiClient;
    }

    @Bean
    public PetsApi petsApi(ApiClient apiClient) {
        return new PetsApi(apiClient);
    }

    public static DateFormat createDefaultDateFormat() {
        DateFormat dateFormat = new RFC3339DateFormat();
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat;
    }

    public static ObjectMapper createDefaultObjectMapper(DateFormat dateFormat) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(dateFormat);
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNullableModule jnm = new JsonNullableModule();
        mapper.registerModule(jnm);
        return mapper;
    }
}

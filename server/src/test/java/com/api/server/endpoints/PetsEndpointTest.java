package com.api.server.endpoints;

import com.api.petstore.ApiClient;
import com.api.petstore.client.PetsApi;
import com.api.petstore.server.model.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = "server.port=8081")
public class PetsEndpointTest {

    @Autowired
    private PetsApi petsApi;

    @Autowired
    private ApiClient apiClient;

    @Test
    public void shouldCallApi() {
        Assertions.assertThrows(WebClientResponseException.NotImplemented.class, () -> petsApi.listPets(10).collectList().block());
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        public ApiClient apiClient() {
            ApiClient apiClient = new ApiClient();
            apiClient.setBasePath("http://localhost:8081");
            return apiClient;
        }

        @Bean
        public PetsApi petsApi(ApiClient apiClient) {
            return new PetsApi(apiClient);
        }
    }
}

package com.api.client.endpoints;

import com.api.petstore.client.PetsApi;
import com.api.petstore.server.model.Pet;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class PetStoreClientEndpoint {

    private final PetsApi petsApi;

    @GetMapping("/pets")
    public Mono<ResponseEntity<Flux<Pet>>> listPetsWithApi() {
        Flux<Pet> petFlux = petsApi.listPets(10);
        return Mono.just(ResponseEntity.ok(petFlux));
    }
}

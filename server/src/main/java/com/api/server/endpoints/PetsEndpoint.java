package com.api.server.endpoints;

import com.api.petstore.server.handler.PetsApi;
import com.api.petstore.server.model.Pet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class PetsEndpoint implements PetsApi {

    //@Override
    //@CrossOrigin(origins = "http://localhost:4200")
    //public Mono<ResponseEntity<Flux<Pet>>> listPets(@Valid Integer limit, ServerWebExchange exchange) {
    //    Pet pet = new Pet();
    //    pet.setId(1l);
    //    pet.setName("Java");
    //    pet.setTag("#cat");
    //    return Mono.just(ResponseEntity.ok(Flux.just(pet)));
    //}
}

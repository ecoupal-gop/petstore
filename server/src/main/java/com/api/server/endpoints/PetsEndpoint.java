package com.api.server.endpoints;

import com.api.petstore.server.handler.PetsApi;
import com.api.petstore.server.model.Pet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.Duration;

@RestController
@Slf4j
public class PetsEndpoint implements PetsApi {

    @Override
    @CrossOrigin(origins = "*")
    public Mono<ResponseEntity<Flux<Pet>>> listPets(@Valid Integer limit, ServerWebExchange exchange) {
        log.info("List Pets");
        Pet java = new Pet();
        java.setId(1l);
        java.setName("Java");
        java.setTag("#cat");

        Pet rex = new Pet();
        rex.setId(2l);
        rex.setName("Rex");
        rex.setTag("#dog");

        Pet donald = new Pet();
        donald.setId(3l);
        donald.setName("Donald");
        donald.setTag("#duck");

        Flux<Pet> pets = Flux.just(java, rex, donald);

        //Delay to display usage of application/stream+json
        return Mono.just(ResponseEntity.ok(pets.delayElements(Duration.ofSeconds(2))));
    }
}

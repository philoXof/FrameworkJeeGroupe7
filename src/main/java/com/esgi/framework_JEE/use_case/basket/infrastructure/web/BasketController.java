package com.esgi.framework_JEE.use_case.basket.infrastructure.web;


import com.esgi.framework_JEE.use_case.basket.domain.Basket;
import com.esgi.framework_JEE.use_case.basket.domain.BasketService;
import com.esgi.framework_JEE.use_case.basket.infrastructure.web.response.BasketResponse;
import com.esgi.framework_JEE.use_case.user.query.UserQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/basket")
public class BasketController {

    private final BasketService basketService;
    private final UserQuery userQuery;

    public BasketController(BasketService basketService, UserQuery userQuery) {
        this.basketService = basketService;
        this.userQuery = userQuery;
    }

    @PostMapping
    public ResponseEntity<?> create(){
        var basketCreated = basketService.createEmpty();

        return ResponseEntity.created(
                linkTo(
                        methodOn(BasketController.class).getById(basketCreated.getId())
                ).toUri()
        ).build();
    }

    @PostMapping("/generate/{user_id}")
    public ResponseEntity<?> generateBasket(@PathVariable int user_id){
        var user = userQuery.getById(user_id);
        if(user == null){
            return new ResponseEntity<>(" User not found", HttpStatus.NOT_FOUND);
        }

        var basketCreated = basketService.generateWithUser(user);

        return ResponseEntity.created(
                linkTo(
                        methodOn(BasketController.class).getById(basketCreated.getId())
                ).toUri()
        ).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        var basket = basketService.getById(id);
        if(basket == null){
            return new ResponseEntity<>(" Basket not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toResponse(basket), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<BasketResponse>> getAll(){
        var basketResponses = basketService.getAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(basketResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id){

        var basketToDelete = basketService.getById(id);
        if(basketToDelete == null){
            return new ResponseEntity<>(" Basket not found", HttpStatus.NOT_FOUND);
        }

        basketService.delete(id);

        return new ResponseEntity<>("Basket deleted", HttpStatus.OK);
    }

    
    private BasketResponse toResponse(Basket basket){
        return new BasketResponse()
                .setUserId(
                        basket.getUser() == null ? 0 : basket.getUser().getId()
                );
    }
}

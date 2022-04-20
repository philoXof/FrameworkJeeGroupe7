package com.esgi.framework_JEE.use_case.basket.infrastructure.web;


import com.esgi.framework_JEE.use_case.basket.domain.BasketService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/basket")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }
}

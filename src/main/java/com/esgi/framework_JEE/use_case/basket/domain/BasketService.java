package com.esgi.framework_JEE.use_case.basket.domain;

import com.esgi.framework_JEE.use_case.basket.infrastructure.repository.BasketRepository;
import org.springframework.stereotype.Service;

@Service
public class BasketService {

    private final BasketRepository basketRepository;

    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }







}

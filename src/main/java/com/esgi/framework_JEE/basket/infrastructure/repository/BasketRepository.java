package com.esgi.framework_JEE.basket.infrastructure.repository;

import com.esgi.framework_JEE.basket.domain.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Integer> {

    Basket getBasketById(int basketId);

    Basket findBasketByUser_Id(int user_id);

}

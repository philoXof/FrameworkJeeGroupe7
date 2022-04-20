package com.esgi.framework_JEE.use_case.basket.infrastructure.repository;

import com.esgi.framework_JEE.use_case.basket.domain.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Integer> {

}

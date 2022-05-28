package com.esgi.framework_JEE.use_case.basket.domain;

import com.esgi.framework_JEE.use_case.basket.infrastructure.repository.BasketRepository;
import com.esgi.framework_JEE.use_case.user.Domain.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {

    private final BasketRepository basketRepository;

    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public Basket createEmpty(){
        var basket = new Basket();
        basketRepository.save(basket);

        return basket;
    }

    public Basket generateWithUser(User user){
        var basket = new Basket()
                .setUser(user);

        basketRepository.save(basket);
        return basket;
    }

    public Basket getById(int id){
        return basketRepository.getBasketById(id);
    }

    public Basket getByUserId(int id){
        return basketRepository.findBasketByUser_Id(id);
    }

    public List<Basket> getAll(){
        return basketRepository.findAll();
    }

    public void delete(int id){
        basketRepository.deleteById(id);
    }


}

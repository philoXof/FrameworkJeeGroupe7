package com.esgi.framework_JEE.basket;


import com.esgi.framework_JEE.use_case.basket.domain.Basket;
import com.esgi.framework_JEE.use_case.basket.infrastructure.repository.BasketRepository;
import com.esgi.framework_JEE.use_case.user.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class BasketRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    BasketRepository basketRepository;
    User user = new User();

    @BeforeEach
    public void setup(){
        user.setEmail("mail@mail.fr");
        user.setFirstname("Jean");
        user.setLastname("Pierre");
        user.setPassword("amezfjzlkvokl");
    }

    @Test
    public void shouldFindEmpty(){
        var baskets = basketRepository.findAll();
        assertThat(baskets).isEmpty();
    }

    @Test
    public void shouldFindNoBasketInEmptyRepository(){
        var baskets = basketRepository.findAll();

        assertThat(baskets).size().isEqualTo(0);
    }


    @Test
    public void shouldGetAllBasket(){
        var basket1 = new Basket()
                .setUser(user);

        basketRepository.save(basket1);


        var basket2 = new Basket()
                .setUser(user);

        basketRepository.save(basket2);


        var basket3 = new Basket()
                .setUser(user);

        basketRepository.save(basket3);


        var baskets = basketRepository.findAll();

        assertThat(baskets).hasSize(3).contains(basket1, basket2, basket3);
    }


    @Test
    public void shouldGetBasketById(){
        var basket = new Basket()
                .setUser(user);

        var basketCreated = entityManager.persist(basket);

        var invoiceFind = basketRepository.findById(basketCreated.getId());
        assertThat(invoiceFind).hasValue(basketCreated);
    }


    @Test
    public void shouldDeleteBasketById(){
        var basket1 = new Basket();
        entityManager.persist(basket1);

        var basket2 = new Basket();
        entityManager.persist(basket2);

        basketRepository.deleteAll();

        assertThat(basketRepository.findAll()).isEmpty();
    }


}

package com.esgi.framework_JEE.use_case.slot.domain.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class SlotRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    SlotRepository slotRepository;


    @Test
    public void should_find_empty() {
        var pc = slotRepository.findAll();
        assertThat(pc).isEmpty();
    }
}
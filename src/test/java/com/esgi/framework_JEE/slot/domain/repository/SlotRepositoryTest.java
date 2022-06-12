package com.esgi.framework_JEE.slot.domain.repository;

import com.esgi.framework_JEE.slot.domain.entities.Slot;
import com.esgi.framework_JEE.slot.domain.repository.SlotRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

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


    @Test
    public void should_find_by_start() {
        var pc = slotRepository.findAllByStartSlot("09/05/2012 11:00");
        assertThat(pc).isEmpty();
    }

    @Test
    public void should_find_by_start2() {
        var slot = new Slot();
        slot.setStartSlot("09/05/2012 11:00");
        slot.setEndSlot("09/05/2013 11:00");
        slotRepository.save(slot);
        var response = slotRepository.findAllByStartSlot("09/05/2012 11:00");
        assertThat(response).contains(slot);
    }
}
package com.esgi.framework_JEE.use_case.slot.validation;

import java.text.ParseException;
import com.esgi.framework_JEE.use_case.slot.domain.entities.Slot;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SlotValidationServiceTest {
    SlotValidationService slotValidationService = new SlotValidationService();

    @Test
    void should_be_valid() {
        var slot = new Slot();
        slot.setStartSlot("09/05/2022 09:00");
        slot.setEndSlot("09/05/2022 10:00");

        assertThat(slotValidationService.isValid(slot)).isEqualTo(true);
    }

    @Test
    void should_be_invalid() {
        var slot = new Slot();
        slot.setStartSlot("09/05/2022 09:00");
        slot.setEndSlot("09/05/2022 08:00");
        System.out.println(slot.getStartSlot());
        System.out.println(slot.getEndSlot());
        assertThat(slotValidationService.isValid(slot)).isEqualTo(false);
    }

    @Test
    void should_be_invalid2() {
        var slot = new Slot();
        slot.setStartSlot("");
        slot.setEndSlot("");
        System.out.println(slot.getStartSlot());
        System.out.println(slot.getEndSlot());
        assertThat(slotValidationService.isValid(slot)).isEqualTo(false);
    }

    @Test
    void should_be_invalid3() {
        var slot = new Slot();
        slot.setStartSlot(null);
        slot.setEndSlot(null);
        System.out.println(slot.getStartSlot());
        System.out.println(slot.getEndSlot());
        assertThat(slotValidationService.isValid(slot)).isEqualTo(false);
    }
}
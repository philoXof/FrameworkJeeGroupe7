package com.esgi.framework_JEE.use_case.slot.validation;

import com.esgi.framework_JEE.use_case.slot.domain.entities.Slot;

public class SlotValidationService {

    public boolean isValid(Slot slot){
        var end = slot.getEndSlot();
        var start = slot.getEndSlot();
        return end != null && start != null && !end.before(start);
    }
}

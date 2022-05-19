package com.esgi.framework_JEE.use_case.slot.validation;

import com.esgi.framework_JEE.kernel.date.DateManipulator;
import com.esgi.framework_JEE.use_case.slot.domain.entities.Slot;

import java.util.Date;

public class SlotValidationService {

    public boolean isValid(Slot slot) {
        if(slot.getEndSlot() == null || slot.getStartSlot() == null)
            return false;
        Date end;
        Date start;
        try{
            end = DateManipulator.stringToDate(slot.getEndSlot());
            start = DateManipulator.stringToDate(slot.getStartSlot());
            return !end.before(start) && !start.after(end);
        }catch (Exception e){
            return false;
        }
    }
}

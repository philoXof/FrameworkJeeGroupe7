package com.esgi.framework_JEE.use_case.slot.command;

import com.esgi.framework_JEE.kernel.date.DateManipulator;
import com.esgi.framework_JEE.use_case.slot.domain.entities.Slot;
import com.esgi.framework_JEE.use_case.slot.domain.repository.SlotRepository;
import com.esgi.framework_JEE.use_case.slot.validation.SlotValidationService;
import com.esgi.framework_JEE.use_case.slot.web.request.SlotRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;

@Service
public class SlotCommand {

    @Autowired
    SlotRepository slotRepository;

    SlotValidationService slotValidationService = new SlotValidationService();
    //DateManipulator dateManipulator;

    public Slot create(SlotRequest slotRequest) throws ParseException {
        var slot = new Slot();
        slot.setStartSlot(slotRequest.start);
        slot.setEndSlot(slotRequest.end);
        if(slotValidationService.isValid(slot))
            return slotRepository.save(slot);
        return null;
    }


    public Slot changeStart(int id,SlotRequest request) throws ParseException {
        Optional<Slot> dbSlot = Optional.ofNullable(slotRepository.findById(id));
        if(dbSlot.isPresent() && !request.start.equals("")){
            dbSlot.get().setStartSlot(request.start);
            if(slotValidationService.isValid(dbSlot.get()))
                return slotRepository.save(dbSlot.get());
        }
        return null;
    }

    public Slot changeEnd(int id,SlotRequest request) throws ParseException {
        Optional<Slot> dbSlot = Optional.ofNullable(slotRepository.findById(id));
        if(dbSlot.isPresent() && !request.end.equals("")){
            dbSlot.get().setEndSlot(request.end);
            if(slotValidationService.isValid(dbSlot.get()))
                return slotRepository.save(dbSlot.get());
        }
        return null;
    }

    public void delete(int id){
        Optional<Slot> dbSlot = Optional.ofNullable(slotRepository.findById(id));
        dbSlot.ifPresent(slot -> slotRepository.delete(slot));
    }
}

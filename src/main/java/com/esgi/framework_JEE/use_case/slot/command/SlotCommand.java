package com.esgi.framework_JEE.use_case.slot.command;

import com.esgi.framework_JEE.use_case.slot.domain.entities.Slot;
import com.esgi.framework_JEE.use_case.slot.domain.repository.SlotRepository;
import com.esgi.framework_JEE.use_case.slot.validation.SlotValidationService;
import com.esgi.framework_JEE.use_case.slot.web.request.SlotRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SlotCommand {

    @Autowired
    SlotRepository slotRepository;

    SlotValidationService slotValidationService = new SlotValidationService();

    public Slot create(SlotRequest slotRequest){
        var slot = new Slot();
        slot.setStart(slotRequest.start);
        slot.setEnd(slotRequest.end);
        if(slotValidationService.isValid(slot))
            return slotRepository.save(slot);
        return null;
    }


    public Slot changeStart(int id,SlotRequest request){
        Optional<Slot> dbSlot = Optional.ofNullable(slotRepository.findById(id));
        if(dbSlot.isPresent()){
            dbSlot.get().setStart(request.start);
            if(slotValidationService.isValid(dbSlot.get()))
                return slotRepository.save(dbSlot.get());
        }
        return null;
    }

    public Slot changeEnd(int id,SlotRequest request){
        Optional<Slot> dbSlot = Optional.ofNullable(slotRepository.findById(id));
        if(dbSlot.isPresent()){
            dbSlot.get().setEnd(request.end);
            if(slotValidationService.isValid(dbSlot.get()))
                return slotRepository.save(dbSlot.get());
        }
        return null;
    }

    public void delete(int id){
        Optional<Slot> dbSlot = Optional.ofNullable(slotRepository.findById(id));
        dbSlot.ifPresent(slot -> delete(id));
    }
}

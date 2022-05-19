package com.esgi.framework_JEE.use_case.slot.query;

import com.esgi.framework_JEE.use_case.slot.domain.entities.Slot;
import com.esgi.framework_JEE.use_case.slot.domain.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SlotQuery {
    @Autowired
    SlotRepository slotRepository;

    public SlotQuery(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    public Slot getById(int id){
        return slotRepository.findById(id);
    }

    public List<Slot> getAll(){
        return slotRepository.findAll();
    }

    public List<Slot> findByStartSlot(String start){
        return slotRepository.findAllByStartSlot(start);
    }
}

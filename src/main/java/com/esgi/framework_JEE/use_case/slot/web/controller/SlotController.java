package com.esgi.framework_JEE.use_case.slot.web.controller;

import com.esgi.framework_JEE.use_case.slot.command.SlotCommand;
import com.esgi.framework_JEE.use_case.slot.domain.entities.Slot;
import com.esgi.framework_JEE.use_case.slot.query.SlotQuery;
import com.esgi.framework_JEE.use_case.slot.web.request.SlotRequest;
import com.esgi.framework_JEE.use_case.slot.web.response.SlotResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/slot")
public class SlotController {
    @Autowired
    SlotCommand slotCommand;

    @Autowired
    SlotQuery slotQuery;


    public SlotController(SlotCommand slotCommand, SlotQuery slotQuery) {
        this.slotCommand = slotCommand;
        this.slotQuery = slotQuery;
    }

    
    @PostMapping("/create")
    public ResponseEntity<SlotResponse> create(@RequestBody SlotRequest slotRequest){
        var slot = slotCommand.create(slotRequest);
        if(slot == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(slotToSlotResponse(slot),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<SlotResponse>> getAll(){
        return new ResponseEntity<>(
                listSlotToListSlotResponse(
                        slotQuery.getAll()
                ),
                HttpStatus.OK);
    }

    @GetMapping("/{slotId}")
    public ResponseEntity<SlotResponse> getSlotById(@PathVariable int slotId){
        var slot = slotQuery.getById(slotId);
        if(slot == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(
                slotToSlotResponse(slot),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{slotId}")
    public ResponseEntity<SlotResponse> changeStart(@PathVariable int slotId,@RequestBody SlotRequest slotRequest){
        var slot = slotCommand.changeStart(slotId,slotRequest);
        if(slot == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(slotToSlotResponse(slot), HttpStatus.OK);
    }


    @DeleteMapping("/{slotId}")
    public ResponseEntity<?> deleteSlot(@PathVariable int slotId){
        var slot = slotQuery.getById(slotId);
        if(slot == null)
            return new ResponseEntity<>(
                    "Slot " + slotId + " not exist",
                    HttpStatus.BAD_REQUEST
            );
        slotCommand.delete(slotId);
        return new ResponseEntity<>(
                "Slot " + slotId + " deleted",
                HttpStatus.BAD_REQUEST
        );
    }







    private SlotResponse slotToSlotResponse(Slot slot){
        return new SlotResponse()
                .setId(slot.getId())
                .setStart(slot.getStartSlot())
                .setEnd(slot.getEndSlot());
    }

    private List<SlotResponse> listSlotToListSlotResponse(List<Slot> slots){
        List<SlotResponse> slotResponses = new ArrayList<>();
        slots.forEach(slot -> slotResponses.add(
                slotToSlotResponse(slot))
        );
        return slotResponses;
    }
}

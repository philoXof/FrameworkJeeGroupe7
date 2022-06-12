package com.esgi.framework_JEE.slot.domain.repository;

import com.esgi.framework_JEE.slot.domain.entities.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SlotRepository extends JpaRepository<Slot,Integer> {
    Slot findById(int id);

    List<Slot> findAllByStartSlot(String start);
}

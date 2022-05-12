package com.esgi.framework_JEE.use_case.slot.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "slot")
public class Slot {


    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private int id;

    @DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    @Column(name = "start_slot")
    private String startSlot;

    @DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    @Column(name = "end_slot")
    private String endSlot;

}

package com.esgi.framework_JEE.use_case.slot.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "slot")
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private int id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "start_slot")
    private Date startSlot;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "end_slot")
    private Date endSlot;
}

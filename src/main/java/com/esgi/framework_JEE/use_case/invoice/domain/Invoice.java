package com.esgi.framework_JEE.use_case.invoice.domain;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private String id;
    @Column(name = "amount")
    private Double amount;

    @Column(name = "creationDate")
    private Date creationDate;



    public String getId() {
        return id;
    }

    public Invoice setId(String id) {
        this.id = id;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public Invoice setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Invoice setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }
}

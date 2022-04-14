package com.esgi.framework_JEE.use_case.invoice.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

public class Invoice {

    @Id
    private InvoiceId id;
    @Column(name = "amount")
    private Double amount;

    @Column(name = "creationDate")
    private Date creationDate;

    public Invoice(InvoiceId id, Double amount, Date creationDate) {
        this.id = id;
        this.amount = amount;
        this.creationDate = creationDate;
    }

    public InvoiceId getId() {
        return id;
    }

    public Invoice setId(InvoiceId id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id) && Objects.equals(amount, invoice.amount) && Objects.equals(creationDate, invoice.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, creationDate);
    }
}

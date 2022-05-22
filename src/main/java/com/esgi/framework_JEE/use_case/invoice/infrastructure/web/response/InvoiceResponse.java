package com.esgi.framework_JEE.use_case.invoice.infrastructure.web.response;


import java.util.Date;

public class InvoiceResponse{

    private Double amount;
    private Date creationDate;
    private int user_id;


    public Double getAmount() {
        return amount;
    }

    public InvoiceResponse setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public InvoiceResponse setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public int getUser_id() {
        return user_id;
    }

    public InvoiceResponse setUser_id(int user_id) {
        this.user_id = user_id;
        return this;
    }
}

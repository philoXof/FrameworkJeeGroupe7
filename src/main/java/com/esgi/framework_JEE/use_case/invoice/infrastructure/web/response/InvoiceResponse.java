package com.esgi.framework_JEE.use_case.invoice.infrastructure.web.response;

import java.util.Date;

public class InvoiceResponse{

    private Double amount;
    private Date creationDate;


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
}

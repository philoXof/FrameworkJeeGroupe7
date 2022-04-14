package com.esgi.framework_JEE.use_case.invoice.domain;

import java.util.Objects;
import java.util.UUID;

public class InvoiceId {

    private final String value;

    public InvoiceId(String value) {
        this.value = value;
    }

    public static InvoiceId fromUUID(UUID uuid){
        return new InvoiceId(uuid.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceId invoiceId = (InvoiceId) o;
        return Objects.equals(value, invoiceId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

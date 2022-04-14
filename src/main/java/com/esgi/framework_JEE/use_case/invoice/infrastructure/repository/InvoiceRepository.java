package com.esgi.framework_JEE.use_case.invoice.infrastructure.repository;

import com.esgi.framework_JEE.use_case.invoice.domain.Invoice;
import com.esgi.framework_JEE.use_case.invoice.domain.InvoiceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, InvoiceId> {

    //TODO : Create ???

    Invoice getInvoiceById(InvoiceId id);

    void deleteInvoiceById(InvoiceId id);

}

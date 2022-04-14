package com.esgi.framework_JEE.use_case.invoice.infrastructure.repository;

import com.esgi.framework_JEE.use_case.invoice.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {

    //TODO : Create ???

    Invoice getInvoiceById(String id);

    void deleteInvoiceById(String id);

}

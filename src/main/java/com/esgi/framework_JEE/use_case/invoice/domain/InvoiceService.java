package com.esgi.framework_JEE.use_case.invoice.domain;

import com.esgi.framework_JEE.use_case.invoice.infrastructure.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice create(){
        var invoiceId = new InvoiceId(UUID.randomUUID().toString());
        var invoice = new Invoice(invoiceId, 0.0, new Date());
        invoiceRepository.save(invoice);

        return invoice;
    }

    public Invoice getById(InvoiceId invoiceId){
        return invoiceRepository.getInvoiceById(invoiceId);
    }

    public List<Invoice> getAll(){
        return invoiceRepository.findAll();
    }

    public void delete(InvoiceId invoiceId){
        invoiceRepository.deleteInvoiceById(invoiceId);
    }
}

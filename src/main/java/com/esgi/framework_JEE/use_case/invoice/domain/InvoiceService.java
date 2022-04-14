package com.esgi.framework_JEE.use_case.invoice.domain;

import com.esgi.framework_JEE.use_case.invoice.infrastructure.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice create(){
        var invoice = new Invoice().setAmount(0.0).setCreationDate(new Date());
        invoiceRepository.save(invoice);

        return invoice;
    }

    public Invoice getById(String id){
        return invoiceRepository.getInvoiceById(id);
    }

    public List<Invoice> getAll(){
        return invoiceRepository.findAll();
    }

    public void delete(String id){
        invoiceRepository.deleteInvoiceById(id);
    }
}

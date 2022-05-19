package com.esgi.framework_JEE.use_case.invoice.domain;

import com.esgi.framework_JEE.use_case.invoice.infrastructure.repository.InvoiceRepository;
import com.esgi.framework_JEE.use_case.user.Domain.entities.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice createEmpty(){
        var invoice = new Invoice().setAmount(0.0).setCreationDate(new Date());
        invoiceRepository.save(invoice);

        return invoice;
    }

    public Invoice generateWithUser(User user){
        var invoice = new Invoice()
                .setAmount(0.0)
                .setCreationDate(new Date())
                .setUser(user);

        invoiceRepository.save(invoice);
        return invoice;
    }

    public Invoice getById(int id){
        return invoiceRepository.getInvoiceById(id);
    }

    public List<Invoice> getAll(){
        return invoiceRepository.findAll();
    }

    public void delete(int id){
        invoiceRepository.deleteById(id);
    }
}

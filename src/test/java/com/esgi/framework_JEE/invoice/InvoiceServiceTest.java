package com.esgi.framework_JEE.invoice;

import com.esgi.framework_JEE.use_case.User.Domain.entities.User;
import com.esgi.framework_JEE.use_case.invoice.domain.Invoice;
import com.esgi.framework_JEE.use_case.invoice.domain.InvoiceService;
import com.esgi.framework_JEE.use_case.invoice.infrastructure.repository.InvoiceRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@DataJpaTest
@SpringBootTest
public class InvoiceServiceTest {

    @MockBean
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceService invoiceService;// = new InvoiceService(invoiceRepository);
    User user;



    @Before
    public void init(){
        //invoiceRepository = new InvoiceRepository();
        invoiceService = new InvoiceService(invoiceRepository);

        user = new User();
        user.setEmail("test@gmail.com");
        user.setFirstname("kelyan");
        user.setLastname("bervin");
        user.setPassword("azertyuiop");
    }



    @Test
    public void shouldCreateEmptyInvoice(){
        var invoiceCreate = invoiceService.createEmpty();

        var invoice = new Invoice().setAmount(0.0);

        assertEquals(invoiceCreate, invoice);
    }

    @Test
    public void shouldGenerateInvoiceWithUserId(){
        var invoiceCreate = invoiceService.generateWithUser(user);

        assertEquals(invoiceCreate.getUser(), user);
    }

    @Test
    public void shouldGetInvoiceById(){
        var invoiceCreate = invoiceService.createEmpty();

        var invoiceGetById = invoiceService.getById(invoiceCreate.getId());

        assertEquals(invoiceCreate, invoiceGetById);
    }

    @Test
    public void shouldGetAllInvoice(){
        var invoice = new Invoice()
                .setAmount(0.0)
                .setCreationDate(new Date());

        var invoice1 = new Invoice()
                .setAmount(0.0)
                .setCreationDate(new Date());

        var invoice2 = new Invoice()
                .setAmount(0.0)
                .setCreationDate(new Date());

        invoiceRepository.save(invoice);
        invoiceRepository.save(invoice1);
        invoiceRepository.save(invoice2);

        var invoiceSS = new ArrayList<Invoice>();
        invoiceSS.add(invoice);
        invoiceSS.add(invoice1);
        invoiceSS.add(invoice2);


        var invoices = invoiceRepository.findAll();
        assertThat(invoices).isIn(invoiceSS);
    }

    @Test
    public void shouldDeleteInvoice(){

        var invoiceCreate = invoiceService.createEmpty();

        invoiceService.delete(invoiceCreate.getId());

        var invoiceGetById = invoiceService.getById(invoiceCreate.getId());


        if(invoiceGetById == null) {
            assert (true);
        } else {
            fail("Should be null");
        }
    }

}

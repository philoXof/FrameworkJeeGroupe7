package com.esgi.framework_JEE.invoice;

import com.esgi.framework_JEE.use_case.invoice.domain.Invoice;
import com.esgi.framework_JEE.use_case.invoice.infrastructure.repository.InvoiceRepository;
import com.esgi.framework_JEE.use_case.user.Domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
class InvoiceRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    InvoiceRepository invoiceRepository;
    User user = new User();

    @BeforeEach
    public void setup(){
        user.setEmail("mail@mail.fr");
        user.setFirstname("Jean");
        user.setLastname("Pierre");
        user.setPassword("amezfjzlkvokl");
    }


    @Test
    public void shouldFindEmpty(){
        var invoices = invoiceRepository.findAll();
        assertThat(invoices).isEmpty();
    }

    @Test
    public void shouldFindNoInvoiceInEmptyRepository(){
        var invoices = invoiceRepository.findAll();

        assertThat(invoices).size().isEqualTo(0);
    }


    @Test
    public void shouldGetAllInvoice(){
        var invoice1 = new Invoice()
                .setAmount(101.1)
                .setCreationDate(new Date())
                .setUser(user);

        invoiceRepository.save(invoice1);

        var invoice2 = new Invoice()
                .setAmount(102.2)
                .setCreationDate(new Date())
                .setUser(user);

        invoiceRepository.save(invoice2);

        var invoice3 = new Invoice()
                .setAmount(103.3)
                .setCreationDate(new Date())
                .setUser(user);

        invoiceRepository.save(invoice3);


        var invoices = invoiceRepository.findAll();

        assertThat(invoices).hasSize(3).contains(invoice1,invoice2,invoice3);
    }


    @Test
    public void shouldGetInvoiceById(){

        var invoice = new Invoice()
                .setAmount(103.3)
                .setCreationDate(new Date());

        var invoiceCreated = entityManager.persist(invoice);

        var invoiceFind = invoiceRepository.findById(invoiceCreated.getId());
        assertThat(invoiceFind).hasValue(invoiceCreated);
    }


    @Test
    public void shouldDeleteInvoiceById(){
        var invoice1 = new Invoice();
        entityManager.persist(invoice1);

        var invoice2 = new Invoice();
        entityManager.persist(invoice2);

        invoiceRepository.deleteAll();

        assertThat(invoiceRepository.findAll()).isEmpty();
    }



}

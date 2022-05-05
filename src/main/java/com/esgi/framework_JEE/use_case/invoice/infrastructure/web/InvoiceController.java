package com.esgi.framework_JEE.use_case.invoice.infrastructure.web;

import com.esgi.framework_JEE.use_case.invoice.domain.Invoice;
import com.esgi.framework_JEE.use_case.invoice.domain.InvoiceService;
import com.esgi.framework_JEE.use_case.invoice.infrastructure.web.response.InvoiceResponse;
import com.esgi.framework_JEE.use_case.user.query.UserQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final UserQuery userQuery;

    public InvoiceController(InvoiceService invoiceService, UserQuery userQuery) {
        this.invoiceService = invoiceService;
        this.userQuery = userQuery;
    }


    @PostMapping
    public ResponseEntity<?> create(){
        var invoiceCreated = invoiceService.createEmpty();

        return ResponseEntity.created(
                linkTo(
                        methodOn(InvoiceController.class).getById(invoiceCreated.getId())
                ).toUri()
        ).build();
    }


    @PostMapping("/generate/{user_id}")
    public ResponseEntity<?> generateInvoice(@PathVariable int user_id){
        var user = userQuery.getById(user_id);
        if(user == null){
            return new ResponseEntity<>(" User not found", HttpStatus.NOT_FOUND);
        }

        var invoiceCreated = invoiceService.generateWithUser(user);

        return ResponseEntity.created(
                linkTo(
                        methodOn(InvoiceController.class).getById(invoiceCreated.getId())
                ).toUri()
        ).build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponse> getById(@PathVariable int id){
        var invoice = invoiceService.getById(id);

        return new ResponseEntity<>(toResponse(invoice), HttpStatus.FOUND);
    }

    //TODO: Get Invoices By user_id

    @GetMapping
    public ResponseEntity<List<InvoiceResponse>> getAll(){
        var invoiceResponses = invoiceService.getAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(invoiceResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id){

        var invoiceToDelete = invoiceService.getById(id);
        if(invoiceToDelete == null){
            return new ResponseEntity<>(" Invoice not found", HttpStatus.NOT_FOUND);
        }

        //TODO :
        // 1 - Chercher tous les produits de la factures (getAllProductByInvoiceId)
        // 2 - Supprimer l'id de la facture dans les produits

        invoiceService.delete(id);

        return new ResponseEntity<>("Invoice deleted", HttpStatus.OK);
    }


    private InvoiceResponse toResponse(Invoice invoice){
        var invoiceResponse =  new InvoiceResponse()
                .setAmount(invoice.getAmount())
                .setCreationDate(invoice.getCreationDate());

        if(invoice.getUser() != null) {
            invoiceResponse.setUser_id(invoice.getUser().getId());
        }

        return invoiceResponse;
    }
}

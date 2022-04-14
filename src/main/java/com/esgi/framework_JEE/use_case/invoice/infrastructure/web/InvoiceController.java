package com.esgi.framework_JEE.use_case.invoice.infrastructure.web;

import com.esgi.framework_JEE.use_case.invoice.domain.Invoice;
import com.esgi.framework_JEE.use_case.invoice.domain.InvoiceService;
import com.esgi.framework_JEE.use_case.invoice.infrastructure.web.response.InvoiceResponse;
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

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    @PostMapping
    public ResponseEntity<?> create(){
        var invoiceCreated = invoiceService.create();

        return ResponseEntity.created(
                linkTo(
                        methodOn(InvoiceController.class).getById(invoiceCreated.getId())
                ).toUri()
        ).build();
    }

    /*
    @PostMapping("/generate")
    public ResponseEntity<Invoice> generateInvoice(@RequestBody List<String> productIdList){
        //for (String productId: productIdList) {
            //est ce que l'id existe
        //}

        //créer une facture par ID

        //return laListe
    }
    */


    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponse> getById(@PathVariable int id){
        var invoice = invoiceService.getById(id);

        return new ResponseEntity<>(toResponse(invoice), HttpStatus.FOUND);
    }

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
        return new InvoiceResponse()
                .setAmount(invoice.getAmount())
                .setCreationDate(invoice.getCreationDate());
    }
}

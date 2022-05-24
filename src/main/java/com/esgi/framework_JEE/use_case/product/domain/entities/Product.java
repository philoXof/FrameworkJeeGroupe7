package com.esgi.framework_JEE.use_case.product.domain.entities;

import com.esgi.framework_JEE.use_case.basket.domain.Basket;
import com.esgi.framework_JEE.use_case.invoice.domain.Invoice;
import com.esgi.framework_JEE.use_case.product_category.domain.entities.ProductCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private int id;
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

    @Column(name = "nutriscore", nullable = true)
    private String nutriscore;
/*
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;*/

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;


}

package com.esgi.framework_JEE.product.domain.entities;


import com.esgi.framework_JEE.product_category.domain.entities.ProductCategory;
import com.esgi.framework_JEE.basket.domain.Basket;
import com.esgi.framework_JEE.invoice.domain.Invoice;
import com.esgi.framework_JEE.visited_product.domain.entities.VisitedProduct;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "products")
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "visited_product_id")
    private VisitedProduct visitedProduct;

}

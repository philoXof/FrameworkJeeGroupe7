package com.esgi.framework_JEE.use_case.Product.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "products")
public final class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private int id;

    @Column(name = "ean")
    private String ean;

    @Column(name = "name")
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "nutriscore", nullable = true)
    private String nutriscore;

    @Column(name = "category_id")
    private int categoryId;

}

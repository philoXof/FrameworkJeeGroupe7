package com.esgi.framework_JEE.use_case.product_category.web.response;

public class ProductCategoryResponse {
    public int id;
    public String name;

    public ProductCategoryResponse() {
    }

    public int getId() {
        return id;
    }

    public ProductCategoryResponse setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductCategoryResponse setName(String name) {
        this.name = name;
        return this;
    }
}

package com.esgi.framework_JEE.product_category.validation;

import com.esgi.framework_JEE.product_category.domain.entities.ProductCategory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductCategoryValidationTest {
    ProductCategoryValidation productCategoryValidation = new ProductCategoryValidation();

    @Test
    void should_be_valid() {
        var productCategory = new ProductCategory();
        productCategory.setName("name");
        assertThat(productCategoryValidation.isValid(productCategory)).isEqualTo(true);
    }

    @Test
    void should_be_invalid() {
        var productCategory = new ProductCategory();
        productCategory.setName("");
        assertThat(productCategoryValidation.isValid(productCategory)).isEqualTo(false);
    }
}
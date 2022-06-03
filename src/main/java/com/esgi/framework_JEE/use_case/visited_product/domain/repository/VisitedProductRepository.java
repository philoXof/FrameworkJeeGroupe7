package com.esgi.framework_JEE.use_case.visited_product.domain.repository;

import com.esgi.framework_JEE.use_case.product.domain.entities.Product;
import com.esgi.framework_JEE.use_case.product_category.domain.entities.ProductCategory;
import com.esgi.framework_JEE.use_case.user.Domain.entities.User;
import com.esgi.framework_JEE.use_case.visited_product.domain.entities.VisitedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitedProductRepository extends JpaRepository<VisitedProduct, Integer> {
    VisitedProduct getVisitedProductByProductAndUser(Product product, User user);
}

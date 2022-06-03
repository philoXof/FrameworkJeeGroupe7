package com.esgi.framework_JEE.visited_product.domain.repository;

import com.esgi.framework_JEE.product.domain.entities.Product;
import com.esgi.framework_JEE.user.Domain.entities.User;
import com.esgi.framework_JEE.visited_product.domain.entities.VisitedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitedProductRepository extends JpaRepository<VisitedProduct, Integer> {
    VisitedProduct getVisitedProductByProductAndUser(Product product, User user);
}

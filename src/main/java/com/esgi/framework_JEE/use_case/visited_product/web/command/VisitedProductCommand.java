package com.esgi.framework_JEE.use_case.visited_product.web.command;

import com.esgi.framework_JEE.use_case.product.domain.entities.Product;
import com.esgi.framework_JEE.use_case.product.web.query.ProductQuery;
import com.esgi.framework_JEE.use_case.user.Domain.entities.User;
import com.esgi.framework_JEE.use_case.user.query.UserQuery;
import com.esgi.framework_JEE.use_case.visited_product.domain.entities.VisitedProduct;
import com.esgi.framework_JEE.use_case.visited_product.domain.repository.VisitedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisitedProductCommand {

    @Autowired
    VisitedProductRepository visitedProductRepository;

    public VisitedProduct saveVisitedProduct(Product product, User user) {

        VisitedProduct alreadyVisitedProduct = visitedProductRepository.getVisitedProductByProductAndUser(product, user);

        if(alreadyVisitedProduct != null){
            visitedProductRepository.delete(alreadyVisitedProduct);
        }

        VisitedProduct visitedProduct = new VisitedProduct();
        visitedProduct.setProduct(product);
        visitedProduct.setUser(user);
        return visitedProductRepository.save(visitedProduct);
    }

}

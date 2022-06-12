package com.esgi.framework_JEE.visited_product.web.command;

import com.esgi.framework_JEE.product.domain.entities.Product;
import com.esgi.framework_JEE.user.Domain.entities.User;
import com.esgi.framework_JEE.visited_product.domain.entities.VisitedProduct;
import com.esgi.framework_JEE.visited_product.domain.repository.VisitedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitedProductCommand {

    final
    VisitedProductRepository visitedProductRepository;

    public VisitedProductCommand(VisitedProductRepository visitedProductRepository) {
        this.visitedProductRepository = visitedProductRepository;
    }

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

package com.esgi.framework_JEE.product.web.services;

import com.esgi.framework_JEE.product.domain.entities.Product;
import org.springframework.stereotype.Service;

@Service
public class NutriscoreApiService {
    private String baseUrl = "nutriscoreapifakeurl.pro/service?serviceName=";
    private String serviceProductNutriscore = "productNutriscore";

    public String getProductNutriscore(){
        Product product = new Product();
        product.setNutriscore("E");
        return product.getNutriscore();
    }
}

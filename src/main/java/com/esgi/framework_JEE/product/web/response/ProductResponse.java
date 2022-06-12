package com.esgi.framework_JEE.product.web.response;

import com.esgi.framework_JEE.product_category.domain.entities.ProductCategory;
/*import org.springframework.web.bind.annotation.ResponseBody;*/

/*@ResponseBody*/
public class ProductResponse {
    public String name;
    public Double price;
    public ProductCategory productCategory;
    public String nutriscore;


}

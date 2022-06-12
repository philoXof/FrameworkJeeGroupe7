package com.esgi.framework_JEE.product_category.domain.repository;

import com.esgi.framework_JEE.product_category.domain.entities.ProductCategory;
import com.esgi.framework_JEE.product_category.domain.repository.ProductCategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class ProductCategoryRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    ProductCategoryRepository productCategoryRepository;



    @Test
    public void should_find_empty() {
        var pc = productCategoryRepository.findAll();
        assertThat(pc).isEmpty();
    }


    @Test
    public void should_find_no_productCategory_if_repository_is_empty() {
        var pc = productCategoryRepository.findAll();
        assertThat(pc).size().isEqualTo(0);
    }

    @Test
    public void should_find_all_productCategory() {
        var pc1 = new ProductCategory();
        pc1.setName("1");
        entityManager.persist(pc1);

        var pc2 = new ProductCategory();
        pc2.setName("2");
        entityManager.persist(pc2);

        var pc3 = new ProductCategory();
        pc3.setName("3");
        entityManager.persist(pc3);

        var pcs = productCategoryRepository.findAll();
        assertThat(pcs).hasSize(3).contains(pc1, pc2, pc3);
    }


    @Test
    public void should_find_ProductCategory_by_id() {
        var pc1 = new ProductCategory();
        entityManager.persist(pc1);
        var pc2 = new ProductCategory();
        var pc2created = entityManager.persist(pc2);
        var dbPc = productCategoryRepository.findById(pc2created.getId());
        assertThat(dbPc).isEqualTo(pc2created);
    }

    @Test
    public void should_delete_ProductCategory_by_id() {
        var pc1 = new ProductCategory();
        pc1.setName("name");
        entityManager.persist(pc1);
        var pc2 = new ProductCategory();
        entityManager.persist(pc2);
        var pc3 = new ProductCategory();
        entityManager.persist(pc3);
        productCategoryRepository.deleteById(pc2.getId());
        var pcs = productCategoryRepository.findAll();
        assertThat(pcs).hasSize(2).contains(pc1, pc3);
    }


    @Test
    public void should_delete_all_productCategory() {
        var pc1 = new ProductCategory();
        var pc2 = new ProductCategory();
        entityManager.persist(pc1);
        entityManager.persist(pc2);

        productCategoryRepository.deleteAll();
        assertThat(productCategoryRepository.findAll()).isEmpty();
    }
}
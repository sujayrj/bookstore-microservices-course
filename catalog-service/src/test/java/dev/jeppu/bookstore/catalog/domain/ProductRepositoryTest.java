package dev.jeppu.bookstore.catalog.domain;

import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest(
        properties = {"spring.test.database.replace=none", "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db"})
@Sql("/test-data.sql")
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    void findAllProductsWhenProductsAreAvailable() {
        List<ProductEntity> allProducts = productRepository.findAll();
        Assertions.assertThat(allProducts).isNotNull().hasSize(15);
    }

    @Test
    void findProductByCodeWhenProductExists() {
        Optional<ProductEntity> productEntity = productRepository.findByCode("P100");
        Assertions.assertThat(productEntity).isPresent();
        Assertions.assertThat(productEntity.get().getCode()).isEqualTo("P100");
        Assertions.assertThat(productEntity.get().getName()).isEqualTo("The Hunger Games");
    }

    @Test
    void findProductByCodeWhenProductDoesntExist() {
        Optional<ProductEntity> actualProductEntity = productRepository.findByCode("P1000");
        Assertions.assertThat(actualProductEntity).isNotPresent();
    }
}

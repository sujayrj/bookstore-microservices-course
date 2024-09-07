package dev.jeppu.bookstore.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Import;

@Import({ContainersConfig.class})
public class TestCatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(CatalogServiceApplication::main)
                .with(TestCatalogServiceApplication.class)
                .run(args);
    }
}

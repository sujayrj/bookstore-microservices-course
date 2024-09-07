package dev.jeppu.bookstore.catalog.domain;

public class ProductMapper {
    private ProductMapper() {}

    public static ProductDTO mapProductEntityToProductDTO(ProductEntity productEntity) {
        return new ProductDTO(
                productEntity.getCode(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getImageUrl(),
                productEntity.getPrice());
    }
}

package dev.jeppu.bookstore.catalog.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductEntity {
    @Id
    @SequenceGenerator(name = "product_id_sequence", sequenceName = "product_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_sequence")
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    @NotEmpty(message = "Product Code cannot be blank")
    private String code;

    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Product name cannot be blank")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "price", nullable = false)
    @NotEmpty(message = "Product price cannot be blank")
    @NotNull(message = "Product price cannot be null") @DecimalMin(value = "0.1")
    private BigDecimal price;
}

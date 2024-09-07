package dev.jeppu.bookstore.catalog.web;

import dev.jeppu.bookstore.catalog.AbstractTest;
import dev.jeppu.bookstore.catalog.domain.ProductDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-data.sql")
class ProductControllerTest extends AbstractTest {
    @Test
    void testFindAllProductsWhenExists() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("totalElements", Matchers.is(15))
                .body("totalPages", Matchers.is(2))
                .body("pageNumber", Matchers.is(1))
                .body("numberOfElements", Matchers.is(10))
                .body("isFirst", Matchers.is(true))
                .body("isLast", Matchers.is(false))
                .body("hasNext", Matchers.is(true))
                .body("hasPrevious", Matchers.is(false));
    }

    @Test
    void testFindProductWhenExists() {
        ProductDTO actualProductDTO = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/products/P100")
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(ProductDTO.class);
        Assertions.assertThat(actualProductDTO).isNotNull();
        Assertions.assertThat(actualProductDTO.code()).isEqualTo("P100");
        Assertions.assertThat(actualProductDTO.name()).isEqualTo("The Hunger Games");
        Assertions.assertThat(actualProductDTO.description())
                .isEqualTo("Winning will make you famous. Losing means certain death...");
    }

    @Test
    void testFindProductWhenNotExists() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/products/P100111")
                .then()
                .statusCode(404)
                .body("title", Matchers.is("Product Not Found"))
                .body("serviceName", Matchers.is("Catalog Service"))
                .body("error_message", Matchers.is("Product with code P100111 not found"));
    }
}

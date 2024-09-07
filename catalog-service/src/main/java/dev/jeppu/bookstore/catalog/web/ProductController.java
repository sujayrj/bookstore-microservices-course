package dev.jeppu.bookstore.catalog.web;

import dev.jeppu.bookstore.catalog.domain.PagedRequest;
import dev.jeppu.bookstore.catalog.domain.ProductDTO;
import dev.jeppu.bookstore.catalog.domain.ProductNotFoundException;
import dev.jeppu.bookstore.catalog.domain.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public PagedRequest<ProductDTO> getAllProducts(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo) {
        return productService.getAllProducts(pageNo);
    }

    @GetMapping("/{code}")
    public ProductDTO getProductByCode(@PathVariable(value = "code") String code) {
        return productService.getProductByCode(code).orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}

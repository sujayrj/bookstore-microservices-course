package dev.jeppu.bookstore.catalog.domain;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public PagedRequest<ProductDTO> getAllProducts(int pageNo) {
        pageNo = pageNo > 1 ? pageNo - 1 : 0;
        Sort defaultSort = Sort.by("code");
        Pageable pageable = PageRequest.of(pageNo, 10, defaultSort);
        Page<ProductEntity> productEntityPage = productRepository.findAll(pageable);
        List<ProductDTO> productDTOList = productEntityPage.stream()
                .map(ProductMapper::mapProductEntityToProductDTO)
                .toList();
        return new PagedRequest<>(
                productEntityPage.getTotalElements(),
                productEntityPage.getTotalPages(),
                productEntityPage.getNumber() + 1,
                productEntityPage.getNumberOfElements(),
                productEntityPage.isFirst(),
                productEntityPage.isLast(),
                productEntityPage.hasNext(),
                productEntityPage.hasPrevious(),
                productDTOList);
    }

    public Optional<ProductDTO> getProductByCode(String code) {
        return productRepository.findByCode(code).map(ProductMapper::mapProductEntityToProductDTO);
    }
}

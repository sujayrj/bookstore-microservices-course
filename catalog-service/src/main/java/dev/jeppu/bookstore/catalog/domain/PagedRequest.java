package dev.jeppu.bookstore.catalog.domain;

import java.util.List;

public record PagedRequest<T>(
        long totalElements,
        int totalPages,
        int pageNumber,
        int numberOfElements,
        boolean isFirst,
        boolean isLast,
        boolean hasNext,
        boolean hasPrevious,
        List<T> data) {}

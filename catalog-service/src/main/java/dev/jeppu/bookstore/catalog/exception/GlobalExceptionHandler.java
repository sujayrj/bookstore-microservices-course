package dev.jeppu.bookstore.catalog.exception;

import dev.jeppu.bookstore.catalog.domain.ProductNotFoundException;
import java.net.URI;
import java.time.Instant;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final URI NOT_FOUND_TYPE = URI.create("https://api.bookstore.com/errors/not-found");
    private static final URI ISE_TYPE = URI.create("https://api.bookstore.com/errors/ise");
    private static final String SERVICE_NAME = "Catalog Service";

    @ExceptionHandler(ProductNotFoundException.class)
    public ProblemDetail handleProductNotFoundException(ProductNotFoundException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
        problemDetail.setTitle("Product Not Found");
        problemDetail.setProperty("serviceName", SERVICE_NAME);
        problemDetail.setType(NOT_FOUND_TYPE);
        problemDetail.setProperty("timestamp", Instant.now().toString());
        problemDetail.setProperty("error_category", "Generic");
        problemDetail.setProperty("error_message", exception.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception exception) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        problemDetail.setType(ISE_TYPE);
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setProperty("serviceName", SERVICE_NAME);
        problemDetail.setProperty("timestamp", Instant.now().toString());
        problemDetail.setProperty("error_category", "Generic");
        problemDetail.setProperty("error_message", exception.getMessage());
        problemDetail.setProperty("stacktrace", Arrays.toString(exception.getStackTrace()));
        return problemDetail;
    }
}

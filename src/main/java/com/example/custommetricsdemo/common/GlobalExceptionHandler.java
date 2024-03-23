package com.example.custommetricsdemo.common;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final MeterRegistry meterRegistry;

    @Autowired
    public GlobalExceptionHandler(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public final ResponseEntity<Error> handleDataIntegrityViolation(final Exception exception, final WebRequest request) {
        Counter counter = Counter.builder("api.orders.failed.count")
                .description("Total number of failed orders")
                .register(meterRegistry);
        counter.increment();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new Error("Failed to create order")
        );
    }
}

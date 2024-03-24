package com.example.custommetricsdemo.metrics;

import com.example.custommetricsdemo.infrastructure.OrderRepo;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderGaugeConfiguration {
    private final OrderRepo orderRepo;

    @Autowired
    public OrderGaugeConfiguration(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Bean
    public Gauge createOrderCountMetric(MeterRegistry meterRegistry) {
        return Gauge.builder("api.orders.created.count", orderRepo::count)
                .description("Total number of orders created")
                .register(meterRegistry);
    }

}

package com.example.custommetricsdemo.metrics;

import com.example.custommetricsdemo.infrastructure.Order;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OrderTimerAspect {
    private final MeterRegistry meterRegistry;

    @Autowired
    public OrderTimerAspect(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Around("execution(* com.example.custommetricsdemo.service.OrderService.createOrder(..))")
    public Order timerExecution(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Timer.Sample timer = Timer.start(meterRegistry);
        try {
            Order order = (Order) proceedingJoinPoint.proceed();
            timer.stop(Timer.builder("api.orders.created.timer")
                    .description("order creation timer")
                    .register(meterRegistry));
            return order;
        } catch (Exception e) {
            timer.stop(Timer.builder("api.orders.failed.timer")
                    .description("order creation timer")
                    .register(meterRegistry));
            throw e;
        }
    }
}

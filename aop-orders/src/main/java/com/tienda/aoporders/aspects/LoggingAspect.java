package com.tienda.aoporders.aspects;

import com.tienda.aoporders.orders.Order;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("@annotation(com.tienda.aoporders.annotations.Auditable)")
    public Object auditProcess(ProceedingJoinPoint joinPoint) throws Throwable {
        Order order = (Order) joinPoint.getArgs()[0];

        System.out.println("--- Auditoría: Inicio de proceso para Pedido " + order.getId() + " ---");

        long start = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            long time = System.currentTimeMillis() - start;

            System.out.println("[PERFORMANCE] Pedido " + order.getId() + " procesado en " + time + " ms");

            System.out.println("--- Auditoría: Fin de proceso para Pedido " + order.getId() + " ---");
            return result;

        } catch (Exception e) {
            System.out.println("[ERROR] Pedido " + order.getId() + " falló: " + e.getMessage());
            throw e;
        }
    }
}


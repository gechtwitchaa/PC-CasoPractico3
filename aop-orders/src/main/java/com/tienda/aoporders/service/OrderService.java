package com.tienda.aoporders.service;

import com.tienda.aoporders.annotations.Auditable;
import com.tienda.aoporders.orders.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderService {

    private final Random random = new Random();

    @Async
    @Auditable
    public CompletableFuture<Order> processOrder(Order order) throws Exception {

        System.out.println("[INFO] Pedido " + order.getId()
                + " recibido para el cliente: " + order.getCustomerName());

        // Simular procesamiento
        Thread.sleep(1500 + random.nextInt(2000));

        // Simular errores aleatorios
        if (random.nextInt(10) < 2) { // 20% de fallos
            throw new Exception("Error simulado: Pago rechazado / stock insuficiente");
        }

        return CompletableFuture.completedFuture(order);
    }
}



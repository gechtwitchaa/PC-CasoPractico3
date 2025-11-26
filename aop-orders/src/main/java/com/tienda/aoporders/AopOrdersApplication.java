package com.tienda.aoporders;

import com.tienda.aoporders.orders.Order;
import com.tienda.aoporders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import org.springframework.boot.CommandLineRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@EnableAsync
public class AopOrdersApplication implements CommandLineRunner {

    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(AopOrdersApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("=== INICIO DE SIMULACIÓN DE PEDIDOS ===");

        List<Order> pedidos = List.of(
                new Order(1, 120.50, "Ana López"),
                new Order(2, 89.99, "Carlos Gómez"),
                new Order(3, 42.00, "Marta Ruiz"),
                new Order(4, 59.95, "Diego Torres"),
                new Order(5, 300.00, "Laura Fernández"),
                new Order(6, 185.75, "Pedro Ramírez"),
                new Order(7, 49.90, "Sofía Medina"),
                new Order(8, 10.00, "Juan Pérez"),
                new Order(9, 15.50, "Lucía Vargas"),
                new Order(10, 150.00, "Jorge Castillo")
        );

        List<CompletableFuture<Order>> futures = new ArrayList<>();

        for (Order order : pedidos) {
            futures.add(orderService.processOrder(order));
        }

        // Esperar todos los hilos
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        System.out.println("=== PROCESAMIENTO FINALIZADO ===");
    }
}

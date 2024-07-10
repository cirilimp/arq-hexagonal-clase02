package com.example.hexagonal.domain;

import com.example.hexagonal.domain.model.Order;
import com.example.hexagonal.domain.model.OrderItem;
import com.example.hexagonal.domain.service.OrderService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


// Pruebas unitarias y de integración
@QuarkusTest
class OrderServiceTest {

    @Inject
    OrderService orderService;


    @Test
    public void testCreateOrder() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        assertNotNull(order.getId());
    }

    @Test
    public void testAddItemToOrder() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        OrderItem item = new OrderItem("Product1", 2, new BigDecimal("50.0"));
        orderService.addItemToOrder(order.getId(), item);
        assertEquals(1, orderService.getOrderById(order.getId()).getItems().size());
    }

    @Test
    public void testUpdateOrderStatus() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        orderService.updateOrderStatus(order.getId(), "CONFIRMED");
        assertEquals("CONFIRMED", orderService.getOrderById(order.getId()).getStatus());
    }

}
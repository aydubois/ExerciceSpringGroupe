package com.order.order.controller;

import com.order.order.NotFoundException;
import com.order.order.entity.Order;
import com.order.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.getOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        if(order != null) {
            orderService.addOrder(order);
            return ResponseEntity.status(HttpStatus.OK).body(order);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<Order> editOrder(@RequestBody Order order) throws NotFoundException {
        if(order != null) {
            Order originalOrder = orderService.findOrderById(order.getId());
            orderService.updateOrder(order);
            return ResponseEntity.status(HttpStatus.OK).body(originalOrder);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(path = "/{id}")
    public Order getOrder(@PathVariable Long id) throws NotFoundException {
        return orderService.findOrderById(id);
    }

    @RequestMapping("/search")
    public ResponseEntity<List<Order>> searchOrders(@RequestParam(required = false) String name, @RequestParam(required = false) String city, @RequestParam(required = false) Integer lattitude, @RequestParam(required = false) Integer longitude) {
        List<Order> orders = orderService.findOrdersByParam(name, city, lattitude, longitude);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) throws NotFoundException {
        Order order = orderService.findOrderById(id);
        orderService.removeOrder(order);
        return ResponseEntity.status(HttpStatus.OK).body("Delete");
    }
}

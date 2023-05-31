package com.web.controller;

import com.web.dto.OrderDtoIn;
import com.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public List<OrderDtoIn> getOrder() {
        return orderService.getAllOrder();
    }

    @GetMapping("/orders/{id}")
    private ResponseEntity getOrderById(@PathVariable("id") Long idOrder) {
        OrderDtoIn OrderDtoIn = orderService.getOrder(idOrder);
        if (OrderDtoIn.getIdOrder() == null)
            return (ResponseEntity<String>) ResponseEntity.notFound();
        return ResponseEntity.ok(OrderDtoIn);
    }

    @PostMapping("/orders")
    public OrderDtoIn saveOrder(@RequestBody OrderDtoIn orderDtoIn) {
        orderService.saveOrder(orderDtoIn);
        return orderDtoIn;
    }

    @GetMapping("/find-orders/{id}")
    public List<Object[]> findOrderDetailByOrderId(@PathVariable(name = "id", required = false) Long idOrder) {
        return orderService.findOrderDetailByOrderId(idOrder);
    }

}

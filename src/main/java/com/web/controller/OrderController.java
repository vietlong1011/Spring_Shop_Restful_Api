package com.web.controller;

import com.web.dto.OrderDtoIn;
import com.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> getOrderById(@PathVariable("id") Long idOrder) {
        OrderDtoIn dto = orderService.getOrder(idOrder);
        if (dto.getIdOrder() == null)
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No value present");
        return ResponseEntity.ok(dto);
    }



}

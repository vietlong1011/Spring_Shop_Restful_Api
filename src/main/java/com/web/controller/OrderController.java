package com.web.controller;

import com.web.dto.DtoIn.OrderDtoIn;
import com.web.repository.OrderRepository;
import com.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService OrderService;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/Order")
    public List<OrderDtoIn> getOrder(){
        return OrderService.getAllOrder();
    }

    @GetMapping("/Order/{idOrder}")
    private ResponseEntity getOrderById(@PathVariable("idOrder") Long idOrder){
        OrderDtoIn OrderDtoIn = OrderService.getOrder(idOrder);
        if (OrderDtoIn.getIdOrder() == null)
            return (ResponseEntity<String>) ResponseEntity.notFound();
        return ResponseEntity.ok(OrderDtoIn);
    }

    @PostMapping("/Order")
    public OrderDtoIn saveOrder(@RequestBody OrderDtoIn OrderDtoIn){
        OrderService.saveOrder(OrderDtoIn);
        return OrderDtoIn;
    }

    @GetMapping("/findOrder/{idOrder}")
    public List<Object[]> findOrderDetailByOrderId(@PathVariable(name = "idOrder", required = false) Long idOrder){
        return OrderService.findOrderDetailByOrderId(idOrder);
    }

//    @DeleteMapping("/Order/{idOrder}")
//    public ResponseEntity<String> deleteOrderById(@PathVariable("idOrder") Long idOrder){
//        OrderService.deleteOrderById(idOrder);
////        OrderRepository.deleteById(idOrder); // error
//        if (idOrder == null)
//            return (ResponseEntity<String>) ResponseEntity.notFound();
//        return ResponseEntity.status(HttpStatus.OK).body("delete Order successfully");
//    }
}

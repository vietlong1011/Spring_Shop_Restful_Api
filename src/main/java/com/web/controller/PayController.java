package com.web.controller;

import com.web.dto.OrderDtoIn;
import com.web.dto.UserDtoIn;
import com.web.service.OrderService;
import com.web.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * y tuong cho chuc nang thanh toan: khi khach hang thanh toan se truyen vao idOrder
 * cap nhat trang thai don hang (neu da thanh toan -> thong bao)
 * tru tien khach hang (neu so du k du -> thong bao)
 **/
@RestController
@RequestMapping("/api")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    // thanh toan don hang
    @GetMapping("pay/{idOrder}")
    @Transactional
    public ResponseEntity<?> payOrder(@PathVariable("idOrder") Long idOrder) {
        OrderDtoIn orderDto = orderService.getOrder(idOrder);
        UserDtoIn userDtoIn = null;
        if (orderDto != null) {
            userDtoIn = userService.getUser(orderDto.getIdUser());
        }
        // xu ly cac case giao dich
        if (orderDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay don hang co idOrder " + idOrder);
        } else if (orderDto.getStatus().equals("da thanh toan")) {
            return ResponseEntity.badRequest().body("Don hang nay da duoc thanh toan");
        } else if (userDtoIn.getMoney() < orderDto.getTotal()) {
            return ResponseEntity.badRequest().body("So tien cua ban khong du de thanh toan don hang");
        } else {
            userDtoIn.setMoney(userDtoIn.getMoney() - orderDto.getTotal());
            orderDto.setStatus("da thanh toan");
            userService.saveUser(userDtoIn);
            orderService.saveOrder(orderDto);
            return ResponseEntity.ok(orderDto);
        }
    }


}

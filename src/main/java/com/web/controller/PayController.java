package com.web.controller;

import com.web.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * y tuong cho chuc nang thanh toan: khi khach hang thanh toan se truyen vao idOrder
 * tu do se lay gia tri so du (pay) cua view de thiet lap lai cho so tien cua khach hang
 **/
@RestController
@RequestMapping("/api")
public class PayController {

    @Autowired
    private PayService payService;

    @GetMapping("/pay/{id}")
    public List<?> getViewPay(@PathVariable("id") Long idOrder) throws SQLException {
        return payService.getViewPay(idOrder);
    }

}

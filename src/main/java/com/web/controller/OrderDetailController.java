package com.web.controller;

import java.util.List;

import com.web.service.OrderDetailService;
import com.web.dto.OrderDetailDtoIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/detail")
    public List<OrderDetailDtoIn> getOderDetail() {
        return orderDetailService.getOderDetail();
    }

    /**
     * y tuong : tao ra mot obj cho client nhap thong tin
     * OrderDetail se duoc mapping va set cac gia tri vi no co 2 khoa ngoai duoc 2 table khac tham chieu
     * Cau hinh de lay gia tri cua composite key id
     **/
    @PostMapping("/detail/")
    public OrderDetailDtoIn saveOrderDetail(@RequestParam("idItems") Long idItems,
                                            @RequestParam("idOrder") Long idOrder,
                                            @RequestParam("quantity") Integer quantity) {
        return orderDetailService.saveOrderDetail(idItems, idOrder,quantity);
    }

}

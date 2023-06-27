package com.web.controller;

import java.util.*;

import com.web.dto.ItemsDtoIn;
import com.web.dto.OrderDtoIn;
import com.web.service.ItemsService;
import com.web.service.OrderDetailService;
import com.web.dto.OrderDetailDtoIn;
import com.web.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/detail")
    public List<OrderDetailDtoIn> getOderDetail() {
        return orderDetailService.getOderDetail();
    }


    /**
     * chuc nang dat don hang
     * truyen vao :
     * + PathVariable : idUser
     * + RequestParam : idItems , quantity
     **/
    @GetMapping("/add/{idUser}")
    public ResponseEntity<?> addOrder(@PathVariable("idUser") Long idUser,
                                      @RequestParam Map<Long, Integer> itemQuantities) {
        // tao moi order
        OrderDtoIn dto = new OrderDtoIn();
        dto.setIdOrder(orderService.maxIdOrder() + 1);
        dto.setIdUser(idUser);
        dto.setStatus("chua thanh toan");
        orderService.saveOrder(dto);
        Set<Map.Entry<Long, Integer>> entries = itemQuantities.entrySet();
        // tao moi order_detail theo idOrder o tren
        for (Map.Entry<Long, Integer> entry : entries) {
            String key = String.valueOf(entry.getKey());
            String value = String.valueOf(entry.getValue());
            orderDetailService.saveOrderDetail(Long.valueOf(key), dto.getIdOrder(), Integer.parseInt(value));
            // xoa so luong ben bang items
            ItemsDtoIn items = itemsService.getItems(Long.valueOf(key));
            if (Objects.equals(items.getIdItems(), Long.valueOf(key))) {
                items.setQuantity(items.getQuantity() - 1);
                itemsService.saveItems(items);
            }
        }
        // lay gia tri cua mat hang trong chi tiet hoa don cho hoa don

        dto.setTotal((orderDetailService.getPrinceByIdOrder(dto.getIdOrder())));
        orderService.saveOrder(dto);
        return new ResponseEntity<>("Hoa don dat hang cua ban", HttpStatus.OK);

    }

}




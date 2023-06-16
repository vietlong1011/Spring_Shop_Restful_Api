package com.web.controller;

import java.util.List;
import java.util.Objects;
import com.web.dto.ItemsDtoIn;
import com.web.dto.OrderDtoIn;
import com.web.service.ItemsService;
import com.web.service.OrderDetailService;
import com.web.dto.OrderDetailDtoIn;
import com.web.service.OrderService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
     **/
    @GetMapping("/add/{idUser}/{idOrder}/{idItems}")
    public ResponseEntity<?> addToCart(@PathVariable("idUser") Long idUser,
                                       @PathVariable("idOrder") Long idOrder,
                                       @PathVariable("idItems") Long idItems,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {

        ItemsDtoIn itemsDto;
        itemsDto = itemsService.getItems(idItems);
        Integer quantity = null;
        ResponseEntity<?> re;
        // tao moi hoa don neu chua ton tai
        OrderDtoIn orderDto = orderService.getOrder(idOrder);

        // xu ly ben client
        if (itemsDto.getQuantity() == 0) {
            re = new ResponseEntity<>("So luong hang trong kho khong du", HttpStatus.NOT_FOUND);
            return re;
        } else { //neu ton tai
            boolean found = false;
            Cookie[] cookie = request.getCookies();
            for (Cookie item : cookie) { // xet danh sach cookie , check xem co cookie nay chua
                if (item.getName().equals(String.valueOf(idItems))) {
                    item.setValue(Integer.toString(Integer.parseInt(item.getValue()) + 1)); // neu san pham da ton tan -> +1
                    item.setMaxAge(60 * 60 * 24);
                    response.addCookie(item);
                    quantity = Integer.parseInt(item.getValue());
                    found = true;
                    break;
                }
            }
            if (!found)   //Neu san pham ko co trong cookie,them vao cookie
            {
                String key = String.valueOf(idItems);
                String value = String.valueOf(1);
                Cookie c = new Cookie(key, value);
                c.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(c);
                quantity = 1;
            }
        }
        if (orderDto == null) {
            OrderDtoIn dto = new OrderDtoIn();
            dto.setIdOrder(idOrder);
            dto.setIdUser(idUser);
            dto.setStatus("chua thanh toan");
            orderService.saveOrder(dto);
            // lay gia tri cua mat hang trong chi tiet hoa don cho hoa don
            orderDetailService.saveOrderDetail(idItems, idOrder, 1); // error
            if (Objects.equals(orderService.getOrder(idOrder).getIdOrder(), idOrder)) {
                dto.setTotal((orderDetailService.getPrinceByIdOrder(idOrder)));
            }
            orderService.saveOrder(dto);
            re = new ResponseEntity<>("them mat hang thanh cong", HttpStatus.OK);
            // xoa so luong ben bang items
            ItemsDtoIn items = itemsService.getItems(idItems);
            if (Objects.equals(items.getIdItems(), idItems)) {
                items.setQuantity(items.getQuantity() - 1);
                itemsService.saveItems(items);
            }
        } else {
            orderDetailService.saveOrderDetail(idItems, idOrder, quantity);
            orderService.saveOrder(orderDto);
            orderDto.setTotal((orderDetailService.getPrinceByIdOrder(idOrder)));
            orderService.saveOrder(orderDto);
            re = new ResponseEntity<>("them mat hang thanh cong", HttpStatus.OK);
            // xoa so luong ben bang items
            ItemsDtoIn items = itemsService.getItems(idItems);
            if (Objects.equals(items.getIdItems(), idItems)) {
                items.setQuantity(items.getQuantity() - 1);
                itemsService.saveItems(items);
            }
        }

        return re;
    }
}




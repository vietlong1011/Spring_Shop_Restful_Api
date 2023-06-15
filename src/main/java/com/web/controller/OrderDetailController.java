package com.web.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.web.dto.ItemsDtoIn;
import com.web.dto.OrderDtoIn;
import com.web.entity.Items;
import com.web.entity.Order;
import com.web.service.ItemsService;
import com.web.service.OrderDetailService;
import com.web.dto.OrderDetailDtoIn;
import com.web.service.OrderService;
import com.web.service.UserService;
import com.web.service.items_v1.Audit;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
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

    @Autowired
    private Audit audit;


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
        // Kiểm tra xem có dữ liệu trong bảng "web_ban_hang.items_aud" hay không
        boolean useAuditTable = audit.checkAuditTableExist(idItems); // Hàm checkAuditTableExists() cần được triển khai để kiểm tra sự tồn tại của bảng "web_ban_hang.items_aud"
        System.out.println("useAuditTable " + useAuditTable);
        ItemsDtoIn itemsDto = new ItemsDtoIn();

        if (useAuditTable) {
            // Lấy dữ liệu từ bảng "web_ban_hang.items_aud"
            // itemsDto = (ItemsDtoIn) audit.getItemsAudit(idItems);
            itemsDto = itemsService.getItems(idItems);
        } else {
            // Lấy dữ liệu từ bảng "items"
            itemsDto = itemsService.getItems(idItems);
        }

        Integer quantity = null;
        ResponseEntity<?> re;
        if (itemsDto == null) {
            return new ResponseEntity<>("Sản phẩm không tồn tại", HttpStatus.NOT_FOUND);
        }
        Optional<OrderDtoIn> optionalOrder = Optional.ofNullable(orderService.getOrderByIdUser(idUser));
        Optional<OrderDtoIn> optionalUser = Optional.ofNullable(orderService.findUserByIdOrder(idOrder));
        if (optionalOrder.isEmpty() && ((optionalUser.isPresent()) && !optionalUser.get().getIdUser().equals(idUser))) { // loi o day, no k lay ra duoc gia tri
            return new ResponseEntity<>("Hoa don da tồn tại ", HttpStatus.NOT_FOUND);
        } else {
            // tao moi hoa don neu chua ton tai
            OrderDtoIn orderDto = orderService.getOrder(idOrder);
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
            }


            // xu ly ben client
            if (itemsDto.getQuantity() == 0) {
                re = new ResponseEntity<>("So luong hang trong kho khong du", HttpStatus.NOT_FOUND);
            } else { //neu ton tai
                boolean found = false;
                Cookie[] cookie = request.getCookies();
                for (int i = 0; i < cookie.length; i++) { // xet danh sach cookie , check xem co cookie nay chua
                    if (cookie[i].getName().equals(String.valueOf(idItems))) {
                        cookie[i].setValue(Integer.toString(Integer.parseInt(cookie[i].getValue()) + 1)); // neu san pham da ton tan -> +1
                        cookie[i].setMaxAge(60 * 60 * 24);
                        response.addCookie(cookie[i]);
                        quantity = Integer.parseInt(cookie[i].getValue());
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
                orderDetailService.saveOrderDetail(idItems, idOrder, quantity);
                orderService.saveOrder(orderDto);
                assert orderDto != null;
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
        }

        return re;
    }
}
//dag convert tu list sang obj loi



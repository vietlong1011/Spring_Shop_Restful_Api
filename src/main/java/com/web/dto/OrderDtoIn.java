package com.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class OrderDtoIn {

    private Long idOrder;

    private Long idUser;

    private Date oderDate;

    private Long total;

    private String status;

}

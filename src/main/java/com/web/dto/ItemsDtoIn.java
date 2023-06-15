package com.web.dto;

import lombok.*;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
public class ItemsDtoIn {


    private Long idItems;


    private String nameItems;

    private String made;


    private Long price;


    private int quantity;

    private Date createDate;

}

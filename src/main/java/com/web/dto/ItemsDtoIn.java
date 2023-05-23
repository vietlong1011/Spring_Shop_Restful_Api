package com.web.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
public class ItemsDtoIn {
    @NonNull
    private Long idItems;
    @NonNull
    private String nameItems;

    private String made;
    @NonNull
    private Long price;
    @NonNull
    private int quantity;
    private Date createDate;
}

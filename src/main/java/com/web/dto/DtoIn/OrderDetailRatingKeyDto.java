package com.web.dto.DtoIn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRatingKeyDto {
    private Long idItems;
    private Long idOrder;
}

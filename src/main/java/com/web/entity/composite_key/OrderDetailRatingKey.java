package com.web.entity.composite_key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRatingKey implements Serializable {
    @Column(name = "idItems")
    Long idItems;
    @Column(name = "idOrder")
    Long idOrder;
}

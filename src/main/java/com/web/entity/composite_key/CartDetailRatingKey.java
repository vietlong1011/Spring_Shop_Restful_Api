package com.web.entity.composite_key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class CartDetailRatingKey implements Serializable {
    @Column(name = "idItems")
    Long idItems;
    @Column(name = "idCart")
    Long idCart;
}

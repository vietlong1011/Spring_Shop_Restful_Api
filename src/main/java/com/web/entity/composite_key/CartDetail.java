package com.web.entity.composite_key;

import com.web.entity.Cart;
import com.web.entity.Items;
import jakarta.persistence.*;

@Entity
@Table(name = "CartDetail")
public class CartDetail {
    @EmbeddedId
    private CartDetailRatingKey id;

    @ManyToOne
    @MapsId("idItems")
    @JoinColumn(name = "idItems",foreignKey = @ForeignKey(name = "Items_fk"))
    private Items items;

    @ManyToOne
    @MapsId("idCart")
    @JoinColumn(name = "idCart",foreignKey = @ForeignKey(name = "Cart_fk"))
    private Cart cart;

    @Column(name ="quantity")
    private  int quantity;

}

package com.web.entity.composite_key;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.web.entity.Order;
import com.web.entity.Items;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "order_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @EmbeddedId
    private OrderDetailRatingKey id;

    @ManyToOne
    @MapsId("idItems")
    @JoinColumn(name = "idItems",foreignKey = @ForeignKey(name = "Items_fk"))
    private Items items;

    @ManyToOne
    @MapsId("idOrder")
    @JoinColumn(name = "idOrder",foreignKey = @ForeignKey(name = "Order_fk"))
    @JsonBackReference
    private Order order;

    @Column(name ="prince")
    private Long prince;
    @Column(name ="quantity")
    private  int quantity;



}

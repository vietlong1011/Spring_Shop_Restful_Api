package com.web.entity.composite_key;

import com.fasterxml.jackson.annotation.*;
import com.web.entity.Order;
import com.web.entity.Items;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "order_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable {

        @Serial
        private static final long serialVersionUID = 1905122041950251207L;

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private OrderDetailRatingKey id;

    @ManyToOne
    @MapsId("idItems")
    @JoinColumn(name = "idItems", foreignKey = @ForeignKey(name = "Items_fk"))
    private Items items;

    @JsonIgnore // annotation chuyen cac obj java -> json
    @ManyToOne
    @MapsId("idOrder")
    @JoinColumn(name = "idOrder", foreignKey = @ForeignKey(name = "Order_fk"))
    private Order order;


    @Column(name = "prince")
    private Long prince;

    @Column(name = "quantity")
    private Integer quantity;



}

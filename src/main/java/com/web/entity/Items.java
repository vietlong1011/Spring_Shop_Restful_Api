package com.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Audited
public class Items extends AuditTable{

    private static final long serialVersionUID = -1000119078147252957L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_items")
    private Long idItems;

    @Column(name = "nameItems", nullable = false)
    private String nameItems;

    @Column(name = "made", nullable = false)
    private String made;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Create_Date", nullable = false)
    private Date createDate;

}

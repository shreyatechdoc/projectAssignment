package com.ProjectAssignment.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity(name = "products")
public class Product {
    @Id
   private String p_id;
    @Column(name = "P_Name", length = 50)
    private String productName;
    @Column(name = "p_desc", length = 500)
    private String P_description;
    private int price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
}

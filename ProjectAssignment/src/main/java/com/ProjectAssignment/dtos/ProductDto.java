package com.ProjectAssignment.dtos;

import com.ProjectAssignment.entities.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDto {
    private String p_id;
    private String productName;
    private String P_description;
    private int price;
    private CategoryDto category;
}

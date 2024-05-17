package com.ra.baitapsession18_orm.dto;

import com.ra.baitapsession18_orm.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto
{
    private Integer id;
    private String name;
    private Double price;
    private Integer stock;
    private Boolean status;
    private Integer categoryId;
}
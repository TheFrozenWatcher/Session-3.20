package com.ra.baitapsession18_orm.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product
{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    @NotEmpty(message = "Name can't be empty")
    private String name;
    @Column(name = "price")
    @Min(value = 0, message = "Price must be greater than 0")
    @NotNull
    private Double price;
    @Column(name = "stock")
    @Min(value = 0, message = "Stock can't be negative")
    @NotNull
    private Integer stock;
    @Column(name = "status")
    @NotNull
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
}

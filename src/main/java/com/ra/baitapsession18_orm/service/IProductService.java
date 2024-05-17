package com.ra.baitapsession18_orm.service;

import com.ra.baitapsession18_orm.dto.ProductDto;
import com.ra.baitapsession18_orm.entity.Product;

import java.util.List;

public interface IProductService
{
    List<Product> findAll();

    Product findById(int id);

    boolean save(Product product);

    boolean delete(Integer id);
}

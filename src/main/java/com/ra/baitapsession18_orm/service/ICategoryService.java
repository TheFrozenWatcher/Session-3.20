package com.ra.baitapsession18_orm.service;

import com.ra.baitapsession18_orm.entity.Category;

import java.util.List;

public interface ICategoryService
{
    List<Category> findAll();

    Category findById(int id);

    boolean save(Category category);

    boolean delete(Integer id);
}

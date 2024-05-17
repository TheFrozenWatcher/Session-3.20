package com.ra.baitapsession18_orm.dao;

import com.ra.baitapsession18_orm.entity.Category;

import java.util.List;

public interface ICategoryDao
{
    List<Category> findAll();

    Category findById(int id);

    boolean save(Category category);

    boolean delete(Integer id);
}

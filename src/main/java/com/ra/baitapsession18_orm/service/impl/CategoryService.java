package com.ra.baitapsession18_orm.service.impl;

import com.ra.baitapsession18_orm.dao.ICategoryDao;
import com.ra.baitapsession18_orm.dao.impl.CategoryDao;
import com.ra.baitapsession18_orm.entity.Category;
import com.ra.baitapsession18_orm.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService
{
    private final ICategoryDao categoryDao;

    @Autowired
    public CategoryService(ICategoryDao categoryDao)
    {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> findAll()
    {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(int id)
    {
        return categoryDao.findById(id);
    }

    @Override
    public boolean save(Category category)
    {
        return categoryDao.save(category);
    }

    @Override
    public boolean delete(Integer id)
    {
        return categoryDao.delete(id);
    }
}

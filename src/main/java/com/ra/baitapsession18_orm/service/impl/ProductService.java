package com.ra.baitapsession18_orm.service.impl;

import com.ra.baitapsession18_orm.dao.impl.CategoryDao;
import com.ra.baitapsession18_orm.dao.impl.ProductDao;
import com.ra.baitapsession18_orm.dto.ProductDto;
import com.ra.baitapsession18_orm.entity.Product;
import com.ra.baitapsession18_orm.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService
{
    private final ProductDao productDao;

    public ProductService(ProductDao productDao)
    {
        this.productDao = productDao;
    }

    @Override
    public List<Product> findAll()
    {
        return productDao.findAll();
    }

    @Override
    public Product findById(int id)
    {
        return productDao.findById(id);
    }

    @Override
    public boolean save(Product product)
    {
        return productDao.save(product);
    }

    @Override
    public boolean delete(Integer id)
    {
        return productDao.delete(id);
    }
}

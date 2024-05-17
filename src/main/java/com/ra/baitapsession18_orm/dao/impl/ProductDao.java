package com.ra.baitapsession18_orm.dao.impl;

import com.ra.baitapsession18_orm.dao.IProductDao;
import com.ra.baitapsession18_orm.entity.Category;
import com.ra.baitapsession18_orm.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao implements IProductDao
{
    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDao(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> findAll()
    {
        List<Product> productList = null;
        Session session = sessionFactory.openSession();
        try
        {
            productList = session.createQuery("from Product ").list();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            session.close();
        }
        return productList;
    }

    @Override
    public Product findById(int id)
    {
        Product product = null;
        Session session = sessionFactory.openSession();
        try
        {
            product = session.get(Product.class, id);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            session.close();
        }
        return product;
    }

    @Override
    public boolean save(Product product)
    {
        Session session = sessionFactory.openSession();
        try
        {
            session.beginTransaction();
            if (product.getId() == null)
            {
                session.save(product);
            } else
            {
                session.update(product);
            }
            session.getTransaction().commit();
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally
        {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id)
    {
        Session session = sessionFactory.openSession();
        try
        {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
        } catch (Exception e)
        {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally
        {
            session.close();
        }
        return false;
    }
}

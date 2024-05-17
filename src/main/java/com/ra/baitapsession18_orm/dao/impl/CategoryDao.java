package com.ra.baitapsession18_orm.dao.impl;

import com.ra.baitapsession18_orm.dao.ICategoryDao;
import com.ra.baitapsession18_orm.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDao implements ICategoryDao
{
    private final SessionFactory sessionFactory;

    @Autowired
    public CategoryDao(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Category> findAll()
    {
        List<Category> categorieList = null;
        Session session = sessionFactory.openSession();
        try
        {
            categorieList = session.createQuery("from Category").list();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            session.close();
        }
        return categorieList;
    }


    @Override
    public Category findById(int id)
    {
        Category category = null;
        Session session = sessionFactory.openSession();
        try
        {
            category = session.get(Category.class, id);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            session.close();
        }
        return category;
    }

    @Override
    public boolean save(Category category)
    {
        Session session = sessionFactory.openSession();
        try
        {
            session.beginTransaction();
            if (category.getId() == null)
            {
                session.save(category);
            } else
            {
                session.update(category);
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

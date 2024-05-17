package com.ra.baitapsession18_orm.controller;

import com.ra.baitapsession18_orm.dao.impl.CategoryDao;
import com.ra.baitapsession18_orm.dao.impl.ProductDao;
import com.ra.baitapsession18_orm.entity.Category;
import com.ra.baitapsession18_orm.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController
{
    private final CategoryDao categoryDao;

    @Autowired
    public CategoryController(CategoryDao categoryDao)
    {
        this.categoryDao = categoryDao;
    }

    @GetMapping("/view")
    public String list(Model model)
    {
        model.addAttribute("categories", categoryDao.findAll());
        return "category/list";
    }

    @GetMapping("/add")
    public String viewAdd(Model model)
    {
        model.addAttribute("category", new Category());
        return "category/add";
    }

    @PostMapping("/add")
    public String add(@Validated @ModelAttribute("category") Category category, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            model.addAttribute("category", category);
            return "category/add";
        } else
        {
            categoryDao.save(category);
            return "redirect:/category/view";
        }
    }

    @GetMapping("/edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Integer id)
    {
        model.addAttribute("category", categoryDao.findById(id));
        return "category/edit";
    }

    @PostMapping("/edit")
    public String updateProduct(@Validated @ModelAttribute("category") Category category, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            model.addAttribute("category", category);
            return "category/edit";
        } else
        {
            categoryDao.save(category);
            return "redirect:/category/view";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id)
    {
        categoryDao.delete(id);
        return "redirect:/category/view";
    }
}


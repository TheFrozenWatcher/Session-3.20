package com.ra.baitapsession18_orm.controller;

import com.ra.baitapsession18_orm.dao.impl.CategoryDao;
import com.ra.baitapsession18_orm.dao.impl.ProductDao;
import com.ra.baitapsession18_orm.dto.ProductDto;
import com.ra.baitapsession18_orm.entity.Category;
import com.ra.baitapsession18_orm.entity.Product;
import com.ra.baitapsession18_orm.service.ICategoryService;
import com.ra.baitapsession18_orm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController
{
    private final IProductService productService;
    private final ICategoryService categoryService;

    @Autowired
    public ProductController(IProductService productService, ICategoryService categoryService)
    {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/view")
    public String list(Model model)
    {
        model.addAttribute("products", productService.findAll());
        return "product/list";
    }

    @GetMapping("/add")
    public String viewAdd(Model model)
    {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "product/add";
    }

    @PostMapping("/add")
    public String add(@Validated @ModelAttribute("product") Product product, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            model.addAttribute("product", product);
            model.addAttribute("categories", categoryService.findAll());
            return "product/add";
        } else
        {
            productService.save(product);
            return "redirect:/product/view";
        }
    }

    @GetMapping("/edit/{id}")
    public String viewEdit(Model model, @PathVariable("id") Integer id)
    {
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        return "product/edit";
    }

    @PostMapping("/edit")
    public String updateProduct(@Validated @ModelAttribute("product") Product product, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            model.addAttribute("product", product);
            model.addAttribute("categories", categoryService.findAll());
            return "product/edit";
        } else
        {
            productService.save(product);
            return "redirect:/product/view";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id)
    {
        productService.delete(id);
        return "redirect:/product/view";
    }
}

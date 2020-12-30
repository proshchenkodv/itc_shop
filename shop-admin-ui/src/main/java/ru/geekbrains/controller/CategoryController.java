package ru.geekbrains.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entities.Category;
import ru.geekbrains.exceptions.ResourceNotFoundException;
import ru.geekbrains.services.CategoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class CategoryController {

    private CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String showAllCategories(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/category/add")
    public String addCategory(
            Model model
    ) {
        model.addAttribute("category", new Category());
        return "product_create_form";
    }

    @PostMapping("/category/add")
    public String addProduct(
            @Valid @ModelAttribute Category category,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "category_create_form";
        }
        categoryService.saveOrUpdate(category);
        return "redirect:/categories";
    }

    @GetMapping("/category/edit/{id}")
    public String showProductEditForm(@PathVariable Long id, Model model) {
        Category category = categoryService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category with id: " + id + " doesn't exists (for edit)"));
        model.addAttribute("category", category);
        return "categories";
    }

    @PostMapping("/category/edit")
    public String showEditForm(@ModelAttribute Category category) {
        categoryService.saveOrUpdate(category);
        return "redirect:/categories";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteOneProductById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }
}

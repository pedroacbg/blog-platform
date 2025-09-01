package com.pedroacbg.blog.controllers;

import com.pedroacbg.blog.domain.dto.CategoryDTO;
import com.pedroacbg.blog.domain.dto.CreateCategoryRequest;
import com.pedroacbg.blog.domain.model.Category;
import com.pedroacbg.blog.mappers.CategoryMapper;
import com.pedroacbg.blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listCategories(){
        List<CategoryDTO> categories = categoryService.listCategories()
                .stream().map(category -> categoryMapper.toDTO(category)).toList();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CreateCategoryRequest request){
        Category category = categoryMapper.toEntity(request);
        Category savedCategory =  categoryService.createCategory(category);
        return new ResponseEntity<>(categoryMapper.toDTO(savedCategory), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

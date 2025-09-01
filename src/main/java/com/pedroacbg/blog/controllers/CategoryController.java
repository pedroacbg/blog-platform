package com.pedroacbg.blog.controllers;

import com.pedroacbg.blog.domain.dto.CategoryDTO;
import com.pedroacbg.blog.mappers.CategoryMapper;
import com.pedroacbg.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

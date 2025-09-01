package com.pedroacbg.blog.services;

import com.pedroacbg.blog.domain.model.Category;
import com.pedroacbg.blog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listCategories(){
        return categoryRepository.findAllWithPostCount();
    }
}

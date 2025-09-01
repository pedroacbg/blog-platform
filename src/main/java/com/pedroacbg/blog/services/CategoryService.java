package com.pedroacbg.blog.services;

import com.pedroacbg.blog.domain.model.Category;
import com.pedroacbg.blog.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private Logger logger = LoggerFactory.getLogger(CategoryService.class);

    public List<Category> listCategories(){
        logger.info("Listando todas as categorias...");
        return categoryRepository.findAllWithPostCount();
    }

    @Transactional
    public Category createCategory(Category category){
        logger.info("Verificando se uma categoria com o nome atual existe...");
        if(categoryRepository.existsByNameIgnoreCase(category.getName())){
            throw new IllegalArgumentException("Categoria já cadastrada com o nome: " + category.getName());
        }
        logger.info("Criando a nova categoria...");
        return categoryRepository.save(category);
    }
}

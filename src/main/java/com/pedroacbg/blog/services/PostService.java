package com.pedroacbg.blog.services;

import com.pedroacbg.blog.domain.PostStatus;
import com.pedroacbg.blog.domain.model.Category;
import com.pedroacbg.blog.domain.model.Post;
import com.pedroacbg.blog.domain.model.Tag;
import com.pedroacbg.blog.domain.model.User;
import com.pedroacbg.blog.repositories.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    Logger logger = LoggerFactory.getLogger(PostService.class);

    @Transactional(readOnly = true)
    public List<Post> getAllPosts(Long categoryId, Long tagId){
        if(categoryId != null && tagId != null){
            logger.info("Buscando categoria e tag por id...");
            Category category = categoryService.getCategoryById(categoryId);
            Tag tag = tagService.getTagById(tagId);
            logger.info("Buscando posts...");
            return postRepository.findAllByStatusAndCategoryAndTagsContaining(PostStatus.PUBLICADO, category, tag);
        }

        if(categoryId != null){
            logger.info("Buscando categoria por id...");
            Category category = categoryService.getCategoryById(categoryId);
            logger.info("Buscando posts...");
            return postRepository.findAllByStatusAndCategory(PostStatus.PUBLICADO, category);
        }

        if(tagId != null){
            logger.info("Buscando tag por id...");
            Tag tag = tagService.getTagById(tagId);
            logger.info("Buscando posts...");
            return postRepository.findAllByStatusAndTagsContaining(PostStatus.PUBLICADO, tag);
        }

        logger.info("Buscando posts...");
        return postRepository.findAllByStatus(PostStatus.PUBLICADO);
    }

    public List<Post> getDraftPosts(User user){
        logger.info("Buscando rascunhos...");
        return postRepository.findAllByAuthorAndStatus(user, PostStatus.RASCUNHO);
    }

}

package com.pedroacbg.blog.services;

import com.pedroacbg.blog.domain.CreatePostRequest;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    private static final int WORDS_PER_MINUTE = 200;

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

    @Transactional(readOnly = true)
    public List<Post> getDraftPosts(User user){
        logger.info("Buscando rascunhos...");
        return postRepository.findAllByAuthorAndStatus(user, PostStatus.RASCUNHO);
    }

    @Transactional
    public Post createPost(User user, CreatePostRequest request){
        logger.info("Atribuindo valores...");
        Post newPost = new Post();
        newPost.setTitle(request.getTitle());
        newPost.setContent(request.getContent());
        newPost.setStatus(request.getStatus());
        newPost.setAuthor(user);
        newPost.setReadingTime(calculateReadingTime(request.getContent()));

        logger.info("Relacionando categoria ao post...");
        Category category = categoryService.getCategoryById(request.getCategoryId());
        newPost.setCategory(category);

        logger.info("Relacionando tags ao post...");
        Set<Long> tagIds = request.getTagsId();
        List<Tag> tags = tagService.getTagByIds(tagIds);
        newPost.setTags(new HashSet<>(tags));

        logger.info("Salvando o novo post no banco de dados...");
        return postRepository.save(newPost);
    }

    private Integer calculateReadingTime(String content){
        if(content == null || content.isEmpty()){
            return 0;
        }
        int wordCount = content.trim().split("\\s+").length;
        return (int) Math.ceil((double) wordCount / WORDS_PER_MINUTE);
    }

}

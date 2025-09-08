package com.pedroacbg.blog.services;

import com.pedroacbg.blog.domain.CreatePostRequest;
import com.pedroacbg.blog.domain.PostStatus;
import com.pedroacbg.blog.domain.UpdatePostRequest;
import com.pedroacbg.blog.domain.model.Category;
import com.pedroacbg.blog.domain.model.Post;
import com.pedroacbg.blog.domain.model.Tag;
import com.pedroacbg.blog.domain.model.User;
import com.pedroacbg.blog.repositories.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Transactional
    public Post getPost(Long id){
        logger.info("Buscando post com o id informado...");
        return postRepository.findByIdWithAuthor(id).orElseThrow(() -> new EntityNotFoundException("Nenhum post encontrado com este ID " + id));
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

    @Transactional
    public Post updatePost(Long id, UpdatePostRequest updatePostRequest){
        logger.info("Verificando se o post existe...");
        Post existingPost = postRepository.findByIdWithAuthor(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum post encontrado com o id " + id));

        logger.info("Verificando se o post é do usuário logado...");
        String authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!existingPost.getAuthor().getEmail().equals(authenticatedUsername)) {
            throw new AccessDeniedException("Você não tem permissão para atualizar este post");
        }

        String content = updatePostRequest.getContent();
        existingPost.setTitle(updatePostRequest.getTitle());
        existingPost.setContent(content);
        existingPost.setStatus(updatePostRequest.getStatus());
        existingPost.setReadingTime(calculateReadingTime(content));

        logger.info("Verificando se a categoria está sendo atualizada...");
        Long updatePostRequestCategoryId = updatePostRequest.getCategoryId();
        if(!existingPost.getCategory().getId().equals(updatePostRequestCategoryId)){
            Category newCategory = categoryService.getCategoryById(updatePostRequestCategoryId);
            existingPost.setCategory(newCategory);
        }

        logger.info("Verificando se as tags estão sendo atualizadas...");
        Set<Long> existingTagIds = existingPost.getTags().stream().map(tag -> tag.getId()).collect(Collectors.toSet());
        Set<Long> updatePostRequestTagsId = updatePostRequest.getTagsId();
        if(!existingTagIds.equals(updatePostRequestTagsId)){
            List<Tag> newTags = tagService.getTagByIds(updatePostRequestTagsId);
            existingPost.setTags(new HashSet<>(newTags));
        }

        logger.info("Salvando a atualização no banco de dados...");
        return postRepository.save(existingPost);
    }

    public void deletePost(Long id){
        Post post = getPost(id);

        logger.info("Verificando se o post é do usuário logado...");
        String authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!post.getAuthor().getEmail().equals(authenticatedUsername)) {
            throw new AccessDeniedException("Você não tem permissão para atualizar este post");
        }
        postRepository.delete(post);
    }

    private Integer calculateReadingTime(String content){
        if(content == null || content.isEmpty()){
            return 0;
        }
        int wordCount = content.trim().split("\\s+").length;
        return (int) Math.ceil((double) wordCount / WORDS_PER_MINUTE);
    }

}

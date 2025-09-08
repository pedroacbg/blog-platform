package com.pedroacbg.blog.controllers;

import com.pedroacbg.blog.domain.CreatePostRequest;
import com.pedroacbg.blog.domain.UpdatePostRequest;
import com.pedroacbg.blog.domain.dto.CreatePostRequestDTO;
import com.pedroacbg.blog.domain.dto.PostDTO;
import com.pedroacbg.blog.domain.dto.UpdatePostRequestDTO;
import com.pedroacbg.blog.domain.model.Post;
import com.pedroacbg.blog.domain.model.User;
import com.pedroacbg.blog.mappers.PostMapper;
import com.pedroacbg.blog.services.PostService;
import com.pedroacbg.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostMapper postMapper;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts(@RequestParam(required = false) Long categoryId, @RequestParam(required = false) Long tagId){
        List<Post> posts = postService.getAllPosts(categoryId, tagId);
        List<PostDTO> postsDTO = posts.stream().map(post -> postMapper.toDTO(post)).toList();
        return ResponseEntity.ok(postsDTO);
    }

    @GetMapping(path = "/drafts")
    public ResponseEntity<List<PostDTO>> getDrafts(@RequestAttribute Long userId){
        User loggedInUser = userService.getUserById(userId);
        List<Post> draftPosts = postService.getDraftPosts(loggedInUser);
        List<PostDTO> postDTOS = draftPosts.stream().map(post -> postMapper.toDTO(post)).toList();
        var times = postDTOS.stream().map(draft -> draft.getReadingTime());
        times.forEach(x -> System.out.println(x));
        return ResponseEntity.ok(postDTOS);
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid@RequestBody CreatePostRequestDTO request, @RequestAttribute Long userId){
        User loggedInUser = userService.getUserById(userId);
        CreatePostRequest createPostRequest = postMapper.toCreatePostRequest(request);
        Post createdPost = postService.createPost(loggedInUser, createPostRequest);
        PostDTO createdPostDTO = postMapper.toDTO(createdPost);
        return new ResponseEntity<>(createdPostDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @Valid @RequestBody UpdatePostRequestDTO updatePostRequestDTO){
        UpdatePostRequest updatePostRequest = postMapper.toUpdatePostRequest(updatePostRequestDTO);
        Post updatedPost = postService.updatePost(id, updatePostRequest);
        PostDTO updatedPostDTO = postMapper.toDTO(updatedPost);
        return ResponseEntity.ok(updatedPostDTO);
    }

}

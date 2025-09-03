package com.pedroacbg.blog.controllers;

import com.pedroacbg.blog.domain.dto.PostDTO;
import com.pedroacbg.blog.domain.model.Post;
import com.pedroacbg.blog.mappers.PostMapper;
import com.pedroacbg.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostMapper postMapper;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts(@RequestParam(required = false) Long categoryId, @RequestParam(required = false) Long tagId){
        List<Post> posts = postService.getAllPosts(categoryId, tagId);
        List<PostDTO> postsDTO = posts.stream().map(post -> postMapper.toDTO(post)).toList();
        return ResponseEntity.ok(postsDTO);
    }

}

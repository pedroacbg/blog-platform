package com.pedroacbg.blog.controllers;

import com.pedroacbg.blog.domain.dto.TagResponse;
import com.pedroacbg.blog.domain.model.Tag;
import com.pedroacbg.blog.mappers.TagMapper;
import com.pedroacbg.blog.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags(){
        List<Tag> tags = tagService.getTags();
        List<TagResponse>  tagResponses = tags.stream().map(tag -> tagMapper.toTagResponse(tag)).toList();
        return ResponseEntity.ok(tagResponses);
    }

}

package com.pedroacbg.blog.controllers;

import com.pedroacbg.blog.controllers.docs.TagControllerDocs;
import com.pedroacbg.blog.domain.dto.CreateTagsRequest;
import com.pedroacbg.blog.domain.dto.TagResponse;
import com.pedroacbg.blog.domain.model.Tag;
import com.pedroacbg.blog.mappers.TagMapper;
import com.pedroacbg.blog.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/tags")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Tags", description = "Endpoint de controle de Tags")
public class TagController implements TagControllerDocs {

    @Autowired
    private TagService tagService;

    @Autowired
    private TagMapper tagMapper;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TagResponse>> getAllTags(){
        List<Tag> tags = tagService.getTags();
        List<TagResponse>  tagResponses = tags.stream().map(tag -> tagMapper.toTagResponse(tag)).toList();
        return ResponseEntity.ok(tagResponses);
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TagResponse>> createTags(@RequestBody CreateTagsRequest request){
        List<Tag> savedTags = tagService.createTags(request.getNames());
        List<TagResponse> createdTagResponses = savedTags.stream().map(tag -> tagMapper.toTagResponse(tag)).toList();
        return new ResponseEntity<>(createdTagResponses, HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id){
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }


}

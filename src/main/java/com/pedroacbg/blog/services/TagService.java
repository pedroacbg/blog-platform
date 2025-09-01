package com.pedroacbg.blog.services;

import com.pedroacbg.blog.domain.model.Tag;
import com.pedroacbg.blog.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getTags(){
        return tagRepository.findAllWithPostCount();
    }

}

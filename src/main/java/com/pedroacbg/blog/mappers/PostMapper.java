package com.pedroacbg.blog.mappers;

import com.pedroacbg.blog.domain.CreatePostRequest;
import com.pedroacbg.blog.domain.dto.CreatePostRequestDTO;
import com.pedroacbg.blog.domain.dto.PostDTO;
import com.pedroacbg.blog.domain.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    @Mapping(target = "status", source = "status")
    PostDTO toDTO(Post post);

    CreatePostRequest toCreatePostRequest(CreatePostRequestDTO dto);
}

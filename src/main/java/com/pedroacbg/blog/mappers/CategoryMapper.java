package com.pedroacbg.blog.mappers;

import com.pedroacbg.blog.domain.PostStatus;
import com.pedroacbg.blog.domain.dto.CategoryDTO;
import com.pedroacbg.blog.domain.dto.CreateCategoryRequest;
import com.pedroacbg.blog.domain.model.Category;
import com.pedroacbg.blog.domain.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDTO toDTO(Category category);

    Category toEntity(CreateCategoryRequest createCategoryRequest);

    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts){
        if(posts == null){
            return 0;
        }
        return posts.stream().filter(post -> PostStatus.PUBLICADO.equals(post.getStatus())).count();
    }

}

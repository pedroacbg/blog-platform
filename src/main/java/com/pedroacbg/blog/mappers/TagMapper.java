package com.pedroacbg.blog.mappers;

import com.pedroacbg.blog.domain.PostStatus;
import com.pedroacbg.blog.domain.dto.TagResponse;
import com.pedroacbg.blog.domain.model.Post;
import com.pedroacbg.blog.domain.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    TagResponse toTagResponse(Tag tag);

    @Named("calculatePostCount")
    default Integer calculatePostCount(Set<Post> posts){
        if(posts == null){
            return 0;
        }
        return (int) posts.stream().filter(post -> PostStatus.PUBLICADO.equals(post.getStatus())).count();
    }

}

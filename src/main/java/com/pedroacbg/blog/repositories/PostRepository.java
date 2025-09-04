package com.pedroacbg.blog.repositories;

import com.pedroacbg.blog.domain.PostStatus;
import com.pedroacbg.blog.domain.model.Category;
import com.pedroacbg.blog.domain.model.Post;
import com.pedroacbg.blog.domain.model.Tag;
import com.pedroacbg.blog.domain.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatus status, Category category, Tag tag);
    List<Post> findAllByStatusAndCategory(PostStatus status, Category category);
    List<Post> findAllByStatusAndTagsContaining(PostStatus status, Tag tag);
    List<Post> findAllByStatus(PostStatus status);

    @EntityGraph(attributePaths = {"author", "category", "tags"})
    @Query("SELECT DISTINCT p FROM Post p WHERE p.author = :author AND p.status = :status")
    List<Post> findAllByAuthorAndStatus(@Param("author") User author, @Param("status") PostStatus status);
}

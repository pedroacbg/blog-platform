package com.pedroacbg.blog.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UpdatePostRequest {

    private Long id;
    private String title;
    private String content;
    private Long categoryId;
    private Set<Long> tagsId = new HashSet<>();
    private PostStatus status;

    public UpdatePostRequest() {
    }

    public UpdatePostRequest(Long id, String title, String content, Long categoryId, Set<Long> tagsId, PostStatus status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.tagsId = tagsId;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Set<Long> getTagsId() {
        return tagsId;
    }

    public void setTagsId(Set<Long> tagsId) {
        this.tagsId = tagsId;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UpdatePostRequest that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getContent(), that.getContent()) && Objects.equals(getCategoryId(), that.getCategoryId()) && Objects.equals(getTagsId(), that.getTagsId()) && getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getContent(), getCategoryId(), getTagsId(), getStatus());
    }
}

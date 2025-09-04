package com.pedroacbg.blog.domain.dto;

import com.pedroacbg.blog.domain.PostStatus;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public class PostDTO {

    private Long id;
    private String title;
    private String content;
    private AuthorDTO author;
    private CategoryDTO category;
    private Set<TagResponse> tags;
    private Integer readingTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private PostStatus postStatus;

    public PostDTO() {
    }

    public PostDTO(Long id, String title, String content, AuthorDTO author, CategoryDTO category, Set<TagResponse> tags, Integer readingTime, LocalDateTime createdAt, LocalDateTime updatedAt, PostStatus postStatus) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
        this.tags = tags;
        this.readingTime = readingTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.postStatus = postStatus;
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

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public Set<TagResponse> getTags() {
        return tags;
    }

    public void setTags(Set<TagResponse> tags) {
        this.tags = tags;
    }

    public Integer getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(Integer readingTime) {
        this.readingTime = readingTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PostStatus getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(PostStatus postStatus) {
        this.postStatus = postStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PostDTO postDTO)) return false;
        return Objects.equals(getId(), postDTO.getId()) && Objects.equals(getTitle(), postDTO.getTitle()) && Objects.equals(getContent(), postDTO.getContent()) && Objects.equals(getAuthor(), postDTO.getAuthor()) && Objects.equals(getCategory(), postDTO.getCategory()) && Objects.equals(getTags(), postDTO.getTags()) && Objects.equals(getReadingTime(), postDTO.getReadingTime()) && Objects.equals(getCreatedAt(), postDTO.getCreatedAt()) && Objects.equals(getUpdatedAt(), postDTO.getUpdatedAt()) && getPostStatus() == postDTO.getPostStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getContent(), getAuthor(), getCategory(), getTags(), getReadingTime(), getCreatedAt(), getUpdatedAt(), getPostStatus());
    }
}

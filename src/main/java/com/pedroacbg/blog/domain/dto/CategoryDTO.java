package com.pedroacbg.blog.domain.dto;

import java.util.Objects;

public class CategoryDTO {

    private Long id;
    private String name;
    private long postCount;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id, String name, long postCount) {
        this.id = id;
        this.name = name;
        this.postCount = postCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPostCount() {
        return postCount;
    }

    public void setPostCount(long postCount) {
        this.postCount = postCount;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CategoryDTO that)) return false;
        return getPostCount() == that.getPostCount() && Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPostCount());
    }
}

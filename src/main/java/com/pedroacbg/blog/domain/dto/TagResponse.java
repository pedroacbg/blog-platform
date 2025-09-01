package com.pedroacbg.blog.domain.dto;

import java.util.Objects;

public class TagResponse {

    private Long id;
    private String name;
    private Integer postCount;

    public TagResponse() {
    }

    public TagResponse(Long id, String name, Integer postCount) {
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

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TagResponse that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getPostCount(), that.getPostCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPostCount());
    }
}

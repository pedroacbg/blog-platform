package com.pedroacbg.blog.domain.dto;

import java.util.Objects;

public class AuthorDTO {

    private Long id;
    private String name;

    public AuthorDTO() {
    }

    public AuthorDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AuthorDTO authorDTO)) return false;
        return Objects.equals(getId(), authorDTO.getId()) && Objects.equals(getName(), authorDTO.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}

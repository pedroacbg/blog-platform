package com.pedroacbg.blog.domain.dto;

import com.pedroacbg.blog.domain.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UpdatePostRequestDTO {

    @NotNull(message = "O ID do post é obrigatório")
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(min = 3, max = 200, message = "O título deve conter entre {min} e {max} caracteres")
    private String title;

    @NotBlank(message = "O conteúdo é obrigatório")
    @Size(min = 10, max = 50000, message = "O conteúdo deve conter entre {min} e {max} caracteres")
    private String content;

    @NotNull(message = "O ID da categoria é obrigatório")
    private Long categoryId;

    @Size(max = 10, message = "Máximo de {max} tags permitido")
    private Set<Long> tagsId = new HashSet<>();

    @NotNull(message = "O status é obrigatório")
    private PostStatus status;

    public UpdatePostRequestDTO() {
    }

    public UpdatePostRequestDTO(Long id, String title, String content, Long categoryId, Set<Long> tagsId, PostStatus status) {
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
        if (!(o instanceof UpdatePostRequestDTO that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getContent(), that.getContent()) && Objects.equals(getCategoryId(), that.getCategoryId()) && Objects.equals(getTagsId(), that.getTagsId()) && getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getContent(), getCategoryId(), getTagsId(), getStatus());
    }
}

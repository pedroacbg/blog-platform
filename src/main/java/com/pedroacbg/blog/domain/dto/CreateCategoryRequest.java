package com.pedroacbg.blog.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class CreateCategoryRequest {

    @NotBlank(message = "O nome da categoria é obrigatório")
    @Size(min = 2, max = 50, message = "A categoria deve conter entre {min} e {max} caracteres")
    @Pattern(regexp = "^[\\w\\s-]+$", message = "O nome da categoria deve conter apenas letras, números, espaços e hifens")
    private String name;

    public CreateCategoryRequest() {
    }

    public CreateCategoryRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CreateCategoryRequest that)) return false;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}

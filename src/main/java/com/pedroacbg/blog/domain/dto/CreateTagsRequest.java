package com.pedroacbg.blog.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.Set;

public class CreateTagsRequest {

    @NotEmpty(message = "Pelo menos uma tag é requerida.")
    @Size(max = 10, message = "Maximo de {max} tags permitido.")
    private Set<@Size(min = 2, max = 30, message = "O nome da tag deve estar entre {min} e {max} caracteres.")
                @Pattern(regexp = "^[\\w\\s-]+$", message = "O nome da tag deve conter apenas letras, números, espaços e hifens.") String> names;

    public CreateTagsRequest() {
    }

    public CreateTagsRequest(Set<@Size(min = 2, max = 30, message = "O nome da tag deve estar entre {min} e {max} caracteres.") @Pattern(regexp = "^[\\w\\s-]+$", message = "O nome da tag deve conter apenas letras, números, espaços e hifens.") String> names) {
        this.names = names;
    }

    public Set<
            String> getNames() {
        return names;
    }

    public void setNames(Set<
            String> names) {
        this.names = names;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CreateTagsRequest that)) return false;
        return Objects.equals(getNames(), that.getNames());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNames());
    }
}

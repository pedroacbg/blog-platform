package com.pedroacbg.blog.domain.dto;

import java.util.List;
import java.util.Objects;

public class ApiErrorResponse {

    private int status;
    private String message;
    private List<FieldError> errors;

    public static class FieldError{

        private String field;
        private String message;

        public FieldError() {
        }

        public FieldError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof FieldError that)) return false;
            return Objects.equals(getField(), that.getField()) && Objects.equals(getMessage(), that.getMessage());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getField(), getMessage());
        }
    }

    public ApiErrorResponse() {
    }

    public ApiErrorResponse(int status, String message, List<FieldError> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ApiErrorResponse that)) return false;
        return getStatus() == that.getStatus() && Objects.equals(getMessage(), that.getMessage()) && Objects.equals(getErrors(), that.getErrors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getMessage(), getErrors());
    }
}

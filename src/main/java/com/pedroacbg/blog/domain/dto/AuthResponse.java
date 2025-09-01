package com.pedroacbg.blog.domain.dto;

import java.util.Objects;

public class AuthResponse {

    private String token;
    private long expiresIn;

    public AuthResponse() {
    }

    public AuthResponse(String token, long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AuthResponse that)) return false;
        return getExpiresIn() == that.getExpiresIn() && Objects.equals(getToken(), that.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken(), getExpiresIn());
    }
}

package com.example.backend.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
    public String getTocken(String key, Object value);

    Claims getClaims(String token);

}

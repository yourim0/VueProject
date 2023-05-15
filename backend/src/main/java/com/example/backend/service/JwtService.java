package com.example.backend.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
    public String getToken(String key, Object value);

    Claims getClaims(String token);

    Boolean isValid(String token);

    int getId(String token);


}

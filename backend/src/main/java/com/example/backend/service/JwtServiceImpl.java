package com.example.backend.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtServiceImpl implements JwtService{

    private String secretKey="abc@asdfggggajdkfls;eirnk456jdklfj!!jdkflsijfkemjl";

    @Override
    public String getTocken(String key, Object value) {

        Date expTime = new Date();//토큰의 유효한시간
        expTime.setTime(expTime.getTime() + 1000 * 60 * 5); //5분(milliseconds)
        byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
        Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ","JWT");
        headerMap.put("alg","HS256");

        Map<String,Object> map = new HashMap<>();
        map.put(key, value);

        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
                .setClaims(map)
                .setExpiration(expTime)
                .signWith(signKey, SignatureAlgorithm.HS256);


        return builder.compact(); //입력받은 key와 value를 secret키로 만들어주는 메서드
    }

    @Override
    public Claims getClaims(String token) {
        if(token != null && !"".equals(token)){
            try{
                byte[] secretByteKey = DatatypeConverter.parseBase64Binary(secretKey);
                Key signKey = new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());
                Claims claims = Jwts.parserBuilder().setSigningKey();
            }
        }
        return null;
    }
}

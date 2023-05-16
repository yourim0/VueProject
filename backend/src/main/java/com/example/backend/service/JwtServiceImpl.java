package com.example.backend.service;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("jwtService")
public class JwtServiceImpl implements JwtService{

    private String secretKey="abc@asdfggggajdkfls;eirnk456jdklfj!!jdkflsijfkemjl";

    @Override
    public String getToken(String key, Object value) {

        Date expTime = new Date();//토큰의 유효한시간
        expTime.setTime(expTime.getTime() + 1000 * 60 * 30); //30분(milliseconds)
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
                return Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody();
            }
            catch(ExpiredJwtException e){
                //만료됨
            }
            catch(JwtException e) {
                //유효하지 않음
            }
        }
        return null;
    }

    @Override
    public Boolean isValid(String token) {
        return this.getClaims(token) != null;

    }

    @Override
    public int getId(String token) {
        Claims claims = this.getClaims(token);
        if(claims != null){
            return Integer.parseInt(claims.get("id").toString());
        }
        return 0;
    }
}

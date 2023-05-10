package com.example.backend.controller;

import com.example.backend.entity.Item;
import com.example.backend.entity.Member;
import com.example.backend.repository.ItemRepository;
import com.example.backend.repository.MemberRepository;
import com.example.backend.service.JwtService;
import com.example.backend.service.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class AccountController {


    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/api/account/login")
    public ResponseEntity login(@RequestBody Map<String, String> params,
                                HttpServletResponse res) {
            Member member = memberRepository.findByEmailAndPassword(params.get("email"), params.get("password"));

            if(member != null){
                JwtService jwtService = new JwtServiceImpl();
                int id =  member.getId();
                String token = jwtService.getTocken("id", id);

                Cookie cookie = new Cookie("token",token);
                cookie.setHttpOnly(true);
                cookie.setPath("/");

                res.addCookie(cookie);
                return new ResponseEntity<>(id,HttpStatus.OK);

                }

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}

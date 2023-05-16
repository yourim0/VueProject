package com.example.backend.controller;

import com.example.backend.entity.Cart;
import com.example.backend.entity.Item;
import com.example.backend.repository.CartRepository;
import com.example.backend.repository.ItemRepository;
import com.example.backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CartController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    CartRepository cartRepository;

    @GetMapping("/api/cart/items") //특정 사용자에게 담긴 item을 가져온다
    public ResponseEntity getCartItems(@CookieValue(value="token",required = false) String token){
        if(!jwtService.isValid(token)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        int memberId = jwtService.getId(token);
        List<Cart> carts = cartRepository.findByMemberId(memberId);
        List<Integer> itemIds = carts.stream().map(Cart::getItemId).collect(Collectors.toList());//Stream.toList는 java16 에서 추가됨
        List<Item> items = itemRepository.findByIdIn(itemIds); //상품정보

        return new ResponseEntity<>(items, HttpStatus.OK);
    }


    @PostMapping("/api/cart/items/{itemId}") //특정아이템을 받았을때
    public ResponseEntity pushCartItem(
        @PathVariable("itemId") int itemId,
        @CookieValue(value = "token", required = false) String token
    ) {
            System.out.println("-------itemId post-------");
            if(!jwtService.isValid(token)){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
            int memberId = jwtService.getId(token);
            Cart cart = cartRepository.findByMemberIdAndItemId(memberId, itemId);

        if(cart == null){
            Cart newCart = new Cart();
            System.out.println("memberid");
            newCart.setMemberId(memberId);
            System.out.println("itemid");
            newCart.setItemId(itemId);
            cartRepository.save(newCart); //cart값이 null일 경우
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    
    //장바구니 삭제
        @DeleteMapping("api/cart/items/{itemId}")
        public ResponseEntity removeCartItem(
            @PathVariable("itemId") int itemId,
            @CookieValue(value="token",required = false) String token
        ){
            if(!jwtService.isValid(token)){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }

            int memberId = jwtService.getId(token);
            Cart cart = cartRepository.findByMemberIdAndItemId(memberId,itemId);

            cartRepository.delete(cart);
            return new ResponseEntity<>(HttpStatus.OK);
        }


}

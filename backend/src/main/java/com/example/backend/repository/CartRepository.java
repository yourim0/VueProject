package com.example.backend.repository;

import com.example.backend.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByMemberId(int memberId); //member에 있는 카트정보를 list형태로 가져옴
    Cart findByMemberIdAndItemId(int memberId, int itemId); //memberid, itemid 값이 있으면 카트 id 값을 반환

    void deleteByMemberId(int memberId); //결제가 완료된 특정 id 제품을 지움
}

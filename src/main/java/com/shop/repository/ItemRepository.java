package com.shop.repository;

import java.awt.print.Pageable;
import java.util.List;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    // 쿼리 메소드를 이용한 상품 조회
    List<Item> findByItemNm(String itemNm);
    // Or 조건 처리
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
    // LessThan 조건 처리
    List<Item> findByPriceLessThan(Integer price);
    // OrdertBy로 정렬 처리
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    // Query를 이요한 검색 처리(JPQL로 쿼리문 작성)- '%:itemDetail%' = '%?1%' 대처가능 그러나 명시적인 전자 선택.
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
    // Query-nativeQuery 속성 예제
    @Query(value="select*from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery=true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);

}

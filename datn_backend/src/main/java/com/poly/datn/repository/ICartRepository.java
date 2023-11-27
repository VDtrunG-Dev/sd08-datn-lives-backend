package com.poly.datn.repository;

import com.poly.datn.model.TShoppingCartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICartRepository extends JpaRepository<TShoppingCartDetail,Long> {

    @Query("SELECT c FROM TShoppingCartDetail c WHERE c.user.id = :idUser")
    List<TShoppingCartDetail> findCart(Long idUser);

    @Query("SELECT c FROM TShoppingCartDetail c WHERE c.user.id = :userId AND c.productVariation.id = :productId")
    TShoppingCartDetail findByProductIdAndUserId(Long productId,Long userId);
}

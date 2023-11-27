package com.poly.datn.service;


import com.poly.datn.dto.CartDTO;
import com.poly.datn.dto.ProductVariantDTO;
import com.poly.datn.model.TProductVariation;
import com.poly.datn.model.TShoppingCartDetail;
import com.poly.datn.model.TUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface ICartServices {

    List<CartDTO> findCart(HttpSession session);

    String addCart(HttpSession session, ProductVariantDTO productVariationDto);
}

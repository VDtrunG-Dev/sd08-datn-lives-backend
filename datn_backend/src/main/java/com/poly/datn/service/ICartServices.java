package com.poly.datn.service;


import com.poly.datn.dto.ProductVariantDTO;
import com.poly.datn.model.TProductVariation;
import com.poly.datn.model.TUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public interface ICartServices {

    String addCart(HttpSession session, ProductVariantDTO productVariationDto);
}

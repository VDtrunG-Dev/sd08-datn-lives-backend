package com.poly.datn.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.datn.dto.ProductVariantDTO;
import com.poly.datn.model.TProductVariation;
import com.poly.datn.model.TShoppingCartDetail;
import com.poly.datn.model.TUser;
import com.poly.datn.repository.ICartRepository;
import com.poly.datn.repository.IUserRepository;
import com.poly.datn.service.ICartServices;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServicesImpl implements ICartServices {

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private ProductDetailServicesImpl productDetailServices;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public String addCart(HttpSession session,ProductVariantDTO productVariantDTO) {
        TUser user = (TUser) session.getAttribute("user");
        long idUser = 1111;
        user = userRepository.findByIdUser(idUser);
        if(user == null){
            return addCartUserNull(session,productVariantDTO, null);
        }else {
            return addCartUserNotNull(productVariantDTO,user);
        }
    }

    private String addCartUserNotNull (ProductVariantDTO productVariantDTO, TUser user){
        TProductVariation productDetail = productDetailServices.findByName(productVariantDTO);
        TShoppingCartDetail findCartByProductIdAndUserId = cartRepository.findByProductIdAndUserId(productDetail.getId(),user.getId());
        if(findCartByProductIdAndUserId == null){
            TShoppingCartDetail cartDetail = new TShoppingCartDetail();
            BigDecimal totalPrice = new BigDecimal(productVariantDTO.getQuantity()).multiply(productVariantDTO.getPrice());

            cartDetail.setPrice(productVariantDTO.getPrice());
            cartDetail.setQuantity(productVariantDTO.getQuantity());
            cartDetail.setProductVariation(productDetail);
            cartDetail.setUser(user);
            cartDetail.setTotalPrice(totalPrice);
            cartDetail.setStatus(1);
//            cartRepository.save(cartDetail);
            return "Thêm Thành Công";
        }else {
            int quantityNew = findCartByProductIdAndUserId.getQuantity() + productVariantDTO.getQuantity();
            BigDecimal totalPrice = new BigDecimal(quantityNew).multiply(productVariantDTO.getPrice());

            findCartByProductIdAndUserId.setQuantity(quantityNew);
            findCartByProductIdAndUserId.setTotalPrice(totalPrice);
            findCartByProductIdAndUserId.setPrice(productVariantDTO.getPrice());
//            cartRepository.save(findCartByProductIdAndUserId);
            return "Thêm Thành Công";
        }
    }

    private String addCartUserNull(HttpSession session, ProductVariantDTO productVariantDTO, TUser user){

        List<TShoppingCartDetail> cartDetails = (List<TShoppingCartDetail>) session.getAttribute("cart");
        TProductVariation productDetail = productDetailServices.findByName(productVariantDTO);

        if (cartDetails == null){
            cartDetails = new ArrayList<>();
            TShoppingCartDetail cartDetail = new TShoppingCartDetail();
            BigDecimal totalPrice = new BigDecimal(productVariantDTO.getQuantity()).multiply(productVariantDTO.getPrice());

            cartDetail.setPrice(productDetail.getPrice());
            cartDetail.setQuantity(productVariantDTO.getQuantity());
            cartDetail.setProductVariation(productDetail);
            cartDetail.setUser(user);
            cartDetail.setTotalPrice(totalPrice);
            cartDetail.setStatus(1);
            cartDetails.add(cartDetail);

            session.setAttribute("cart",cartDetails);
            return "Thêm Thành Công";
        }else {
            for(int i = 0; i < cartDetails.size(); i++){
                TShoppingCartDetail cart = cartDetails.get(i);
                if(cart.getProductVariation().getName().equals(productDetail.getName())){
                    TShoppingCartDetail shoppingCartDetail = new TShoppingCartDetail();
                    int quantityNew = cart.getQuantity() + productVariantDTO.getQuantity();
                    BigDecimal totalPrice = new BigDecimal(quantityNew).multiply(productDetail.getPrice());

                    shoppingCartDetail.setProductVariation(productDetail);
                    shoppingCartDetail.setPrice(productDetail.getPrice());
                    shoppingCartDetail.setQuantity(quantityNew);
                    shoppingCartDetail.setTotalPrice(totalPrice);
                    shoppingCartDetail.setUser(user);
                    shoppingCartDetail.setStatus(1);
                    cartDetails.set(i,shoppingCartDetail);
                }
            }
            session.setAttribute("cart",cartDetails);
            return "Có sản phẩm";
        }
    }
}

package com.poly.datn.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.datn.dto.CartDTO;
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
    public List<CartDTO> findCart(HttpSession session) {
        TUser user = (TUser) session.getAttribute("user");
        long id = 1111;
        user = userRepository.findByIdUser(id);
        if(user == null){
            List<CartDTO> cartDTOS = (List<CartDTO>) session.getAttribute("cart");
            return cartDTOS;
        }else {
            return null;
        }

    }

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
//        if(productDetail.getQuantity() < productVariantDTO.getQuantity()){
//            return "Số Lượng Sản Phẩm Không Đủ";
//        }

        TShoppingCartDetail findCartByProductIdAndUserId = cartRepository.findByProductIdAndUserId(productDetail.getId(),user.getId());
        if(findCartByProductIdAndUserId == null){
            TShoppingCartDetail cartDetail = new TShoppingCartDetail();
            BigDecimal totalPrice = new BigDecimal(productVariantDTO.getQuantity()).multiply(productDetail.getPrice());

            cartDetail.setPrice(productDetail.getPrice());
            cartDetail.setQuantity(productVariantDTO.getQuantity());
            cartDetail.setProductVariation(productDetail);
            cartDetail.setUser(user);
            cartDetail.setTotalPrice(totalPrice);
            cartDetail.setStatus(1);
            cartRepository.save(cartDetail);
            return "Thêm Thành Công";
        }else {
            int quantityNew = findCartByProductIdAndUserId.getQuantity() + productVariantDTO.getQuantity();
            BigDecimal totalPrice = new BigDecimal(quantityNew).multiply(productVariantDTO.getPrice());

            findCartByProductIdAndUserId.setQuantity(quantityNew);
            findCartByProductIdAndUserId.setTotalPrice(totalPrice);
            findCartByProductIdAndUserId.setPrice(productVariantDTO.getPrice());
            cartRepository.save(findCartByProductIdAndUserId);
            return "Thêm Thành Công";
        }
    }

    private String addCartUserNull(HttpSession session, ProductVariantDTO productVariantDTO, TUser user){
        List<CartDTO> cartDetails = (List<CartDTO>) session.getAttribute("cart");
        TProductVariation productDetail = productDetailServices.findByName(productVariantDTO);
//        if(productDetail.getQuantity() < productVariantDTO.getQuantity()){
//            return "Số Lượng Sản Phẩm Không Đủ";
//        }
        if (cartDetails == null){
            cartDetails = new ArrayList<>();
            CartDTO cartDTO = new CartDTO();
            BigDecimal totalPrice = new BigDecimal(productVariantDTO.getQuantity()).multiply(productDetail.getPrice());

            cartDTO.setProductVariationsName(productDetail.getName());
            cartDTO.setPrice(productDetail.getPrice());
            cartDTO.setQuantity(productVariantDTO.getQuantity());
            cartDTO.setTotalPrice(totalPrice);
            cartDetails.add(cartDTO);
            session.setAttribute("cart",cartDetails);
            return "Thêm Thành Công";
        }else {
            for(int i = 0; i < cartDetails.size(); i++){
                CartDTO cart = cartDetails.get(i);
                if(cart.getProductVariationsName().equals(productDetail.getName())){
                    CartDTO cartDTO = new CartDTO();
                    int quantityNew = cart.getQuantity() + productVariantDTO.getQuantity();
                    BigDecimal totalPrice = new BigDecimal(quantityNew).multiply(productDetail.getPrice());

                    cartDTO.setProductVariationsName(productDetail.getName());
                    cartDTO.setPrice(productDetail.getPrice());
                    cartDTO.setQuantity(quantityNew);
                    cartDTO.setTotalPrice(totalPrice);

                    cartDetails.set(i,cartDTO);
                }
            }
            session.setAttribute("cart",cartDetails);
            return "Có sản phẩm";
        }
    }
}

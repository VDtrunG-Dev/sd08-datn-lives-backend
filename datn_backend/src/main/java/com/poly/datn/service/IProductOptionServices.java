package com.poly.datn.service;

import com.poly.datn.model.TAddress;
import com.poly.datn.model.TOption;
import com.poly.datn.model.TProduct;
import com.poly.datn.model.TUser;

import java.util.List;

public interface IProductOptionServices {

    String save(TProduct product, List<TOption> options);

    String deleteById(Long id);
}

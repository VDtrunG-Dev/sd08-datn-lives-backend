package com.poly.datn.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OptionDTO {
    private String optionName;
    private List<String> optionValueName;
}

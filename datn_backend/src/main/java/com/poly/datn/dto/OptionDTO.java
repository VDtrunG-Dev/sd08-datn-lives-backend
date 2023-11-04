package com.poly.datn.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OptionDTO {
    private String optionName;
    private List<String> optionValueName;
}

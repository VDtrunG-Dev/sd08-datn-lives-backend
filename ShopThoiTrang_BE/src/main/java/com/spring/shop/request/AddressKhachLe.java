package com.spring.shop.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressKhachLe {
    private Integer Id;
    private String Fullname;
    private String Phone;
    private String Address;
    private String CityName;
    private String DistrictName;
    private String WardName;
    private Integer CityId;
    private Integer DistrictId;
    private Integer WardId;

}

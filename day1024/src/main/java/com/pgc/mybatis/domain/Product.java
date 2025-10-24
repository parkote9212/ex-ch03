package com.pgc.mybatis.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {
//    상품번호
    private Long proId;
    private String proName;
    private  Integer price;
    private Integer amount;
}

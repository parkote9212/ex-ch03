package com.pgc.mybatis.dto;
// 클라이언트에게 응답할떄 사용하는 객체

import com.pgc.mybatis.domain.Product;
import lombok.Getter;

@Getter
public class ProductResponse {

    private Long proId;
    private String proName;
    private Integer price;
    private Integer amount;

//    Entity -> DTO 변환 생성자
    public ProductResponse(Product product){
        this.proId = product.getProId();
        this.proName = product.getProName();
        this.price = product.getPrice();
        this.amount = product.getAmount();
    }

}

package com.pgc.mybatis.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateRequest {
    // proId는 URL 경로에서 받으므로 여기서는 제외합니다.
    @NotBlank(message = "상품 이름은 필수입니다.")
    private String proName;

    @NotNull(message = "가격은 필수입니다.")
    @Min(value = 0, message = "가격은 0원 이상이어야 합니다.")
    private Integer price;

    @NotNull(message = "수량은 필수입니다.")
    @Min(value = 0, message = "수량은 0개 이상이어야 합니다.")
    private Integer amount;

}
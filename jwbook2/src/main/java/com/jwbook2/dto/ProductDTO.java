package com.jwbook2.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data // getter, setter, toString 포함
public class ProductDTO {
    // 상품명, 가격
    // 제약 사항 메세지: 비어있을 수 없습니다.
    @NotEmpty(message = "품명을 입력해주세요.")
    private String name;
    @Min(value = 0, message = "가격을 입력해주세요.")
    private Integer price;
}

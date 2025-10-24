package com.pgc.mybatis.controller;

import com.pgc.mybatis.domain.Product;
import com.pgc.mybatis.dto.ProductCreateRequest;
import com.pgc.mybatis.dto.ProductResponse;
import com.pgc.mybatis.dto.ProductUpdateRequest;
import com.pgc.mybatis.service.ProServ;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProRestCon {

    private final ProServ proServ;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> list() {
        List<Product> products = proServ.getAllProducts();
//        Entity List -> DTO List 변환
        List<ProductResponse> responseList = products.stream()
//                // .map(product -> new ProductResponse(product))
                .map(ProductResponse::new)
                .toList();

        return ResponseEntity.ok(responseList);
    }

    /**
     * [수정됨] POST 메서드
     * RequestBody로 ProductCreateRequest DTO를 받습니다.
     * ResponseEntity로 ProductResponse DTO를 반환합니다.
     */

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductCreateRequest createRequest) {
        // 1. 서비스에 DTO를 전달하여 엔티티를 생성합니다.
        Product createdProduct = proServ.createProduct(createRequest);

        // 2. 생성된 엔티티를 Response DTO로 변환합니다.
        ProductResponse response = new ProductResponse(createdProduct);

        // 3. 201 Created 상태 코드와 함께 응답 DTO를 반환합니다.
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{proId}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long proId) {
//      proServ.getProductId가 예외처리를 대신해준다
        Product product = proServ.getProductId(proId);
//        Entity -< DTO
        return ResponseEntity.ok(new ProductResponse(product)); // 200 OK
    }


    @PutMapping("/{proId}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long proId,
                                                  @Valid @RequestBody ProductUpdateRequest updateRequest) {
//      proServ.getProductId가 예외처리를 대신해준다
//        서비스에 ID와 DTO를 전달하여 엔티티수정
        Product updatedProduct = proServ.updateProduct(proId, updateRequest);
//        수정된 엔티티를 Response DTO로 변환
        ProductResponse response = new ProductResponse(updatedProduct);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{proId}")
    public ResponseEntity<Void> delete(@PathVariable Long proId) { // 반환 타입을 Void로 변경

        proServ.deleteProduct(proId);

        return ResponseEntity.noContent().build();

    }
}

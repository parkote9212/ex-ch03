package com.pgc.mybatis.service;

import com.pgc.mybatis.domain.Product;
import com.pgc.mybatis.dto.ProductCreateRequest;
import com.pgc.mybatis.dto.ProductUpdateRequest;
import com.pgc.mybatis.exception.ResourceNotFoundException;
import com.pgc.mybatis.mapper.ProMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProServ {

    private final ProMapper proMapper;

    public List<Product> getAllProducts() {
        return proMapper.findAll();
    }

    @Transactional
    public Product createProduct(ProductCreateRequest createRequest) {
//        DTO -> Entity
        Product product = createRequest.toEntity();
//        DB에 삽입
        proMapper.insert(product);
//        ID가 채워진 Entity반환
        return product;
    }

    // ProServ.java
    public Product getProductId(Long proId) {
        Product product = proMapper.findById(proId);
        if (product == null) {
//            null 대신 예외 발생
            throw new ResourceNotFoundException("Product not found with id: " + proId);
        }
        return product;
    }

    @Transactional
    public Product updateProduct(Long proId, ProductUpdateRequest updateRequest) {
//         1. ID로 정보를 조회 ID가 없으면 예외처리
        Product findProduct = getProductId(proId);

        findProduct.setProName(updateRequest.getProName());
        findProduct.setPrice(updateRequest.getPrice());
        findProduct.setAmount(updateRequest.getAmount());
        proMapper.update(findProduct);
        // 5. 수정된 객체를 반환합니다.
        return findProduct;
    }

    @Transactional
    public void deleteProduct(Long proId) {
        //    만약 상품이 없으면 이 줄에서 ResourceNotFoundException이 발생하고 메서드가 종료됨.
        getProductId(proId);

        // 3. (위에서 예외가 안 났다면) 상품이 존재하므로 삭제 수행
        proMapper.delete(proId);
    }


}

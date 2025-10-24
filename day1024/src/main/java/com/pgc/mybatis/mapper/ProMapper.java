package com.pgc.mybatis.mapper;

import com.pgc.mybatis.domain.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProMapper {


    List<Product> findAll();

    int insert(Product product);

    Product findById(Long proId);

    int update(Product product) ;

    int delete(Long proId);
}

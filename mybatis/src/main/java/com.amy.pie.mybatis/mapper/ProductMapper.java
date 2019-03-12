package com.amy.pie.mybatis.mapper;

import com.amy.pie.mybatis.entity.Product;

import java.util.List;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/3/12 10:06
 */
public interface ProductMapper {
    /**
     * 查询所有的产品
     * @return
     */
    List<Product> selectProductList();
}

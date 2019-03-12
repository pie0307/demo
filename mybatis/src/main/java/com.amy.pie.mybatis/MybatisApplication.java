package com.amy.pie.mybatis;

import com.amy.pie.mybatis.entity.Product;
import com.amy.pie.mybatis.mapper.ProductMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author : Administrator.
 * @Description :
 * @Date : Created in 2019/1/6 21:42
 * @Modified By :
 */

public class MybatisApplication {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            List<Product> productList = productMapper.selectProductList();
            for (Product product : productList) {
                System.out.printf(product.toString());
            }
        } finally {
            sqlSession.close();
        }
    }
}

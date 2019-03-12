package com.amy.pie.mybatis.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/3/12 10:05
 */
@Setter
@Getter
@ToString
public class Product {
    private long id;
    private String columnCode;
    private String columnName;
}

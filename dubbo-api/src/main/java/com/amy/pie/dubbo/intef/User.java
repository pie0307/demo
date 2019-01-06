package com.amy.pie.dubbo.intef;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author : Administrator.
 * @Description :
 * @Date : Created in 2019/1/5 22:23
 * @Modified By :
 */
@Setter
@Getter
@ToString
public class User implements Serializable{
    private Integer id;
    private String username;
}

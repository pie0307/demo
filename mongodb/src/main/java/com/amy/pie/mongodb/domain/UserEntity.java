package com.amy.pie.mongodb.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author : Administrator.
 * @Description :
 * @Date : Created in 2019/1/6 21:42
 * @Modified By :
 */
@Getter
@Setter
@ToString
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -3258839839160856613L;
    private Long id;
    private String userName;
    private String passWord;
}

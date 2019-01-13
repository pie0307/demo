package com.amy.pie.mongodb.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 管家实体
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ManagerDocument {

    /**
     * 管家系统号
     */
    @Field(value = "code")
    private String code;

    /**
     * 管家姓名
     */
    @Field(value = "name")
    private String name;

    /**
     * 管家类型
     */
    @Field(value = "type")
    private String type;

    /**
     * 管家所属类型 1-所属 2-所属服务 3-国际管家
     */
    @Field(value = "ext_type")
    private Integer extendType;
}

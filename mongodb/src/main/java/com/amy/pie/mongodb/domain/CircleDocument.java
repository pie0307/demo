package com.amy.pie.mongodb.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 商圈信息
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CircleDocument {

    /**
     * code
     */
    @Field(value = "code")
    private Long code;

    /**
     * 名称
     */
    @Field(value = "name")
    private String name;
}

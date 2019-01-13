package com.amy.pie.mongodb.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 公区图片
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UnitPicDocument {

    /**
     * 厨房图片
     */
    @Field(value = "kitchen")
    private List<String> kitchen;

    /**
     * 卫生间图片
     */
    @Field(value = "toilet")
    private List<String> toilet;

    /**
     * 客厅图片
     */
    @Field(value = "parlour")
    private List<String> parlour;

    /**
     * 花园图片
     */
    @Field(value = "garden")
    private List<String> garden;

    /**
     * 阳台图片
     */
    @Field(value = "balcony")
    private List<String> balcony;

    /**
     * 露台图片
     */
    @Field(value = "terrace")
    private List<String> terrace;

    /**
     * 餐厅图片
     */
    @Field(value = "dining")
    private List<String> dining;

}

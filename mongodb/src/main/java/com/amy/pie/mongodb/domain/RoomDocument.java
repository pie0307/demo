package com.amy.pie.mongodb.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 卧室图片
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RoomDocument {

    /**
     * 房间code
     */
    @Field(value = "id")
    private Long roomId;

    /**
     * 房间code
     */
    @Field(value = "code")
    private String roomCode;

    /**
     * 房间朝向
     */
    @Field(value = "face")
    private String face;

    /**
     * 房间大小
     */
    @Field(value = "size")
    private Double size;

    /**
     * 独卫
     */
    @Field(value = "has_toilet")
    private Integer hasToilet;

    /**
     * 独立阳台
     */
    @Field(value = "has_balcony")
    private Integer hasBalcony;

    /**
     * 房间图片
     */
    @Field(value = "pic_urls")
    private List<String> picUrls;
}

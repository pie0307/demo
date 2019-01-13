package com.amy.pie.mongodb.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 */
@Setter
@Getter
@EqualsAndHashCode
public class StationDocument {

    /**
     * 地铁站名称
     */
    @Field(value = "name")
    private String stationName;

    /**
     * 经度
     */
    @Field(value = "lng")
    private Double longitude;

    /**
     * 纬度
     */
    @Field(value = "lat")
    private Double latitude;

    /**
     * 楼盘距离地铁站距离
     */
    @Field(value = "distance")
    private Integer distance;
}

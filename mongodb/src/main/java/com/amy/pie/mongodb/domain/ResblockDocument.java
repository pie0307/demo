package com.amy.pie.mongodb.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ResblockDocument {

    /**
     * 建筑年限
     */
    @Field(value = "build_year")
    private String buildYear;

    /**
     * 城区编码
     */
    @Field(value = "dis_code")
    private Long districtCode;

    /**
     * 城区名称
     */
    @Field(value = "dis_name")
    private String districtName;

    /**
     * 绿化率
     */
    @Field(value = "green_rate")
    private String greenRate;

    /**
     * 经度
     */
    @Field(value = "lng")
    private String longitude;

    /**
     * 纬度
     */
    @Field(value = "lat")
    private String latitude;

    /**
     * 楼盘id
     */
    @Field(value = "id")
    private Long id;

    /**
     * 楼盘名称
     */
    @Field(value = "name")
    private String name;


    /**
     * 是否学区房
     */
    @Field(value = "sch_district")
    private Integer schoolDistrict;

    /**
     * 地铁
     */
    @Field(value = "subway")
    List<SubwayDocument> subway;

    /**
     * 商圈
     */
    @Field(value = "circle")
    List<CircleDocument> circle;

    /**
     * 楼栋id
     */
    @Field(value = "building_id")
    private Long buildingId;

    /**
     * 自如经纬度
     */
    private String zrposition;
}

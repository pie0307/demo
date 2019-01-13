package com.amy.pie.mongodb.domain;

import com.amy.pie.mongodb.common.BaseDocument;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 *         出租单位Document
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Document(collection = "rentunit")
public class RentUnitDocument extends BaseDocument {

    /**
     * 库存id
     */
    @Field(value = "inv_id")
    private Long invId;

    /**
     * 库存房屋编码(资产房屋id)
     */
    @Field(value = "inv_hcode")
    private String invHouseCode;

    /**
     * 库存房间编码(资产房间id)
     */
    @Field(value = "inv_rcode")
    private String invRoomCode;

    /**
     * 库存房源状态
     */
    @Field(value = "inv_status")
    private String housingStatus;

    /**
     * 库存房源展示状态
     */
    @Field(value = "dis_status")
    private String housingDisplayStatus;

    /**
     * 库存销售状态
     */
    @Field(value = "sale_status")
    private String salesStatus;

    /**
     * 库存空置天数
     */
    @Field(value = "vacancy_day")
    private Long vacancyDays;

    /**
     * 是否首次出租
     */
    @Field(value = "is_new")
    private Integer isNew;

    /**
     * 空气质量
     */
    @Field(value = "air_quality")
    private Integer airQuality;


    /**
     * 收房合同截止日期
     */
    @Field(value = "agent_end_date")
    private Date agentEndDate;

    /**
     * 出房合同截止日期
     */
    @Field(value = "rent_end_date")
    private Date rentEndDate;

    /**
     * 城市编码
     */
    @Field(value = "city_code")
    private String cityCode;

    /**
     * 房屋信息,来自房源档案
     */
    @Field(value = "book")
    private HouseDocument house;

    /**
     * 楼盘信息
     */
    @Field(value = "resblock")
    private ResblockDocument resblock;
}

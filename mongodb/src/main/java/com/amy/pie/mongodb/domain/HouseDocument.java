package com.amy.pie.mongodb.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 *         house实体
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class HouseDocument {

    /**
     * 房屋ID
     */
    @Field(value = "id")
    private Long houseId;

    /**
     * 房屋编码
     */
    @Field(value = "code")
    private String houseCode;

    /**
     * 房源编号
     */
    @Field(value = "source_code")
    private String houseSourceCode;

    /**
     * 合同号
     */
    @Field(value = "hire_code")
    private String hireContractCode;

    /**
     * 房间ID
     */
    @Field(value = "room_id")
    private Long roomId;

    /**
     * 房间编码
     */
    @Field(value = "room_code")
    private String roomCode;

    /**
     * 卧室数量
     */
    @Field(value = "bedroom_count")
    private Integer bedroomCount;

    /**
     * 能否养宠物
     */
    @Field(value = "can_pet")
    private Integer canHasPet;

    /**
     * 朝向
     */
    @Field(value = "face")
    private String face;

    /**
     * 是否智能锁
     */
    @Field(value = "has_ailock")
    private Integer hasAiLock;

    /**
     * 是否电梯
     */
    @Field(value = "has_lift")
    private Integer hasLift;

    /**
     * 客厅数量
     */
    @Field(value = "parlor_count")
    private Integer parlorCount;

    /**
     * 供暖方式
     */
    @Field(value = "heat_mode")
    private String heatMode;

    /**
     * 物业地址
     */
    @Field(value = "rating_addr")
    private String ratingAddress;

    /**
     * 面积大小
     */
    @Field(value = "size")
    private Double size;

    /**
     * 风格
     */
    @Field(value = "style_code")
    private String styleCode;

    /**
     * 版本号
     */
    @Field(value = "ziroom_version")
    private Long ziroomVersionId;

    /**
     * 整分租
     */
    @Field(value = "is_whole")
    private Integer isWhole;

    /**
     * 评价
     */
    @Field(value = "evaluation")
    private String evaluation;

    /**
     * 户型图
     */
    @Field(value = "hx_pic")
    private String hxPic;

    /**
     * 楼层 第几层
     */
    @Field(value = "floor")
    private String floor;

    /**
     * 楼层 总共
     */
    @Field(value = "floor_total")
    private String floorTotal;

    /**
     * 公共卫生间数量
     */
    @Field(value = "pub_toilet_count")
    private Integer pubToiletCount;

    /**
     * 价格
     */
    @Field(value = "sell_price")
    private Integer sellPrice;


    /**
     * 房间首图
     */
    @Field(value = "first_pic")
    private String firstPic;


    /**
     * 卧室图片
     */
    @Field(value = "rooms")
    private List<RoomDocument> rooms;

    /**
     * 公区图片
     */
    @Field(value = "unit_pics")
    private UnitPicDocument unitPic;

    /**
     * 管家信息
     */
    @Field(value = "manager")
    private List<ManagerDocument> houseManager;
}

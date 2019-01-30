package com.amy.pie.sharding.mapper;

import com.amy.pie.sharding.domain.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/1/29 16:28
 */
public interface OrderMapper {

    @Insert("INSERT INTO t_order (user_id, status) VALUES (#{userId}, #{status})")
    int createUser(Order order);

    @Select("SELECT * FROM t_order WHERE user_id = #{userId}")
    List<Order> selectByUserId(Long userId);

    @Select("SELECT * FROM t_order WHERE order_id between #{startOrderId} and #{endOrderId}")
    List<Order> selectByOrderIdBetween(@Param("startOrderId") Long startOrderId, @Param("endOrderId") Long endOrderId);
}


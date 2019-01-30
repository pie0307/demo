package com.amy.pie.sharding.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/1/29 16:28
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Long orderItemId;

    private Long orderId;

    private Integer userId;
}

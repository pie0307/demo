package com.amy.pie.sharding.domain;

import lombok.*;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/1/29 16:28
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private Long orderId;

    private Long userId;

    private String status;
}

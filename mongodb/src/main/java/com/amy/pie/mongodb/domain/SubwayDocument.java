package com.amy.pie.mongodb.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SubwayDocument {

    /**
     * 地铁线
     */
    @Field(value = "line")
    private String line;

    /**
     * 地铁站列表
     */
    @Field(value = "station")
    private List<StationDocument> station;
}

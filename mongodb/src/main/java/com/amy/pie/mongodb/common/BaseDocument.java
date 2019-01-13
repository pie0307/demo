package com.amy.pie.mongodb.common;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author guolq on 2018/11/28.
 */
@Getter
@Setter
public class BaseDocument {

    @Field(value = "_id")
    private ObjectId id;
}

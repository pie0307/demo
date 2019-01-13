package com.amy.pie.mongodb.common;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author guolq on 2018/11/28.
 */
@Component
@Slf4j
public class MongoUtil<T extends BaseDocument> {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增记录
     *
     * @param document 文档
     */
    public void insert(T document) {
        mongoTemplate.save(document);
    }

    /**
     * 批量插入
     *
     * @param list list
     */
    public void insertBatch(List<T> list) {
        mongoTemplate.insertAll(list);
    }


    public UpdateResult updateIgnoreNull(T document) {
        return update(document, true);
    }

    public UpdateResult update(T document) {
        return update(document, false);
    }


    public UpdateResult update(Query query, Update update, Class<T> clazz) {
        return mongoTemplate.updateFirst(query, update, clazz);
    }


    public DeleteResult delete(T document) {
        return mongoTemplate.remove(document);
    }

    public DeleteResult delete(Query query, Class<T> clazz) {
        return mongoTemplate.remove(query, clazz);
    }

    public T findOne(Query query, Class<T> clazz) {
        return mongoTemplate.findOne(query, clazz);
    }

    public List<T> findList(Query query, Class<T> clazz) {
        return mongoTemplate.find(query, clazz);
    }

    public Long count(Class<T> clazz) {
        return mongoTemplate.count(new Query(), clazz);
    }

    public Long count(Query query, Class<T> clazz) {
        return mongoTemplate.count(query, clazz);
    }


    private UpdateResult update(T document, boolean ignoreNull) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(document.getId()));
        Update update = new Update();
        Field[] fields = document.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                if (field.getName().equals("id") || (ignoreNull && field.get(document) == null)) {
                    continue;
                }
                update.set(field.getName(), field.get(document));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("mongo update error", e);
            }
        }
        return mongoTemplate.updateFirst(query, update, document.getClass().getName());
    }

}


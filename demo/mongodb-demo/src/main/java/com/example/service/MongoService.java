package com.example.service;

import com.example.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

/**
 * @author PF.Tian
 * @since 2021/10/24
 */
@Service
public class MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建对象
     */
    public void save(UserInfo userInfo) {
        mongoTemplate.save(userInfo);
    }

    /**
     * 根据用户名查询对象
     *
     * @return
     */
    public UserInfo findByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        UserInfo mgt = mongoTemplate.findOne(query, UserInfo.class);
        return mgt;
    }

    /**
     * 更新对象
     */
    public void update(UserInfo userInfo) {
        Query query = new Query(Criteria.where("id").is(userInfo.getId()));
        Update update = new Update().set("age", userInfo.getAge()).set("name", userInfo.getName());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, UserInfo.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserInfo.class);
    }

    /**
     * 删除对象
     *
     * @param id
     */
    public void deleteById(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, UserInfo.class);
    }

}

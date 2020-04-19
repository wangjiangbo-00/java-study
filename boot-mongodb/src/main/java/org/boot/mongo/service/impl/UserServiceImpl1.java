package org.boot.mongo.service.impl;

import org.boot.mongo.entity.User;
import org.boot.mongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Priority;
import javax.annotation.Resource;

/**
 * @author luoliang
 * @date 2018/10/15
 */
@Service
@Priority(value = 0)
public class UserServiceImpl1 implements UserService {
    @Resource
    private MongoTemplate mongoTemplate;




    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public User findUserByUsername(String username) {
        Criteria criteria = Criteria.where("username").is(username);
        Query query = new Query(criteria);

        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public void updateUser(User user) {
        Criteria criteria = Criteria.where("username").is(user.getUsername());
        Query query = new Query(criteria);
        Update update = new Update().set("username", user.getUsername())
                .set("password", user.getPassword());
        //更新结果集的第一条
        mongoTemplate.updateFirst(query, update, User.class);
        //更新结果集的所有
//        mongoTemplate.updateMulti(query, update, User.class);
    }

    @Override
    public void deleteUserById(String id) {
        Criteria criteria = Criteria.where("username").is(id);
        Query query = new Query(criteria);
        mongoTemplate.remove(query, User.class);
    }
}

package org.boot.mongo.service.impl;

import org.boot.mongo.entity.User;
import org.boot.mongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author luoliang
 * @date 2018/10/15
 */
@Service
public  interface UserServiceJpa extends MongoRepository<User, String> {

    public List<User> findByUsername(String name);



}

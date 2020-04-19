package org.boot.mongo;

import lombok.extern.slf4j.Slf4j;
import org.boot.mongo.entity.Address;
import org.boot.mongo.entity.User;
import org.boot.mongo.service.UserService;
import org.boot.mongo.service.impl.UserServiceImpl1;
import org.boot.mongo.service.impl.UserServiceJpa;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author luoliang
 * @date 2018/10/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceTest implements ApplicationContextAware{

    @Resource
    private UserService userServiceImpl;

    @Resource
    private UserServiceJpa userServiceJpa;



    @Override
    public void setApplicationContext(ApplicationContext var1)
    {
        String[] names = var1.getBeanDefinitionNames();

        System.out.println(names);
    }

    @Test
    public void saveUser() {
        User user = new User();
        //user.setId(123456L);
        user.setUsername("mongodb");
        user.setPassword("root");

        Address address = new Address();
        address.setAddr("1111");
        address.setStreet("2222222");


        List<Address> addresses= new ArrayList<Address>();

        addresses.add(address);

        user.setAddresses(addresses);




        userServiceJpa.save(user);

        System.out.println(user);
    }

    @Test
    public void findUserByUsername() {
        User user = userServiceImpl.findUserByUsername("mongodb");
        List<User> user1 = userServiceJpa.findByUsername("mongodb");

        log.debug("user is: {}", user.toString());
        System.out.println(user1);
    }

    @Test
    public void updateUser() {
        User user = new User();

        List<User> user1 = userServiceJpa.findByUsername("mongodb");

        user1.get(0).setPassword("aaa");

        userServiceJpa.saveAll(user1);

        user.setUsername("mongodb");
        user.setPassword("rootroot");
        //userService.updateUser(user);

    }

    @Test
    public void deleteUserById() {
        userServiceImpl.deleteUserById("mongodb");
    }
}

package org.boot.mystarter;

import my.boot.starter.MyDataTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyStarterApplicationTests {
    private final Logger logger = LoggerFactory.getLogger(MyStarterApplicationTests.class);
    @Autowired
    private MyDataTemplate myDataTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
        String result = myDataTemplate.getData();
        logger.info("get data result：{}", result);
    }
}


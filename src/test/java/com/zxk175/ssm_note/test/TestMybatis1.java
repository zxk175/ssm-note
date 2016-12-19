package com.zxk175.ssm_note.test;

import com.zxk175.ssm_note.conf.spring.SpringBase;
import com.zxk175.ssm_note.conf.spring.SpringDaoXml;
import com.zxk175.ssm_note.conf.spring.SpringMvcXml;
import com.zxk175.ssm_note.pojo.User;
import com.zxk175.ssm_note.pojo.UserExample;
import com.zxk175.ssm_note.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by zxk175 on 16/11/13.
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringBase.class, SpringMvcXml.class, SpringDaoXml.class})
public class TestMybatis1 {
    private static final Logger log = LogManager.getLogger(TestMybatis1.class);

    @Autowired
    private UserService userService;

    @Test
    public void test1() {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo((short) 0);
        List<User> users = userService.getAllUserByExample(example);
        String str = "";
        for (User user : users) {
            str = user.getUserName();
            log.trace(str);
            log.debug(str);
            log.error(str);
            log.warn(str);
            log.info(str);
        }
    }
}
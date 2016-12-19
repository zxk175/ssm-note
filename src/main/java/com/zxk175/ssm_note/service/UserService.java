package com.zxk175.ssm_note.service;

import com.zxk175.ssm_note.pojo.User;
import com.zxk175.ssm_note.pojo.UserExample;

import java.util.List;

/**
 * Created by zxk175 on 16/11/13.
 */
public interface UserService {
    int insertUser(User user);

    int deleteByExample(UserExample example);

    int updateByPrimaryKeySelective(User user);

    List<User> getAllUserByExample(UserExample example);
}
package com.just.toyim.service.impl;

import com.just.toyim.dao.UserDao;
import com.just.toyim.service.meta.HttpResponse;
import com.just.toyim.service.meta.UserDto;
import com.just.toyim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDaoImpl;

    @Override
    public HttpResponse login(UserDto user) {
        if (isValid(user.getUsername(), user.getPassword())) {
            return new HttpResponse().error("密码或者用户名不能为空");
        }

        return null;
    }

    private boolean isValid(String name,String passwd){
        if (name.equals("") || passwd.equals("")) {
            return true;
        }
        return false;
    }
}

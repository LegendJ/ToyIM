package com.just.toyim.service.impl;

import com.just.toyim.pojo.HttpResponse;
import com.just.toyim.pojo.User;
import com.just.toyim.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public HttpResponse login(User user) {
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

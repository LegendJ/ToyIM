package com.just.toyim.service.impl;

import com.just.toyim.dao.GroupDao;
import com.just.toyim.dao.UserDao;
import com.just.toyim.dao.meta.GroupInfo;
import com.just.toyim.dao.meta.UserInfo;
import com.just.toyim.service.meta.GroupDto;
import com.just.toyim.service.meta.HttpResponse;
import com.just.toyim.service.meta.UserDto;
import com.just.toyim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    GroupDao groupDao;

    @Override
    public HttpResponse login(UserDto user) {
        if (isValid(user.getUsername(), user.getPassword())) {
            return new HttpResponse().error("密码或者用户名不能为空");
        }

        return null;
    }

    @Override
    public HttpResponse getUserById(long userId) {
        UserInfo userInfo = userDao.get(userId);

        UserDto userDto = new UserDto();
        userDto.setUserInfo(userInfo);
        userDto.setFriendList(userDao.findFriends(userId));

        List<GroupInfo> groupInfos = groupDao.getByUserId(userId);
        List<GroupDto> groupDtos = new ArrayList<>();
        for(GroupInfo groupInfo: groupInfos){
            GroupDto groupDto = new GroupDto();
            List<UserInfo> members = userDao.findGroupUsers(userId);
            groupDto.setMembers(members);
            groupDto.setGroupInfo(groupInfo);
            groupDtos.add(groupDto);
        }

        userDto.setGroupDtos(groupDtos);
        return new HttpResponse().setData("userInfo", userInfo);
    }

    private boolean isValid(String name, String passwd) {
        if ("".equals(name) || "".equals(passwd)) {
            return true;
        }
        return false;
    }
}

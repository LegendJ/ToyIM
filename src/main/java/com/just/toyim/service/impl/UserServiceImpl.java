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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    GroupDao groupDao;

    @Override
    public HttpResponse login(UserDto user) {
        if (isValid(user.getUsername(), user.getPassword())) {
            Map<String, Object> params = new HashMap<>();
            params.put("username", user.getUsername());
            params.put("password", user.getPassword());
            List<UserInfo> userInfos = userDao.getRecordsByField(params);

            if (userInfos == null){
                return new HttpResponse().error("用户名或密码错误");
            }
            UserInfo userInfo = userInfos.get(0);
            user.setUserId(userInfo.getId());
            return new HttpResponse().success();
        }else {
            return new HttpResponse().error("密码或者用户名不能为空");
        }
    }

    @Override
    public HttpResponse getUserById(String userId) {
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
        return new HttpResponse().success().setData("userInfo", userDto);
    }

    private boolean isValid(String name, String passwd) {
        if ("".equals(name) || "".equals(passwd)) {
            return false;
        }
        return true;
    }
}

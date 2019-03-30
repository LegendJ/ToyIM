package com.just.toyim.dao;

import com.just.toyim.dao.meta.UserInfo;

import java.util.List;

public interface UserDao extends IBaseDao<UserInfo>{

    List<UserInfo> findFriends(String userId);

    List<UserInfo> findGroupUsers(String groupId);
}

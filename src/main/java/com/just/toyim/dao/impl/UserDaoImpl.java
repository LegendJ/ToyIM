package com.just.toyim.dao.impl;

import com.just.toyim.dao.UserDao;
import com.just.toyim.dao.meta.UserInfo;

import java.util.List;
import java.util.Map;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public long add(UserInfo userInfo) {
        return 0;
    }

    @Override
    public int update(UserInfo userInfo) {
        return 0;
    }

    @Override
    public int delete(UserInfo userInfo) {
        return 0;
    }

    @Override
    public UserInfo get(long id) {
        return null;
    }

    @Override
    public List<UserInfo> findAll() {
        return null;
    }

    @Override
    public List<UserInfo> getRecordsByField(Map<String, Object> params) {
        return null;
    }

    @Override
    public int getCountByFields(Map<String, Object> params) {
        return 0;
    }
}

package com.just.toyim.dao;

import com.just.toyim.dao.meta.GroupInfo;
import com.just.toyim.dao.meta.UserInfo;

import java.util.List;

public interface GroupDao extends IBaseDao<GroupInfo>{

    List<GroupInfo> getByUserId(String userId);
}

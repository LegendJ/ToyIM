package com.just.toyim.dao.impl;

import com.just.toyim.dao.GroupDao;
import com.just.toyim.dao.meta.GroupInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GroupDaoImpl extends BaseDao implements GroupDao {
    @Override
    public long add(GroupInfo groupInfo) {
        return 0;
    }

    @Override
    public int update(GroupInfo groupInfo) {
        return 0;
    }

    @Override
    public int delete(GroupInfo groupInfo) {
        return 0;
    }

    @Override
    public GroupInfo get(long id) {
        return null;
    }

    @Override
    public List<GroupInfo> findAll() {
        return null;
    }

    @Override
    public List<GroupInfo> getRecordsByField(Map<String, Object> params) {
        return null;
    }

    @Override
    public int getCountByFields(Map<String, Object> params) {
        return 0;
    }
}

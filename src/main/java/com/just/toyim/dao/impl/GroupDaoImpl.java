package com.just.toyim.dao.impl;

import com.just.toyim.dao.GroupDao;
import com.just.toyim.dao.meta.GroupInfo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@Component
public class GroupDaoImpl extends BaseDao implements GroupDao {

    @Override
    public List<GroupInfo> getByUserId(String userId) {
        String sql = "select distinct g.* from (toyim_user as u join toyim_group_user as gu on u.id = gu.user_id) join toyim_group as g on gu.group_id = g.id where u.id=:id ";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("id", userId), new GroupInfoRowMapper());
    }

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
    public GroupInfo get(String id) {
        String sql = "SELECT * FROM toyim_group WHERE id=:id";
        return queryForObject(sql, new MapSqlParameterSource("id", id), new GroupInfoRowMapper());
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

    class GroupInfoRowMapper implements RowMapper<GroupInfo> {

        @Override
        public GroupInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            GroupInfo info = new GroupInfo();

            info.setGroupId(resultSet.getString("id"));
            info.setGroupAvatarUrl(resultSet.getString("groupAvatarUrl"));
            info.setGroupName(resultSet.getString("groupName"));
            return info;
        }
    }
}

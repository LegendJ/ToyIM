package com.just.toyim.dao.impl;

import com.just.toyim.dao.UserDao;
import com.just.toyim.dao.meta.GroupInfo;
import com.just.toyim.dao.meta.UserInfo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao {


    @Override
    public List<UserInfo> findFriends(long userId) {
        String sql = "select u.* from toyim_user as u join toyim_friend as f on u.id = f.friend_id where f.user_id=:id ";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("id", userId), new UserInfoRowMapper());
    }

    @Override
    public List<UserInfo> findGroupUsers(long groupId) {
        String sql = "select u.* from toyim_user as u join toyim_group_user as g on u.id = g.user_id where g.group_id=:id ";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("id", groupId), new UserInfoRowMapper());
    }

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
        String sql = "SELECT * FROM toyim_user WHERE id=:id";
        return queryForObject(sql, new MapSqlParameterSource("id", id), new UserInfoRowMapper());
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

    class UserInfoRowMapper implements RowMapper<UserInfo> {

        @Override
        public UserInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(resultSet.getLong("id"));
            userInfo.setUsername(resultSet.getString("username"));
            userInfo.setPassword(resultSet.getString("password"));
            userInfo.setAvatarUrl(resultSet.getString("avatarUrl"));
            userInfo.setRole(resultSet.getString("role"));
            return userInfo;
        }
    }
}

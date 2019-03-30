package com.just.toyim.service.meta;


import com.just.toyim.dao.meta.UserInfo;

import java.util.List;

public class UserDto {

    String userId;
    String username;
    String password;
    String avatarUrl;
    String role;
    List<UserInfo> friendList;
    List<GroupDto> groupDtos;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UserInfo> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<UserInfo> friendList) {
        this.friendList = friendList;
    }

    public List<GroupDto> getGroupDtos() {
        return groupDtos;
    }

    public void setGroupDtos(List<GroupDto> groupDtos) {
        this.groupDtos = groupDtos;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userId = userInfo.getId();
        this.username = userInfo.getUsername();
        this.password = userInfo.getPassword();
        this.avatarUrl = userInfo.getAvatarUrl();
        this.role = userInfo.getRole();
    }
}

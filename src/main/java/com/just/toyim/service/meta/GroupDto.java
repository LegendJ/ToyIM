package com.just.toyim.service.meta;

import com.just.toyim.dao.meta.GroupInfo;
import com.just.toyim.dao.meta.UserInfo;

import java.util.List;

public class GroupDto {

    private String groupId;
    private String groupName;
    private String avatarUrl;
    private List<UserInfo> members;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<UserInfo> getMembers() {
        return members;
    }

    public void setMembers(List<UserInfo> members) {
        this.members = members;
    }

    public void setGroupInfo(GroupInfo groupInfo) {
        groupId = groupInfo.getGroupId();
        groupName = groupInfo.getGroupName();
        avatarUrl = groupInfo.getAvatarUrl();
    }
}

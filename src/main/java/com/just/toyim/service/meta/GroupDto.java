package com.just.toyim.service.meta;

import com.just.toyim.dao.meta.GroupInfo;
import com.just.toyim.dao.meta.UserInfo;

import java.util.List;

public class GroupDto {

    private String groupId;
    private String groupName;
    private String groupAvatarUrl;
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

    public String getGroupAvatarUrl() {
        return groupAvatarUrl;
    }

    public void setGroupAvatarUrl(String groupAvatarUrl) {
        this.groupAvatarUrl = groupAvatarUrl;
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
        groupAvatarUrl = groupInfo.getGroupAvatarUrl();
    }
}

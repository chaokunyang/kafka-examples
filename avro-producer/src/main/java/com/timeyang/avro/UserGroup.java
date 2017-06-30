package com.timeyang.avro;

import java.util.List;

/**
 * @author yangck
 */
public class UserGroup {
    private String groupName;
    private String interests;
    private List<User> users;

    public UserGroup(String groupName, String interests, List<User> users) {
        this.groupName = groupName;
        this.interests = interests;
        this.users = users;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

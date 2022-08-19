package uk.fergcb.sakila.data.security;

import java.io.Serializable;

public class User implements Serializable {
    private String userId;
    private String username;
    private String group;

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}

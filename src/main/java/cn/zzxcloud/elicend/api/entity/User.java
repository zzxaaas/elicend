package cn.zzxcloud.elicend.api.entity;

import cn.zzxcloud.elicend.common.persistence.BaseEntity;

public class User extends BaseEntity {

    private String loginCode;
    private String password;
    private String username;
    private String privateFilePath;

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrivateFilePath() {
        return privateFilePath;
    }

    public void setPrivateFilePath(String privateFilePath) {
        this.privateFilePath = privateFilePath;
    }

    @Override
    public String toString() {
        return "User{" +
                "loginCode='" + loginCode + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", privateFilePath='" + privateFilePath + '\'' +
                '}';
    }
}

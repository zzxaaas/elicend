package cn.zzxcloud.elicend.api.entity;

import cn.zzxcloud.elicend.common.persistence.BaseEntity;
import lombok.Data;

@Data
public class User extends BaseEntity {

    private String loginCode;
    private String password;
    private String username;
    private String privateFilePath;

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

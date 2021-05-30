package cn.zzxcloud.elicend.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTO {
    private Integer id;
    private String loginCode;

    @JsonIgnore
    private String password;
    private String username;
    private String privateFilePath;
}

package cn.zzxcloud.elicend.mapper;

import cn.zzxcloud.elicend.entity.User;

public interface UserMapper {

    User getUserByLoginCode(String loginCode);
    User getUserByUsername(String loginCode);
    void saveUser(User user);
}

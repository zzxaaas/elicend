package cn.zzxcloud.elicend.api.mapper;

import cn.zzxcloud.elicend.api.entity.User;
import cn.zzxcloud.elicend.common.persistence.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过账号获取用户
     * @param loginCode
     * @return
     */
    User getUserByLoginCode(String loginCode);

    /**
     * 通过用户名获取用户
     * @param loginCode
     * @return
     */
    User getUserByUsername(String loginCode);

    String getPrivateFilePathById(int id);

}

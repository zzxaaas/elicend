package cn.zzxcloud.elicend.api.service;

import cn.zzxcloud.elicend.api.entity.User;
import cn.zzxcloud.elicend.common.persistence.BaseService;

public interface UserService extends BaseService<User> {

   User login(String loginCode,String password);

}

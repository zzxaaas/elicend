package cn.zzxcloud.elicend.api.service.impl;

import cn.zzxcloud.elicend.api.entity.User;
import cn.zzxcloud.elicend.api.mapper.UserMapper;
import cn.zzxcloud.elicend.api.service.UserService;
import cn.zzxcloud.elicend.common.abstracts.AbstractBaseServiceImpl;
import cn.zzxcloud.elicend.common.constant.Constant;
import cn.zzxcloud.elicend.common.dto.BaseResult;
import cn.zzxcloud.elicend.common.persistence.BaseService;
import cn.zzxcloud.elicend.common.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.apache.commons.lang3.StringUtils;


@Service
public class UserServiceImpl extends AbstractBaseServiceImpl<User, UserMapper> implements UserService {


    @Override
    public BaseResult save(User user) {

        // 新增用户
        if (user.getId() == null){
            if (mapper.getUserByLoginCode(user.getLoginCode()) != null){
                return BaseResult.fail("账号已存在");
            }
            if (mapper.getUserByUsername(user.getUsername()) != null){
                return BaseResult.fail("用户名已存在，不可重复");
            }
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            String userPrivateFilePath = Constant.USER_ROOT_PATH+user.getLoginCode()+"/";
            user.setPrivateFilePath(userPrivateFilePath);
            FileUtil.mkdirs(userPrivateFilePath);
            mapper.insert(user);
        }
        // 编辑用户
        else{
            if (StringUtils.isBlank(user.getPassword())){
                User oldUser = getById(user.getId());
                user.setPassword(oldUser.getPassword());
            } else {
                user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            }
            update(user);
        }

        return BaseResult.success("保存用户信息成功");
    }

    @Override
    public User login(User loginUser) {
        User user  = mapper.getUserByLoginCode(loginUser.getLoginCode());
        if (user != null) {
            String password = DigestUtils.md5DigestAsHex(loginUser.getPassword().getBytes());
            if (password.equals(user.getPassword())){
                return user;
            }
        }
        return null;
    }
}

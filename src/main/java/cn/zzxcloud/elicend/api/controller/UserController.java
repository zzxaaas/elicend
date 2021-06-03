package cn.zzxcloud.elicend.api.controller;

import cn.zzxcloud.elicend.api.dto.UserDTO;
import cn.zzxcloud.elicend.api.entity.User;
import cn.zzxcloud.elicend.api.service.UserService;
import cn.zzxcloud.elicend.common.dto.BaseResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public BaseResult login(@RequestBody User loginUser) {

        User user = userService.login(loginUser);
        if (user == null) {
            return BaseResult.fail("账号或密码错误");
        } else {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            return BaseResult.success("success", userDTO);
        }
    }

    @RequestMapping("/register")
    public BaseResult register(@RequestBody User user) {
        return userService.save(user);
    }

}

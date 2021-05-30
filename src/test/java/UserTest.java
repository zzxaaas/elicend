import cn.zzxcloud.elicend.Application;
import cn.zzxcloud.elicend.api.entity.User;
import cn.zzxcloud.elicend.api.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void testInsert() {
        User user = new User();
//        user.setId(3);
        user.setUsername("zzxaaas");
        user.setLoginCode("zzxaaas");
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        userService.save(user);
    }

}

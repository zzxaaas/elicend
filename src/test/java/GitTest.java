import cn.zzxcloud.elicend.Application;
import cn.zzxcloud.elicend.common.constant.Constant;
import cn.zzxcloud.elicend.common.utils.JGitUtil;
import cn.zzxcloud.elicend.common.vo.GitVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class GitTest {

    @Test
    public void gitTest(){
        GitVO gitVO = new GitVO.GitVOBuilder(Constant.GIT_USERNAME,Constant.GIT_PASSWORD)
                .setLocalRepoGitConfig("D:\\Users\\Zzxaaas\\Desktop\\gittest")
                .setRemoteRepoUri("https://github.com.cnpmjs.org/zzxaaas/elicend.git")
                .build();
        JGitUtil jGitUtil = new JGitUtil(gitVO);
        jGitUtil.setupRepository();
//        gitVO.setLocalRepoGitConfig("D:\\Users\\Zzxaaas\\Desktop\\gittest\\.git");
        jGitUtil.pull();
    }


}

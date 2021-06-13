import cn.zzxcloud.elicend.Application;
import cn.zzxcloud.elicend.api.entity.Project;
import cn.zzxcloud.elicend.api.entity.User;
import cn.zzxcloud.elicend.api.service.ProjectService;
import cn.zzxcloud.elicend.api.service.UserService;
import cn.zzxcloud.elicend.common.vo.ProjectVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ProjectTest {

    @Autowired
    private ProjectService projectService;

    @Test
    public void selectAll() {
        List<Project> projectList = projectService.getAllByUserId(12);
        ProjectVO projectVO = new ProjectVO();
        for (Project project:projectList) {
            BeanUtils.copyProperties(project, projectVO);
            System.out.println(projectVO);
        }

    }

    @Test
    public void build() {
        Project project = projectService.getById(11);
        projectService.buildProject(project);

    }
}

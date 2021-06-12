package cn.zzxcloud.elicend.api.controller;

import cn.zzxcloud.elicend.api.entity.Project;
import cn.zzxcloud.elicend.api.service.ProjectService;
import cn.zzxcloud.elicend.api.service.UserService;
import cn.zzxcloud.elicend.common.dto.BaseResult;
import cn.zzxcloud.elicend.common.vo.ProjectVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/save")
    public BaseResult saveProject(@RequestBody Project project){
        String privateFilePath = userService.getPrivateFilePathById(project.getUserId());
        if ( privateFilePath == null || privateFilePath.equals("")){
            return BaseResult.fail("用户私有文件路径出错");
        }

        String localRepo = privateFilePath  + project.getProjectName() + "\\repo\\";
        project.setLocalRepo(localRepo);
        project.setState(0);
        Random random = new Random(1);
        project.setBindPort(random.nextInt(65534)+1);
        return projectService.save(project);
    }

    @GetMapping("/all")
    public BaseResult getAll(@RequestParam int userId){
        List<Project> projectList = projectService.getAllByUserId(userId);
        List<ProjectVO> projectVOList = new ArrayList<>();
        ProjectVO projectVO = new ProjectVO();
        for (Project project:projectList) {
            BeanUtils.copyProperties(project, projectVO);
            projectVOList.add(projectVO);
        }
        return BaseResult.success("success",projectVOList);
    }

    @GetMapping("/build")
    public BaseResult buildProject(@RequestParam int projectId){
        projectService.buildProject(projectId);
        return BaseResult.success();
    }
}

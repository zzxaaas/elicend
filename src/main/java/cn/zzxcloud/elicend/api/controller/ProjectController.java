package cn.zzxcloud.elicend.api.controller;

import cn.zzxcloud.elicend.api.entity.BuildHistory;
import cn.zzxcloud.elicend.api.entity.Project;
import cn.zzxcloud.elicend.api.service.BuildHistoryService;
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

    @Autowired
    private BuildHistoryService buildHistoryService;

    @PostMapping("/save")
    public BaseResult saveProject(@RequestBody Project project){
        String privateFilePath = userService.getPrivateFilePathById(project.getUserId());
        if ( privateFilePath == null || privateFilePath.equals("")){
            return BaseResult.fail("用户私有文件路径出错");
        }

        String localRepo = privateFilePath  + project.getProjectName() + "/repo/";
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
        for (Project project:projectList) {
            ProjectVO projectVO = new ProjectVO();
            BeanUtils.copyProperties(project, projectVO);
            projectVOList.add(projectVO);
        }
        return BaseResult.success("success",projectVOList);
    }

    @GetMapping("/single")
    public BaseResult getProjectById(@RequestParam int projectId){
        Project project = projectService.getById(projectId);
        ProjectVO projectVO = new ProjectVO();
        BeanUtils.copyProperties(project, projectVO);

        return BaseResult.success("success",projectVO);
    }

    @GetMapping("/git/pull")
    public BaseResult gitFromRepo(@RequestParam int projectId){
        Project project = projectService.getById(projectId);
        BuildHistory buildHistory = new BuildHistory(projectId,0);
        buildHistoryService.insert(buildHistory);

        buildHistory.setGitMsg("git pull from " + project.getGitRepoUri());
        buildHistory.setState(1);
        buildHistoryService.update(buildHistory);

        projectService.gitFromRepo(project);

        buildHistory.setGitMsg(buildHistory.getGitMsg()+"</br>git pull success");
        buildHistory.setState(2);
        buildHistoryService.update(buildHistory);

        return BaseResult.success("success",buildHistory);
    }

    @GetMapping("/build")
    public BaseResult buildProject(@RequestParam int buildId){
        BuildHistory buildHistory = buildHistoryService.getById(buildId);
        Project project = projectService.getById(buildHistory.getProjectId());
        projectService.buildProject(project,buildId);
        return BaseResult.success();
    }

    @GetMapping("/state")
    public BaseResult updateState(@RequestParam int projectId,int state){
        projectService.updateStateById(projectId,state);
        return BaseResult.success();
    }
}

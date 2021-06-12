package cn.zzxcloud.elicend.api.service.impl;

import cn.zzxcloud.elicend.api.entity.Project;
import cn.zzxcloud.elicend.api.mapper.ProjectMapper;
import cn.zzxcloud.elicend.api.service.ProjectService;
import cn.zzxcloud.elicend.common.abstracts.AbstractBaseServiceImpl;
import cn.zzxcloud.elicend.common.constant.Constant;
import cn.zzxcloud.elicend.common.dto.BaseResult;
import cn.zzxcloud.elicend.common.utils.DockerUtil;
import cn.zzxcloud.elicend.common.utils.FileUtil;
import cn.zzxcloud.elicend.common.utils.JGitUtil;
import cn.zzxcloud.elicend.common.vo.GitVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ProjectServiceImpl extends AbstractBaseServiceImpl<Project, ProjectMapper> implements ProjectService {
    @Override
    public BaseResult save(Project entity) {

        if (mapper.getByName(entity.getProjectName()) != null){
            return BaseResult.fail("项目名不可重复");
        }
        FileUtil.mkdirs(entity.getLocalRepo());

        GitVO gitVO = new GitVO.GitVOBuilder(Constant.GIT_USERNAME,Constant.GIT_PASSWORD)
                .setLocalRepoPath(entity.getLocalRepo())
                .setRemoteRepoUri(entity.getGitRepoUri())
                .build();

        JGitUtil jGitUtil = new JGitUtil(gitVO);

        if (jGitUtil.setupRepository()) {
            return BaseResult.fail("git拉取仓库错误");
        }

        mapper.insert(entity);
        return BaseResult.success();
    }

    @Override
    public List<Project> getAllByUserId(int userId) {
        return mapper.selectAllByUserId(userId);
    }

    @Override
    public void gitFromRepo(int id) {
        Project project = mapper.getById(id);
        GitVO gitVO = new GitVO.GitVOBuilder(Constant.GIT_USERNAME,Constant.GIT_PASSWORD).build();
        JGitUtil jGitUtil = new JGitUtil(gitVO);
        gitVO.setLocalRepoGitConfig(project.getLocalRepo() + "\\.git");
        jGitUtil.pull();
    }

    @Override
    public void buildProject(int id) {
        Project project = mapper.getById(id);
        DockerUtil dockerUtil = new DockerUtil();
        String baseImage="";
        if(project.getLanguage().equals("Golang")){
            baseImage = "library/golang:" + project.getLanguageVersion();
        }
        dockerUtil.pullImage(baseImage);

        String[] env = {"GO111MODULE=on","GOPROXY=https://goproxy.io","CGO_ENABLED=0","GOOS=linux"};
        dockerUtil.createContainer(baseImage,project.getBindPort(),project.getPort(),"/data/app","go build -o app;./app;",env);

    }
}

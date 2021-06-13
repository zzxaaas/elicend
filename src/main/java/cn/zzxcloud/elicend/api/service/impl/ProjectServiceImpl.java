package cn.zzxcloud.elicend.api.service.impl;

import cn.zzxcloud.elicend.api.entity.Project;
import cn.zzxcloud.elicend.api.mapper.ProjectMapper;
import cn.zzxcloud.elicend.api.service.ProjectService;
import cn.zzxcloud.elicend.common.abstracts.AbstractBaseServiceImpl;
import cn.zzxcloud.elicend.common.constant.Constant;
import cn.zzxcloud.elicend.common.dto.BaseResult;
import cn.zzxcloud.elicend.common.lang.GOLANG;
import cn.zzxcloud.elicend.common.lang.Lang;
import cn.zzxcloud.elicend.common.lang.LangFactory;
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

        System.out.println("git from "+gitVO.getRemoteRepoUri());
        if (!jGitUtil.setupRepository()) {
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
    public void gitFromRepo(Project project) {
        GitVO gitVO = new GitVO.GitVOBuilder(Constant.GIT_USERNAME,Constant.GIT_PASSWORD).build();
        JGitUtil jGitUtil = new JGitUtil(gitVO);
        gitVO.setLocalRepoGitConfig(project.getLocalRepo() + "/.git");
        jGitUtil.pull();
    }

    @Override
    public void buildProject(Project project) {
        DockerUtil dockerUtil = new DockerUtil();
        LangFactory factory=new LangFactory();
        Lang lang = factory.getInstance(project.getLanguage());
        String baseImage = lang.getBaseImage() + project.getLanguageVersion();
        String[] env = lang.getEnv();
        String baseCmd = lang.getBaseCmd();
        dockerUtil.pullImage(baseImage);
        if(project.getContainer()!=null){
            dockerUtil.removeContainer(project.getContainer());
        }
        String containerId = dockerUtil.createContainer(baseImage,project.getBindPort(),project.getPort(),project.getLocalRepo(),project.getCommand() + baseCmd, env);
        project.setContainer(containerId);
        mapper.update(project);

    }
}

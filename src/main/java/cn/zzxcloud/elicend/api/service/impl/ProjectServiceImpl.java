package cn.zzxcloud.elicend.api.service.impl;

import cn.zzxcloud.elicend.api.entity.Project;
import cn.zzxcloud.elicend.api.mapper.ProjectMapper;
import cn.zzxcloud.elicend.api.service.ProjectService;
import cn.zzxcloud.elicend.common.abstracts.AbstractBaseServiceImpl;
import cn.zzxcloud.elicend.common.dto.BaseResult;
import cn.zzxcloud.elicend.common.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractBaseServiceImpl<Project, ProjectMapper> implements ProjectService {
    @Override
    public BaseResult save(Project entity) {

        if (mapper.getByName(entity.getProjectName()) != null){
            return BaseResult.fail("项目名不可重复");
        }
        FileUtil.mkdirs(entity.getLocalRepo());
        mapper.insert(entity);
        return BaseResult.success();
    }

    @Override
    public List<Project> getAllByUserId(int userId) {
        return mapper.selectAllByUserId(userId);
    }
}

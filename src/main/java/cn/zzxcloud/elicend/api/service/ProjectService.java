package cn.zzxcloud.elicend.api.service;

import cn.zzxcloud.elicend.api.entity.Project;
import cn.zzxcloud.elicend.common.persistence.BaseService;

import java.util.List;

public interface ProjectService extends BaseService<Project> {
    List<Project> getAllByUserId(int userId);
    void gitFromRepo(Project project);
    void buildProject(Project project,int buildId);
    void updateStateById(int id,int state);
}

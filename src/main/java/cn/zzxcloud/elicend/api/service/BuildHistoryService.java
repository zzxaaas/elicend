package cn.zzxcloud.elicend.api.service;

import cn.zzxcloud.elicend.api.entity.BuildHistory;
import cn.zzxcloud.elicend.common.persistence.BaseService;

import java.util.List;

public interface BuildHistoryService extends BaseService<BuildHistory> {
    List<BuildHistory> getByProjectId(int projectId);
    void updateStateById(int id,int state);
}

package cn.zzxcloud.elicend.api.service.impl;

import cn.zzxcloud.elicend.api.entity.BuildHistory;
import cn.zzxcloud.elicend.api.mapper.BuildHistoryMapper;
import cn.zzxcloud.elicend.api.service.BuildHistoryService;
import cn.zzxcloud.elicend.common.abstracts.AbstractBaseServiceImpl;
import cn.zzxcloud.elicend.common.dto.BaseResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildHistoryImpl extends AbstractBaseServiceImpl<BuildHistory, BuildHistoryMapper> implements BuildHistoryService {
    @Override
    public BaseResult save(BuildHistory entity) {
        mapper.insert(entity);
        return BaseResult.success();
    }

    @Override
    public List<BuildHistory> getByProjectId(int projectId) {
        return mapper.getByProjectId(projectId);
    }

    @Override
    public void updateStateById(int id, int state) {
        mapper.updateStateById(id,state);
    }
}

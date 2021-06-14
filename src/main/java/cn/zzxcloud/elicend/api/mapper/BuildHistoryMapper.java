package cn.zzxcloud.elicend.api.mapper;

import cn.zzxcloud.elicend.api.entity.BuildHistory;
import cn.zzxcloud.elicend.common.persistence.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BuildHistoryMapper extends BaseMapper<BuildHistory> {

    List<BuildHistory> getByProjectId(int projectId);
    void updateStateById(int id,int state);
}

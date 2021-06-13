package cn.zzxcloud.elicend.api.mapper;

import cn.zzxcloud.elicend.api.entity.Project;
import cn.zzxcloud.elicend.common.persistence.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProjectMapper extends BaseMapper<Project> {
    Project getByName(String name);
    List<Project> selectAllByUserId(int userId);
    String getGitRepoById(int id);
}

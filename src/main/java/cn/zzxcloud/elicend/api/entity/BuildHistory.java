package cn.zzxcloud.elicend.api.entity;

import cn.zzxcloud.elicend.common.persistence.BaseEntity;
import lombok.Data;

@Data
public class BuildHistory extends BaseEntity {

    private int projectId;
    private String gitMsg;
    private String buildMsg;
    private String runMsg;
    private int state;

    public BuildHistory() {
    }

    public BuildHistory(int projectId, int state) {
        this.projectId = projectId;
        this.state = state;
    }

    @Override
    public String toString() {
        return "BuildHistory{" +
                "projectId=" + projectId +
                ", gitMsg='" + gitMsg + '\'' +
                ", buildMsg='" + buildMsg + '\'' +
                ", runMsg='" + runMsg + '\'' +
                ", state=" + state +
                '}';
    }
}

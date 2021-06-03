package cn.zzxcloud.elicend.api.entity;

import cn.zzxcloud.elicend.common.persistence.BaseEntity;
import lombok.Data;

@Data
public class Project extends BaseEntity {

    private int userId;
    private String language;
    private String language_version;
    private String gitRepoUri;
    private String projectName;
    private String projectVersion;
    private String command;
    private String sqlSys;
    private String sqlVersion;
    private String container;
    private int state;

    @Override
    public String toString() {
        return "Project{" +
                "userId=" + userId +
                ", language='" + language + '\'' +
                ", language_version='" + language_version + '\'' +
                ", gitRepoUri='" + gitRepoUri + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectVersion='" + projectVersion + '\'' +
                ", command='" + command + '\'' +
                ", sqlSys='" + sqlSys + '\'' +
                ", sqlVersion='" + sqlVersion + '\'' +
                ", container='" + container + '\'' +
                ", state=" + state +
                '}';
    }
}

package cn.zzxcloud.elicend.api.entity;

import cn.zzxcloud.elicend.common.persistence.BaseEntity;
import lombok.Data;

@Data
public class Project extends BaseEntity {

    private int userId;
    private String language;
    private String languageVersion;
    private String gitRepoUri;
    private int port;
    private int bindPort;
    private String localRepo;
    private String projectName;
    private String projectVersion;
    private String command;
    private String db;
    private String dbVersion;
    private String container;
    private int state;

    @Override
    public String toString() {
        return "Project{" +
                "userId=" + userId +
                ", language='" + language + '\'' +
                ", languageVersion='" + languageVersion + '\'' +
                ", gitRepoUri='" + gitRepoUri + '\'' +
                ", port=" + port +
                ", bindPort=" + bindPort +
                ", localRepo='" + localRepo + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectVersion='" + projectVersion + '\'' +
                ", command='" + command + '\'' +
                ", db='" + db + '\'' +
                ", dbVersion='" + dbVersion + '\'' +
                ", container='" + container + '\'' +
                ", state=" + state +
                '}';
    }
}

package cn.zzxcloud.elicend.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Time;

@Data
public class ProjectVO implements Serializable {
    private Integer id;
    private String projectName;
    private String projectVersion;
    private String language;
    private boolean hasDB;
    private int state;
    private Time gmtCreate;
}
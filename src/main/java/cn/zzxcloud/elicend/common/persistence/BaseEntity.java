package cn.zzxcloud.elicend.common.persistence;

import java.io.Serializable;
import java.sql.Time;

public abstract class BaseEntity implements Serializable {
    private Integer id;
    private Time gmtCreate;
    private Time gmtModifyed;
    private int isDelete;

    public Integer getId() {
        return id;
    }

    public Time getGmtCreate() {
        return gmtCreate;
    }

    public Time getGmtModifyed() {
        return gmtModifyed;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}

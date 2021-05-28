package cn.zzxcloud.elicend.entity;

import java.sql.Time;

public class Common {
    private int id;
    private Time gmtCreate;
    private Time gmtModifyed;
    private int isDelete;

    public int getId() {
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

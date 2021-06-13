package cn.zzxcloud.elicend.common.lang;

import lombok.Data;

@Data
public abstract class Lang {
    private String[] env;
    private String baseCmd;
    private String baseImage;
    abstract void make();
}

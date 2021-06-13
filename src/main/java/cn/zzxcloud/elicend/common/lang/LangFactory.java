package cn.zzxcloud.elicend.common.lang;

public class LangFactory {

    public Lang getInstance(String langType){
        if(langType.equalsIgnoreCase("Golang")){
            return new GOLANG();
        }
        return null;
    }
}

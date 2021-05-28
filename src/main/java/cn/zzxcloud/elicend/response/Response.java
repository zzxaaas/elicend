package cn.zzxcloud.elicend.response;

import java.util.HashMap;
import java.util.Map;

public class Response {

    public static Map<String,Object> success(){
        return new HashMap<String, Object>(){{
            put("status",1);
            put("message","success");
        }};
    }

    public static Map<String,Object> successWithData(Object obj){
        return new HashMap<String, Object>(){{
            put("status",1);
            put("message","success");
            put("data",obj);
        }};
    }

    public static Map<String,Object> error(String msg){
        return new HashMap<String, Object>(){{
            put("status",0);
            put("error",msg);
        }};
    }

    public static Map<String,Object> error(int status,String msg){
        return new HashMap<String, Object>(){{
            put("status",status);
            put("error",msg);
        }};
    }

}

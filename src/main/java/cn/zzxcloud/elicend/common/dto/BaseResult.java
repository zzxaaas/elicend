package cn.zzxcloud.elicend.common.dto;
import cn.zzxcloud.elicend.common.constant.Constant;

import java.io.Serializable;

public class BaseResult implements Serializable {


    private int status;
    private String message;
    private Object data;

    public static BaseResult success() {
        return BaseResult.createResult(Constant.STATUS_SUCCESS, "success", null);
    }

    public static BaseResult success(String message) {
        return BaseResult.createResult(Constant.STATUS_SUCCESS, message, null);
    }

    public static BaseResult success(String message, Object data) {
        return BaseResult.createResult(Constant.STATUS_SUCCESS, message, data);
    }

    public static BaseResult fail() {
        return BaseResult.createResult(Constant.STATUS_FAIL, "fail", null);
    }

    public static BaseResult fail(String message) {
        return BaseResult.createResult(Constant.STATUS_FAIL, message, null);
    }

    public static BaseResult fail(int status, String message) {
        return BaseResult.createResult(status, message, null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private static BaseResult createResult(int status, String message, Object data) {
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        baseResult.setData(data);
        return baseResult;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

package com.cui.blog.web.form;

import java.io.Serializable;

/**
 * 封装返回给外围系统的结果数据模型
 * Created by cuishixiang on 2017-09-12.
 */
public class BaseResult implements Serializable {

    /**
     * 服务是否执行成功
     */
    private boolean success = false;
    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 默认构造函数
     */
    BaseResult() {
        super();
    }

    /**
     * 构造函数设置执行结果是否成功
     *
     * @param success 服务是否执行成功
     */
    public BaseResult(boolean success) {
        this.success = success;
    }

    /**
     * 带参数构造函数
     *
     * @param errorCode    错误码
     * @param errorMessage 错误信息
     */
    public BaseResult(String errorCode, String errorMessage) {
        this(false, errorCode, errorMessage);
    }

    /**
     * 带参数构造函数
     *
     * @param success      服务是否执行成功
     * @param errorCode    错误码
     * @param errorMessage 错误信息
     */
    public BaseResult(boolean success, String errorCode, String errorMessage) {
        super();
        this.success = success;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "success=" + success +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}

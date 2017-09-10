package com.cui.common.exception;

import com.cui.common.errorMessage.AbstractErrorMessageFactory;
import com.cui.common.errorMessage.ErrorMessage;

import java.lang.reflect.InvocationTargetException;

/**
 * 运行时基础异常，所有的异常定义都应继承该异常
 * <p>
 * Created by cuishixiang on 2017-09-10.
 */
public class CommonRuntimeException extends RuntimeException {

    /**
     * 异常描述文本
     */
    private String message;
    /**
     * 错误消息对象
     */
    private ErrorMessage errorMessage;

    /**
     * 构造方法
     *
     * @param errorMessage 错误实例
     */
    public CommonRuntimeException(ErrorMessage errorMessage) {
        super();
        setErrorMessage(errorMessage);
    }

    /**
     * 构造方法
     *
     * @param errorMessage 错误实例
     * @param cause        异常
     */
    public CommonRuntimeException(ErrorMessage errorMessage, Throwable cause) {
        super(CommonRuntimeException.unwrap(cause));
        setErrorMessage(errorMessage);
    }

    /**
     * 构造方法
     *
     * @param cause 异常
     */
    public CommonRuntimeException(Throwable cause) {
        super(CommonRuntimeException.unwrap(cause));
        if (cause != null) {
            setErrorMessage(AbstractErrorMessageFactory.createStaticError(cause.getMessage() + " (" + cause.getClass().getName() + ")", ErrorMessage.DEFAULT_ERROR_CODE));
        }
    }


    /**
     * 获取异常描述文本
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 获取该异常的错误消息
     *
     * @return 错误消息
     */
    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    /**
     * 获取错误消息的错误码
     *
     * @return 错误码
     */
    public String getErrorCode() {
        return errorMessage == null ? errorMessage.DEFAULT_ERROR_CODE : errorMessage.getErrorCode();
    }

    /**
     * 在原错误信息基础上，添加额外错误描述
     *
     * @param s 额外错误描述
     */
    protected void appendMessage(String s) {
        message += s;
    }

    /**
     * 预处理消息
     *
     * @param s 预处理消息描述
     */
    protected void prependMessage(String s) {
        message = message + ". " + s;
    }

    /**
     * 设置异常的错误消息
     * <p>
     * 一般来说，一条错误消息针对资源文件中的一条配置记录
     * 错误码＝错误描述
     *
     * @param errorMessage 资源文件描述的错误消息
     */
    private void setErrorMessage(ErrorMessage errorMessage) {
        this.message = errorMessage.getMessage();
        this.errorMessage = errorMessage;
    }


    /**
     * 处理Java动态代理产生的异常，如果异常类型是动态代理反射异常，则获取真实异常类型
     *
     * @param t Java异常
     * @return 实际Java异常
     */
    @SuppressWarnings("unchecked")
    private static <T extends Throwable> T unwrap(T t) {
        if (t instanceof InvocationTargetException) {
            return ((T) ((InvocationTargetException) t).getTargetException());
        }
        return t;
    }

}

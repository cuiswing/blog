package com.cui.blog.biz.exception;

import com.cui.common.errorMessage.ErrorMessage;
import com.cui.common.exception.CommonException;

/**
 * blog相关业务异常
 * <p>
 * Created by cuishixiang on 2017-09-08.
 */
public class BlogException extends CommonException {

    /**
     * 构造方法
     *
     * @param errorMessage 错误实例
     */
    public BlogException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

    /**
     * 构造方法
     *
     * @param errorMessage 错误实例
     * @param cause        异常
     */
    public BlogException(ErrorMessage errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    /**
     * 构造方法
     *
     * @param cause 异常
     */
    public BlogException(Throwable cause) {
        super(cause);
    }
}

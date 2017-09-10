package com.cui.common.exception;

import com.cui.common.errorMessage.ErrorMessage;

/**
 * util工具类统一异常，如此设计：将所有的checked exception包装成runtime exception后抛出去，异常链不能掉，但是又不能给调用者造成麻烦
 * <p>
 * 可参见：http://www.iteye.com/topic/2038
 * <p>
 * Created by cuishixiang on 2017-09-09.
 */
public class UtilException extends CommonRuntimeException {

    public UtilException(Throwable cause) {
        super(cause);
    }

    public UtilException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

    public UtilException(ErrorMessage errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}

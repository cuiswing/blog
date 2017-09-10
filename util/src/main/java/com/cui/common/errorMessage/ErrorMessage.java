package com.cui.common.errorMessage;

import com.cui.common.i18n.Message;

/**
 * 错误信息基础模型
 * <p>
 * #CE00101
 * #第1-2位是项目错误码标识，固定位：CE；
 * #第3位是规范版本位，目前为0；
 * #第4位是错误类别，0-系统错误, 1-业务错误, 2-第三方错误
 * #5位代表具体的业务层次：0系统默认使用，1代表web层，2代表业务层(biz)；3代表DAL层；4代表接口层(facade)；5代表串口层(SPI)；6代表集成层(integration)
 * #6、7位代表具体的错误
 * <p>
 * Created by cuishixiang on 2017-09-10.
 */
public class ErrorMessage extends Message {

    /**
     * 默认错误消息, 应用程序切勿使用该错误信息，只有容器框架使用该错误信息
     */
    public static final String DEFAULT_ERROR_MESSAGE = "容器框架内部系统错误";

    /**
     * 默认错误码, 应用程序切勿使用该错误码，只有容器框架使用该错误码
     */
    public static final String DEFAULT_ERROR_CODE = "CE00000";

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * @param errMsg    错误文案模板
     * @param errorCode 错误码
     * @param args      错误文案模板占位符参数
     */
    protected ErrorMessage(String errMsg, String errorCode, Object... args) {
        super(errMsg, errorCode, args);
        this.errorCode = errorCode;
    }

    /**
     * 获取错误码
     *
     * @return 错误码
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Error [errorCode=" + errorCode + ", errorMessage=" + getMessage() + "]";
    }

}

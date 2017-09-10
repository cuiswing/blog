package com.cui.common.errorMessage;

import com.cui.common.i18n.AbstractMessageFactory;
import com.cui.common.i18n.Message;

/**
 * 错误消息资源创建工厂
 * Created by cuishixiang on 2017-09-10.
 */
public abstract class AbstractErrorMessageFactory extends AbstractMessageFactory {
    /**
     * 获取资源文件名称，资源文件名称是不包含文件扩展类型的，其中的路径分隔符要换成.
     * 通过提供的资源文件名称，消息工厂计算出资源文件完整路径
     * <p>
     * 如资源文件路径：
     * <code>META-INF/messages/bundleName-messages_zh_CN.properties</code>
     * 应返回：META-INF.messages.bundleName-messages_zh_CN
     *
     * @return 返回资源文件名称
     */
    protected abstract String provideBundleName();

    /**
     * 创建错误消息对象
     *
     * @param message   错误描述
     * @param errorCode 错误代码
     * @return 错误消息
     */
    public static ErrorMessage createStaticError(String message, String errorCode) {
        return new ErrorMessage(message, errorCode);
    }

    /**
     * 从错误消息描述资源文件中根据错误代码创建错误消息对象
     *
     * @param errorCode 错误代码
     * @param args      错误消息占位符参数
     * @return 错误消息
     */
    protected final ErrorMessage createError(String errorCode, Object... args) {
        Message msg = getMessage(errorCode, args);
        return new ErrorMessage(msg.getMessage(), errorCode, args);
    }
}

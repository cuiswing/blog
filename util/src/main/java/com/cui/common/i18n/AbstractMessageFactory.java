package com.cui.common.i18n;

/**
 * 资源消息抽象工厂
 * Created by cuishixiang on 2017-09-10.
 */
public abstract class AbstractMessageFactory extends MessageFactory {
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
     * Factory method to create a new {@link Message} instance that is filled with the formatted
     * message with id <code>key</code> from xyz-messages.properties.
     *
     * @param key  Message key
     * @param args The parameters for formating message
     * @return Message
     */
    public final Message getMessage(String key, Object... args) {
        String bundleName = provideBundleName();
        if (bundleName == null || "".equals(bundleName)) {
            throw new IllegalArgumentException("The name of the resource bundle is required");
        }
        return createMessage(bundleName, key, args);
    }
}

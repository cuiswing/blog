package com.cui.common.errorMessage;

/**
 * 工具包异常消息工厂
 * <p>
 * Created by cuishixiang on 2017-09-10.
 */
public class UtilErrorMessageFactory extends AbstractErrorMessageFactory {
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
    @Override
    protected String provideBundleName() {
        return "messages.util_error_message";
    }

    /**
     * 获取UtilErrorMessageFactory单例
     *
     * @return
     */
    public static UtilErrorMessageFactory getInstance() {
        return UtilErrorMessageFactoryHolder.INSTANCE;
    }

    /**
     * UtilErrorMessageFactoryHolder instance keeper
     * 这是把单例模式用到了极致，防止对象过早实例化却不使用前占用内存
     */
    private static final class UtilErrorMessageFactoryHolder {
        /**
         * instance
         */
        private static final UtilErrorMessageFactory INSTANCE = new UtilErrorMessageFactory();
    }


    public ErrorMessage createMd5ErrorMessage() {
        return createError("CE00001");
    }
}

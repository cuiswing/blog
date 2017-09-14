package com.cui.blog.biz.errormessage;

import com.cui.common.errorMessage.AbstractErrorMessageFactory;
import com.cui.common.errorMessage.ErrorMessage;

/**
 * Blog相关错误消息对象创建工厂
 * <p>
 * Created by cuishixiang on 2017-09-10.
 */
public class BlogErrorMessageFactory extends AbstractErrorMessageFactory {
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
        return "error.blog_error_messages";
    }

    /**
     * 获取BlogErrorMessageFactory单例
     *
     * @return 单例对象
     */
    public static BlogErrorMessageFactory getInstance() {
        return BlogErrorMessageFactoryHolder.INSTANCE;
    }

    /**
     * ErrorMessageFactoryHolder instance keeper
     * 这是把单例模式用到了极致，防止对象过早实例化却不使用前占用内存
     */
    private static final class BlogErrorMessageFactoryHolder {
        /**
         * instance
         */
        private static final BlogErrorMessageFactory INSTANCE = new BlogErrorMessageFactory();
    }


    /**
     * 用户不存在
     *
     * @param username 账户
     * @return 错误详情
     */
    public ErrorMessage userNotExist(String username) {
        return createError("CE01201", username);
    }

}

package com.cui.common.i18n;

import java.io.Serializable;

/**
 * i18n资源文件消息数据模型
 * <p>
 * Created by cuishixiang on 2017-09-10.
 */
public class Message implements Serializable {
    /**
     * 消息数据文本
     */
    private String message;
    /**
     * 消息代码
     */
    private String messageKey;
    /**
     * 消息模版占位符参数
     */
    private Object[] args;
    /**
     * 下一条消息
     */
    private Message nextMessage;

    protected Message(String message, String messageKey, Object... args) {
        super();
        this.message = message;
        this.messageKey = messageKey;
        this.args = args;
    }

    public String getMessage() {
        return message + (nextMessage != null ? ". " + nextMessage.getMessage() : "");

    }

    public String getMessageKey() {
        return messageKey;
    }

    public Object[] getArgs() {
        return args;
    }

    public Message getNextMessage() {
        return nextMessage;
    }

    /**
     * 设置关联的下一条消息
     *
     * @param nextMessage the nextMessage to set
     */
    public Message setNextMessage(Message nextMessage) {
        this.nextMessage = nextMessage;
        return this;
    }


    @Override
    public String toString() {
        return this.getMessage();
    }
}

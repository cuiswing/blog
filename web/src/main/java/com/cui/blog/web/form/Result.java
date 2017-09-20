package com.cui.blog.web.form;

/**
 * 前端返回的对象模型
 * Created by cuishixiang on 2017-09-12.
 */
public class Result<T> extends BaseResult {

    /**
     * 具体消息对象
     */
    private T data;

    /**
     * 默认构造函数，设置服务执行结果为成功
     */
    public Result() {
        super(true);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                "} " + super.toString();
    }
}

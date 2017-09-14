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

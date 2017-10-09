package com.cui.blog.web.form;

import java.io.Serializable;

/**
 * 分页查询请求
 * Created by cuishixiang on 2017-09-14.
 */
public class PageRequest implements Serializable {

    /**
     * 查询页，默认第一页
     */
    private int pageNo = 1;
    /**
     * 单页数据量，默认20
     */
    private int pageSize = 20;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

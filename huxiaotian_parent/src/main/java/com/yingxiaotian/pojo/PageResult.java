package com.yingxiaotian.pojo;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {

    private long total;//总记录数
    private List<T> list;//每页显示数据

    public PageResult(long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}

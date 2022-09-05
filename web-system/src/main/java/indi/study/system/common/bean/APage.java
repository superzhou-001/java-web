package indi.study.system.common.bean;

import java.io.Serializable;

public class APage<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    // 分页数据
    private T list;

    // 数据总数
    private long count;

    // 当前页
    private int pageNo;

    // 页数据数
    private int pageSize;

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

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

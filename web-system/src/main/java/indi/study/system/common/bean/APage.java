package indi.study.system.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description ="APage")
public class APage<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分页数据")
    private T list;

    @ApiModelProperty("数据总数")
    private long count;

    @ApiModelProperty("当前页")
    private int pageNo;

    @ApiModelProperty("页数据数")
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

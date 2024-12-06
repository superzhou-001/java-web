package indi.study.system.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @version: 1.0
 * @Author：zhouming.zm
 * @Description:
 * @Date：2024/1/16 16:49
 */
@Data
public class ApiChannelReturnModel implements Serializable {
    /**
     * 结果码   0-成功，非0-失败
     */
    private Integer status;

    /**
     * 错误描述，成功时可为空
     */
    private String msg;

    /**
     * 单号
     */
    private String billNo;
}

package indi.study.system.test.vo;

import java.io.Serializable;

/**
 * @Author 99288
 * @Description 商越返回实体类
 * @Date 17:38 2021/1/11
 * @Param
 * @return
 **/
public class SunyurResultSet implements Serializable {

    private static final long serialVersionUID = 2596006438989201663L;
    /**
     * 错误信息־
     */
    private String errorMessage;
    /**
     * 信息码
     */
    private String code = "";
    /**
     * 成功标示
     */
    private static String SUCCESS = "00000";

    public boolean isSuccess() {
        if (getCode() == null) {
            return false;
        }
        return SUCCESS.equals(getCode()) || "success".equals(getCode());
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public String getCode() {
        return this.code;
    }



    public SunyurResultSet(String errorMessage, String code) {
        this.errorMessage = errorMessage;
        this.code = code;
    }

    public SunyurResultSet(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public SunyurResultSet() {
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static String getSUCCESS() {
        return SUCCESS;
    }

    public static void setSUCCESS(String SUCCESS) {
        SunyurResultSet.SUCCESS = SUCCESS;
    }
}
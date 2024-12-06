package indi.study.system.test.vo;

/**
 * @Author 99288
 * @Description 商越返回实体类
 * @Date 17:38 2021/1/11
 * @Param
 * @return
 **/
public class SunyurResult<T> extends SunyurResultSet {

    private static final long serialVersionUID = 8973890607687883933L;

    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public SunyurResult(T content, String errorMsg) {
        super(errorMsg);
        this.content = content;
    }

    public SunyurResult(T content) {
        this.content = content;
    }

    public SunyurResult() {
    }
}
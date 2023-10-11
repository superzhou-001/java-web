package indi.study.system.test.vo;

public class LockItem {
    private String key;
    private String value;

    public LockItem(String key, String value) {
        this.key = key;
        this.value = value;
    }
    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
}

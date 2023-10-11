package indi.study.system.test.vo;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 存放到延迟队列的元素，比标准的delay的实现要提前一点
 * */
public class ItemVo<T> implements Delayed {
    // 到期时间
    private long activeTime;
    // 业务数据，泛型
    private T data;

    public long getActiveTime() {
        return activeTime;
    }

    public T getData() {
        return data;
    }

    public ItemVo(long expirationTime, T data) {
        super();
        // 将要续约的时间推迟100毫秒
        this.activeTime = expirationTime + System.currentTimeMillis() - 100;
        this.data = data;
    }

    /**
     * 返回元素到激活时刻的剩余时长
     * */
    @Override
    public long getDelay(TimeUnit unit) {
        long d = unit.convert(this.activeTime - System.currentTimeMillis(), unit);
        return d;
    }

    /**
     * 按剩余时长排序
     * */
    @Override
    public int compareTo(Delayed o) {
        long d = (getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        if (d == 0) {
            return 0;
        } else {
            if (d < 0) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}

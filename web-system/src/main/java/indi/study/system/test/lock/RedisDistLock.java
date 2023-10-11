package indi.study.system.test.lock;

import indi.study.system.common.utils.RedisClient;
import indi.study.system.test.vo.ItemVo;
import indi.study.system.test.vo.LockItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Component
public class RedisDistLock implements Lock {

    private final static Long LOCK_TIME = 1*1000L;
    private final static String RS_DISTLOCK_NS = "rds:";
    /*解锁的lua脚本*/
    private final static String RELEASE_LOCK_LUA =
            "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    /*保存每个线程设置独有的ID值*/
    private ThreadLocal<String> lockerId = new ThreadLocal<>();
    /*解决锁重入问题*/
    private Thread ownerThread;
    /*看门狗线程*/
    private Thread expireThread;
    private String lockName = "lock";
    // 设置延迟队列
    private DelayQueue<ItemVo<LockItem>> delayDog = new DelayQueue<ItemVo<LockItem>>();

    public Thread getOwnerThread() {
        return ownerThread;
    }

    public void setOwnerThread(Thread ownerThread) {
        this.ownerThread = ownerThread;
    }

    @Autowired
    RedisClient redisClient;

    @Override
    public void lock() {
        while (!tryLock()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException("不支持可中断获取锁");
    }

    @Override
    public boolean tryLock() {
        Thread thread = Thread.currentThread();
        if (ownerThread == thread) {/*说明该线程持有锁*/
            return true;
        } else if (ownerThread != null) { /*说明本进程中有别的线程正在持有分布式锁*/
            return false;
        }
        try {
            /*每一个锁的持有者都分配一个唯一的id,也可以采用雪花算法*/
            String uuid = UUID.randomUUID().toString();
            synchronized (this) { /*线程们，本地抢锁*/
                if (ownerThread == null &&
                    redisClient.setnx(RS_DISTLOCK_NS+lockName, uuid, LOCK_TIME)) {
                    lockerId.set(uuid);
                    this.setOwnerThread(thread);
                    // 看门狗线程启动
                    if (expireThread == null) {
                        expireThread = new Thread(new ExpireTask(), "expireTask");
                        expireThread.setDaemon(true); // 开启守护线程
                        expireThread.start();
                    }
                    // 往延迟阻塞队列中加入元素（让看门狗能提前续期）
                    delayDog.add(new ItemVo<>(LOCK_TIME, new LockItem(lockName, uuid)));
                    System.out.println(Thread.currentThread().getName()+"已获得锁------");
                    return true;
                } else {
                    System.out.println(Thread.currentThread().getName()+"未获得锁------");
                    return false;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("分布式锁尝试加锁失败");
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        throw new RuntimeException("不支持等待获取锁");
    }

    @Override
    public void unlock() {
        if (ownerThread != Thread.currentThread()) {
            throw new RuntimeException("试图释放无所有权的锁");
        }
        try {
            DefaultRedisScript<Long> script = new DefaultRedisScript<>(RELEASE_LOCK_LUA, Long.class);

            Long result = (Long) redisClient.exec(script,
                    Arrays.asList(RS_DISTLOCK_NS+lockName),
                    lockerId.get()); /*lockerId.get() 记录线程的id(uuid)*/
            if (result.longValue() != 0L) {
                System.out.println("Redis上的锁已释放");
            } else {
                System.out.println("Redis上的锁释放失败");
            }
        } catch (Exception e) {
            throw new RuntimeException("释放锁失败", e);
        } finally {
            lockerId.remove();
            this.setOwnerThread(null);
        }
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException("不支持等待通知操作");
    }


    /*续约的lua脚本*/
    private final static String EXPIRE_LOCK_LUA =
            "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('pexpire', KEYS[1], ARGV[2]) else return 0 end";

    private class ExpireTask implements Runnable {
        @Override
        public void run() {
            System.out.println("看门狗线程已启动......");
            // 检验该线程是否被中断
            while(!Thread.currentThread().isInterrupted()) {
                try {
                    // 从延迟队列中获取锁相关信息 ---只有元素到期才能take的到
                    LockItem lockItem = delayDog.take().getData();
                    DefaultRedisScript<Long> script = new DefaultRedisScript<>();
                    script.setScriptText(EXPIRE_LOCK_LUA);
                    script.setResultType(Long.class);
                    Long result = (Long) redisClient.exec(script,
                            Arrays.asList(RS_DISTLOCK_NS+lockItem.getKey()),
                            lockItem.getValue(), LOCK_TIME);
                    if (result.longValue() == 0l) {
                        System.out.println("Redis上的锁已释放，无需续期！");
                    } else {
                        delayDog.add(new ItemVo<>(LOCK_TIME, new LockItem(lockItem.getKey(), lockItem.getValue())));
                        System.out.println("Redis上的锁已续期，已续期："+LOCK_TIME);
                    }
                } catch (InterruptedException e) {
                    System.out.println("看门狗线程被中断....");
                    break;
                } catch (Exception e) {
                    throw new RuntimeException("锁续期失败！", e);
                }
            }
            System.out.println("看门狗线程准备关闭......");
        }
    }

    @PostConstruct
    public void initExpireThread() {
        System.out.println("init.....");
    }

    @PreDestroy
    public void closeExpireThread() {
        if (null != expireThread) {
            expireThread.interrupt();
        }
    }
}

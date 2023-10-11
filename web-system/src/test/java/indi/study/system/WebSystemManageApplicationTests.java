package indi.study.system;

import indi.study.system.test.lock.RedisDistLock;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class WebSystemManageApplicationTests {

    private int count = 0;

    @Autowired
    RedisDistLock redisDistLock;
    @Autowired
    RedissonClient redissonClient;

    @Test
    void contextLoads() {
        redisDistLock.lock();
        redisDistLock.unlock();
    }
    @Test
    public void testRedisDistLockDog() throws InterruptedException {
        int clientCount = 3;
        CountDownLatch countDownLatch = new CountDownLatch(clientCount);
        ExecutorService executorService = Executors.newFixedThreadPool(clientCount);
        for (int i = 0; i < clientCount; i ++) {
            executorService.execute(() ->{
                try {
                    redisDistLock.lock();
                    System.out.println(Thread.currentThread().getName()+"准备累加---");
                    Thread.sleep(2000);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    redisDistLock.unlock();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(count);
        //redisDistLock.closeExpireThread();
    }

    /**
     * 使用Redisson分布式锁
     * */
    @Test
    public void testA() throws InterruptedException{
        int clientCount = 3;
        CountDownLatch countDownLatch = new CountDownLatch(clientCount);
        ExecutorService executorService = Executors.newFixedThreadPool(clientCount);
        RLock lock = redissonClient.getLock("RD-lock");
        for (int i = 0; i < clientCount; i ++) {
            executorService.execute(() ->{
                try {
                    lock.lock(5, TimeUnit.SECONDS);
                    System.out.println(Thread.currentThread().getName()+"准备累加---");
                    Thread.sleep(2000);
                    count++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(count);
    }

    public static void main(String[] args) {
        System.out.println("主线程启动。。。。");
        Thread thread = new Thread(() -> {
            System.out.println("守护线程开启");
            while (true){
                try {
                    System.out.println("子线程启动。。。。");
                    Thread.sleep(2000);
                    System.out.println("子线程结束。。。。");
                } catch (InterruptedException e) {
                    System.out.println("守护线程被中断....");
                    e.printStackTrace();
                    break;
                }
            }
            System.out.println("守护线程完成.....");;
        });
        thread.setDaemon(true);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("主线程结束。。。。");
        thread.interrupt();
    }
    class ExpireTaskTwo implements Runnable {
        @Override
        public void run() {
            System.out.println("守护线程开启");
            while (true){
                try {
                    System.out.println("子线程启动。。。。");
                    Thread.sleep(2000);
                    System.out.println("子线程结束。。。。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

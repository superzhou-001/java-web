package indi.study.system.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 * */
@Slf4j
@Configuration
@EnableAsync
public class TheadPoolConfig {

    /**
     * 线程池--测试
     * */
    @Bean(name = "testExecutor")
    public ThreadPoolTaskExecutor testExecutor() {
        return asyncServiceExecutor(5, 200, 25, 600, "testExecutor");
    }

    private ThreadPoolTaskExecutor asyncServiceExecutor(int corePoolSize,
                                                        int maxPoolSize,
                                                        int queueCapacity,
                                                        int keepAliveSeconds,
                                                        String namePrefix) {
        log.info("start asyncServiceExecutor");
        // 实现线程监控
        ThreadPoolTaskExecutor executor = new VisibleThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(corePoolSize);
        //配置最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        //配置队列大小
        executor.setQueueCapacity(queueCapacity);
        //空闲线程存活时长，当线程池中线程数量大于核心线程数之后，并且存在空闲线程的情况下，当空闲时长超过该时长之后则会被销毁，直到剩余线程数量和corePoolSize数量相等
        executor.setKeepAliveSeconds(keepAliveSeconds);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix(namePrefix+"-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }

    /**
     * 线程监控类
     * 如不想线程监控则直接 new ThreadPoolTaskExecutor();
     * ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
     * */
    @Slf4j
    public static class VisibleThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
        private void showThreadPoolInfo(String prefix) {
            ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();
            if (null == threadPoolExecutor) {
                return;
            }
            log.info("{}, {},taskCount [{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]",
                    this.getThreadNamePrefix(),
                    prefix,
                    threadPoolExecutor.getTaskCount(),
                    threadPoolExecutor.getCompletedTaskCount(),
                    threadPoolExecutor.getActiveCount(),
                    threadPoolExecutor.getQueue().size());
        }
        @Override
        public void execute(Runnable task) {
            showThreadPoolInfo("1. do execute");
            super.execute(task);
        }
        @Override
        public void execute(Runnable task, long startTimeout) {
            showThreadPoolInfo("2. do execute");
            super.execute(task, startTimeout);
        }
        @Override
        public Future<?> submit(Runnable task) {
            showThreadPoolInfo("1. do submit");
            return super.submit(task);
        }
        @Override
        public <T> Future<T> submit(Callable<T> task) {
            showThreadPoolInfo("2. do submit");
            return super.submit(task);
        }
        @Override
        public ListenableFuture<?> submitListenable(Runnable task) {
            showThreadPoolInfo("1. do submitListenable");
            return super.submitListenable(task);
        }
        @Override
        public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
            showThreadPoolInfo("2. do submitListenable");
            return super.submitListenable(task);
        }

    }
}

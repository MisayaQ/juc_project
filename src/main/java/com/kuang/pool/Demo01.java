package com.kuang.pool;

// Executors 工具类、3大方法

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * new ThreadPoolExecutor.AbortPolicy() // 银行满了，还有人进来，不处理这个人的，抛出异常
 * new ThreadPoolExecutor.CallerRunsPolicy() // 哪来的去哪里！
 * new ThreadPoolExecutor.DiscardPolicy() //队列满了，丢掉任务，不会抛出异常！
 * new ThreadPoolExecutor.DiscardOldestPolicy() //队列满了，尝试去和最早的竞争，也不会抛出异常！
 */
public class Demo01 {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//单个线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);//创建一个固定的线程池大小
        ExecutorService threadPool = Executors.newCachedThreadPool();//可伸缩的 遇强则强 遇弱则弱
        try {
            for (int i = 0; i < 100; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "OK");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程用完 程序结束 关闭线程池
            threadPool.shutdown();
        }
    }
}

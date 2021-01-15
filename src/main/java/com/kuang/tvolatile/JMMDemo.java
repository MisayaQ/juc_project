package com.kuang.tvolatile;

import java.util.concurrent.TimeUnit;

/**
 * @program: juc_project
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2021-01-15 11:22
 **/
public class JMMDemo {
    // 不加 volatile 程序就会死循环！
    // 加 volatile 可以保证可见性
    private volatile static int num = 0;

    public static void main(String[] args) {//main线程
        new Thread(() -> {
            while (num == 0) {

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;
        System.out.println(num);

    }
}

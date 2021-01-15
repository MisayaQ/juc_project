package com.kuang.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @program: juc_project
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2021-01-15 17:11
 **/
public class CASDemo {
    //AtomicStampedReference 注意，如果泛型是一个包装类，注意对象的引用问题

    // 正常在业务操作，这里面比较的都是一个个对象
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1,1);

    //CAS compareAndSet: 比较并交换
    public static void main(String[] args) {
//        AtomicInteger atomicInteger = new AtomicInteger(2020);

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();// 获得版本号
            System.out.println("a1=>"+stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicStampedReference.compareAndSet(1, 2,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("a2=>"+atomicStampedReference.getStamp());

            System.out.println(atomicStampedReference.compareAndSet(2, 1,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("a3=>"+atomicStampedReference.getStamp());

        }, "a").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();// 获得版本号
            System.out.println("b1=>"+stamp);

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicStampedReference.compareAndSet(1, 2,
                    stamp, stamp + 1));
            System.out.println("b2=>"+stamp);
        }, "b").start();

    }
}

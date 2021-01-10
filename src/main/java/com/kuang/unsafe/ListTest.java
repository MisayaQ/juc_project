package com.kuang.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// java.util.ConcurrentModificationException 并发修改异常！
public class ListTest {
    public static void main(String[] args) {
        // 并发下 ArrayList 不安全的吗，Synchronized；
        /**
         * 解决方案；
         * 1、List<String> list = new Vector<>();
         * 2、List<String> list = Collections.synchronizedList(new ArrayList<>());
         * 3、List<String> list = new CopyOnWriteArrayList<>()；
         */
        // CopyOnWrite:写入时复制  简称:COW,计算机程序设计领域的一种优化策略；
        // 多个线程调用的时候,list,读取的时候是固定的,写入（覆盖）
        // 在写入的时候避免覆盖，造成数据问题！
        // 读写分离
        // CopyOnWriteArrayList  比 Vector Nb 在哪里？

//        Vector是增删改查方法都加了synchronized，
//          保证同步，但是每个方法执行的时候都要去获得锁，性能就会大大下降，
//        而CopyOnWriteArrayList 只是在增删改上加锁，
    //        但是读不加锁，在读方面的性能就好于Vector，CopyOnWriteArrayList支持读多写少的并发情况
    //        读写分离，写时复制出一个新的数组，完成插入、修改或者移除操作后将新数组赋值给array

        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }
}

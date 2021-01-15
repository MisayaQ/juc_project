package com.kuang.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @program: juc_project
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2021-01-15 15:41
 **/
public class LazyMan {
    private static boolean jiaqi = false;

    private LazyMan() {
        synchronized (LazyMan.class) {
            if (jiaqi == false) {
                jiaqi = true;
            } else {
                throw new RuntimeException("不要试图使用反射破坏异常");
            }
        }
        System.out.println(Thread.currentThread().getName());
    }

    private volatile static LazyMan lazyMan;

    // 双重检测锁模式的 懒汉式单例  DCL懒汉式
    public static LazyMan getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan(); // 不是一个原子性操作
                    /**
                     * 1. 分配内存空间
                     * 2、执行构造方法，初始化对象
                     * 3、把这个对象指向这个空间
                     *
                     * 123
                     * 132 A
                     *     B // 此时lazyMan还没有完成构造 所以一定要加volatile
                     */
                }
            }
        }
        return lazyMan;
    }

    //反射
    public static void main(String[] args) throws Exception {
//        LazyMan instance1 = LazyMan.getInstance();
        //得到标识符
        Field jiaqi = LazyMan.class.getDeclaredField("jiaqi");
        jiaqi.setAccessible(true);

        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true); //忽略访问权限修饰符的安全检查
        LazyMan instance1 = declaredConstructor.newInstance();
        //修改标识符
        jiaqi.set(instance1, false);
        LazyMan instance2 = declaredConstructor.newInstance();
        System.out.println(instance1 == instance2);
    }
}

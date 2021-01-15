package com.kuang.single;

import com.sun.org.apache.bcel.internal.classfile.InnerClass;
import jdk.internal.org.objectweb.asm.tree.InnerClassNode;

/**
 * @program: juc_project
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2021-01-15 15:54
 **/

//静态内部类
public class Holder {
    private Holder(){

    }

    public static Holder getInstance() {
        return InnerClass.HOLDER;
    }

    public static class InnerClass{
        private static final Holder HOLDER = new Holder();
    }
}

package com.kuang.function;

import java.util.function.Predicate;

/**
 * @program: juc_project
 * @description:
 * @version: 1.0
 * @author: LiuJiaQi
 * @create: 2021-01-14 10:45
 **/

/**
 * 断定型接口：有一个输入参数，返回值只能是 布尔值！
 */
public class Demo02 {
    public static void main(String[] args) {
        // 判断字符串是否为空
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String str) {
//                return  str.isEmpty();
//            }
//        };

        Predicate<String> predicate = (str) -> {
            return str.isEmpty();
        };
        System.out.println(predicate.test(""));

    }
}

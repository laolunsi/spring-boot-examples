package com.example.serviceconsumer;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/8/28 12:33
 */
public class Test {
    public static void main(String[] args) {
        double basic = 5000;
        double rate = 0.1;
        double added = 5000;

        for (int i = 1; i <= 30; i++) {
            basic = (basic + added) * (rate + 1.0);
            System.out.println("第" + i + "年，共计：" + basic);
        }
    }
}

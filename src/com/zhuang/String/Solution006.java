package com.zhuang.String;

/**
 * @Classname Solution006
 * @Description 数组学习
 * @Date 2021/8/17 11:46
 * @Author by Zhuang
 */
public class Solution006 {

    public static void main(String[] args) {
        int x = 123;
        reverse(123);
    }

    /**
     * https://leetcode-cn.com/problems/reverse-integer/
     *
     * @param x 数
     * @return int
     */
    public static int reverse(int x) {
        int res = 0;
        while (x != 0) {
            if (res < Integer.MIN_VALUE / 10 || res > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            res = res * 10 + digit;
        }
        System.out.println(res);
        return res;
    }
}

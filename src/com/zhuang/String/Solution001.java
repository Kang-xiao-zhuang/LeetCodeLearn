package com.zhuang.String;

import java.util.Arrays;

/**
 * @Deacription 字符串学习
 * @Author Zhuang
 * @Date 2021/7/28 16:21
 * @Version 1.0
 **/
public class Solution001 {
    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        char[] s2 = {'H', 'a', 'n', 'n', 'a', 'h'};
        //   reverseString(s);
        //  reverseString(s2);
        reverseString2(s);
    }

    /**
     * https://leetcode-cn.com/problems/reverse-string/
     *
     * @param s 字符串
     */
    public static void reverseString(char[] s) {
        int len = s.length;
        for (int left = 0, right = len - 1; left < right; left++, right--) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
        }
        System.out.println(String.valueOf(s));
    }

    /**
     * 异或法
     * https://leetcode-cn.com/problems/reverse-string/
     *
     * @param s 数组
     */
    public static void reverseString2(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while (l < r) {
            //构造 a ^ b 的结果，并放在 a 中
            s[l] ^= s[r];
            //将 a ^ b 这一结果再 ^ b ，存入b中，此时 b = a, a = a ^ b
            s[r] ^= s[l];
            //a ^ b 的结果再 ^ a ，存入 a 中，此时 b = a, a = b 完成交换
            s[l] ^= s[r];
            l++;
            r--;
        }
        System.out.println(String.valueOf(s));
    }
}

package com.zhuang.Array;

import java.util.Arrays;

/**
 * @Classname Solution006
 * @Description 数组学习
 * @Date 2021/8/1 10:12
 * @Created by dell
 */

public class Solution006 {
    public static void main(String[] args) {
        int[] digits = {9, 9, 9};
        //   plusOne(digits);
        plusOne2(digits);
    }

    /**
     * https://leetcode-cn.com/problems/plus-one/
     *
     * @param digits 数组
     * @return int数组
     */
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] %= 10;
            if (digits[i] != 0) {
                System.out.println(Arrays.toString(digits));
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        System.out.println(Arrays.toString(digits));
        return digits;
    }

    public static int[] plusOne2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                digits[i] += 1;
                System.out.println(Arrays.toString(digits));
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        System.out.println(Arrays.toString(digits));
        return digits;
    }
}

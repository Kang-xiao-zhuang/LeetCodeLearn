package com.zhuang.string;

/**
 * @Classname Solution007
 * @Description 字符串学习
 * @Date 2021/9/3 15:13
 * @Author by Zhuang
 */
public class Solution007 {
    public static void main(String[] args) {
        intToRoman(1994);
    }

    /**
     * https://leetcode-cn.com/problems/integer-to-roman/
     *
     * @param num 数字
     * @return 字符串
     */
    public static String intToRoman(int num) {
        int[] val = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] str = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < val.length; i++) {
            while (num >= val[i]) {
                sb.append(str[i]);
                num -= val[i];
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}

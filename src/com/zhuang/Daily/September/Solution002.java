package com.zhuang.Daily.September;

/**
 * @Classname Solution002
 * @Description 2021.9.7-2021.9.14每日一题
 * @Date 2021/9/7 8:45
 * @Author by Zhuang
 */
public class Solution002 {
    public static void main(String[] args) {
        String s = "RLLLLRRRLR";
        balancedStringSplit(s);
    }

    /**
     * https://leetcode-cn.com/problems/split-a-string-in-balanced-strings/
     * 9.7
     *
     * @param s 字符串
     * @return 最大数量
     */
    public static int balancedStringSplit(String s) {
        int res = 0, diff = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                diff++;
            } else {
                diff--;
            }
            if (diff == 0) {
                res++;
            }
        }
        System.out.println(res);
        return res;
    }
}

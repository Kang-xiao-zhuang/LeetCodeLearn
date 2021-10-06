package com.zhuang.Daily.October;

/**
 * @Classname Solution002
 * @Description 2021.10.7-2021.10.7每日一题
 * @Date 2021/10/7 6:56
 * @Author by dell
 */

public class Solution002 {
    public static void main(String[] args) {
        //countSegments("Hello, my name is John");
    }

    /**
     * https://leetcode-cn.com/problems/number-of-segments-in-a-string/
     * 10.7
     *
     * @param s 字符串
     * @return 统计字符串中的单词个数
     */
    public static int countSegments(String s) {
        int count = 0;
        for (String s1 : s.split(" ")) {
            if (!s1.equals("")) {
                count++;
            }
        }
        System.out.println(count);
        return count;
    }

    public static int countSegments2(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((i == 0 || s.charAt(i - 1) == ' ')&&s.charAt(i) != ' '){
                count++;
            }
        }
        System.out.println(count);
        return count;
    }
}

package com.zhuang.Daily.October;

/**
 * @Classname Solution003
 * @Description 2021.10.14-2021.10.21每日一题
 * @Date 2021/10/14 19:43
 * @Author by dell
 */

public class Solution003 {
    public static void main(String[] args) {
        //countAndSay(4);
        //countAndSay(4);
    }

    /**
     * https://leetcode-cn.com/problems/count-and-say/
     * 循环法
     * 10.15
     *
     * @param n 正整数
     * @return 字符串
     */
    public static String countAndSay(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        for (int i = 1; i < n; i++) {
            // 记录当前的字符串
            StringBuilder s = new StringBuilder();
            // 记录每个数字的开始索引
            int start = 0;
            for (int j = 1; j < sb.length(); j++) {
                // 当数字发生改变时执行
                if (sb.charAt(j) != sb.charAt(start)) {
                    s.append(j - start).append(sb.charAt(start));
                    start = j;
                }
            }
            // 字符串最后一个数字
            s.append(sb.length() - start).append(sb.charAt(start));
            sb = s;
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * 递归法
     *
     * @param n 正整数
     * @return 字符串
     */
    public static String countAndSay2(int n) {
        // 递归终止条件
        if (n == 1) {
            return "1";
        }
        // 获取到上一层的字符串
        String s = countAndSay2(n - 1);
        StringBuilder result = new StringBuilder();
        // 记录每个数字的开始索引
        int start = 0;
        for (int i = 1; i < s.length(); i++) {
            // 当数字发生改变时执行
            if (s.charAt(i) != s.charAt(start)) {
                result.append(i - start).append(s.charAt(start));
                start = i;
            }
        }
        // 字符串最后一个数字
        result.append(s.length() - start).append(s.charAt(start));
        return result.toString();
    }
}

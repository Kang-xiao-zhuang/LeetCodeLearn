package com.zhuang.Daily.October;

import java.util.ArrayList;
import java.util.List;

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

        addOperators("123", 6);
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

    /**
     * https://leetcode-cn.com/problems/expression-add-operators/
     * 10.16
     *
     * @param num    数组
     * @param target 目标整数
     * @return 目标值表达式
     */
    public static List<String> addOperators(String num, int target) {
        /**
         * 这个方法有问题，不能通过所有测试用例，以后解决，做次记录 2021年10月16日08:44:43
         */
        List<String> res = new ArrayList<>();
        backtrack(num, target, res, "", 0, 0, 0);
        System.out.println(res.toString());
        return res;
    }

    /**
     * @param num    数组
     * @param target 目标值
     * @param res    集合
     * @param path   路径
     * @param start  开始索引
     * @param value  当前的值
     * @param pre    之前值
     */
    private static void backtrack(String num, int target, List<String> res, String path, int start, long value, long pre) {
        if (start == num.length()) {
            if (target == value) {
                res.add(path);
            }
            return;
        }
        for (int i = start; i < num.length(); i++) {
            // 数字不能以0开头
            if (num.charAt(start) == '0' && i > start) {
                break;
            }
            long cur = Long.parseLong(num.substring(start, i + 1));
            // 选取第一个数不加符号
            if (start == 0) {
                backtrack(num, target, res, path + cur, i + 1, cur, cur);
            } else {
                // 加当前值
                backtrack(num, target, res, path + "+" + cur, i + 1, value + cur, cur);
                // 减当前值
                backtrack(num, target, res, path + "-" + cur, i + 1, value - cur, cur);
                /*
                乘法不同，当前表达式的值就是先减去之前的值，然后加上，当前值和边上的操作数相乘   value-prev+prev*cur
                传入参数 prev*cur
                 */
                backtrack(num, target, res, path + "*" + cur, i + 1, value - pre + pre * cur, pre * cur);
            }
        }
    }
}

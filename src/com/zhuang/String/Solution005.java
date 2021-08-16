package com.zhuang.String;

/**
 * @Classname Solution005
 * @Description 字符串学习
 * @Date 2021/8/13 11:51
 * @Author by Zhuang
 */
public class Solution005 {
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        // isPalindrome(s);
        //   isPalindrome2(s);

        //  countAndSay(4);
        countAndSay2(4);
    }

    /**
     * https://leetcode-cn.com/problems/valid-palindrome/
     *
     * @param s 字符串
     * @return 布尔值
     */
    public static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sb.append((Character.toLowerCase(ch)));
            }
        }
        StringBuffer reverse = new StringBuffer(sb).reverse();
        System.out.println(sb.toString().equals(reverse.toString()));
        return sb.toString().equals(reverse.toString());
    }

    /**
     * 双指针法
     *
     * @param s 字符串
     * @return 布尔值
     */
    public static boolean isPalindrome2(String s) {
        StringBuilder sb = new StringBuilder();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sb.append((Character.toLowerCase(ch)));
            }
        }
        int n = sb.length();
        int left = 0;
        int rigth = n - 1;
        while (left < rigth) {
            if (Character.toLowerCase(sb.charAt(left)) != Character.toLowerCase(sb.charAt(rigth))) {
                System.out.println(false);
                return false;
            }
            left++;
            rigth--;
        }
        System.out.println(true);
        return true;
    }

    /**
     * https://leetcode-cn.com/problems/count-and-say/
     * 循环法
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

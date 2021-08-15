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
        isPalindrome(s);
        isPalindrome2(s);
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
}
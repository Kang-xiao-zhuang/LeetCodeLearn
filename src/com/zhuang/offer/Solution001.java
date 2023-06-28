package com.zhuang.offer;

/**
 * 剑指offer题目
 */
public class Solution001 {
    public static void main(String[] args) {
        Solution001 solution001 = new Solution001();
        solution001.reverseLeftWords("lrloseumgh", 6);
    }

    /**
     * https://leetcode.cn/problems/ti-huan-kong-ge-lcof/
     *
     * @param s String
     * @return String
     */
    public String replaceSpace(String s) {
        return s.replace(" ", "%20");
    }

    public String replaceSpace2(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
     *
     * @param s String
     * @param n int
     * @return String
     */
    public String reverseLeftWords(String s, int n) {
        if (n == 0) {
            return s;
        }
        return s.substring(n) + s.substring(0, n);
    }

    public String reverseLeftWords2(String s, int n) {
        if (n == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = n; i < s.length(); i++) {
            sb.append(s.charAt(i));
        }
        for (int i = 0; i < n; i++) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}

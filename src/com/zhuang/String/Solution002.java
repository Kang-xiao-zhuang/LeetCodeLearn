package com.zhuang.String;

/**
 * @Description 字符串学习
 * @Author Zhuang
 * @Date 2021/8/2 10:05
 * @Version 1.0
 **/
public class Solution002 {
    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        String[] strs2 = {"dog", "racecar", "car"};
        //  longestCommonPrefix(strs);
        //  longestCommonPrefix(strs2);
    }

    /**
     * https://leetcode-cn.com/problems/longest-common-prefix/
     *
     * @param strs 字符串
     * @return 字符串
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        } else if (strs.length == 1) {
            return strs[0];
        }
        // 把数组中第一个字符串的值赋给s
        String s = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(s)) {
                s = s.substring(0, s.length() - 1);
                i--;
            }
        }
        System.out.println(s);
        return s;
    }
}

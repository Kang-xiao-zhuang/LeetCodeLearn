package com.zhuang.Daily.December;

/**
 * @Classname Solution001
 * @Description 2021.12.1-2021.12.7每日一题
 * @Date 2021/12/1 20:26
 * @Author by dell
 */
public class Solution001 {
    public static void main(String[] args) {
        maxPower("abbcccddddeeeeedcba");
    }

    /**
     * https://leetcode-cn.com/problems/consecutive-characters/
     * 12.1
     *
     * @param s 字符串
     * @return 只包含一种字符的最长非空子字符串的长度
     */
    public static int maxPower(String s) {
        int ans = 1, cnt = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                cnt++;
                ans = Math.max(ans, cnt);
            } else {
                cnt = 1;
            }
        }
        System.out.println(ans);
        return ans;
    }
}

package com.zhuang.Daily.September;

/**
 * @Classname Solution004
 * @Description 2021.9.25-2021.9.30每日一题
 * @Date 2021/9/25 7:36
 * @Author by Zhuang
 */

public class Solution004 {
    public static void main(String[] args) {
        String word1 = "sea";
        String word2 = "eat";
        minDistance(word1, word2);
    }

    /**
     * https://leetcode-cn.com/problems/delete-operation-for-two-strings/
     * 9.25
     *
     * @param word1 单词1
     * @param word2 单词2
     * @return 最小步数
     */
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int lcs = dp[m][n];
        System.out.println(m - lcs + n - lcs);
        return m - lcs + n - lcs;
    }
}

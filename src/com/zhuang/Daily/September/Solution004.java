package com.zhuang.Daily.September;

/**
 * @Classname Solution004
 * @Description 2021.9.25-2021.9.30每日一题
 * @Date 2021/9/25 7:36
 * @Author by Zhuang
 */

public class Solution004 {
    public static void main(String[] args) {
        //String word1 = "sea";
        //String word2 = "eat";
        //minDistance(word1, word2);

        //getSum(1, 2);
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

    /**
     * https://leetcode-cn.com/problems/sum-of-two-integers/
     * 9.26每日一题
     * 位运算
     *
     * @param a 整数
     * @param b 整数
     * @return 两整数之和
     */
    public static int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        System.out.println(a);
        return a;
    }


    static final int MOD = 1000000007;

    /**
     * https://leetcode-cn.com/problems/decode-ways-ii/
     * 9.27
     *
     * @param s 字符串
     * @return 解码该字符串的方法数目
     */
    public int numDecodings(String s) {
        int n = s.length();
        // a = f[i-2], b = f[i-1], c = f[i]
        long a = 0, b = 1, c = 0;
        for (int i = 1; i <= n; ++i) {
            c = b * check1digit(s.charAt(i - 1)) % MOD;
            if (i > 1) {
                c = (c + a * check2digits(s.charAt(i - 2), s.charAt(i - 1))) % MOD;
            }
            a = b;
            b = c;
        }
        return (int) c;
    }

    public int check1digit(char ch) {
        if (ch == '0') {
            return 0;
        }
        return ch == '*' ? 9 : 1;
    }

    public int check2digits(char c0, char c1) {
        if (c0 == '*' && c1 == '*') {
            return 15;
        }
        if (c0 == '*') {
            return c1 <= '6' ? 2 : 1;
        }
        if (c1 == '*') {
            if (c0 == '1') {
                return 9;
            }
            if (c0 == '2') {
                return 6;
            }
            return 0;
        }
        return (c0 != '0' && (c0 - '0') * 10 + (c1 - '0') <= 26) ? 1 : 0;
    }
}

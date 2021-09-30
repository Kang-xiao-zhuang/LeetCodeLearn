package com.zhuang.Daily.September;

import java.util.Arrays;
import java.util.HashMap;

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

        //computeArea(-3, 0, 3, 4, 0, -1, 9, 2);
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

    /**
     * https://leetcode-cn.com/problems/path-sum-iii/
     * 9.28
     * 深度优先搜索
     *
     * @param root      根节点
     * @param targetSum 整数
     * @return 节点值之和等于targetSum的路径的数目
     */
    public static int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int res = rootSum(root, targetSum);
        res += pathSum(root.left, targetSum);
        res += pathSum(root.right, targetSum);
        return res;
    }

    /**
     * @param root      根节点
     * @param targetSum 整数
     * @return 数目
     */
    private static int rootSum(TreeNode root, int targetSum) {
        int res = 0;
        if (root == null) {
            return 0;
        }
        int val = root.val;
        if (val == targetSum) {
            res++;
        }
        res += rootSum(root.left, targetSum - val);
        res += rootSum(root.right, targetSum - val);
        return res;
    }

    public static int pathSum2(TreeNode root, int targetSum) {
        HashMap<Integer, Integer> prefix = new HashMap<>();
        prefix.put(0, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    /**
     * @param root      根节点
     * @param prefix    前缀和
     * @param cur       当前的值
     * @param targetSum 目标值
     * @return 节点值之和等于targetSum的路径的数目
     */
    private static int dfs(TreeNode root, HashMap<Integer, Integer> prefix, int cur, int targetSum) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        cur += root.val;
        res = prefix.getOrDefault(cur - targetSum, 0);
        prefix.put(cur, prefix.getOrDefault(cur, 0) + 1);
        res += dfs(root.left, prefix, cur, targetSum);
        res += dfs(root.right, prefix, cur, targetSum);
        prefix.put(cur, prefix.getOrDefault(cur, 0) - 1);
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/super-washing-machines/
     * 9.29
     *
     * @param machines 洗衣机数组
     * @return 最少操作步骤
     */
    public int findMinMoves(int[] machines) {
        int tot = Arrays.stream(machines).sum();
        int n = machines.length;
        if (tot % n != 0) {
            return -1;
        }
        int avg = tot / n;
        int ans = 0, sum = 0;
        for (int num : machines) {
            num -= avg;
            sum += num;
            ans = Math.max(ans, Math.max(Math.abs(sum), num));
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/rectangle-area/
     * 9.30
     *
     * @param ax1 第一个矩形左下顶点横坐标
     * @param ay1 第一个矩形左下坐标纵坐标
     * @param ax2 第一个矩形右上顶点横坐标
     * @param ay2 第一个矩形右上顶点纵坐标
     * @param bx1 第二个矩形左下顶点横坐标
     * @param by1 第二个矩形左下顶点纵坐标
     * @param bx2 第二个矩形右上顶点横坐标
     * @param by2 第二个矩形右上顶点纵坐标
     * @return 两个矩形覆盖的总面积
     */
    public static int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area1 = (ax2 - ax1) * (ay2 - ay1), area2 = (bx2 - bx1) * (by2 - by1);
        int overlapWidth = Math.min(ax2, bx2) - Math.max(ax1, bx1), overlapHeight = Math.min(ay2, by2) - Math.max(ay1, by1);
        int overlapArea = Math.max(overlapWidth, 0) * Math.max(overlapHeight, 0);
        System.out.println(area1 + area2 - overlapArea);
        return area1 + area2 - overlapArea;
    }
}

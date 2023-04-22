package com.zhuang.dynamicPlanning;

import java.util.Arrays;

/**
 * description: Solution002
 * date: 2023/4/22 21:17
 * author: Zhuang
 * version: 1.0
 */
public class Solution002 {
    public static void main(String[] args) {
//        integerBreak(10);
        numTrees(4);
    }

    /**
     * https://leetcode.cn/problems/integer-break/
     *
     * @param n 正整数
     * @return 可以获得的最大乘积
     */
    public static int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            System.out.println(Arrays.toString(dp));
            for (int j = 1; j < i - 1; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
                System.out.println(Arrays.toString(dp));
            }
            System.out.println("---------------");
        }
        return dp[n];
    }

    /**
     * https://leetcode.cn/problems/unique-binary-search-trees/
     *
     * @param n 整数
     * @return 求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种
     */
    public static int numTrees(int n) {
        int[] dp = new int[n + 1];  // 初始化动态规划数组
        dp[0] = 1;  // 边界条件：0 和 1 对应的二叉搜索树数量都为 1
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {  // 从小到大枚举整数 i 的所有可能取值
            for (int j = 1; j <= i; j++) {  // 枚举根节点的位置 j（其值在 1~i 之间）
                dp[i] += dp[j - 1] * dp[i - j];  // 转移方程，计算所有以 j 为根节点的二叉搜索树数量之和
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[n];  // 返回最终结果
    }

}

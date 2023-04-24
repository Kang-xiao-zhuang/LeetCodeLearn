package com.zhuang.dynamicplanning;

import java.util.Arrays;

/**
 * description: Solution002
 * date: 2023/4/22 21:17
 * author: Zhuang
 * version: 1.0
 */
public class Solution002 {
    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        canPartition(nums);
        //integerBreak(10);
        //numTrees(4);
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

    /**
     * https://leetcode.cn/problems/partition-equal-subset-sum/
     *
     * @param nums 包含正整数 的 非空 数组 nums
     * @return 判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等
     */
    public static boolean canPartition(int[] nums) {
        int len = nums.length;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum / 2;
        boolean[][] dp = new boolean[len][target + 1];

        dp[0][0] = true;
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (nums[i] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
            if (dp[i][target]) {
                return true;
            }
        }
        return dp[len - 1][target];
    }

}

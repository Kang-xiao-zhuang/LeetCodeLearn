package com.zhuang.dynamicPlanning;


/**
 * @Classname Solution001
 * @Description 动态规划学习
 * @Date 2021/8/18 10:05
 * @Author by Zhuang
 */

public class Solution001 {
    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1};
        //  rob(nums);
        //  rob2(nums);
        //  rob3(nums);

        //    climbStairs(3);
        climbStairs2(3);
        climbStairs3(3);
    }

    /**
     * https://leetcode-cn.com/problems/house-robber/
     * 动态规划
     *
     * @param nums 数组
     * @return int
     */
    public static int rob(int[] nums) {
        int n = nums.length;
        if (nums == null || n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        System.out.println(dp[n - 1]);
        return dp[n - 1];
    }

    /**
     * 简单版本
     *
     * @param nums 数组
     * @return int
     */
    public static int rob2(int[] nums) {
        int first = 0;
        int second = 0;
        for (int num : nums) {
            int temp = second;
            second = Math.max(second, first + num);
            first = temp;
        }
        System.out.println(second);
        return second;
    }

    /**
     * 动态规划+滚动数组
     *
     * @param nums 数组
     * @return int
     */
    public static int rob3(int[] nums) {
        int n = nums.length;
        if (nums == null || n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n];
        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            int temp = second;
            second = Math.max(second, nums[i] + first);
            first = temp;
        }
        System.out.println(second);
        return second;
    }

    /**
     * https://leetcode-cn.com/problems/climbing-stairs/
     *
     * @param n n阶楼梯
     * @return int 次数
     */
    public static int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        System.out.println(r);
        return r;
    }

    /**
     * @param n n级台阶
     * @return int
     */
    public static int climbStairs2(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(dp[n]);
        return dp[n];
    }

    /**
     * 斐波那契数列 滚动数组
     *
     * @param n n阶楼梯
     * @return int
     */
    public static int climbStairs3(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        System.out.println(second);
        return second;
    }
}

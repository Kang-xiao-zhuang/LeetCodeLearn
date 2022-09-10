package com.zhuang.daily.twotwo.february;

import java.util.Arrays;

public class Solution002 {
    public static void main(String[] args) {
        Solution002 solution002 = new Solution002();
        int[] nums = {9, 4, 1, 7};
        solution002.minimumDifference(nums, 2);
    }

    /**
     * https://leetcode.cn/problems/minimum-difference-between-highest-and-lowest-of-k-scores/
     * 2.11
     *
     * @param nums 数组
     * @param k    整数
     * @return 最小差值
     */
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, ans = nums[k - 1] - nums[0];
        for (int i = k; i < n; i++) {
            ans = Math.min(ans, nums[i] - nums[i - k + 1]);
        }
        return ans;
    }
}

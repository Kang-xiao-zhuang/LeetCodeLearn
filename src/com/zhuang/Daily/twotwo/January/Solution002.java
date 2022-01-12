package com.zhuang.Daily.twotwo.January;

/**
 * @Classname Solution002
 * @Description 2022.1.12-2022.1.22 每日一题
 * @Date 2022/1/12 16:54
 * @Author by dell
 */
public class Solution002 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        increasingTriplet(nums);
    }

    /**
     * https://leetcode-cn.com/problems/increasing-triplet-subsequence/
     * 1.12
     *
     * @param nums 整数数组
     * @return 判断这个数组中是否存在长度为 3 的递增子序列
     */
    public static boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int[] leftMin = new int[n];
        leftMin[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
        }
        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > leftMin[i - 1] && nums[i] < rightMax[i + 1]) {
                return true;
            }
        }
        return false;
    }
}

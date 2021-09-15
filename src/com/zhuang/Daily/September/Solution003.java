package com.zhuang.Daily.September;

/**
 * @Classname Solution002
 * @Description 2021.9.15-2021.9.24每日一题
 * @Date 2021/9/15 8:45
 * @Author by Zhuang
 */
public class Solution003 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        findPeakElement(nums);
    }

    /**
     * https://leetcode-cn.com/problems/find-peak-element/
     * 9.15
     *
     * @param nums 整数数组
     * @return 任何一个峰值的所在位置
     */
    public static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
        return left;
    }
}

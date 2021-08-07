package com.zhuang.BinarySearch;

import java.util.Arrays;

/**
 * @Deacription 二分查找学习
 * @Author Zhuang
 * @Date 2021/8/6 10:26
 * @Version 1.0
 **/
public class Solution002 {
    public static void main(String[] args) {
        // int[] nums = {1, 2, 3, 1};
        //  findPeakElement(nums);
        // findPeakElement2(nums);
        //findPeakElement3(nums);

        // int[] nums = {4, 5, 6, 7, 0, 1, 2};
        //  findMin(nums);
        //  findMin2(nums);
        // findMin3(nums);
        int[] nums = {5, 7, 7, 8, 8, 10};
        searchRange(nums, 8);
    }


    /**
     * https://leetcode-cn.com/problems/find-peak-element/
     *
     * @param nums 数组
     * @return int
     */
    public static int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                System.out.println(i);
                return i;
            }
        }
        System.out.println(nums.length - 1);
        return nums.length - 1;
    }

    /**
     * 二分查找
     *
     * @param nums 数组
     * @return int
     */
    public static int findPeakElement2(int[] nums) {
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

    /**
     * 二分法递归调用
     *
     * @param nums 数组
     * @return int
     */
    public static int findPeakElement3(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    public static int search(int[] nums, int left, int right) {
        if (left == right) {
            System.out.println(left);
            return left;
        }
        int mid = (left + right) / 2;
        if (nums[mid] > nums[mid + 1]) {
            return search(nums, left, mid);
        }
        return search(nums, mid + 1, right);
    }


    /**
     * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
     * 暴力解法
     *
     * @param nums 数组
     * @return int
     */
    public static int findMin(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                System.out.println(nums[i]);
                return nums[i];
            }
        }
        System.out.println(nums[0]);
        return nums[0];
    }

    /**
     * 暴力解法2
     *
     * @param nums 数组
     * @return int
     */
    public static int findMin2(int[] nums) {
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
        }
        System.out.println(min);
        return min;
    }


    /**
     * 二分查找
     *
     * @param nums 数组
     * @return int
     */
    public static int findMin3(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(nums[left]);
        return nums[left];
    }


    /**
     * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
     *
     * @param nums   数组
     * @param target 目标值
     * @return int数组
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] ans = new int[]{-1, -1};
        int n = nums.length;
        if (n == 0) {
            System.out.println(Arrays.toString(ans));
            return ans;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + right >> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (nums[left] != target) {
            System.out.println(Arrays.toString(ans));
            return ans;
        } else {
            ans[0] = left;
            left = 0;
            right = nums.length - 1;
            while (left < right) {
                int mid = left + right + 1 >> 1;
                if (nums[mid] <= target) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            ans[1] = left;
        }
        System.out.println(Arrays.toString(ans));
        return ans;
    }
}

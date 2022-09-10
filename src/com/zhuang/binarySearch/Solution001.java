package com.zhuang.binarySearch;

/**
 * @Description 二分查找学习
 * @Author Zhuang
 * @Date 2021/8/5 15:34
 * @Version 1.0
 **/
public class Solution001 {

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        search(nums, 9);
        //  mySqrt(8);

    }

    /**
     * https://leetcode-cn.com/problems/binary-search/
     *
     * @param nums   数组
     * @param target 目标值
     * @return 数组
     */
    public static int search(int[] nums, int target) {
        // 定义左指针
        int left = 0;
        // 定义右指针
        int right = nums.length - 1;
        while (left <= right) {
            // 防止计算时候溢出
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                System.out.println(mid);
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(-1);
        return -1;
    }

    /**
     * https://leetcode-cn.com/problems/sqrtx/
     *
     * @param x 值
     * @return int
     */
    public static int mySqrt(int x) {
        int left = 0;
        int right = x;
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long) mid * mid <= x) {
                res = mid;
                System.out.println(res);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(res);
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
     *
     * @param nums   数组
     * @param target 目标值
     * @return int
     */
    public static int searchSort(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        if (len == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[len - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}

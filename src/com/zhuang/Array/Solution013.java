package com.zhuang.Array;

import java.util.Arrays;

/**
 * @Classname Solution013
 * @Description 数组学习
 * @Date 2021/8/27 11:39
 * @Author by Zhuang
 */
public class Solution013 {
    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 4};
        //triangleNumber(nums);
        //triangleNumber2(nums);
        //triangleNumber3(nums);
    }


    /**
     * https://leetcode-cn.com/problems/valid-triangle-number/
     * 暴力法
     *
     * @param nums 非负整数数组
     * @return 可以组成三角形的个数
     */
    public static int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if (nums[k] + nums[j] > nums[i]) {
                        ans++;
                    }
                }
            }
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * 排序+二分法
     *
     * @param nums
     * @return
     */
    public static int triangleNumber2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                // 第三个数判断范围
                int left = 0;
                int right = j - 1;
                while (left < right) {
                    int mid = left + right >> 1;
                    if (nums[mid] + nums[j] > nums[i]) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                if (left == right && nums[right] + nums[j] > nums[i]) {
                    ans += j - right;
                }
            }
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * 排序+双指针
     *
     * @param nums
     * @return
     */
    public static int triangleNumber3(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1, k = 0; k < j; j--) {
                while (k < j && nums[k] + nums[j] <= nums[i]) {
                    k++;
                }
                ans += j - k;
            }
        }
        System.out.println(ans);
        return ans;
    }
}

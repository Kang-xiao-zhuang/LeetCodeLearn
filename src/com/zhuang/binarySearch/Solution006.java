package com.zhuang.binarySearch;

/**
 * description: Solution006
 * date: 2022/9/16 8:39
 * author: Zhuang
 * version: 1.0
 */
public class Solution006 {
    public static void main(String[] args) {
        Solution006 solution006 = new Solution006();
        int[] nums1 = {55, 30, 5, 4, 2};
        int[] nums2 = {100, 20, 10, 10, 5};
        solution006.maxDistance(nums1, nums2);
        solution006.maxDistance2(nums1, nums2);
    }

    /**
     * https://leetcode.cn/problems/maximum-distance-between-a-pair-of-values/
     *
     * @param nums1 整数数组
     * @param nums2 整数数组
     * @return 所有 有效 下标对 (i, j) 中的 最大距离 。如果不存在有效下标对，返回 0
     */
    public int maxDistance(int[] nums1, int[] nums2) {
        int cnt = 0;
        int res = 0;
        for (int i = 0; i < nums2.length; i++) {
            while (cnt < nums1.length && nums1[cnt] > nums2[i]) {
                cnt++;
            }
            if (cnt < nums1.length) {
                res = Math.max(res, i - cnt);
            }
        }
        return res;
    }

    public int maxDistance2(int[] nums1, int[] nums2) {
        int res = 0;
        for (int i = 0; i < nums1.length; i++) {
            int left = 0;
            int right = nums2.length - 1;
            while (left < right) {
                int mid = left + right + 1 >> 1;
                if (nums2[mid] >= nums1[i]) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
                if (i <= left) {
                    res = Math.max(res, left - i);
                }
            }
        }
        return res;
    }
}

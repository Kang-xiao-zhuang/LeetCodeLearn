package com.zhuang.daily.twotwo.october;

/**
 * description: Solution002
 * date: 2022/10/10 8:30
 * author: Zhuang
 * version: 1.0
 */
public class Solution002 {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode.cn/problems/minimum-swaps-to-make-sequences-increasing/
     * 10.10
     *
     * @param nums1 整型数组
     * @param nums2 整型数组
     * @return 使 nums1 和 nums2 严格递增 所需操作的最小次数
     */
    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int a = 0, b = 1;
        for (int i = 1; i < n; i++) {
            int at = a, bt = b;
            a = b = n;
            if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
                a = Math.min(a, at);
                b = Math.min(b, bt + 1);
            }
            if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                a = Math.min(a, bt);
                b = Math.min(b, at + 1);
            }
        }
        return Math.min(a, b);
    }
}

package com.zhuang.binarySearch;

import java.util.Arrays;

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
        int[] nums = {9};
        int[] piles={30,11,23,4,20};
        solution006.maxDistance(nums1, nums2);
        solution006.maxDistance2(nums1, nums2);
        solution006.minimumSize(nums, 2);
        solution006.minEatingSpeed(piles,5);
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

    /**
     * https://leetcode.cn/problems/minimum-limit-of-balls-in-a-bag/
     *
     * @param nums          整数数组
     * @param maxOperations 整数
     * @return 最小开销
     */
    public int minimumSize(int[] nums, int maxOperations) {
        int ans = 0;
        int left = 1, right = 1000000000;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int cnt = 0;
            for (int num : nums) {
                if (num > mid) {
                    cnt += num / mid;
                    if (num % mid == 0) {
                        cnt -= 1;
                    }
                    if (cnt > maxOperations) {
                        break;
                    }
                }
            }
            if (cnt > maxOperations) {
                left = mid + 1;
            } else {
                ans = mid;
                right = mid - 1;
            }
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/koko-eating-bananas/
     * 9.21
     *
     * @param piles 香蕉
     * @param h     小时
     * @return 她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）
     */
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = 0;
        for (int pile : piles) {
            high = Math.max(high, pile);
        }
        int k = high;
        while (low < high) {
            int speed = (high - low) / 2 + low;
            long time = getTime(piles, speed);
            if (time <= h) {
                k = speed;
                high = speed;
            } else {
                low = speed + 1;
            }
        }
        return k;
    }

    public long getTime(int[] piles, int speed) {
        long time = 0;
        for (int pile : piles) {
            int curTime = (pile + speed - 1) / speed;
            time += curTime;
        }
        return time;
    }

    /**
     * https://leetcode.cn/problems/magnetic-force-between-two-balls/
     * @param position 整数数组
     * @param m 整数
     * @return 最大化的最小磁力
     */
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int left = 1, right = position[position.length - 1] - position[0], ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(mid, position, m)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public boolean check(int x, int[] position, int m) {
        int pre = position[0], cnt = 1;
        for (int i = 1; i < position.length; ++i) {
            if (position[i] - pre >= x) {
                pre = position[i];
                cnt += 1;
            }
        }
        return cnt >= m;
    }
}

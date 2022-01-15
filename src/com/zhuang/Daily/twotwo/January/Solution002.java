package com.zhuang.Daily.twotwo.January;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

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

    /**
     * https://leetcode-cn.com/problems/largest-number-at-least-twice-of-others/
     * 1.13
     *
     * @param nums 整数数组
     * @return 返回 最大元素的下标
     */
    public int dominantIndex(int[] nums) {
        int m1 = -1, m2 = -1;
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > m1) {
                m2 = m1;
                m1 = nums[i];
                index = i;
            } else if (nums[i] > m2) {
                m2 = nums[i];
            }
        }
        return m1 >= m2 * 2 ? index : -1;
    }

    /**
     * https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/
     * 1.14
     *
     * @param nums1 整数数组
     * @param nums2 整数数组
     * @param k     整数
     * @return 最小的 k 个数对
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2) -> {
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i, 0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            if (idxPair[1] + 1 < n) {
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/calculate-money-in-leetcode-bank/
     * 1.15
     *
     * @param n 天数
     * @return 总价
     */
    public int totalMoney(int n) {
        int week = 1, day = 1;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += week + day - 1;
            ++day;
            if (day == 8) {
                day = 1;
                ++week;
            }
        }
        return res;
    }
}

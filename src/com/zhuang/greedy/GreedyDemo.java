package com.zhuang.greedy;

import java.util.Arrays;

/**
 * @Description 贪心例子
 * @Author Zhuang
 * @Date 2023/8/9 09:41
 * @Version 1.0
 **/
public class GreedyDemo {
    public static void main(String[] args) {

    }


    /**
     * https://leetcode.cn/problems/assign-cookies/
     *
     * @param g 小孩数组
     * @param s 饼干数组
     * @return 满足的小孩数量
     */
    public int findContentChildren(int[] g, int[] s) {
        // 排序
        Arrays.sort(g);
        Arrays.sort(s);
        int start = s.length - 1;
        int count = 0;
        // 先满足大胃口
        for (int index = g.length - 1; index >= 0; index--) {
            if (start >= 0 && g[index] <= s[start]) {
                start--;
                count++;
            }
        }
        return count;
    }

    public int findContentChildren2(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int start = 0;
        int count = 0;
        for (int i = 0; i < s.length && start < g.length; i++) {
            if (s[i] >= g[start]) {
                start++;
                count++;
            }
        }
        return count;
    }

    /**
     * https://leetcode.cn/problems/wiggle-subsequence/
     *
     * @param nums 数组
     * @return 个数
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums == null) return 0;
        if (nums.length < 2) return nums.length;
        int sum = 1;
        Boolean direction = null;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) continue;//相同值不算摆动
            if (nums[i] - nums[i - 1] > 0) {
                if (direction != null && direction) continue;//上一次是正值
                direction = true;
            } else {
                if (direction != null && !direction) continue;//上一次是负值
                direction = false;
            }
            sum++;
        }
        return sum;
    }


    /**
     * https://leetcode.cn/problems/maximum-subarray/description/
     *
     * @param nums 数组
     * @return 最大子数组的和
     */
    public int maxSubArray(int[] nums) {
        // 子数组的起始位置
        int count = 0;
        if (nums.length == 1) return nums[0];
        int sum = Integer.MIN_VALUE;
        for (int num : nums) {
            count += num;
            // 取区间累计的最大值（相当于不断确定最大子序终止位置）
            sum = Math.max(sum, count);
            if (count <= 0) count = 0;
        }
        return sum;
    }

}

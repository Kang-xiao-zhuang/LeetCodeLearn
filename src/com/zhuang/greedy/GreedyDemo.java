package com.zhuang.greedy;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @Description 贪心例子
 * @Author Zhuang
 * @Date 2023/8/9 09:41
 * @Version 1.0
 **/
public class GreedyDemo {
    public static void main(String[] args) {
        GreedyDemo demo = new GreedyDemo();
        int[] nums = {2, -3, -1, 5, -4};
        demo.largestSumAfterKNegations(nums, 2);
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

    /**
     * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
     *
     * @param prices 价格
     * @return 最大利润
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res += Math.max(prices[i] - prices[i - 1], 0);
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/jump-game/
     *
     * @param nums 数组
     * @return 是否可以到达最后一个目的地
     */
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        // 当前能覆盖的范围
        int coverRange = 0;
        for (int i = 0; i <= coverRange; i++) {
            coverRange = Math.max(nums[i] + i, coverRange);
            // 如果能覆盖的范围大于等于最后 说明可以到达
            if (coverRange >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * https://leetcode.cn/problems/maximize-sum-of-array-after-k-negations/description/
     *
     * @param nums 数组
     * @param k    整数k的次数
     * @return 数组可能的最大和
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        // 将数组按照绝对值大小从大到小排序，注意要按照绝对值的大小
        nums = IntStream.of(nums)
                .boxed()
                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                .mapToInt(Integer::intValue).toArray();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            //从前向后遍历，遇到负数将其变为正数，同时k--
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
        }
        // 如果k还大于0 继续遍历最小的一位 变号
        if (k % 2 == 1) {
            nums[len - 1] = -nums[len - 1];
        }
        return Arrays.stream(nums).sum();
    }

    /**
     * https://leetcode.cn/problems/lemonade-change/description/
     *
     * @param bills 整数数组
     * @return 能否给每位顾客正确找零
     */
    public boolean lemonadeChange(int[] bills) {
        // 5元数量
        int five = 0;
        // 10元数量
        int ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                five--;
                ten++;
            } else if (bill == 20) {
                if (ten > 0) {
                    five--;
                    ten--;
                } else {
                    five -= 3;
                }
            }
            if (five < 0 || ten < 0) return false;
        }
        return true;
    }
}

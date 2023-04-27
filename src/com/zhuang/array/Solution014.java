package com.zhuang.array;

public class Solution014 {
    public static void main(String[] args) {
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 5, 1, 2};
        canCompleteCircuit(gas, cost);
    }

    /**
     * https://leetcode.cn/problems/gas-station/
     *
     * @param gas  加油站
     * @param cost 汽油
     * @return 是否存在解
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int sumCurrent = 0;
        int res = 0;
        int sum = 0;
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            sumCurrent += gas[i] - cost[i];
            if (sumCurrent < 0) {
                res = i + 1;
                sumCurrent = 0;
            }
        }
        System.out.println(sum < 0 ? -1 : res);
        return sum < 0 ? -1 : res;
    }

    /**
     * https://leetcode.cn/problems/squares-of-a-sorted-array/
     *
     * @param nums 非递减顺序 排序的整数数组 nums
     * @return 返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序
     */
    public int[] sortedSquares(int[] nums) {
        int index = nums.length - 1;
        int left = 0;
        int right = nums.length - 1;
        int[] res = new int[nums.length];
        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                res[index--] = nums[left] * nums[left];
                left++;
            } else {
                res[index--] = nums[right] * nums[right];
                right--;
            }
        }
        return res;
    }
}

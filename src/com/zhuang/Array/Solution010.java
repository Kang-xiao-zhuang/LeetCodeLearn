package com.zhuang.Array;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Classname Solution010
 * @Description 数组学习
 * @Date 2021/8/9 8:53
 * @Author by Zhuang
 */
public class Solution010 {
    public static void main(String[] args) {
        //   int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        //   int[] nums = {1, 1, 2};
        //   removeDuplicates(nums);
        //    int[] nums = {0, 1, 0, 3, 12};
        //    moveZeroes(nums);
        //     moveZeroes2(nums);
        int[] prices = {1, 2, 3, 4, 5};
        //    maxProfit(prices);

        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        //  rotate(nums, 3);
        rotate2(nums, 3);

        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(matrix);

    }

    /**
     * https://leetcode-cn.com/problems/move-zeroes/
     *
     * @param nums 数组
     */
    public static void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != j) {
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                j++;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void moveZeroes2(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                int temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;
                slow++;
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 1;
        int fast = 1;
        while (fast < nums.length) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        System.out.println(slow);
        return slow;
    }

    /**
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
     *
     * @param prices 整数数组
     * @return 最大利润
     */
    public static int maxProfit(int[] prices) {
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                sum += prices[i] - prices[i - 1];
            }
        }
        System.out.println(sum);
        return sum;
    }

    /**
     * https://leetcode-cn.com/problems/rotate-array/
     *
     * @param nums 数组
     * @param k    次数
     */
    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        int[] temp = new int[len];
        for (int i = 0; i < len; i++) {
            temp[(i + k) % len] = nums[i];
        }
        System.arraycopy(temp, 0, nums, 0, len);
    }


    public static void rotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }


    /**
     * https://leetcode-cn.com/problems/contains-duplicate/
     *
     * @param nums 数组
     * @return 布尔
     */
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i : nums) {
            if (set.add(i) == false) {
                return true;
            }
        }
        return false;
    }

    /**
     * https://leetcode-cn.com/problems/rotate-image/
     *
     * @param matrix 二维数组
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = temp[i][j];
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }
}
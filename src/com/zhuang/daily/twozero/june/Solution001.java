package com.zhuang.daily.twozero.june;

import java.util.*;

/**
 * description: Solution001
 * date: 2022/11/3 16:26
 * author: Zhuang
 * version: 1.0
 */
public class Solution001 {
    public static void main(String[] args) {
        Solution001 solution001 = new Solution001();
        int[] candies = {2, 3, 5, 1, 3};
        solution001.kidsWithCandies(candies, 3);
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        solution001.spiralOrder(matrix);
    }

    /**
     * https://leetcode.cn/problems/kids-with-the-greatest-number-of-candies/
     * 2020.6.1
     *
     * @param candies      数组
     * @param extraCandies 整数
     * @return 额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        ArrayList<Boolean> res = new ArrayList<>();
        int max = 0;
        /*  for (int candy : candies) {
            max = Math.max(candy, max);
        }*/
        int[] temp = Arrays.copyOf(candies, candies.length);
        Arrays.sort(temp);
        max = temp[temp.length - 1];
        for (int candy : candies) {
            res.add(candy + extraCandies >= max);
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/qiu-12n-lcof/
     * 2020.6.2
     *
     * @param n 数
     * @return 结果
     */
    public int sumNums(int n) {
        boolean x = n > 1 && (n += sumNums(n - 1)) > 0;
        return n;
    }

    /**
     * https://leetcode.cn/problems/product-of-array-except-self/
     * 2022.6.4
     *
     * @param nums 数组
     * @return 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        // L 和 R 分别表示左右两侧的乘积列表
        int[] L = new int[n];
        int[] R = new int[n];
        L[0] = 1;
        // L[i] 为索引 i 左侧所有元素的乘积
        // 对于索引为 '0' 的元素，因为左侧没有元素，所以 L[0] = 1
        for (int i = 1; i < n; i++) {
            L[i] = nums[i - 1] * L[i - 1];
        }
        R[n - 1] = 1;
        // R[i] 为索引 i 右侧所有元素的乘积
        // 对于索引为 'length-1' 的元素，因为右侧没有元素，所以 R[length-1] = 1
        for (int i = n - 2; i >= 0; i--) {
            R[i] = nums[i + 1] * R[i + 1];
        }
        // 对于索引 i，除 nums[i] 之外其余各元素的乘积就是左侧所有元素的乘积乘以右侧所有元素的乘积
        for (int i = 0; i < n; i++) {
            ans[i] = L[i] * R[i];
        }
        return ans;
    }

    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ans[0] = 1;
        for (int i = 1; i < n; i++) {
            ans[i] = nums[i - 1] * ans[i - 1];
        }
        int R = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = ans[i] * R;
            R *= nums[i];
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
     * 2022.6.5
     *
     * @param matrix 矩阵
     * @return 按照从外向里以顺时针的顺序依次打印出每一个数字
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] order = new int[rows * cols];
        int index = 0;
        int left = 0;
        int right = cols - 1;
        int top = 0;
        int bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                order[index] = matrix[top][i];
                index++;
            }
            for (int j = top + 1; j <= bottom; j++) {
                order[index] = matrix[j][right];
                index++;
            }
            if (left < right && top < bottom) {
                for (int i = right - 1; i > left; i--) {
                    order[index] = matrix[bottom][i];
                    index++;
                }
                for (int j = bottom; j > top; j--) {
                    order[index] = matrix[j][left];
                    index++;
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }

    /**
     * https://leetcode.cn/problems/longest-consecutive-sequence/
     * 2020.6.6
     *
     * @param nums 整数数组
     * @return 找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longestStreak = 0;

        for (int num : set) {
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}

package com.zhuang.binarySearch;

/**
 * description: Solution005
 * date: 2022/9/11 10:32
 * author: Zhuang
 * version: 1.0
 */
public class Solution005 {
    public static void main(String[] args) {
        Solution005 solution005 = new Solution005();
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        solution005.searchMatrix(matrix, 3);
    }


    /**
     * https://leetcode.cn/problems/kth-missing-positive-number/
     *
     * @param arr 数组
     * @param k   整数k
     * @return int
     */
    public static int findKthPositive(int[] arr, int k) {
        for (int i : arr) {
            if (i <= k) {
                k++;
            }
        }
        return k;
    }

    public static int findKthPositive2(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            // left是更新为mid，所以+1使中点偏右防止死循环
            int mid = ((right - left + 1) >> 1) + left;
            if (arr[mid] - mid - 1 < k) {
                // mid下标处的数之前缺失的正整数数量小于k
                left = mid;
            } else {
                right = mid + 1;
            }
        }
        return arr[left] - left - 1 >= k ? k : k + left + 1;
    }

    /**
     * https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/
     *
     * @param numbers 整数数组
     * @param target  目标数
     * @return 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2
     */
    public int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        while (l <= r) {
            if (numbers[l] + numbers[r] == target) {
                return new int[]{l + 1, r + 1};
            } else if (numbers[l] + numbers[r] > target) {
                r--;
            } else {
                l++;
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * https://leetcode.cn/problems/count-negative-numbers-in-a-sorted-matrix/
     *
     * @param grid 矩阵
     * @return 负数 的数目
     */
    public int countNegatives(int[][] grid) {
        int cnt = 0;
        for (int[] x : grid) {
            for (int y : x) {
                if (y < 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    /**
     * https://leetcode.cn/problems/search-a-2d-matrix/
     *
     * @param matrix 矩阵
     * @param target 值
     * @return 布尔
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (matrix[mid / n][mid % n] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return matrix[left / n][right % n] == target;
    }
}

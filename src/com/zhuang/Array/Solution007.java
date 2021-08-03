package com.zhuang.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @Deacription 数组学习
 * @Author Zhuang
 * @Date 2021/8/2 8:58
 * @Version 1.0
 **/
public class Solution007 {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        //  findDiagonalOrder(matrix);
        //findDiagonalOrder2(matrix);
        int[] nums = {1, 3, 5, 6};
        searchInsert2(nums, 7);
    }

    /**
     * https://leetcode-cn.com/problems/diagonal-traverse/
     *
     * @param matrix 二维数组
     * @return int数组
     */
    public static int[] findDiagonalOrder(int[][] matrix) {
        // Check for empty matrices
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }

        // Variables to track the size of the matrix
        int N = matrix.length;
        int M = matrix[0].length;

        // The two arrays as explained in the algorithm
        int[] result = new int[N * M];
        int k = 0;
        ArrayList<Integer> intermediate = new ArrayList<Integer>();

        // We have to go over all the elements in the first
        // row and the last column to cover all possible diagonals
        for (int d = 0; d < N + M - 1; d++) {

            // Clear the intermediate array every time we start
            // to process another diagonal
            intermediate.clear();

            // We need to figure out the "head" of this diagonal
            // The elements in the first row and the last column
            // are the respective heads.
            int r = d < M ? 0 : d - M + 1;
            int c = d < M ? d : M - 1;

            // Iterate until one of the indices goes out of scope
            // Take note of the index math to go down the diagonal
            while (r < N && c > -1) {

                intermediate.add(matrix[r][c]);
                ++r;
                --c;
            }

            // Reverse even numbered diagonals. The
            // article says we have to reverse odd
            // numbered articles but here, the numbering
            // is starting from 0 :P
            if (d % 2 == 0) {
                Collections.reverse(intermediate);
            }

            for (int i = 0; i < intermediate.size(); i++) {
                result[k++] = intermediate.get(i);
            }
        }
        System.out.println(Arrays.toString(result));
        return result;
    }


    public static int[] findDiagonalOrder2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[]{};
        }
        int r = 0, c = 0;
        int row = matrix.length, col = matrix[0].length;
        int[] res = new int[row * col];
        for (int i = 0; i < res.length; i++) {
            res[i] = matrix[r][c];
            // r + c 即为遍历的层数，偶数向上遍历，奇数向下遍历
            if ((r + c) % 2 == 0) {
                if (c == col - 1) {
                    // 往下移动一格准备向下遍历
                    r++;
                } else if (r == 0) {
                    // 往右移动一格准备向下遍历
                    c++;
                } else {
                    // 往上移动
                    r--;
                    c++;
                }
            } else {
                if (r == row - 1) {
                    // 往右移动一格准备向上遍历
                    c++;
                } else if (c == 0) {
                    // 往下移动一格准备向上遍历
                    r++;
                } else {
                    // 往下移动
                    r++;
                    c--;
                }
            }
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/search-insert-position/
     *
     * @param nums   数组
     * @param target 目标值
     * @return int
     */
    public static int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    public static int searchInsert2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 定义左右指针
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                System.out.println(mid);
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(left);
        return left;
    }
}

package com.zhuang.array;

import java.util.*;

/**
 * @Description 数组学习
 * @Author Zhuang
 * @Date 2021/7/26 8:41
 * @Version 1.0
 **/
public class Solution004 {
    public static void main(String[] args) {

        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        //setZeroes(matrix);
        // setZeroes2(matrix);

        int[][] accounts = {{1, 5}, {7, 3}, {3, 5}};
        //   maximumWealth(accounts);

        int[] candies = {2, 3, 5, 1, 3};
        int extraCandies = 3;
        kidsWithCandies(candies, extraCandies);
    }

    /**
     * https://leetcode-cn.com/problems/valid-sudoku/
     *
     * @param board 二维数组
     * @return 布尔
     */
    public static boolean isValidSudoku(char[][] board) {
        // 定义三个二维数组
        int[][] rows = new int[9][9];
        int[][] cols = new int[9][9];
        int[][] boxes = new int[9][9];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int index_box = (i / 3) * 3 + j / 3;
                    if (rows[i][num] == 1) {
                        return false;
                    } else {
                        rows[i][num] = 1;
                    }
                    if (cols[j][num] == 1) {
                        return false;
                    } else {
                        cols[j][num] = 1;
                    }
                    if (boxes[index_box][num] == 1) {
                        return false;
                    } else {
                        boxes[index_box][num] = 1;
                    }
                }
            }
        }
        return true;
    }

    /**
     * https://leetcode-cn.com/problems/set-matrix-zeroes/
     * 布尔数组
     *
     * @param matrix 数组
     */
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }

    /**
     * 解法2 使用Set集合
     *
     * @param matrix 二维数组
     */
    public static void setZeroes2(int[][] matrix) {
        HashSet<Integer> row = new HashSet<>();
        HashSet<Integer> col = new HashSet<>();
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row.add(i);
                    col.add(j);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (row.contains(i)) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 0; j < n; j++) {
            if (col.contains(j)) {
                for (int i = 0; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        System.out.println(Arrays.deepToString(matrix));
    }

    /**
     * https://leetcode-cn.com/problems/richest-customer-wealth/
     *
     * @param accounts 二维数组
     * @return int
     */
    public static int maximumWealth(int[][] accounts) {
        int max = 0;
        int m = accounts.length;
        int n = accounts[0].length;
        for (int i = 0; i < m; i++) {
            int temp = 0;
            for (int j = 0; j < n; j++) {
                temp += accounts[i][j];
            }
            max = Math.max(max, temp);
        }
        System.out.println(max);
        return max;
    }

    /**
     * https://leetcode-cn.com/problems/kids-with-the-greatest-number-of-candies/
     *
     * @param candies      数组
     * @param extraCandies int
     * @return 集合
     */
    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        // 先定义数组
        ArrayList<Boolean> list = new ArrayList<Boolean>(candies.length);
        int max = 0;
        for (int candy : candies) {
            // 看谁最大
            max = Math.max(candy, max);
        }
        for (int candy : candies) {
            list.add(candy + extraCandies >= max);
        }
        System.out.println(list.toString());
        return list;
    }
}

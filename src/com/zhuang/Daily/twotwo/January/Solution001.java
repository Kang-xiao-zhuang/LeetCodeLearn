package com.zhuang.Daily.twotwo.January;

import java.util.Arrays;

/**
 * @Classname Solution001
 * @Description 用一句话描述类的作用
 * @Date 2022/1/1 6:54
 * @Author by dell
 */

public class Solution001 {
    public static void main(String[] args) {
        Solution001 solution001 = new Solution001();
        int[] original = {1, 2, 3, 4};
        solution001.construct2DArray(original, 2, 2);
    }

    /**
     * https://leetcode-cn.com/problems/convert-1d-array-into-2d-array/
     * 1.1
     *
     * @param original 一维整数数组
     * @param m        整数
     * @param n        整数
     * @return 二维数组
     */
    public int[][] construct2DArray(int[] original, int m, int n) {
        if (original.length != n * m) {
            return new int[][]{};
        }
        int[][] ans = new int[m][n];
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = original[index++];
            }
        }
        System.out.println(Arrays.deepToString(ans));
        return ans;
    }
}

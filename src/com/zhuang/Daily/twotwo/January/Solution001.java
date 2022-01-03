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

    /**
     * https://leetcode-cn.com/problems/elimination-game/
     *
     * @param n 整数
     * @return 最后剩下的数字
     */
    public int lastRemaining(int n) {
        return n == 1 ? 1 : 2 * (n / 2 + 1 - lastRemaining(n / 2));
    }

    /**
     * https://leetcode-cn.com/problems/day-of-the-week/
     *
     * @param day   整数
     * @param month 整数
     * @param year  整数
     * @return 对应一周中的哪一天
     */
    public String dayOfTheWeek(int day, int month, int year) {
        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        /* 输入年份之前的年份的天数贡献 */
        int days = 365 * (year - 1971) + (year - 1969) / 4;
        /* 输入年份中，输入月份之前的月份的天数贡献 */
        for (int i = 0; i < month - 1; ++i) {
            days += monthDays[i];
        }
        if ((year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) && month >= 3) {
            days += 1;
        }
        /* 输入月份中的天数贡献 */
        days += day;
        return week[(days + 3) % 7];
    }
}

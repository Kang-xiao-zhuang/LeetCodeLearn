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

    /**
     * https://leetcode-cn.com/problems/cat-and-mouse/
     * 1.4
     *
     * @param graph 图
     * @return 获胜的代号
     */
    static int N = 55;
    static int[][][] f = new int[2 * N * N][N][N];
    int[][] g;
    int n;

    public int catMouseGame(int[][] graph) {
        g = graph;
        n = g.length;
        for (int k = 0; k < 2 * n * n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    f[k][i][j] = -1;
                }
            }
        }
        return dfs(0, 1, 2);
    }

    // 0:draw / 1:mouse / 2:cat
    int dfs(int k, int a, int b) {
        int ans = f[k][a][b];
        if (a == 0) ans = 1;
        else if (a == b) ans = 2;
        else if (k >= 2 * n * n) ans = 0;
        else if (ans == -1) {
            if (k % 2 == 0) { // mouse
                boolean win = false, draw = false;
                for (int ne : g[a]) {
                    int t = dfs(k + 1, ne, b);
                    if (t == 1) win = true;
                    else if (t == 0) draw = true;
                    if (win) break;
                }
                if (win) ans = 1;
                else if (draw) ans = 0;
                else ans = 2;
            } else { // cat
                boolean win = false, draw = false;
                for (int ne : g[b]) {
                    if (ne == 0) continue;
                    int t = dfs(k + 1, a, ne);
                    if (t == 2) win = true;
                    else if (t == 0) draw = true;
                    if (win) break;
                }
                if (win) ans = 2;
                else if (draw) ans = 0;
                else ans = 1;
            }
        }
        f[k][a][b] = ans;
        return ans;
    }
}

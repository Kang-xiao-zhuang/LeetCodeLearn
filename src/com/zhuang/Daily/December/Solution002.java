package com.zhuang.Daily.December;

import java.util.*;

/**
 * @Classname Solution002
 * @Description 2021.12.8-2021.12.15每日一题
 * @Date 2021/12/8 20:26
 * @Author by dell
 */
public class Solution002 {
    public static void main(String[] args) {
        //String[] board = {"XOX", "O O", "XOX"};
        //validTicTacToe(board);

        //String[] words = {"step", "steps", "stripe", "stepple"};
        //shortestCompletingWord("1s3 PSt", words);
        int[][] grid = {{3, 0, 8, 4}, {2, 4, 5, 7}, {9, 2, 6, 3}, {0, 3, 1, 0}};
        maxIncreaseKeepingSkyline(grid);
    }

    /**
     * https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays/
     * 12.8
     *
     * @param nums 整数数组
     * @param k    整数
     * @return 数组
     */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        long[] sum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        long[][] f = new long[n + 10][4];
        for (int i = n - k + 1; i >= 1; i--) {
            for (int j = 1; j < 4; j++) {
                f[i][j] = Math.max(f[i + 1][j], f[i + k][j - 1] + sum[i + k - 1] - sum[i - 1]);
            }
        }
        int[] ans = new int[3];
        int i = 1, j = 3, idx = 0;
        while (j > 0) {
            if (f[i + 1][j] > f[i + k][j - 1] + sum[i + k - 1] - sum[i - 1]) {
                i++;
            } else {
                ans[idx++] = i - 1;
                i += k;
                j--;
            }
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/valid-tic-tac-toe-state/
     * 12.9
     *
     * @param board 字符串数组
     * @return 布尔
     */
    public static boolean validTicTacToe(String[] board) {
        int xCount = 0, oCount = 0;
        for (String row : board) {
            for (char c : row.toCharArray()) {
                xCount = (c == 'X') ? (xCount + 1) : xCount;
                oCount = (c == 'O') ? (oCount + 1) : oCount;
            }
        }
        if (oCount != xCount && oCount != xCount - 1) {
            return false;
        }
        if (win(board, 'X') && oCount != xCount - 1) {
            return false;
        }
        if (win(board, 'O') && oCount != xCount) {
            return false;
        }
        return true;
    }

    public static boolean win(String[] board, char p) {
        for (int i = 0; i < 3; i++) {
            if (p == board[0].charAt(i) && p == board[1].charAt(i) && p == board[2].charAt(i)) {
                return true;
            }
            if (p == board[i].charAt(0) && p == board[i].charAt(1) && p == board[i].charAt(2)) {
                return true;
            }
        }
        if (p == board[0].charAt(0) && p == board[1].charAt(1) && p == board[2].charAt(2)) {
            return true;
        }
        if (p == board[0].charAt(2) && p == board[1].charAt(1) && p == board[2].charAt(0)) {
            return true;
        }
        return false;
    }

    /**
     * https://leetcode-cn.com/problems/shortest-completing-word/
     * 12.10
     *
     * @param licensePlate 字符串
     * @param words        字符串数组
     * @return 最短补全词
     */
    public static String shortestCompletingWord(String licensePlate, String[] words) {
        int[] count = getCount(licensePlate);
        String ans = null;
        for (String word : words) {
            int[] cur = getCount(word);
            boolean isOk = true;
            for (int i = 0; i < 26 && isOk; i++) {
                if (count[i] > cur[i]) {
                    isOk = false;
                }
            }
            if (isOk && (ans == null || ans.length() > word.length())) {
                ans = word;
            }
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * 获取个数
     *
     * @param s 字符串
     * @return 个数
     */
    public static int[] getCount(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                count[Character.toLowerCase(c) - 'a']++;
            }
        }
        return count;
    }

    class TopVotedCandidate {
        List<int[]> list = new ArrayList<>();

        /**
         * https://leetcode-cn.com/problems/online-election/
         * 12.11
         *
         * @param persons 整数数组
         * @param times   整数数组
         */
        public TopVotedCandidate(int[] persons, int[] times) {
            int val = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < times.length; i++) {
                map.put(persons[i], map.getOrDefault(persons[i], 0) + 1);
                if (map.get(persons[i]) >= val) {
                    val = map.get(persons[i]);
                    list.add(new int[]{times[i], persons[i]});
                }
            }
        }

        /**
         * @param t 时刻
         * @return 候选人的编号
         */
        public int q(int t) {
            int l = 0, r = list.size() - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (list.get(mid)[0] <= t) l = mid;
                else r = mid - 1;
            }
            return list.get(r)[0] <= t ? list.get(r)[1] : 0;
        }
    }

    /**
     * https://leetcode-cn.com/problems/to-lower-case/
     * 12.12
     *
     * @param s 字符串
     * @return 新的字符串
     */
    public String toLowerCase(String s) {
        return s.toLowerCase();
    }

    /**
     * https://leetcode-cn.com/problems/max-increase-to-keep-city-skyline/
     * 12.13
     *
     * @param grid 二维数组
     * @return 最大总和
     */
    public static int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[] rowMax = new int[n];
        int[] colMax = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rowMax[i] = Math.max(rowMax[i], grid[i][j]);
                colMax[j] = Math.max(colMax[j], grid[i][j]);
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res += Math.min(rowMax[i], colMax[j]) - grid[i][j];
            }
        }
        System.out.println(res);
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/course-schedule-iii/
     * 12.14
     *
     * @param courses 数组
     * @return 最多可以修读的课程数目
     */
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        int sum = 0;
        for (int[] c : courses) {
            int d = c[0], e = c[1];
            sum += d;
            q.add(d);
            if (sum > e) {
                sum -= q.poll();
            }
        }
        return q.size();
    }
}

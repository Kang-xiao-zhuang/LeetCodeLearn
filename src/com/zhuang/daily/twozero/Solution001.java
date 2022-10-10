package com.zhuang.daily.twozero;

import java.util.Arrays;

/**
 * description: Solution001
 * date: 2022/10/9 9:26
 * author: Zhuang
 * version: 1.0
 */
public class Solution001 {

    public static void main(String[] args) {
        Solution001 solution001 = new Solution001();
        int[][] boards = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        solution001.gameOfLife(boards);
        solution001.gameOfLife2(boards);
    }

    /**
     * https://leetcode.cn/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/
     * 2020.4.1
     *
     * @param seq 有效括号字符串
     * @return 将其分成两个不相交的有效括号字符串，A 和 B，并使这两个字符串的深度最小
     */
    public int[] maxDepthAfterSplit(String seq) {
        // 定义深度
        int depth = 0;
        int len = seq.length();
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            if (seq.charAt(i) == '(') {
                depth++;
                ans[i] = depth % 2;
            } else {
                ans[i] = depth % 2;
                depth--;
            }
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/game-of-life/
     * 2020.4.2
     *
     * @param board 包含 m × n 个格子的面板
     */
    public void gameOfLife(int[][] board) {
        int[] neighbors = {0, 1, -1};

        int rows = board.length;
        int cols = board[0].length;

        // 创建复制数组 copyBoard
        int[][] copyBoard = new int[rows][cols];

        // 从原数组复制一份到 copyBoard 中
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                copyBoard[row][col] = board[row][col];
            }
        }

        // 遍历面板每一个格子里的细胞
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                // 对于每一个细胞统计其八个相邻位置里的活细胞数量
                int liveNeighbors = 0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            int r = (row + neighbors[i]);
                            int c = (col + neighbors[j]);

                            // 查看相邻的细胞是否是活细胞
                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && (copyBoard[r][c] == 1)) {
                                liveNeighbors += 1;
                            }
                        }
                    }
                }

                // 规则 1 或规则 3
                if ((copyBoard[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[row][col] = 0;
                }
                // 规则 4
                if (copyBoard[row][col] == 0 && liveNeighbors == 3) {
                    board[row][col] = 1;
                }
            }
        }
    }

    public void gameOfLife2(int[][] board) {
        int[] neighbors = {0, 1, -1};

        int rows = board.length;
        int cols = board[0].length;

        // 遍历面板每一个格子里的细胞
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                // 对于每一个细胞统计其八个相邻位置里的活细胞数量
                int liveNeighbors = 0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            // 相邻位置的坐标
                            int r = (row + neighbors[i]);
                            int c = (col + neighbors[j]);

                            // 查看相邻的细胞是否是活细胞
                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && (Math.abs(board[r][c]) == 1)) {
                                liveNeighbors += 1;
                            }
                        }
                    }
                }

                // 规则 1 或规则 3
                if ((board[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    // -1 代表这个细胞过去是活的现在死了
                    board[row][col] = -1;
                }
                // 规则 4
                if (board[row][col] == 0 && liveNeighbors == 3) {
                    // 2 代表这个细胞过去是死的现在活了
                    board[row][col] = 2;
                }
            }
        }

        // 遍历 board 得到一次更新后的状态
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] > 0) {
                    board[row][col] = 1;
                } else {
                    board[row][col] = 0;
                }
            }
        }
    }

    /**
     * https://leetcode.cn/problems/string-to-integer-atoi/
     * 2020.4.3
     *
     * @param s 字符串
     * @return 整数
     */
    public int myAtoi(String s) {
        Automaton automaton = new Automaton();
        for (int i = 0; i < s.length(); i++) {
            automaton.get(s.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }


    public int myAtoi2(String str) {
        if ((str == null) || (str.length() <= 0)) {
            return 0;
        }
        //正负数的最大最小值
        int MAX = Integer.MAX_VALUE;
        //正负数的最大最小值
        int MIN = Integer.MIN_VALUE;
        int res = 0;
        int index = 0;
        //过滤空格
        while ((index < str.length()) && (str.charAt(index) == ' ')) {
            index++;
        }
        if (index == str.length()) return 0;
        //取正负号
        char firstChar = str.charAt(index);
        boolean positive = true;
        if (!isDigit(firstChar)) {
            if ((firstChar != '+') && (firstChar != '-')) {
                return 0;
            }
            index++;
            positive = firstChar != '-';
        }
        //用负数保存正负数的边界，这样不会溢出
        //正数 -2147483647
        //负数 -2147483648
        int limit = positive ? (-MAX) : MIN;
        //过滤0
        while ((index < str.length()) && (str.charAt(index) == '0')){
            index++;
        }
        //取每一位,在非字符截止
        while ((index < str.length()) && isDigit(str.charAt(index))) {
            int digit = str.charAt(index++) - '0';
            if (res < ((limit + digit) / 10)) {
                return positive ? MAX : MIN;
            }
            //这里的res>=limit
            res = (res * 10) - digit; //用减法
        }
        return positive ? (-res) : res;
    }

    public boolean isDigit(char c) {
        return (c >= '0') && (c <= '9');
    }
}

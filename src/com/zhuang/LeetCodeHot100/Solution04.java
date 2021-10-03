package com.zhuang.LeetCodeHot100;

/**
 * @Classname Solution04
 * @Description LeetCode 热题 HOT
 * @Date 2021/10/3 12:57
 * @Author by Zhuang
 */

public class Solution04 {
    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        exist(board, "ABCCED");
    }

    /**
     * https://leetcode-cn.com/problems/word-search/
     * 第79题
     *
     * @param board 二维字符网格
     * @param word  字符串单词
     * @return 单词是否存在网格中
     */
    public static boolean exist(char[][] board, String word) {
        int rows = board.length, cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    System.out.println(true);
                    return true;
                }
            }
        }
        System.out.println(false);
        return false;
    }

    /**
     * @param board   二维数组
     * @param visited 布尔数组
     * @param i       行
     * @param j       列
     * @param word    单词
     * @param k       索引
     * @return 布尔值
     */
    private static boolean check(char[][] board, boolean[][] visited, int i, int j, String word, int k) {
        if (board[i][j] != word.charAt(k)) {
            return false;
        } else if (k == word.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] direction : directions) {
            int newi = i + direction[0], newj = j + direction[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (!visited[newi][newj]) {
                    boolean flag = check(board, visited, newi, newj, word, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }
}

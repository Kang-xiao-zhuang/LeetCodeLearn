package com.zhuang.dfs;

/**
 * description: Solution001
 * date: 2022/10/16 10:50
 * author: Zhuang
 * version: 1.0
 */
public class Solution001 {
    public static void main(String[] args) {
        Solution001 solution001 = new Solution001();
        int[][] grid = {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
    }

    /**
     * https://leetcode.cn/problems/island-perimeter/
     * 双重for
     *
     * @param grid 二维数组
     * @return 周长
     */
    public int islandPerimeter(int[][] grid) {
        int land = 0;
        int edge = 0;
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    land++;
                    // 向下遍历
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) {
                        edge++;
                    }
                    if (j < grid[0].length - 1 && grid[i][j + 1] == 1) {
                        edge++;
                    }
                }
            }
        }
        return 4 * land - edge * 2;
    }

    // dfs
    public int islandPerimeter2(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    return dfs(grid, i, j);
                }
            }
        }
        return 0;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (!(i >= 0 && i < grid.length && j >= 0 && j < grid[0].length)) {
            return 1;
        }
        if (grid[i][j] == 0) {
            return 1;
        }
        if (grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = 2;
        return dfs(grid, i - 1, j) + dfs(grid, i + 1, j) + dfs(grid, i, j - 1) + dfs(grid, i, j + 1);

    }


    public int islandPerimeter3(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int ans = 0;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (x < 0 || x >= row || y < 0 || y >= col || grid[x][y] == 0) {
                            count+=1;
                        }
                    }
                    ans += count;
                }
            }
        }
        return ans;
    }
}

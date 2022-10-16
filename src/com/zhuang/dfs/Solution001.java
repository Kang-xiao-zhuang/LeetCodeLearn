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
                            count += 1;
                        }
                    }
                    ans += count;
                }
            }
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/max-area-of-island/
     *
     * @param grid 二维表
     * @return 最大面积
     */
    public int maxAreaOfIsland(int[][] grid) {
        //定义一个表示岛屿的面积
        int max = 0;
        //这两个for循环是来遍历整张二维格上的所有陆地的。
        //i 表示行，j表示列
        for(int i = 0;i<grid.length;i++){
            for(int j = 0; j<grid[0].length;j++){
                //陆地的格
                if(grid[i][j]==1){
                    //取出最大的面积
                    max = Math.max(max,dfs2(grid,i,j));
                }
            }
        }
        //返回最大的陆地面积
        return max;
    }
    public int  dfs2(int[][] grid,int i,int j){
        //当超出岛屿边界（上下左右）的时候，就直接退出，特别要加上当遍历到海洋的时候也要退出，
        if(i<0||j<0 || i>=grid.length || j>= grid[0].length|| grid[i][j]==0) return 0;
        //定义一个变量表示岛屿的面积，就是包含几个陆地
        int sum = 1;
        //将陆地改为海洋，防止重复陆地重复遍历。
        grid[i][j] =0;
        //遍历上方元素，每遍历一次陆地就加一
        sum += dfs2(grid,i-1,j);
        //遍历下方元素
        sum +=dfs2(grid,i+1,j);
        //遍历右边元素
        sum +=dfs2(grid,i,j+1);
        //遍历左边元素
        sum += dfs2(grid,i,j-1);
        return sum;
    }

}

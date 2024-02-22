package com.zhuang.dfs;

import java.util.LinkedList;
import java.util.List;

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
        int[][] grid2 = {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        solution001.numEnclaves(grid2);
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
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                //陆地的格
                if (grid[i][j] == 1) {
                    //取出最大的面积
                    max = Math.max(max, dfs2(grid, i, j));
                }
            }
        }
        //返回最大的陆地面积
        return max;
    }

    public int dfs2(int[][] grid, int i, int j) {
        //当超出岛屿边界（上下左右）的时候，就直接退出，特别要加上当遍历到海洋的时候也要退出，
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) return 0;
        //定义一个变量表示岛屿的面积，就是包含几个陆地
        int sum = 1;
        //将陆地改为海洋，防止重复陆地重复遍历。
        grid[i][j] = 0;
        //遍历上方元素，每遍历一次陆地就加一
        sum += dfs2(grid, i - 1, j);
        //遍历下方元素
        sum += dfs2(grid, i + 1, j);
        //遍历右边元素
        sum += dfs2(grid, i, j + 1);
        //遍历左边元素
        sum += dfs2(grid, i, j - 1);
        return sum;
    }

    /**
     * https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof/?envType=study-plan-v2&envId=coding-interviews
     * LCR 129. 字母迷宫
     * 此方法超时 下面是不超时方法！
     *
     * @param grid   二维字符串数组
     * @param target 目标单词
     * @return boolean
     */
    public boolean wordPuzzle(char[][] grid, String target) {
        // 初始化访问数组
        int m = grid.length;
        int n = grid[0].length;
        int[][] visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (recur(grid, target, i, j, 0, visited)) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * @param grid    数组
     * @param target  单词
     * @param i       横向
     * @param j       纵向
     * @param k       遍历的次数限制
     * @param visited 访问数组
     * @return 是否满足要求
     */
    private boolean recur(char[][] grid, String target, int i, int j, int k, int[][] visited) {
        // 越界
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return false;
        }
        // 访问过返回
        if (visited[i][j] == 1) {
            return false;
        }
        // 如果不匹配
        if (grid[i][j] != target.charAt(k)) {
            return false;
        }
        // 遍历结束
        if (k == target.length() - 1) {
            return true;
        }
        // 设置访问过
        visited[i][j] = 1;
        // 满足相等对应单词，开始回溯递归
        // 下 上 左 右 顺序
        boolean down = recur(grid, target, i + 1, j, k + 1, visited);
        boolean up = recur(grid, target, i - 1, j, k + 1, visited);
        boolean left = recur(grid, target, i, j - 1, k + 1, visited);
        boolean right = recur(grid, target, i, j + 1, k + 1, visited);

        visited[i][j] = 0;

        return down || up || left || right;
    }

    public boolean wordPuzzle2(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //  搜索开始的位置可以是网格 board 中的任一个位置
                // z只要有一条路走通了，则可以直接返回结果
                if (backtrack(board, i, j, word, 0)) return true;
            }
        }
        return false;
    }

    // board：网格, i：当前行, j：当前列, word：待匹配字符串, k：当前匹配到字符串word的位置
    public boolean backtrack(char[][] board, int i, int j, String word, int k) {

        // 1. 下标越界，2. 当前位置与word中字符不匹配，则可以直接返回（剪枝）
        if (i < 0 || j < 0 || i >= board.length || j >= board[i].length || board[i][j] != word.charAt(k)) return false;

        if (k == word.length() - 1) return true; // 说明word中的字符全部被匹配

        board[i][j] = '\0'; // 使用board数组充当了访问标记数组，节省了空间（非常妙！）

        // 做出选择：可以向 上，下，左，右移动，只要有一条路可以走通即可, 所以使用 ||
        boolean res = backtrack(board, i, j + 1, word, k + 1) || backtrack(board, i + 1, j, word, k + 1) ||
                backtrack(board, i - 1, j, word, k + 1) || backtrack(board, i, j - 1, word, k + 1);

        board[i][j] = word.charAt(k); // 撤销选择, 还原board数组

        return res; // 返回结果
    }

    /**
     * https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/description/?envType=study-plan-v2&envId=coding-interviews
     *
     * @param m   横向
     * @param n   纵向
     * @param cnt 目标值
     * @return 总数
     */
    public int wardrobeFinishing(int m, int n, int cnt) {
        // 访问数组
        boolean[][] visited = new boolean[m][n];
        return dfs(0, 0, m, n, cnt, visited);
    }

    // dfs 搜索
    private int dfs(int i, int j, int m, int n, int cnt, boolean[][] visited) {
        // 判断越界条件和和是否满足条件
        if (i >= m || j >= n || cnt < getSum(i, j) || visited[i][j]) {
            return 0;
        }
        // 标记被访问过
        visited[i][j] = true;
        // 沿着右边和下边继续访问，因为不能重复 所以左边和上边不用管
        // 结果的次数多加一次
        return dfs(i + 1, j, m, n, cnt, visited) + dfs(i, j + 1, m, n, cnt, visited) + 1;
    }

    private int getSum(int i, int j) {
        int sum = 0;
        while (i != 0) {
            sum += i % 10;
            i /= 10;
        }
        while (j != 0) {
            sum += j % 10;
            j /= 10;
        }
        return sum;
    }

    // 结果集
    LinkedList<List<Integer>> res = new LinkedList<>();
    // 路径
    LinkedList<Integer> path = new LinkedList<>();

    /**
     * https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/description/?envType=study-plan-v2&envId=coding-interviews
     *
     * @param root   TreeNode
     * @param target int
     * @return List<List < Integer>>
     */
    public List<List<Integer>> pathTarget(TreeNode root, int target) {
        recur(root, target);
        return res;
    }

    private void recur(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        // 总和减去节点的值
        sum -= root.val;
        // 遍历到底 和 满足和的要求 停止遍历 保存路径
        if (sum == 0 && root.left == null && root.right == null) {
            // 加入路径的组合集合中
            res.add(new LinkedList<>(path));
        }
        // 层序遍历向左
        recur(root.left, sum);
        // 层序遍历向右
        recur(root.right, sum);
        // 如果不符合的值就去掉
        path.removeLast();
    }

    public static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int m, n;
    private boolean[][] visited;

    /**
     * https://leetcode.cn/problems/number-of-enclaves/description/
     *
     * @param grid int[][]
     * @return int
     */
    public int numEnclaves(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfsgrid(grid, i, 0);
            dfsgrid(grid, i, n - 1);
        }
        for (int j = 1; j < n - 1; j++) {
            dfsgrid(grid, 0, j);
            dfsgrid(grid, m - 1, j);
        }
        int enclaves = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    enclaves++;
                }
            }
        }
        return enclaves;
    }

    public void dfsgrid(int[][] grid, int row, int col) {
        if (row < 0 || row >= m || col < 0 || col >= n || grid[row][col] == 0 || visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        for (int[] dir : dirs) {
            dfsgrid(grid, row + dir[0], col + dir[1]);
        }
    }
}

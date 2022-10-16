#### [463. 岛屿的周长](https://leetcode.cn/problems/island-perimeter/)

给定一个 `row x col` 的二维网格地图 `grid` ，其中：`grid[i][j] = 1` 表示陆地， `grid[i][j] = 0` 表示水域。

网格中的格子 **水平和垂直** 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。

岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。

 

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/island.png)

```
输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
输出：16
解释：它的周长是上面图片中的 16 个黄色的边
```

**示例 2：**

```
输入：grid = [[1]]
输出：4
```

**示例 3：**

```
输入：grid = [[1,0]]
输出：4
```

 

**提示：**

- `row == grid.length`
- `col == grid[i].length`
- `1 <= row, col <= 100`
- `grid[i][j]` 为 `0` 或 `1`

**双重for**

```java
class Solution {
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
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/ebcc69f26d054020b299cb3e3b046d50.png)

```java
class Solution {
    public int islandPerimeter(int[][] grid) {
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
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/c1401e9669784ef2848c822709fee1a3.png)

**模拟**

```java
class Solution {
    public int islandPerimeter(int[][] grid) {
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
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/892ad548e3284db6b7793e9c2c45f0c2.png)



#### [695. 岛屿的最大面积](https://leetcode.cn/problems/max-area-of-island/)

给你一个大小为 `m x n` 的二进制矩阵 `grid` 。

**岛屿** 是由一些相邻的 `1` (代表土地) 构成的组合，这里的「相邻」要求两个 `1` 必须在 **水平或者竖直的四个方向上** 相邻。你可以假设 `grid` 的四个边缘都被 `0`（代表水）包围着。

岛屿的面积是岛上值为 `1` 的单元格的数目。

计算并返回 `grid` 中最大的岛屿面积。如果没有岛屿，则返回面积为 `0` 。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/05/01/maxarea1-grid.jpg)

```
输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
输出：6
解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
```

**示例 2：**

```
输入：grid = [[0,0,0,0,0,0,0,0]]
输出：0
```

 

**提示：**

- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 50`
- `grid[i][j]` 为 `0` 或 `1`

**DFS**

```java
class Solution {
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
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/32fd6ade7edc4263a948bbdd569cacb5.png)
#### [1779. 找到最近的有相同 X 或 Y 坐标的点](https://leetcode.cn/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate/)

给你两个整数 `x` 和 `y` ，表示你在一个笛卡尔坐标系下的 `(x, y)` 处。同时，在同一个坐标系下给你一个数组 `points` ，其中 `points[i] = [ai, bi]` 表示在 `(ai, bi)` 处有一个点。当一个点与你所在的位置有相同的 `x` 坐标或者相同的 `y` 坐标时，我们称这个点是 **有效的** 。

请返回距离你当前位置 **曼哈顿距离** 最近的 **有效** 点的下标（下标从 **0** 开始）。如果有多个最近的有效点，请返回下标 **最小** 的一个。如果没有有效点，请返回 `-1` 。

两个点 `(x1, y1)` 和 `(x2, y2)` 之间的 **曼哈顿距离** 为 `abs(x1 - x2) + abs(y1 - y2)` 。

 

**示例 1：**

```
输入：x = 3, y = 4, points = [[1,2],[3,1],[2,4],[2,3],[4,4]]
输出：2
解释：所有点中，[3,1]，[2,4] 和 [4,4] 是有效点。有效点中，[2,4] 和 [4,4] 距离你当前位置的曼哈顿距离最小，都为 1 。[2,4] 的下标最小，所以返回 2 。
```

**示例 2：**

```
输入：x = 3, y = 4, points = [[3,4]]
输出：0
提示：答案可以与你当前所在位置坐标相同。
```

**示例 3：**

```
输入：x = 3, y = 4, points = [[2,3]]
输出：-1
解释：没有 有效点。
```

 

**提示：**

- `1 <= points.length <= 104`
- `points[i].length == 2`
- `1 <= x, y, ai, bi <= 104`

```java
class Solution {
    public int nearestValidPoint(int x, int y, int[][] points) {
        int minDis = Integer.MAX_VALUE;
        int ans = -1;
        for (int i = 0; i < points.length; i++) {
            int px = points[i][0];
            int py = points[i][1];
            if (x == px || y == py) {
                int dis = Math.abs(x - px) + Math.abs(y - py);
                if (dis < minDis) {
                    minDis = dis;
                    ans = i;
                }
            }
        }
        return ans;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/fa97eb51fe134fc08866da6151e2d07e.png)

#### [1769. 移动所有球到每个盒子所需的最小操作数](https://leetcode.cn/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/)

有 `n` 个盒子。给你一个长度为 `n` 的二进制字符串 `boxes` ，其中 `boxes[i]` 的值为 `'0'` 表示第 `i` 个盒子是 **空** 的，而 `boxes[i]` 的值为 `'1'` 表示盒子里有 **一个** 小球。

在一步操作中，你可以将 **一个** 小球从某个盒子移动到一个与之相邻的盒子中。第 `i` 个盒子和第 `j` 个盒子相邻需满足 `abs(i - j) == 1` 。注意，操作执行后，某些盒子中可能会存在不止一个小球。

返回一个长度为 `n` 的数组 `answer` ，其中 `answer[i]` 是将所有小球移动到第 `i` 个盒子所需的 **最小** 操作数。

每个 `answer[i]` 都需要根据盒子的 **初始状态** 进行计算。

 

**示例 1：**

```
输入：boxes = "110"
输出：[1,1,3]
解释：每个盒子对应的最小操作数如下：
1) 第 1 个盒子：将一个小球从第 2 个盒子移动到第 1 个盒子，需要 1 步操作。
2) 第 2 个盒子：将一个小球从第 1 个盒子移动到第 2 个盒子，需要 1 步操作。
3) 第 3 个盒子：将一个小球从第 1 个盒子移动到第 3 个盒子，需要 2 步操作。将一个小球从第 2 个盒子移动到第 3 个盒子，需要 1 步操作。共计 3 步操作。
```

**示例 2：**

```
输入：boxes = "001011"
输出：[11,8,5,4,3,4]
```

 

**提示：**

- `n == boxes.length`
- `1 <= n <= 2000`
- `boxes[i]` 为 `'0'` 或 `'1'`

```java
class Solution {
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int sm = 0;
            for (int j = 0; j < n; j++) {
                if (boxes.charAt(j) == '1') {
                    sm += Math.abs(j - i);
                }
            }
            res[i] = sm;
        }
        return res;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/4a1fc342a5db40e08658d6866a23bdbe.png)

#### [1796. 字符串中第二大的数字](https://leetcode.cn/problems/second-largest-digit-in-a-string/)

给你一个混合字符串 `s` ，请你返回 `s` 中 **第二大** 的数字，如果不存在第二大的数字，请你返回 `-1` 。

**混合字符串** 由小写英文字母和数字组成。

 

**示例 1：**

```
输入：s = "dfa12321afd"
输出：2
解释：出现在 s 中的数字包括 [1, 2, 3] 。第二大的数字是 2 。
```

**示例 2：**

```
输入：s = "abc1111"
输出：-1
解释：出现在 s 中的数字只包含 [1] 。没有第二大的数字。
```

 

**提示：**

- `1 <= s.length <= 500`
- `s` 只包含小写英文字母和（或）数字。

```java
class Solution {
    public int secondHighest(String s) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch) && !list.contains(ch - '0')) {
                list.add(ch - '0');
            }
        }
        if (list.isEmpty() || list.size() == 1) {
            return -1;
        }
        Collections.sort(list);
        return list.get(list.size() - 2);
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/408822a9afcb491f8a665499456a7766.png)

```java
class Solution {
    public int secondHighest(String s) {
        int first = -1;
        int second = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = c - '0';
                if (num > first) {
                    second = first;
                    first = num;
                } else if (num < first && num > second) {
                    second = num;
                }
            }
        }
        return second;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/844650f6c3cb4dad884233e8cb7bbeab.png)

#### [1774. 最接近目标价格的甜点成本](https://leetcode.cn/problems/closest-dessert-cost/)

你打算做甜点，现在需要购买配料。目前共有 `n` 种冰激凌基料和 `m` 种配料可供选购。而制作甜点需要遵循以下几条规则：

- 必须选择 **一种** 冰激凌基料。
- 可以添加 **一种或多种** 配料，也可以不添加任何配料。
- 每种类型的配料 **最多两份** 。

给你以下三个输入：

- `baseCosts` ，一个长度为 `n` 的整数数组，其中每个 `baseCosts[i]` 表示第 `i` 种冰激凌基料的价格。
- `toppingCosts`，一个长度为 `m` 的整数数组，其中每个 `toppingCosts[i]` 表示 **一份** 第 `i` 种冰激凌配料的价格。
- `target` ，一个整数，表示你制作甜点的目标价格。

你希望自己做的甜点总成本尽可能接近目标价格 `target` 。

返回最接近 `target` 的甜点成本。如果有多种方案，返回 **成本相对较低** 的一种。

 

**示例 1：**

```
输入：baseCosts = [1,7], toppingCosts = [3,4], target = 10
输出：10
解释：考虑下面的方案组合（所有下标均从 0 开始）：
- 选择 1 号基料：成本 7
- 选择 1 份 0 号配料：成本 1 x 3 = 3
- 选择 0 份 1 号配料：成本 0 x 4 = 0
总成本：7 + 3 + 0 = 10 。
```

**示例 2：**

```
输入：baseCosts = [2,3], toppingCosts = [4,5,100], target = 18
输出：17
解释：考虑下面的方案组合（所有下标均从 0 开始）：
- 选择 1 号基料：成本 3
- 选择 1 份 0 号配料：成本 1 x 4 = 4
- 选择 2 份 1 号配料：成本 2 x 5 = 10
- 选择 0 份 2 号配料：成本 0 x 100 = 0
总成本：3 + 4 + 10 + 0 = 17 。不存在总成本为 18 的甜点制作方案。
```

**示例 3：**

```
输入：baseCosts = [3,10], toppingCosts = [2,5], target = 9
输出：8
解释：可以制作总成本为 8 和 10 的甜点。返回 8 ，因为这是成本更低的方案。
```

**示例 4：**

```
输入：baseCosts = [10], toppingCosts = [1], target = 1
输出：10
解释：注意，你可以选择不添加任何配料，但你必须选择一种基料。
```

 

**提示：**

- `n == baseCosts.length`
- `m == toppingCosts.length`
- `1 <= n, m <= 10`
- `1 <= baseCosts[i], toppingCosts[i] <= 104`
- `1 <= target <= 104`

```java
class Solution {
    int res;

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        res = Arrays.stream(baseCosts).min().getAsInt();
        for (int b : baseCosts) {
            dfs(toppingCosts, 0, b, target);
        }
        return res;
    }

    public void dfs(int[] toppingCosts, int p, int curCost, int target) {
        if (Math.abs(res - target) < curCost - target) {
            return;
        } else if (Math.abs(res - target) >= Math.abs(curCost - target)) {
            if (Math.abs(res - target) > Math.abs(curCost - target)) {
                res = curCost;
            } else {
                res = Math.min(res, curCost);
            }
        }
        if (p == toppingCosts.length) {
            return;
        }
        dfs(toppingCosts, p + 1, curCost + toppingCosts[p] * 2, target);
        dfs(toppingCosts, p + 1, curCost + toppingCosts[p], target);
        dfs(toppingCosts, p + 1, curCost, target);
    }
}

```

![在这里插入图片描述](https://img-blog.csdnimg.cn/c1bab94c11c64cdbbff68ecd6d73e8d2.png)

#### [1687. 从仓库到码头运输箱子](https://leetcode.cn/problems/delivering-boxes-from-storage-to-ports/)

你有一辆货运卡车，你需要用这一辆车把一些箱子从仓库运送到码头。这辆卡车每次运输有 **箱子数目的限制** 和 **总重量的限制** 。

给你一个箱子数组 `boxes` 和三个整数 `portsCount`, `maxBoxes` 和 `maxWeight` ，其中 `boxes[i] = [portsi, weighti]` 。

- `portsi` 表示第 `i` 个箱子需要送达的码头， `weightsi` 是第 `i` 个箱子的重量。
- `portsCount` 是码头的数目。
- `maxBoxes` 和 `maxWeight` 分别是卡车每趟运输箱子数目和重量的限制。

箱子需要按照 **数组顺序** 运输，同时每次运输需要遵循以下步骤：

- 卡车从 `boxes` 队列中按顺序取出若干个箱子，但不能违反 `maxBoxes` 和 `maxWeight` 限制。
- 对于在卡车上的箱子，我们需要 **按顺序** 处理它们，卡车会通过 **一趟行程** 将最前面的箱子送到目的地码头并卸货。如果卡车已经在对应的码头，那么不需要 **额外行程** ，箱子也会立马被卸货。
- 卡车上所有箱子都被卸货后，卡车需要 **一趟行程** 回到仓库，从箱子队列里再取出一些箱子。

卡车在将所有箱子运输并卸货后，最后必须回到仓库。

请你返回将所有箱子送到相应码头的 **最少行程** 次数。

 

**示例 1：**

```
输入：boxes = [[1,1],[2,1],[1,1]], portsCount = 2, maxBoxes = 3, maxWeight = 3
输出：4
解释：最优策略如下：
- 卡车将所有箱子装上车，到达码头 1 ，然后去码头 2 ，然后再回到码头 1 ，最后回到仓库，总共需要 4 趟行程。
所以总行程数为 4 。
注意到第一个和第三个箱子不能同时被卸货，因为箱子需要按顺序处理（也就是第二个箱子需要先被送到码头 2 ，然后才能处理第三个箱子）。
```

**示例 2：**

```
输入：boxes = [[1,2],[3,3],[3,1],[3,1],[2,4]], portsCount = 3, maxBoxes = 3, maxWeight = 6
输出：6
解释：最优策略如下：
- 卡车首先运输第一个箱子，到达码头 1 ，然后回到仓库，总共 2 趟行程。
- 卡车运输第二、第三、第四个箱子，到达码头 3 ，然后回到仓库，总共 2 趟行程。
- 卡车运输第五个箱子，到达码头 3 ，回到仓库，总共 2 趟行程。
总行程数为 2 + 2 + 2 = 6 。
```

**示例 3：**

```
输入：boxes = [[1,4],[1,2],[2,1],[2,1],[3,2],[3,4]], portsCount = 3, maxBoxes = 6, maxWeight = 7
输出：6
解释：最优策略如下：
- 卡车运输第一和第二个箱子，到达码头 1 ，然后回到仓库，总共 2 趟行程。
- 卡车运输第三和第四个箱子，到达码头 2 ，然后回到仓库，总共 2 趟行程。
- 卡车运输第五和第六个箱子，到达码头 3 ，然后回到仓库，总共 2 趟行程。
总行程数为 2 + 2 + 2 = 6 。
```

**示例 4：**

```
输入：boxes = [[2,4],[2,5],[3,1],[3,2],[3,7],[3,1],[4,4],[1,3],[5,2]], portsCount = 5, maxBoxes = 5, maxWeight = 7
输出：14
解释：最优策略如下：
- 卡车运输第一个箱子，到达码头 2 ，然后回到仓库，总共 2 趟行程。
- 卡车运输第二个箱子，到达码头 2 ，然后回到仓库，总共 2 趟行程。
- 卡车运输第三和第四个箱子，到达码头 3 ，然后回到仓库，总共 2 趟行程。
- 卡车运输第五个箱子，到达码头 3 ，然后回到仓库，总共 2 趟行程。
- 卡车运输第六和第七个箱子，到达码头 3 ，然后去码头 4 ，然后回到仓库，总共 3 趟行程。
- 卡车运输第八和第九个箱子，到达码头 1 ，然后去码头 5 ，然后回到仓库，总共 3 趟行程。
总行程数为 2 + 2 + 2 + 2 + 3 + 3 = 14 。
```

 

**提示：**

- `1 <= boxes.length <= 105`
- `1 <= portsCount, maxBoxes, maxWeight <= 105`
- `1 <= portsi <= portsCount`
- `1 <= weightsi <= maxWeight`
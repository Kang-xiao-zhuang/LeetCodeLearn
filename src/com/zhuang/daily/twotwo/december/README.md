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
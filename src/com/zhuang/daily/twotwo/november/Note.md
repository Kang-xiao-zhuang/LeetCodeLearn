#### [1662. 检查两个字符串数组是否相等](https://leetcode.cn/problems/check-if-two-string-arrays-are-equivalent/)

难度简单56

给你两个字符串数组 `word1` 和 `word2` 。如果两个数组表示的字符串相同，返回 `true` ；否则，返回 `false` *。*

**数组表示的字符串** 是由数组中的所有元素 **按顺序** 连接形成的字符串。

 

**示例 1：**

```
输入：word1 = ["ab", "c"], word2 = ["a", "bc"]
输出：true
解释：
word1 表示的字符串为 "ab" + "c" -> "abc"
word2 表示的字符串为 "a" + "bc" -> "abc"
两个字符串相同，返回 true
```

**示例 2：**

```
输入：word1 = ["a", "cb"], word2 = ["ab", "c"]
输出：false
```

**示例 3：**

```
输入：word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
输出：true
```

 

**提示：**

- `1 <= word1.length, word2.length <= 103`
- `1 <= word1[i].length, word2[i].length <= 103`
- `1 <= sum(word1[i].length), sum(word2[i].length) <= 103`
- `word1[i]` 和 `word2[i]` 由小写字母组成



```java
class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (String s : word1) {
            s1.append(s);
        }
        for (String s : word2) {
            s2.append(s);
        }
        return (s1.toString()).equals(s2.toString());
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/2e55b3eadcf64ee7b9c71ca83511ce15.png)

```java
class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        return join(word1).equals(join(word2));
    }

    public String join(String[] words) {
        StringBuilder ret = new StringBuilder();
        for (String s : words) {
            ret.append(s);
        }
        return ret.toString();
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/296ce70946154d6db381d0b920933772.png)

#### [1620. 网络信号最好的坐标](https://leetcode.cn/problems/coordinate-with-maximum-network-quality/)

难度中等42收藏分享切换为英文接收动态反馈

给你一个数组 `towers` 和一个整数 `radius` 。

数组 `towers` 中包含一些网络信号塔，其中 `towers[i] = [xi, yi, qi]` 表示第 `i` 个网络信号塔的坐标是 `(xi, yi)` 且信号强度参数为 `qi` 。所有坐标都是在 X-Y 坐标系内的 **整数** 坐标。两个坐标之间的距离用 **欧几里得距离** 计算。

整数 `radius` 表示一个塔 **能到达** 的 **最远距离** 。如果一个坐标跟塔的距离在 `radius` 以内，那么该塔的信号可以到达该坐标。在这个范围以外信号会很微弱，所以 `radius` 以外的距离该塔是 **不能到达的** 。

如果第 `i` 个塔能到达 `(x, y)` ，那么该塔在此处的信号为 `⌊qi / (1 + d)⌋` ，其中 `d` 是塔跟此坐标的距离。一个坐标的 **信号强度** 是所有 **能到达** 该坐标的塔的信号强度之和。

请你返回数组 `[cx, cy]` ，表示 **信号强度** 最大的 **整数** 坐标点 `(cx, cy)` 。如果有多个坐标网络信号一样大，请你返回字典序最小的 **非负** 坐标。

**注意：**

- 坐标 

  ```
  (x1, y1)
  ```

   字典序比另一个坐标 

  ```
  (x2, y2)
  ```

   

  小，需满足以下条件之一：

  - 要么 `x1 < x2` ，
  - 要么 `x1 == x2` 且 `y1 < y2` 。

- `⌊val⌋` 表示小于等于 `val` 的最大整数（向下取整函数）。

 

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/10/17/untitled-diagram.png)

```
输入：towers = [[1,2,5],[2,1,7],[3,1,9]], radius = 2
输出：[2,1]
解释：
坐标 (2, 1) 信号强度之和为 13
- 塔 (2, 1) 强度参数为 7 ，在该点强度为 ⌊7 / (1 + sqrt(0)⌋ = ⌊7⌋ = 7
- 塔 (1, 2) 强度参数为 5 ，在该点强度为 ⌊5 / (1 + sqrt(2)⌋ = ⌊2.07⌋ = 2
- 塔 (3, 1) 强度参数为 9 ，在该点强度为 ⌊9 / (1 + sqrt(1)⌋ = ⌊4.5⌋ = 4
没有别的坐标有更大的信号强度。
```

**示例 2：**

```
输入：towers = [[23,11,21]], radius = 9
输出：[23,11]
解释：由于仅存在一座信号塔，所以塔的位置信号强度最大。
```

**示例 3：**

```
输入：towers = [[1,2,13],[2,1,7],[0,1,9]], radius = 2
输出：[1,2]
解释：坐标 (1, 2) 的信号强度最大。
```

 

**提示：**

- `1 <= towers.length <= 50`
- `towers[i].length == 3`
- `0 <= xi, yi, qi <= 50`
- `1 <= radius <= 50`

**CV**

```java
class Solution {
    public int[] bestCoordinate(int[][] towers, int radius) {
        int mx = 0;
        int[] ans = new int[] {0, 0};
        for (int i = 0; i < 51; ++i) {
            for (int j = 0; j < 51; ++j) {
                int t = 0;
                for (var e : towers) {
                    double d = Math.sqrt((i - e[0]) * (i - e[0]) + (j - e[1]) * (j - e[1]));
                    if (d <= radius) {
                        t += Math.floor(e[2] / (1 + d));
                    }
                }
                if (mx < t) {
                    mx = t;
                    ans = new int[] {i, j};
                }
            }
        }
        return ans;
    }
}
```


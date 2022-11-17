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

#### [1668. 最大重复子字符串](https://leetcode.cn/problems/maximum-repeating-substring/)

给你一个字符串 `sequence` ，如果字符串 `word` 连续重复 `k` 次形成的字符串是 `sequence` 的一个子字符串，那么单词 `word` 的 **重复值为 `k`** 。单词 `word` 的 **最****大重复值** 是单词 `word` 在 `sequence` 中最大的重复值。如果 `word` 不是 `sequence` 的子串，那么重复值 `k` 为 `0` 。

给你一个字符串 `sequence` 和 `word` ，请你返回 **最大重复值 `k`** 。

 

**示例 1：**

```
输入：sequence = "ababc", word = "ab"
输出：2
解释："abab" 是 "ababc" 的子字符串。
```

**示例 2：**

```
输入：sequence = "ababc", word = "ba"
输出：1
解释："ba" 是 "ababc" 的子字符串，但 "baba" 不是 "ababc" 的子字符串。
```

**示例 3：**

```
输入：sequence = "ababc", word = "ac"
输出：0
解释："ac" 不是 "ababc" 的子字符串。
```

 

**提示：**

- `1 <= sequence.length <= 100`
- `1 <= word.length <= 100`
- `sequence` 和 `word` 都只包含小写英文字母。

```java
class Solution {
    public int maxRepeating(String sequence, String word) {
        int count=0;
        String tmp=word;
        while(sequence.contains(word)){
            word+=tmp;
            count++;
        }
        return count;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/295ed76146754581acd50d0cab6b741d.png)

#### [754. 到达终点数字](https://leetcode.cn/problems/reach-a-number/)

在一根无限长的数轴上，你站在`0`的位置。终点在`target`的位置。

你可以做一些数量的移动 `numMoves` :

- 每次你可以选择向左或向右移动。
- 第 `i` 次移动（从  `i == 1` 开始，到 `i == numMoves` ），在选择的方向上走 `i` 步。

给定整数 `target` ，返回 *到达目标所需的 **最小** 移动次数(即最小 `numMoves` )* 。

 

**示例 1:**

```
输入: target = 2
输出: 3
解释:
第一次移动，从 0 到 1 。
第二次移动，从 1 到 -1 。
第三次移动，从 -1 到 2 。
```

**示例 2:**

```
输入: target = 3
输出: 2
解释:
第一次移动，从 0 到 1 。
第二次移动，从 1 到 3 。
```

 

**提示:**

- `-109 <= target <= 109`
- `target != 0`

```Java
class Solution {
    public int reachNumber(int target) {
        target = Math.abs(target);
        int k = 0;
        while (target > 0) {
            k++;
            target -= k;
        }
        return target % 2 == 0 ? k : k + 1 + k % 2;
    }
}
```

#### [1106. 解析布尔表达式](https://leetcode.cn/problems/parsing-a-boolean-expression/)

给你一个以字符串形式表述的 [布尔表达式](https://baike.baidu.com/item/布尔表达式/1574380?fr=aladdin)（boolean） `expression`，返回该式的运算结果。

有效的表达式需遵循以下约定：

- `"t"`，运算结果为 `True`
- `"f"`，运算结果为 `False`
- `"!(expr)"`，运算过程为对内部表达式 `expr` 进行逻辑 **非的运算**（NOT）
- `"&(expr1,expr2,...)"`，运算过程为对 2 个或以上内部表达式 `expr1, expr2, ...` 进行逻辑 **与的运算**（AND）
- `"|(expr1,expr2,...)"`，运算过程为对 2 个或以上内部表达式 `expr1, expr2, ...` 进行逻辑 **或的运算**（OR）

 

**示例 1：**

```
输入：expression = "!(f)"
输出：true
```

**示例 2：**

```
输入：expression = "|(f,t)"
输出：true
```

**示例 3：**

```
输入：expression = "&(t,f)"
输出：false
```

**示例 4：**

```
输入：expression = "|(&(t,f,t),!(t))"
输出：false
```

 

**提示：**

- `1 <= expression.length <= 20000`
- `expression[i]` 由 `{'(', ')', '&', '|', '!', 't', 'f', ','}` 中的字符组成。
- `expression` 是以上述形式给出的有效表达式，表示一个布尔值。

```java
class Solution {
    public boolean parseBoolExpr(String expression) {
        Deque<Character> stack = new ArrayDeque<>();
        int n = expression.length();
        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            if (c == ',') {
                continue;
            } else if (c != ')') {
                stack.push(c);
            } else {
                // 遇到')'开始执行下列代码
                int t = 0, f = 0;
                while (stack.peek() != '(') {
                    char val = stack.pop();
                    if (val == 't') {
                        t++;
                    } else {
                        f++;
                    }
                }
                // 弹出'('
                stack.pop();
                char op = stack.pop();
                switch (op) {
                    case '!':
                        stack.push(f == 1 ? 't' : 'f');
                        break;
                    case '&':
                        stack.push(f == 0 ? 't' : 'f');
                        break;
                    case '|':
                        stack.push(t > 0 ? 't' : 'f');
                        break;
                    default:
                }
            }
        }
        return stack.pop() == 't';
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/5903b563745f4ad28c7c972a8c1d3928.png)

#### [1678. 设计 Goal 解析器](https://leetcode.cn/problems/goal-parser-interpretation/)

请你设计一个可以解释字符串 `command` 的 **Goal 解析器** 。`command` 由 `"G"`、`"()"` 和/或 `"(al)"` 按某种顺序组成。Goal 解析器会将 `"G"` 解释为字符串 `"G"`、`"()"` 解释为字符串 `"o"` ，`"(al)"` 解释为字符串 `"al"` 。然后，按原顺序将经解释得到的字符串连接成一个字符串。

给你字符串 `command` ，返回 **Goal 解析器** 对 `command` 的解释结果。

 

**示例 1：**

```
输入：command = "G()(al)"
输出："Goal"
解释：Goal 解析器解释命令的步骤如下所示：
G -> G
() -> o
(al) -> al
最后连接得到的结果是 "Goal"
```

**示例 2：**

```
输入：command = "G()()()()(al)"
输出："Gooooal"
```

**示例 3：**

```
输入：command = "(al)G(al)()()G"
输出："alGalooG"
```

 

**提示：**

- `1 <= command.length <= 100`
- `command` 由 `"G"`、`"()"` 和/或 `"(al)"` 按某种顺序组成

**模拟**

```java
class Solution {
    public String interpret(String command) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'G') {
                sb.append("G");
            } else if (command.charAt(i) == '(') {
                if (command.charAt(i + 1) == ')') {
                    sb.append("o");
                } else {
                    sb.append("al");
                }
            }
        }
        return sb.toString();
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/15dc3c01917f465eaf6aae3ddba61ea1.png)

```java
class Solution {
    public String interpret(String command) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < command.length(); i++) {
            switch (command.charAt(i)) {
                case 'G':
                    sb.append("G");
                    break;
                case '(':
                    if (command.charAt(i + 1) == ')') {
                        sb.append("o");
                    } else {
                        i += 2;
                        sb.append("al");
                    }
                    break;
                default:
            }
        }
        return sb.toString();
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/79278537d1ce4c12993c36ca29f4c834.png)



#### [816.模糊坐标](https://leetcode.cn/problems/ambiguous-coordinates/)

我们有一些二维坐标，如 `"(1, 3)"` 或 `"(2, 0.5)"`，然后我们移除所有逗号，小数点和空格，得到一个字符串`S`。返回所有可能的原始字符串到一个列表中。

原始的坐标表示法不会存在多余的零，所以不会出现类似于"00", "0.0", "0.00", "1.0", "001", "00.01"或一些其他更小的数来表示坐标。此外，一个小数点前至少存在一个数，所以也不会出现“.1”形式的数字。

最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。

 

```
示例 1:
输入: "(123)"
输出: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
示例 2:
输入: "(00011)"
输出:  ["(0.001, 1)", "(0, 0.011)"]
解释: 
0.0, 00, 0001 或 00.01 是不被允许的。
示例 3:
输入: "(0123)"
输出: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
示例 4:
输入: "(100)"
输出: [(10, 0)]
解释: 
1.0 是不被允许的。
```

 

**提示:**

- `4 <= S.length <= 12`.
- `S[0]` = "(", `S[S.length - 1]` = ")", 且字符串 `S` 中的其他元素都是数字。

```java
class Solution {
    public List<String> ambiguousCoordinates(String s) {
        int n = s.length() - 2;
        List<String> res = new ArrayList<String>();
        s = s.substring(1, s.length() - 1);
        for (int l = 1; l < n; ++l) {
            List<String> lt = getPos(s.substring(0, l));
            if (lt.isEmpty()) {
                continue;
            }
            List<String> rt = getPos(s.substring(l));
            if (rt.isEmpty()) {
                continue;
            }
            for (String i : lt) {
                for (String j : rt) {
                    res.add("(" + i + ", " + j + ")");
                }
            }
        }
        return res;
    }

    public List<String> getPos(String s) {
        List<String> pos = new ArrayList<String>();
        if (s.charAt(0) != '0' || "0".equals(s)) {
            pos.add(s);
        }
        for (int p = 1; p < s.length(); ++p) {
            if ((p != 1 && s.charAt(0) == '0') || s.charAt(s.length() - 1) == '0') {
                continue;
            }
            pos.add(s.substring(0, p) + "." + s.substring(p));
        }
        return pos;
    }
}
```

#### [1684. 统计一致字符串的数目](https://leetcode.cn/problems/count-the-number-of-consistent-strings/)

给你一个由不同字符组成的字符串 `allowed` 和一个字符串数组 `words` 。如果一个字符串的每一个字符都在 `allowed` 中，就称这个字符串是 **一致字符串** 。

请你返回 `words` 数组中 **一致字符串** 的数目。

 

**示例 1：**

```
输入：allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
输出：2
解释：字符串 "aaab" 和 "baa" 都是一致字符串，因为它们只包含字符 'a' 和 'b' 。
```

**示例 2：**

```
输入：allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
输出：7
解释：所有字符串都是一致的。
```

**示例 3：**

```
输入：allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
输出：4
解释：字符串 "cc"，"acd"，"ac" 和 "d" 是一致字符串。
```

 

**提示：**

- `1 <= words.length <= 104`
- `1 <= allowed.length <= 26`
- `1 <= words[i].length <= 10`
- `allowed` 中的字符 **互不相同** 。
- `words[i]` 和 `allowed` 只包含小写英文字母。

```java
class Solution {
    public int countConsistentStrings(String allowed, String[] words) {
        HashMap<Character, Integer> map = new HashMap<>();
        int len = words.length;
        for (int i = 0; i < allowed.length(); i++) {
            map.put(allowed.charAt(i), 1);
        }
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (!map.containsKey(word.charAt(i))) {
                    len--;
                    break;
                }
            }
        }
        return len;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/415deb27fa4e4656b8a6e1d609e4dcea.png)

#### [764. 最大加号标志](https://leetcode.cn/problems/largest-plus-sign/)

在一个 `n x n` 的矩阵 `grid` 中，除了在数组 `mines` 中给出的元素为 `0`，其他每个元素都为 `1`。`mines[i] = [xi, yi]`表示 `grid[xi][yi] == 0`

返回 `grid` *中包含 `1` 的最大的 **轴对齐** 加号标志的阶数* 。如果未找到加号标志，则返回 `0` 。

一个 `k` 阶由 *`1`* 组成的 **“轴对称”加号标志** 具有中心网格 `grid[r][c] == 1` ，以及4个从中心向上、向下、向左、向右延伸，长度为 `k-1`，由 `1` 组成的臂。注意，只有加号标志的所有网格要求为 `1` ，别的网格可能为 `0` 也可能为 `1` 。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/06/13/plus1-grid.jpg)

```
输入: n = 5, mines = [[4, 2]]
输出: 2
解释: 在上面的网格中，最大加号标志的阶只能是2。一个标志已在图中标出。
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2021/06/13/plus2-grid.jpg)

```
输入: n = 1, mines = [[0, 0]]
输出: 0
解释: 没有加号标志，返回 0 。
```

 

**提示：**

- `1 <= n <= 500`
- `1 <= mines.length <= 5000`
- `0 <= xi, yi < n`
- 每一对 `(xi, yi)` 都 **不重复**

```java
class Solution {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], n);
        }
        Set<Integer> banned = new HashSet<Integer>();
        for (int[] vec : mines) {
            banned.add(vec[0] * n + vec[1]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int count = 0;
            /* left */
            for (int j = 0; j < n; j++) {
                if (banned.contains(i * n + j)) {
                    count = 0;
                } else {
                    count++;
                }
                dp[i][j] = Math.min(dp[i][j], count);
            }
            count = 0;
            /* right */ 
            for (int j = n - 1; j >= 0; j--) {
                if (banned.contains(i * n + j)) {
                    count = 0;
                } else {
                    count++;
                }
                dp[i][j] = Math.min(dp[i][j], count);
            }
        }
        for (int i = 0; i < n; i++) {
            int count = 0;
            /* up */
            for (int j = 0; j < n; j++) {
                if (banned.contains(j * n + i)) {
                    count = 0;
                } else {
                    count++;
                }
                dp[j][i] = Math.min(dp[j][i], count);
            }
            count = 0;
            /* down */
            for (int j = n - 1; j >= 0; j--) {
                if (banned.contains(j * n + i)) {
                    count = 0;
                } else {
                    count++;
                }
                dp[j][i] = Math.min(dp[j][i], count);
                ans = Math.max(ans, dp[j][i]);
            }
        }
        return ans;
    }
}
```

#### [864. 获取所有钥匙的最短路径](https://leetcode.cn/problems/shortest-path-to-get-all-keys/)

给定一个二维网格 `grid` ，其中：

- '.' 代表一个空房间
- '#' 代表一堵
- '@' 是起点
- 小写字母代表钥匙
- 大写字母代表锁

我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。我们不能在网格外面行走，也无法穿过一堵墙。如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。

假设 k 为 钥匙/锁 的个数，且满足 `1 <= k <= 6`，字母表中的前 `k` 个字母在网格中都有自己对应的一个小写和一个大写字母。换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。

返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回 `-1` 。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/07/23/lc-keys2.jpg)

```
输入：grid = ["@.a.#","###.#","b.A.B"]
输出：8
解释：目标是获得所有钥匙，而不是打开所有锁。
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2021/07/23/lc-key2.jpg)

```
输入：grid = ["@..aA","..B#.","....b"]
输出：6
```

**示例 3:**

![img](https://assets.leetcode.com/uploads/2021/07/23/lc-keys3.jpg)

```
输入: grid = ["@Aa"]
输出: -1
```

 

**提示：**

- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 30`
- `grid[i][j]` 只含有 `'.'`, `'#'`, `'@'`, `'a'-``'f``'` 以及 `'A'-'F'`
- 钥匙的数目范围是 `[1, 6]` 
- 每个钥匙都对应一个 **不同** 的字母
- 每个钥匙正好打开一个对应的锁

```java
class Solution {
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        int sx = 0, sy = 0;
        Map<Character, Integer> keyToIndex = new HashMap<Character, Integer>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i].charAt(j) == '@') {
                    sx = i;
                    sy = j;
                } else if (Character.isLowerCase(grid[i].charAt(j))) {
                    if (!keyToIndex.containsKey(grid[i].charAt(j))) {
                        int idx = keyToIndex.size();
                        keyToIndex.put(grid[i].charAt(j), idx);
                    }
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<int[]>();
        int[][][] dist = new int[m][n][1 << keyToIndex.size()];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                Arrays.fill(dist[i][j], -1);
            }
        }
        queue.offer(new int[]{sx, sy, 0});
        dist[sx][sy][0] = 0;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0], y = arr[1], mask = arr[2];
            for (int i = 0; i < 4; ++i) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx].charAt(ny) != '#') {
                    if (grid[nx].charAt(ny) == '.' || grid[nx].charAt(ny) == '@') {
                        if (dist[nx][ny][mask] == -1) {
                            dist[nx][ny][mask] = dist[x][y][mask] + 1;
                            queue.offer(new int[]{nx, ny, mask});
                        }
                    } else if (Character.isLowerCase(grid[nx].charAt(ny))) {
                        int idx = keyToIndex.get(grid[nx].charAt(ny));
                        if (dist[nx][ny][mask | (1 << idx)] == -1) {
                            dist[nx][ny][mask | (1 << idx)] = dist[x][y][mask] + 1;
                            if ((mask | (1 << idx)) == (1 << keyToIndex.size()) - 1) {
                                return dist[nx][ny][mask | (1 << idx)];
                            }
                            queue.offer(new int[]{nx, ny, mask | (1 << idx)});
                        }
                    } else {
                        int idx = keyToIndex.get(Character.toLowerCase(grid[nx].charAt(ny)));
                        if ((mask & (1 << idx)) != 0 && dist[nx][ny][mask] == -1) {
                            dist[nx][ny][mask] = dist[x][y][mask] + 1;
                            queue.offer(new int[]{nx, ny, mask});
                        }
                    }
                }
            }
        }
        return -1;
    }
}
```

#### [1704. 判断字符串的两半是否相似](https://leetcode.cn/problems/determine-if-string-halves-are-alike/)

给你一个偶数长度的字符串 `s` 。将其拆分成长度相同的两半，前一半为 `a` ，后一半为 `b` 。

两个字符串 **相似** 的前提是它们都含有相同数目的元音（`'a'`，`'e'`，`'i'`，`'o'`，`'u'`，`'A'`，`'E'`，`'I'`，`'O'`，`'U'`）。注意，`s` 可能同时含有大写和小写字母。

如果 `a` 和 `b` 相似，返回 `true` ；否则，返回 `false` 。

 

**示例 1：**

```
输入：s = "book"
输出：true
解释：a = "bo" 且 b = "ok" 。a 中有 1 个元音，b 也有 1 个元音。所以，a 和 b 相似。
```

**示例 2：**

```
输入：s = "textbook"
输出：false
解释：a = "text" 且 b = "book" 。a 中有 1 个元音，b 中有 2 个元音。因此，a 和 b 不相似。
注意，元音 o 在 b 中出现两次，记为 2 个。
```

 

**提示：**

- `2 <= s.length <= 1000`
- `s.length` 是偶数
- `s` 由 **大写和小写** 字母组成

```java
class Solution {
    public boolean halvesAreAlike(String s) {
        String a = s.substring(0, s.length() / 2);
        String b = s.substring(s.length() / 2);
        ArrayList<Character> list = new ArrayList<>();
        list.add('a');
        list.add('e');
        list.add('i');
        list.add('o');
        list.add('u');
        list.add('A');
        list.add('E');
        list.add('I');
        list.add('I');
        list.add('O');
        list.add('U');
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < a.length(); i++) {
            if (list.contains(a.charAt(i))) {
                sum1++;
            }
        }
        for (int i = 0; i < a.length(); i++) {
            if (list.contains(b.charAt(i))) {
                sum2++;
            }
        }
        return sum1 == sum2;
    }   
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/ee4c335c31314dc88ad973f470b3d93b.png)

```java
class Solution {
    public boolean halvesAreAlike(String s) {
        String a = s.substring(0, s.length() / 2);
        String b = s.substring(s.length() / 2);
        String h = "aeiouAEIOU";
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < a.length(); i++) {
            if (h.indexOf(a.charAt(i)) >= 0) {
                sum1++;
            }
        }
        for (int i = 0; i < b.length(); i++) {
            if (h.indexOf(b.charAt(i)) >= 0) {
                sum2++;
            }
        }
        return sum1 == sum2;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/138c0f72e4544a56a9560a72af9fced6.png)

#### [790. 多米诺和托米诺平铺](https://leetcode.cn/problems/domino-and-tromino-tiling/)

难度中等161收藏分享切换为英文接收动态反馈

有两种形状的瓷砖：一种是 `2 x 1` 的多米诺形，另一种是形如 "L" 的托米诺形。两种形状都可以旋转。

![img](https://assets.leetcode.com/uploads/2021/07/15/lc-domino.jpg)

给定整数 n ，返回可以平铺 `2 x n` 的面板的方法的数量。**返回对** `109 + 7` **取模** 的值。

平铺指的是每个正方形都必须有瓷砖覆盖。两个平铺不同，当且仅当面板上有四个方向上的相邻单元中的两个，使得恰好有一个平铺有一个瓷砖占据两个正方形。

 

**示例 1:**

![img](https://assets.leetcode.com/uploads/2021/07/15/lc-domino1.jpg)

```
输入: n = 3
输出: 5
解释: 五种不同的方法如上所示。
```

**示例 2:**

```
输入: n = 1
输出: 1
```

 

**提示：**

- `1 <= n <= 1000`

```java
class Solution {
    static final int MOD = 1000000007;

    public int numTilings(int n) {
        int[][] dp = new int[n + 1][4];
        dp[0][3] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][3];
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
            dp[i][3] = (((dp[i - 1][0] + dp[i - 1][1]) % MOD + dp[i - 1][2]) % MOD + dp[i - 1][3]) % MOD;
        }
        return dp[n][3];
    }
    
}
```

#### [791. 自定义字符串排序](https://leetcode.cn/problems/custom-sort-string/)

给定两个字符串 `order` 和 `s` 。`order` 的所有单词都是 **唯一** 的，并且以前按照一些自定义的顺序排序。

对 `s` 的字符进行置换，使其与排序的 `order` 相匹配。更具体地说，如果在 `order` 中的字符 `x` 出现字符 `y` 之前，那么在排列后的字符串中， `x` 也应该出现在 `y` 之前。

返回 *满足这个性质的 `s` 的任意排列* 。

 

**示例 1:**

```
输入: order = "cba", s = "abcd"
输出: "cbad"
解释: 
“a”、“b”、“c”是按顺序出现的，所以“a”、“b”、“c”的顺序应该是“c”、“b”、“a”。
因为“d”不是按顺序出现的，所以它可以在返回的字符串中的任何位置。“dcba”、“cdba”、“cbda”也是有效的输出。
```

**示例 2:**

```
输入: order = "cbafg", s = "abcd"
输出: "cbad"
```

 

**提示:**

- `1 <= order.length <= 26`
- `1 <= s.length <= 200`
- `order` 和 `s` 由小写英文字母组成
- `order` 中的所有字符都 **不同**

```java
class Solution {
    public String customSortString(String order, String s) {
        int[] val = new int[26];
        for (int i = 0; i < order.length(); i++) {
            val[order.charAt(i) - 'a'] = i + 1;
        }
        Character[] arr = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.charAt(i);
        }
        Arrays.sort(arr, (c0, c1) -> val[c0 - 'a'] - val[c1 - 'a']);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/44b9519184044c3d89ac1b612d1186e5.png)

#### [805. 数组的均值分割](https://leetcode.cn/problems/split-array-with-same-average/)

给定你一个整数数组 `nums`

我们要将 `nums` 数组中的每个元素移动到 `A` 数组 或者 `B` 数组中，使得 `A` 数组和 `B` 数组不为空，并且 `average(A) == average(B)` 。

如果可以完成则返回`true` ， 否则返回 `false` 。

**注意：**对于数组 `arr` ,  `average(arr)` 是 `arr` 的所有元素的和除以 `arr` 长度。

 

**示例 1:**

```
输入: nums = [1,2,3,4,5,6,7,8]
输出: true
解释: 我们可以将数组分割为 [1,4,5,8] 和 [2,3,6,7], 他们的平均值都是4.5。
```

**示例 2:**

```
输入: nums = [3,1]
输出: false
```

 

**提示:**

- `1 <= nums.length <= 30`
- `0 <= nums[i] <= 104`

```java
class Solution {
    public boolean splitArraySameAverage(int[] nums) {
        if (nums.length == 1) {
            return false;
        }
        int n = nums.length, m = n / 2;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] * n - sum;
        }

        Set<Integer> left = new HashSet<Integer>();
        for (int i = 1; i < (1 << m); i++) {
            int tot = 0;
            for (int j = 0; j < m; j++) {
                if ((i & (1 << j)) != 0) {
                    tot += nums[j];
                }
            }
            if (tot == 0) {
                return true;
            }
            left.add(tot);
        }
        int rsum = 0;
        for (int i = m; i < n; i++) {
            rsum += nums[i];
        }
        for (int i = 1; i < (1 << (n - m)); i++) {
            int tot = 0;
            for (int j = m; j < n; j++) {
                if ((i & (1 << (j - m))) != 0) {
                    tot += nums[j];
                }
            }
            if (tot == 0 || (rsum != tot && left.contains(-tot))) {
                return true;
            }
        }
        return false;
    }
}
```

#### [1710. 卡车上的最大单元数](https://leetcode.cn/problems/maximum-units-on-a-truck/)

请你将一些箱子装在 **一辆卡车** 上。给你一个二维数组 `boxTypes` ，其中 `boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]` ：

- `numberOfBoxesi` 是类型 `i` 的箱子的数量。
- `numberOfUnitsPerBoxi` 是类型 `i` 每个箱子可以装载的单元数量。

整数 `truckSize` 表示卡车上可以装载 **箱子** 的 **最大数量** 。只要箱子数量不超过 `truckSize` ，你就可以选择任意箱子装到卡车上。

返回卡车可以装载 **单元** 的 **最大** 总数*。*

 

**示例 1：**

```
输入：boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
输出：8
解释：箱子的情况如下：
- 1 个第一类的箱子，里面含 3 个单元。
- 2 个第二类的箱子，每个里面含 2 个单元。
- 3 个第三类的箱子，每个里面含 1 个单元。
可以选择第一类和第二类的所有箱子，以及第三类的一个箱子。
单元总数 = (1 * 3) + (2 * 2) + (1 * 1) = 8
```

**示例 2：**

```
输入：boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
输出：91
```

 

**提示：**

- `1 <= boxTypes.length <= 1000`
- `1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000`
- `1 <= truckSize <= 106`

```java
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int res = 0;
        for (int[] boxType : boxTypes) {
            int numberOfBoxes = boxType[0];
            int numberOfUnitsPerBox = boxType[1];
            if (numberOfBoxes < truckSize) {
                res += numberOfBoxes * numberOfUnitsPerBox;
                truckSize -= numberOfBoxes;
            } else {
                res += truckSize * numberOfUnitsPerBox;
                break;
            }
        }
        return res;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/12880cb48e3748f4937eb2444727f760.png)

#### [775. 全局倒置与局部倒置](https://leetcode.cn/problems/global-and-local-inversions/)

给你一个长度为 `n` 的整数数组 `nums` ，表示由范围 `[0, n - 1]` 内所有整数组成的一个排列。

**全局倒置** 的数目等于满足下述条件不同下标对 `(i, j)` 的数目：

- `0 <= i < j < n`
- `nums[i] > nums[j]`

**局部倒置** 的数目等于满足下述条件的下标 `i` 的数目：

- `0 <= i < n - 1`
- `nums[i] > nums[i + 1]`

当数组 `nums` 中 **全局倒置** 的数量等于 **局部倒置** 的数量时，返回 `true` ；否则，返回 `false` 。

 

**示例 1：**

```
输入：nums = [1,0,2]
输出：true
解释：有 1 个全局倒置，和 1 个局部倒置。
```

**示例 2：**

```
输入：nums = [1,2,0]
输出：false
解释：有 2 个全局倒置，和 1 个局部倒置。
```

**提示：**

- `n == nums.length`
- `1 <= n <= 105`
- `0 <= nums[i] < n`
- `nums` 中的所有整数 **互不相同**
- `nums` 是范围 `[0, n - 1]` 内所有数字组成的一个排列

```java
class Solution {
    public boolean isIdealPermutation(int[] nums) {
        int max = nums[0];
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] < max) return false;
            max = Math.max(max, nums[i - 1]);
        }
        return true;
    }
}
```

#### [792. 匹配子序列的单词数](https://leetcode.cn/problems/number-of-matching-subsequences/)

给定字符串 `s` 和字符串数组 `words`, 返回 *`words[i]` 中是`s`的子序列的单词个数* 。

字符串的 **子序列** 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。

- 例如， `“ace”` 是 `“abcde”` 的子序列。

 

**示例 1:**

```
输入: s = "abcde", words = ["a","bb","acd","ace"]
输出: 3
解释: 有三个是 s 的子序列的单词: "a", "acd", "ace"。
```

**Example 2:**

```
输入: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
输出: 2
```

 

**提示:**

- `1 <= s.length <= 5 * 104`
- `1 <= words.length <= 5000`
- `1 <= words[i].length <= 50`
- `words[i]`和 s 都只由小写字母组成。

```java
class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < 26; ++i) {
            pos[i] = new ArrayList<>();
        }
        for (int i = 0; i < s.length(); ++i) {
            pos[s.charAt(i) - 'a'].add(i);
        }
        int res = words.length;
        for (String w : words) {
            if (w.length() > s.length()) {
                --res;
                continue;
            }
            int p = -1;
            for (int i = 0; i < w.length(); ++i) {
                char c = w.charAt(i);
                if (pos[c - 'a'].isEmpty() || pos[c - 'a'].get(pos[c - 'a'].size() - 1) <= p) {
                    --res;
                    break;
                }
                p = binarySearch(pos[c - 'a'], p);
            }
        }
        return res;
    }

    public int binarySearch(List<Integer> list, int target) {
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return list.get(left);
    }
}
```


#### [2022. 将一维数组转变成二维数组](https://leetcode-cn.com/problems/convert-1d-array-into-2d-array/)

难度简单16

给你一个下标从 **0** 开始的一维整数数组 `original` 和两个整数 `m` 和 `n` 。你需要使用 `original` 中 **所有** 元素创建一个 `m` 行 `n` 列的二维数组。

`original` 中下标从 `0` 到 `n - 1` （都 **包含** ）的元素构成二维数组的第一行，下标从 `n` 到 `2 * n - 1` （都 **包含** ）的元素构成二维数组的第二行，依此类推。

请你根据上述过程返回一个 `m x n` 的二维数组。如果无法构成这样的二维数组，请你返回一个空的二维数组。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/08/26/image-20210826114243-1.png)

```
输入：original = [1,2,3,4], m = 2, n = 2
输出：[[1,2],[3,4]]
解释：
构造出的二维数组应该包含 2 行 2 列。
original 中第一个 n=2 的部分为 [1,2] ，构成二维数组的第一行。
original 中第二个 n=2 的部分为 [3,4] ，构成二维数组的第二行。
```

**示例 2：**

```
输入：original = [1,2,3], m = 1, n = 3
输出：[[1,2,3]]
解释：
构造出的二维数组应该包含 1 行 3 列。
将 original 中所有三个元素放入第一行中，构成要求的二维数组。
```

**示例 3：**

```
输入：original = [1,2], m = 1, n = 1
输出：[]
解释：
original 中有 2 个元素。
无法将 2 个元素放入到一个 1x1 的二维数组中，所以返回一个空的二维数组。
```

**示例 4：**

```
输入：original = [3], m = 1, n = 2
输出：[]
解释：
original 中只有 1 个元素。
无法将 1 个元素放满一个 1x2 的二维数组，所以返回一个空的二维数组。
```

 

**提示：**

- `1 <= original.length <= 5 * 104`
- `1 <= original[i] <= 105`
- `1 <= m, n <= 4 * 104`

**模拟**

```java
class Solution {
    public int[][] construct2DArray(int[] original, int m, int n) {
      if (original.length != n * m) {
            return new int[][]{};
        }
        int[][] ans = new int[m][n];
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = original[index++];
            }
        }
        return ans;
    }
}
```

#### [390. 消除游戏](https://leetcode-cn.com/problems/elimination-game/)



列表 `arr` 由在范围 `[1, n]` 中的所有整数组成，并按严格递增排序。请你对 `arr` 应用下述算法：

- 从左到右，删除第一个数字，然后每隔一个数字删除一个，直到到达列表末尾。
- 重复上面的步骤，但这次是从右到左。也就是，删除最右侧的数字，然后剩下的数字每隔一个删除一个。
- 不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。

给你整数 `n` ，返回 `arr` 最后剩下的数字。

 

**示例 1：**

```
输入：n = 9
输出：6
解释：
arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
arr = [2, 4, 6, 8]
arr = [2, 6]
arr = [6]
```

**示例 2：**

```
输入：n = 1
输出：1
```

 

**提示：**

- `1 <= n <= 109`

**约瑟夫回环**

```java
class Solution {
    public int lastRemaining(int n) {
        return n == 1 ? 1 : 2 * (n / 2 + 1 - lastRemaining(n / 2));
    }
}
```

#### [1185. 一周中的第几天](https://leetcode-cn.com/problems/day-of-the-week/)



给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。

输入为三个整数：`day`、`month` 和 `year`，分别表示日、月、年。

您返回的结果必须是这几个值中的一个 `{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}`。

 

**示例 1：**

```
输入：day = 31, month = 8, year = 2019
输出："Saturday"
```

**示例 2：**

```
输入：day = 18, month = 7, year = 1999
输出："Sunday"
```

**示例 3：**

```
输入：day = 15, month = 8, year = 1993
输出："Sunday"
```

 

**提示：**

- 给出的日期一定是在 `1971` 到 `2100` 年之间的有效日期。

```java
class Solution {
    public String dayOfTheWeek(int day, int month, int year) {
      String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        /* 输入年份之前的年份的天数贡献 */
        int days = 365 * (year - 1971) + (year - 1969) / 4;
        /* 输入年份中，输入月份之前的月份的天数贡献 */
        for (int i = 0; i < month - 1; ++i) {
            days += monthDays[i];
        }
        if ((year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) && month >= 3) {
            days += 1;
        }
        /* 输入月份中的天数贡献 */
        days += day;
        return week[(days + 3) % 7];
    }
}
```

#### [913. 猫和老鼠](https://leetcode-cn.com/problems/cat-and-mouse/)



两位玩家分别扮演猫和老鼠，在一张 **无向** 图上进行游戏，两人轮流行动。

图的形式是：`graph[a]` 是一个列表，由满足 `ab` 是图中的一条边的所有节点 `b` 组成。

老鼠从节点 `1` 开始，第一个出发；猫从节点 `2` 开始，第二个出发。在节点 `0` 处有一个洞。

在每个玩家的行动中，他们 **必须** 沿着图中与所在当前位置连通的一条边移动。例如，如果老鼠在节点 `1` ，那么它必须移动到 `graph[1]` 中的任一节点。

此外，猫无法移动到洞中（节点 `0`）。

然后，游戏在出现以下三种情形之一时结束：

- 如果猫和老鼠出现在同一个节点，猫获胜。
- 如果老鼠到达洞中，老鼠获胜。
- 如果某一位置重复出现（即，玩家的位置和移动顺序都与上一次行动相同），游戏平局。

给你一张图 `graph` ，并假设两位玩家都都以最佳状态参与游戏：

- 如果老鼠获胜，则返回 `1`；
- 如果猫获胜，则返回 `2`；
- 如果平局，则返回 `0` 。

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/11/17/cat1.jpg)

```
输入：graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
输出：0
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2020/11/17/cat2.jpg)

```
输入：graph = [[1,3],[0],[3],[0,2]]
输出：1
```

 

**提示：**

- `3 <= graph.length <= 50`
- `1 <= graph[i].length < graph.length`
- `0 <= graph[i][j] < graph.length`
- `graph[i][j] != i`
- `graph[i]` 互不相同
- 猫和老鼠在游戏中总是移动

**CV**

```java
class Solution {
static int N = 55;
    static int[][][] f = new int[2 * N * N][N][N];
    int[][] g;
    int n;

    public int catMouseGame(int[][] graph) {
        g = graph;
        n = g.length;
        for (int k = 0; k < 2 * n * n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    f[k][i][j] = -1;
                }
            }
        }
        return dfs(0, 1, 2);
    }

    // 0:draw / 1:mouse / 2:cat
    int dfs(int k, int a, int b) {
        int ans = f[k][a][b];
        if (a == 0) ans = 1;
        else if (a == b) ans = 2;
        else if (k >= 2 * n * n) ans = 0;
        else if (ans == -1) {
            if (k % 2 == 0) { // mouse
                boolean win = false, draw = false;
                for (int ne : g[a]) {
                    int t = dfs(k + 1, ne, b);
                    if (t == 1) win = true;
                    else if (t == 0) draw = true;
                    if (win) break;
                }
                if (win) ans = 1;
                else if (draw) ans = 0;
                else ans = 2;
            } else { // cat
                boolean win = false, draw = false;
                for (int ne : g[b]) {
                    if (ne == 0) continue;
                    int t = dfs(k + 1, a, ne);
                    if (t == 2) win = true;
                    else if (t == 0) draw = true;
                    if (win) break;
                }
                if (win) ans = 2;
                else if (draw) ans = 0;
                else ans = 1;
            }
        }
        f[k][a][b] = ans;
        return ans;
    }
}
```

#### [1576. 替换所有的问号](https://leetcode-cn.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/)



给你一个仅包含小写英文字母和 `'?'` 字符的字符串 `s`，请你将所有的 `'?'` 转换为若干小写字母，使最终的字符串不包含任何 **连续重复** 的字符。

注意：你 **不能** 修改非 `'?'` 字符。

题目测试用例保证 **除** `'?'` 字符 **之外**，不存在连续重复的字符。

在完成所有转换（可能无需转换）后返回最终的字符串。如果有多个解决方案，请返回其中任何一个。可以证明，在给定的约束条件下，答案总是存在的。

 

**示例 1：**

```
输入：s = "?zs"
输出："azs"
解释：该示例共有 25 种解决方案，从 "azs" 到 "yzs" 都是符合题目要求的。只有 "z" 是无效的修改，因为字符串 "zzs" 中有连续重复的两个 'z' 。
```

**示例 2：**

```
输入：s = "ubv?w"
输出："ubvaw"
解释：该示例共有 24 种解决方案，只有替换成 "v" 和 "w" 不符合题目要求。因为 "ubvvw" 和 "ubvww" 都包含连续重复的字符。
```

**示例 3：**

```
输入：s = "j?qg??b"
输出："jaqgacb"
```

**示例 4：**

```
输入：s = "??yw?ipkj?"
输出："acywaipkja"
```

 

**提示：**

- `1 <= s.length <= 100`
- `s` 仅包含小写英文字母和 `'?'` 字符

```java
class Solution {
    public String modifyString(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (arr[i] == '?') {
                for (char ch = 'a'; ch <= 'c'; ch++) {
                    if ((i > 0 && arr[i - 1] == ch) || (i < n - 1 && arr[i + 1] == ch)) {
                        continue;
                    }
                    arr[i] = ch;
                    break;
                }
            }
        }
        return new String(arr);
    }   
}
```

#### [71. 简化路径](https://leetcode-cn.com/problems/simplify-path/)



给你一个字符串 `path` ，表示指向某一文件或目录的 Unix 风格 **绝对路径** （以 `'/'` 开头），请你将其转化为更加简洁的规范路径。

在 Unix 风格的文件系统中，一个点（`.`）表示当前目录本身；此外，两个点 （`..`） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，`'//'`）都被视为单个斜杠 `'/'` 。 对于此问题，任何其他格式的点（例如，`'...'`）均被视为文件/目录名称。

请注意，返回的 **规范路径** 必须遵循下述格式：

- 始终以斜杠 `'/'` 开头。
- 两个目录名之间必须只有一个斜杠 `'/'` 。
- 最后一个目录名（如果存在）**不能** 以 `'/'` 结尾。
- 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 `'.'` 或 `'..'`）。

返回简化后得到的 **规范路径** 。

 

**示例 1：**

```
输入：path = "/home/"
输出："/home"
解释：注意，最后一个目录名后面没有斜杠。 
```

**示例 2：**

```
输入：path = "/../"
输出："/"
解释：从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。
```

**示例 3：**

```
输入：path = "/home//foo/"
输出："/home/foo"
解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
```

**示例 4：**

```
输入：path = "/a/./b/../../c/"
输出："/c"
```

 

**提示：**

- `1 <= path.length <= 3000`
- `path` 由英文字母，数字，`'.'`，`'/'` 或 `'_'` 组成。
- `path` 是一个有效的 Unix 风格绝对路径。

```java
class Solution {
    public String simplifyPath(String path) {
        String[] names = path.split("/");
        Deque<String> stack = new ArrayDeque<String>();
        for (String name : names) {
            if ("..".equals(name)) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (name.length() > 0 && !".".equals(name)) {
                stack.offerLast(name);
            }
        }
        StringBuffer ans = new StringBuffer();
        if (stack.isEmpty()) {
            ans.append('/');
        } else {
            while (!stack.isEmpty()) {
                ans.append('/');
                ans.append(stack.pollFirst());
            }
        }
        return ans.toString();
    }
}
```

#### [1614. 括号的最大嵌套深度](https://leetcode-cn.com/problems/maximum-nesting-depth-of-the-parentheses/)

难度简单43

如果字符串满足以下条件之一，则可以称之为 **有效括号字符串****（valid parentheses string**，可以简写为 **VPS**）：

- 字符串是一个空字符串 `""`，或者是一个不为 `"("` 或 `")"` 的单字符。
- 字符串可以写为 `AB`（`A` 与 `B` 字符串连接），其中 `A` 和 `B` 都是 **有效括号字符串** 。
- 字符串可以写为 `(A)`，其中 `A` 是一个 **有效括号字符串** 。

类似地，可以定义任何有效括号字符串 `S` 的 **嵌套深度** `depth(S)`：

- `depth("") = 0`
- `depth(C) = 0`，其中 `C` 是单个字符的字符串，且该字符不是 `"("` 或者 `")"`
- `depth(A + B) = max(depth(A), depth(B))`，其中 `A` 和 `B` 都是 **有效括号字符串**
- `depth("(" + A + ")") = 1 + depth(A)`，其中 `A` 是一个 **有效括号字符串**

例如：`""`、`"()()"`、`"()(()())"` 都是 **有效括号字符串**（嵌套深度分别为 0、1、2），而 `")("` 、`"(()"` 都不是 **有效括号字符串** 。

给你一个 **有效括号字符串** `s`，返回该字符串的 `s` **嵌套深度** 。

 

**示例 1：**

```
输入：s = "(1+(2*3)+((8)/4))+1"
输出：3
解释：数字 8 在嵌套的 3 层括号中。
```

**示例 2：**

```
输入：s = "(1)+((2))+(((3)))"
输出：3
```

**示例 3：**

```
输入：s = "1+(2*3)/(2-1)"
输出：1
```

**示例 4：**

```
输入：s = "1"
输出：0
```

 

**提示：**

- `1 <= s.length <= 100`
- `s` 由数字 `0-9` 和字符 `'+'`、`'-'`、`'*'`、`'/'`、`'('`、`')'` 组成
- 题目数据保证括号表达式 `s` 是 **有效的括号表达式**

```java
class Solution {
    public int maxDepth(String s) {
        int ans = 0, size = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                size++;
                ans = Math.max(ans, size);
            } else if (c == ')') {
                --size;
            }
        }
        return ans;
    }
}
```

#### [89. 格雷编码](https://leetcode-cn.com/problems/gray-code/)

难度中等402

**n 位格雷码序列** 是一个由 `2n` 个整数组成的序列，其中：

- 每个整数都在范围 `[0, 2n - 1]` 内（含 `0` 和 `2n - 1`）
- 第一个整数是 `0`
- 一个整数在序列中出现 **不超过一次**
- 每对 **相邻** 整数的二进制表示 **恰好一位不同** ，且
- **第一个** 和 **最后一个** 整数的二进制表示 **恰好一位不同**

给你一个整数 `n` ，返回任一有效的 **n 位格雷码序列** 。

 

**示例 1：**

```
输入：n = 2
输出：[0,1,3,2]
解释：
[0,1,3,2] 的二进制表示是 [00,01,11,10] 。
- 00 和 01 有一位不同
- 01 和 11 有一位不同
- 11 和 10 有一位不同
- 10 和 00 有一位不同
[0,2,3,1] 也是一个有效的格雷码序列，其二进制表示是 [00,10,11,01] 。
- 00 和 10 有一位不同
- 10 和 11 有一位不同
- 11 和 01 有一位不同
- 01 和 00 有一位不同
```

**示例 2：**

```
输入：n = 1
输出：[0,1]
```

 

**提示：**

- `1 <= n <= 16`

```java
class Solution {
    public List<Integer> grayCode(int n) {
      List<Integer> ans = new ArrayList<>();
        ans.add(0);
        while (n-- > 0) {
            int m = ans.size();
            for (int i = m - 1; i >= 0; i--) {
                ans.set(i, ans.get(i) << 1);
                ans.add(ans.get(i) + 1);
            }
        }
        return ans;
    }
}
```

#### [1629. 按键持续时间最长的键](https://leetcode-cn.com/problems/slowest-key/)

难度简单15

LeetCode 设计了一款新式键盘，正在测试其可用性。测试人员将会点击一系列键（总计 `n` 个），每次一个。

给你一个长度为 `n` 的字符串 `keysPressed` ，其中 `keysPressed[i]` 表示测试序列中第 `i` 个被按下的键。`releaseTimes` 是一个升序排列的列表，其中 `releaseTimes[i]` 表示松开第 `i` 个键的时间。字符串和数组的 **下标都从 0 开始** 。第 `0` 个键在时间为 `0` 时被按下，接下来每个键都 **恰好** 在前一个键松开时被按下。

测试人员想要找出按键 **持续时间最长** 的键。第 `i` 次按键的持续时间为 `releaseTimes[i] - releaseTimes[i - 1]` ，第 `0` 次按键的持续时间为 `releaseTimes[0]` 。

注意，测试期间，同一个键可以在不同时刻被多次按下，而每次的持续时间都可能不同。

请返回按键 **持续时间最长** 的键，如果有多个这样的键，则返回 **按字母顺序排列最大** 的那个键。

 

**示例 1：**

```
输入：releaseTimes = [9,29,49,50], keysPressed = "cbcd"
输出："c"
解释：按键顺序和持续时间如下：
按下 'c' ，持续时间 9（时间 0 按下，时间 9 松开）
按下 'b' ，持续时间 29 - 9 = 20（松开上一个键的时间 9 按下，时间 29 松开）
按下 'c' ，持续时间 49 - 29 = 20（松开上一个键的时间 29 按下，时间 49 松开）
按下 'd' ，持续时间 50 - 49 = 1（松开上一个键的时间 49 按下，时间 50 松开）
按键持续时间最长的键是 'b' 和 'c'（第二次按下时），持续时间都是 20
'c' 按字母顺序排列比 'b' 大，所以答案是 'c'
```

**示例 2：**

```
输入：releaseTimes = [12,23,36,46,62], keysPressed = "spuda"
输出："a"
解释：按键顺序和持续时间如下：
按下 's' ，持续时间 12
按下 'p' ，持续时间 23 - 12 = 11
按下 'u' ，持续时间 36 - 23 = 13
按下 'd' ，持续时间 46 - 36 = 10
按下 'a' ，持续时间 62 - 46 = 16
按键持续时间最长的键是 'a' ，持续时间 16
```

 

**提示：**

- `releaseTimes.length == n`
- `keysPressed.length == n`
- `2 <= n <= 1000`
- `1 <= releaseTimes[i] <= 109`
- `releaseTimes[i] < releaseTimes[i+1]`
- `keysPressed` 仅由小写英文字母组成

```java
class Solution {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
      int n = releaseTimes.length;
        char ans = keysPressed.charAt(0);
        int maxTime = releaseTimes[0];
        for (int i = 1; i < n; i++) {
            char key = keysPressed.charAt(i);
            int time = releaseTimes[i] - releaseTimes[i - 1];
            if (time > maxTime || (time == maxTime && key > ans)) {
                ans = key;
                maxTime = time;
            }
        }
        return ans;
    }
}
```

#### [306. 累加数](https://leetcode-cn.com/problems/additive-number/)

难度中等243

**累加数** 是一个字符串，组成它的数字可以形成累加序列。

一个有效的 **累加序列** 必须 **至少** 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。

给你一个只包含数字 `'0'-'9'` 的字符串，编写一个算法来判断给定输入是否是 **累加数** 。如果是，返回 `true` ；否则，返回 `false` 。

**说明：**累加序列里的数 **不会** 以 0 开头，所以不会出现 `1, 2, 03` 或者 `1, 02, 3` 的情况。

 

**示例 1：**

```
输入："112358"
输出：true 
解释：累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
```

**示例 2：**

```
输入："199100199"
输出：true 
解释：累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
```

 

**提示：**

- `1 <= num.length <= 35`
- `num` 仅由数字（`0` - `9`）组成

 

**进阶：**你计划如何处理由过大的整数输入导致的溢出?

```java
class Solution {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int secondStart = 1; secondStart < n - 1; ++secondStart) {
            if (num.charAt(0) == '0' && secondStart != 1) {
                break;
            }
            for (int secondEnd = secondStart; secondEnd < n - 1; ++secondEnd) {
                if (num.charAt(secondStart) == '0' && secondStart != secondEnd) {
                    break;
                }
                if (valid(secondStart, secondEnd, num)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param secondStart 第二个开头
     * @param secondEnd   第二个末尾
     * @param num         字符串
     * @return 布尔
     */
    public boolean valid(int secondStart, int secondEnd, String num) {
        int n = num.length();
        int firstStart = 0, firstEnd = secondStart - 1;
        while (secondEnd <= n - 1) {
            String third = stringAdd(num, firstStart, firstEnd, secondStart, secondEnd);
            int thirdStart = secondEnd + 1;
            int thirdEnd = secondEnd + third.length();
            if (thirdEnd >= n || !num.substring(thirdStart, thirdEnd + 1).equals(third)) {
                break;
            }
            if (thirdEnd == n - 1) {
                return true;
            }
            firstStart = secondStart;
            firstEnd = secondEnd;
            secondStart = thirdStart;
            secondEnd = thirdEnd;
        }
        return false;
    }

    /**
     * @param s           字符串
     * @param firstStart  第一个开头
     * @param firstEnd    第一个末尾
     * @param secondStart 第二个开头
     * @param secondEnd   第二个末尾
     * @return 字符串
     */
    public String stringAdd(String s, int firstStart, int firstEnd, int secondStart, int secondEnd) {
        StringBuilder third = new StringBuilder();
        int carry = 0, cur = 0;
        while (firstEnd >= firstStart || secondEnd >= secondStart || carry != 0) {
            cur = carry;
            if (firstEnd >= firstStart) {
                cur += s.charAt(firstEnd) - '0';
                --firstEnd;
            }
            if (secondEnd >= secondStart) {
                cur += s.charAt(secondEnd) - '0';
                --secondEnd;
            }
            carry = cur / 10;
            cur %= 10;
            third.append((char) (cur + '0'));
        }
        third.reverse();
        return third.toString();
    }
}
```

#### [1036. 逃离大迷宫](https://leetcode-cn.com/problems/escape-a-large-maze/)

难度困难78

在一个 106 x 106 的网格中，每个网格上方格的坐标为 `(x, y)` 。

现在从源方格 `source = [sx, sy]` 开始出发，意图赶往目标方格 `target = [tx, ty]` 。数组 `blocked` 是封锁的方格列表，其中每个 `blocked[i] = [xi, yi]` 表示坐标为 `(xi, yi)` 的方格是禁止通行的。

每次移动，都可以走到网格中在四个方向上相邻的方格，只要该方格 **不** 在给出的封锁列表 `blocked` 上。同时，不允许走出网格。

只有在可以通过一系列的移动从源方格 `source` 到达目标方格 `target` 时才返回 `true`。否则，返回 `false`。

 

**示例 1：**

```
输入：blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
输出：false
解释：
从源方格无法到达目标方格，因为我们无法在网格中移动。
无法向北或者向东移动是因为方格禁止通行。
无法向南或者向西移动是因为不能走出网格。
```

**示例 2：**

```
输入：blocked = [], source = [0,0], target = [999999,999999]
输出：true
解释：
因为没有方格被封锁，所以一定可以到达目标方格。
```

 

**提示：**

- `0 <= blocked.length <= 200`
- `blocked[i].length == 2`
- `0 <= xi, yi < 106`
- `source.length == target.length == 2`
- `0 <= sx, sy, tx, ty < 106`
- `source != target`
- 题目数据保证 `source` 和 `target` 不在封锁列表内

```java
class Solution {
    // 在包围圈中
    static final int BLOCKED = -1;
    // 不在包围圈中
    static final int VALID = 0;
    // 无论在不在包围圈中，但在 n(n-1)/2 步搜索的过程中经过了 target
    static final int FOUND = 1;
    
    static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static final int BOUNDARY = 1000000;

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        if (blocked.length < 2) {
            return true;
        }

        Set<Pair> hashBlocked = new HashSet<Pair>();
        for (int[] pos : blocked) {
            hashBlocked.add(new Pair(pos[0], pos[1]));
        }

        int result = check(blocked, source, target, hashBlocked);
        if (result == FOUND) {
            return true;
        } else if (result == BLOCKED) {
            return false;
        } else {
            result = check(blocked, target, source, hashBlocked);
            return result != BLOCKED;
        }
    }

    public int check(int[][] blocked, int[] start, int[] finish, Set<Pair> hashBlocked) {
        int sx = start[0], sy = start[1];
        int fx = finish[0], fy = finish[1];
        int countdown = blocked.length * (blocked.length - 1) / 2;
        Pair startPair = new Pair(sx, sy);
        Queue<Pair> queue = new ArrayDeque<Pair>();
        queue.offer(startPair);
        Set<Pair> visited = new HashSet<Pair>();
        visited.add(startPair);
        while (!queue.isEmpty() && countdown > 0) {
            Pair pair = queue.poll();
            int x = pair.x, y = pair.y;
            for (int d = 0; d < 4; ++d) {
                int nx = x + dirs[d][0], ny = y + dirs[d][1];
                Pair newPair = new Pair(nx, ny);
                if (nx >= 0 && nx < BOUNDARY && ny >= 0 && ny < BOUNDARY && !hashBlocked.contains(newPair) && !visited.contains(newPair)) {
                    if (nx == fx && ny == fy) {
                        return FOUND;
                    }
                    --countdown;
                    queue.offer(newPair);
                    visited.add(newPair);
                }
            }
        }
        if (countdown > 0) {
            return BLOCKED;
        }
        return VALID;
    }
}

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return (int) ((long) x << 20 | y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pair) {
            Pair pair2 = (Pair) obj;
            return x == pair2.x && y == pair2.y;
        }
        return false;
    }
}
```

#### [334. 递增的三元子序列](https://leetcode-cn.com/problems/increasing-triplet-subsequence/)

难度中等471

给你一个整数数组 `nums` ，判断这个数组中是否存在长度为 `3` 的递增子序列。

如果存在这样的三元组下标 `(i, j, k)` 且满足 `i < j < k` ，使得 `nums[i] < nums[j] < nums[k]` ，返回 `true` ；否则，返回 `false` 。

 

**示例 1：**

```
输入：nums = [1,2,3,4,5]
输出：true
解释：任何 i < j < k 的三元组都满足题意
```

**示例 2：**

```
输入：nums = [5,4,3,2,1]
输出：false
解释：不存在满足题意的三元组
```

**示例 3：**

```
输入：nums = [2,1,5,0,4,6]
输出：true
解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
```

 

**提示：**

- `1 <= nums.length <= 5 * 105`
- `-231 <= nums[i] <= 231 - 1`

 

**进阶：**你能实现时间复杂度为 `O(n)` ，空间复杂度为 `O(1)` 的解决方案吗？

```java
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int[] leftMin = new int[n];
        leftMin[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
        }
        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > leftMin[i - 1] && nums[i] < rightMax[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
```

#### [747. 至少是其他数字两倍的最大数](https://leetcode-cn.com/problems/largest-number-at-least-twice-of-others/)



给你一个整数数组 `nums` ，其中总是存在 **唯一的** 一个最大整数 。

请你找出数组中的最大元素并检查它是否 **至少是数组中每个其他数字的两倍** 。如果是，则返回 **最大元素的下标** ，否则返回 `-1` 。

 

**示例 1：**

```
输入：nums = [3,6,1,0]
输出：1
解释：6 是最大的整数，对于数组中的其他整数，6 大于数组中其他元素的两倍。6 的下标是 1 ，所以返回 1 。
```

**示例 2：**

```
输入：nums = [1,2,3,4]
输出：-1
解释：4 没有超过 3 的两倍大，所以返回 -1 。
```

**示例 3：**

```
输入：nums = [1]
输出：0
解释：因为不存在其他数字，所以认为现有数字 1 至少是其他数字的两倍。
```

 

**提示：**

- `1 <= nums.length <= 50`
- `0 <= nums[i] <= 100`
- `nums` 中的最大元素是唯一的

```java
class Solution {
    public int dominantIndex(int[] nums) {
        int m1 = -1, m2 = -1;
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > m1) {
                m2 = m1;
                m1 = nums[i];
                index = i;
            } else if (nums[i] > m2) {
                m2 = nums[i];
            }
        }
        return m1 >= m2 * 2 ? index : -1;
    }
}
```

#### [373. 查找和最小的 K 对数字](https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/)



给定两个以 **升序排列** 的整数数组 `nums1` 和 `nums2` , 以及一个整数 `k` 。

定义一对值 `(u,v)`，其中第一个元素来自 `nums1`，第二个元素来自 `nums2` 。

请找到和最小的 `k` 个数对 `(u1,v1)`, ` (u2,v2)` ...  `(uk,vk)` 。

 

**示例 1:**

```
输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
输出: [1,2],[1,4],[1,6]
解释: 返回序列中的前 3 对数：
     [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
```

**示例 2:**

```
输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
输出: [1,1],[1,1]
解释: 返回序列中的前 2 对数：
     [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
```

**示例 3:**

```
输入: nums1 = [1,2], nums2 = [3], k = 3 
输出: [1,3],[2,3]
解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
```

 

**提示:**

- `1 <= nums1.length, nums2.length <= 105`
- `-109 <= nums1[i], nums2[i] <= 109`
- `nums1` 和 `nums2` 均为升序排列
- `1 <= k <= 1000`

```java
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
      PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2) -> {
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i, 0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            if (idxPair[1] + 1 < n) {
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }
        return ans;
    }
}
```

#### [1716. 计算力扣银行的钱](https://leetcode-cn.com/problems/calculate-money-in-leetcode-bank/)



Hercy 想要为购买第一辆车存钱。他 **每天** 都往力扣银行里存钱。

最开始，他在周一的时候存入 `1` 块钱。从周二到周日，他每天都比前一天多存入 `1` 块钱。在接下来每一个周一，他都会比 **前一个周一** 多存入 `1` 块钱。

给你 `n` ，请你返回在第 `n` 天结束的时候他在力扣银行总共存了多少块钱。

 

**示例 1：**

```
输入：n = 4
输出：10
解释：第 4 天后，总额为 1 + 2 + 3 + 4 = 10 。
```

**示例 2：**

```
输入：n = 10
输出：37
解释：第 10 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37 。注意到第二个星期一，Hercy 存入 2 块钱。
```

**示例 3：**

```
输入：n = 20
输出：96
解释：第 20 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4 + 5 + 6 + 7 + 8) + (3 + 4 + 5 + 6 + 7 + 8) = 96 。
```

 

**提示：**

- `1 <= n <= 1000`

```java
class Solution {
    public int totalMoney(int n) {
      int week = 1, day = 1;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += week + day - 1;
            ++day;
            if (day == 8) {
                day = 1;
                ++week;
            }
        }
        return res;
    }
}
```

#### [382. 链表随机节点](https://leetcode-cn.com/problems/linked-list-random-node/)

难度中等191

给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 **被选中的概率一样** 。

实现 `Solution` 类：

- `Solution(ListNode head)` 使用整数数组初始化对象。
- `int getRandom()` 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。

 

**示例：**

![img](https://assets.leetcode.com/uploads/2021/03/16/getrand-linked-list.jpg)

```
输入
["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
[[[1, 2, 3]], [], [], [], [], []]
输出
[null, 1, 3, 2, 2, 3]

解释
Solution solution = new Solution([1, 2, 3]);
solution.getRandom(); // 返回 1
solution.getRandom(); // 返回 3
solution.getRandom(); // 返回 2
solution.getRandom(); // 返回 2
solution.getRandom(); // 返回 3
// getRandom() 方法应随机返回 1、2、3中的一个，每个元素被返回的概率相等。
```

 

**提示：**

- 链表中的节点数在范围 `[1, 104]` 内
- `-104 <= Node.val <= 104`
- 至多调用 `getRandom` 方法 `104` 次

 

**进阶：**

- 如果链表非常大且长度未知，该怎么处理？
- 你能否在不使用额外空间的情况下解决此问题？

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    List<Integer> list;
    Random random;

    public Solution(ListNode head) {
        list = new ArrayList<Integer>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        random = new Random();
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }

}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
```

#### [1220. 统计元音字母序列的数目](https://leetcode-cn.com/problems/count-vowels-permutation/)



给你一个整数 `n`，请你帮忙统计一下我们可以按下述规则形成多少个长度为 `n` 的字符串：

- 字符串中的每个字符都应当是小写元音字母（`'a'`, `'e'`, `'i'`, `'o'`, `'u'`）
- 每个元音 `'a'` 后面都只能跟着 `'e'`
- 每个元音 `'e'` 后面只能跟着 `'a'` 或者是 `'i'`
- 每个元音 `'i'` 后面 **不能** 再跟着另一个 `'i'`
- 每个元音 `'o'` 后面只能跟着 `'i'` 或者是 `'u'`
- 每个元音 `'u'` 后面只能跟着 `'a'`

由于答案可能会很大，所以请你返回 模 `10^9 + 7` 之后的结果。

 

**示例 1：**

```
输入：n = 1
输出：5
解释：所有可能的字符串分别是："a", "e", "i" , "o" 和 "u"。
```

**示例 2：**

```
输入：n = 2
输出：10
解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。
```

**示例 3：**

```
输入：n = 5
输出：68
```

 

**提示：**

- `1 <= n <= 2 * 10^4`

```java
class Solution {
    public int countVowelPermutation(int n) {
      long mod = 1000000007;
        long[] dp = new long[5];
        long[] ndp = new long[5];
        for (int i = 0; i < 5; ++i) {
            dp[i] = 1;
        }
        for (int i = 2; i <= n; ++i) {
            /* a前面可以为e,u,i */
            ndp[0] = (dp[1] + dp[2] + dp[4]) % mod;
            /* e前面可以为a,i */
            ndp[1] = (dp[0] + dp[2]) % mod;
            /* i前面可以为e,o */
            ndp[2] = (dp[1] + dp[3]) % mod;
            /* o前面可以为i */
            ndp[3] = dp[2];
            /* u前面可以为i,o */
            ndp[4] = (dp[2] + dp[3]) % mod;
            System.arraycopy(ndp, 0, dp, 0, 5);
        }
        long ans = 0;
        for (int i = 0; i < 5; ++i) {
            ans = (ans + dp[i]) % mod;
        }
        return (int) ans;
    }
}
```

#### [539. 最小时间差](https://leetcode-cn.com/problems/minimum-time-difference/)



给定一个 24 小时制（小时:分钟 **"HH:MM"**）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。

 

**示例 1：**

```
输入：timePoints = ["23:59","00:00"]
输出：1
```

**示例 2：**

```
输入：timePoints = ["00:00","23:59","00:00"]
输出：0
```

 

**提示：**

- `2 <= timePoints.length <= 2 * 104`
- `timePoints[i]` 格式为 **"HH:MM"**

```java
class Solution {
    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size() * 2;
        int[] nums = new int[n];
        for (int i = 0, idx = 0; i < n / 2; i++, idx += 2) {
            String[] ss = timePoints.get(i).split(":");
            int h = Integer.parseInt(ss[0]), m = Integer.parseInt(ss[1]);
            nums[idx] = h * 60 + m;
            nums[idx + 1] = nums[idx] + 1440;
        }
        Arrays.sort(nums);
        int ans = nums[1] - nums[0];
        for (int i = 0; i < n - 1; i++) ans = Math.min(ans, nums[i + 1] - nums[i]);
        return ans;
    }
}
```

#### [219. 存在重复元素 II](https://leetcode-cn.com/problems/contains-duplicate-ii/)



给你一个整数数组 `nums` 和一个整数 `k` ，判断数组中是否存在两个 **不同的索引** `i` 和 `j` ，满足 `nums[i] == nums[j]` 且 `abs(i - j) <= k` 。如果存在，返回 `true` ；否则，返回 `false` 。

 

**示例 1：**

```
输入：nums = [1,2,3,1], k = 3
输出：true
```

**示例 2：**

```
输入：nums = [1,0,1,1], k = 1
输出：true
```

**示例 3：**

```
输入：nums = [1,2,3,1,2,3], k = 2
输出：false
```

 

 

**提示：**

- `1 <= nums.length <= 105`
- `-109 <= nums[i] <= 109`
- `0 <= k <= 105`

```java
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
         HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                Integer tmp = map.get(nums[i]);
                if (i-tmp<=k){
                    return true;
                }
            }
            map.put(nums[i],i);
        }
        return false;
    }
}
```

#### [2029. 石子游戏 IX](https://leetcode-cn.com/problems/stone-game-ix/)



Alice 和 Bob 再次设计了一款新的石子游戏。现有一行 n 个石子，每个石子都有一个关联的数字表示它的价值。给你一个整数数组 `stones` ，其中 `stones[i]` 是第 `i` 个石子的价值。

Alice 和 Bob 轮流进行自己的回合，**Alice** 先手。每一回合，玩家需要从 `stones` 中移除任一石子。

- 如果玩家移除石子后，导致 **所有已移除石子** 的价值 **总和** 可以被 3 整除，那么该玩家就 **输掉游戏** 。
- 如果不满足上一条，且移除后没有任何剩余的石子，那么 Bob 将会直接获胜（即便是在 Alice 的回合）。

假设两位玩家均采用 **最佳** 决策。如果 Alice 获胜，返回 `true` ；如果 Bob 获胜，返回 `false` 。

 

**示例 1：**

```
输入：stones = [2,1]
输出：true
解释：游戏进行如下：
- 回合 1：Alice 可以移除任意一个石子。
- 回合 2：Bob 移除剩下的石子。 
已移除的石子的值总和为 1 + 2 = 3 且可以被 3 整除。因此，Bob 输，Alice 获胜。
```

**示例 2：**

```
输入：stones = [2]
输出：false
解释：Alice 会移除唯一一个石子，已移除石子的值总和为 2 。 
由于所有石子都已移除，且值总和无法被 3 整除，Bob 获胜。
```

**示例 3：**

```
输入：stones = [5,1,2,4,3]
输出：false
解释：Bob 总会获胜。其中一种可能的游戏进行方式如下：
- 回合 1：Alice 可以移除值为 1 的第 2 个石子。已移除石子值总和为 1 。
- 回合 2：Bob 可以移除值为 3 的第 5 个石子。已移除石子值总和为 = 1 + 3 = 4 。
- 回合 3：Alices 可以移除值为 4 的第 4 个石子。已移除石子值总和为 = 1 + 3 + 4 = 8 。
- 回合 4：Bob 可以移除值为 2 的第 3 个石子。已移除石子值总和为 = 1 + 3 + 4 + 2 = 10.
- 回合 5：Alice 可以移除值为 5 的第 1 个石子。已移除石子值总和为 = 1 + 3 + 4 + 2 + 5 = 15.
Alice 输掉游戏，因为已移除石子值总和（15）可以被 3 整除，Bob 获胜。
```

 

**提示：**

- `1 <= stones.length <= 105`
- `1 <= stones[i] <= 104`

```java
class Solution {
    public boolean stoneGameIX(int[] stones) {
        int cnt0 = 0, cnt1 = 0, cnt2 = 0;
        for (int val : stones) {
            int type = val % 3;
            if (type == 0) {
                ++cnt0;
            } else if (type == 1) {
                ++cnt1;
            } else {
                ++cnt2;
            }
        }
        if (cnt0 % 2 == 0) {
            return cnt1 >= 1 && cnt2 >= 1;
        }
        return cnt1 - cnt2 > 2 || cnt2 - cnt1 > 2;
    }
}
```

#### [1345. 跳跃游戏 IV](https://leetcode-cn.com/problems/jump-game-iv/)



给你一个整数数组 `arr` ，你一开始在数组的第一个元素处（下标为 0）。

每一步，你可以从下标 `i` 跳到下标：

- `i + 1` 满足：`i + 1 < arr.length`
- `i - 1` 满足：`i - 1 >= 0`
- `j` 满足：`arr[i] == arr[j]` 且 `i != j`

请你返回到达数组最后一个元素的下标处所需的 **最少操作次数** 。

注意：任何时候你都不能跳到数组外面。

 

**示例 1：**

```
输入：arr = [100,-23,-23,404,100,23,23,23,3,404]
输出：3
解释：那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。
```

**示例 2：**

```
输入：arr = [7]
输出：0
解释：一开始就在最后一个元素处，所以你不需要跳跃。
```

**示例 3：**

```
输入：arr = [7,6,9,6,9,6,9,7]
输出：1
解释：你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
```

**示例 4：**

```
输入：arr = [6,1,9]
输出：2
```

**示例 5：**

```
输入：arr = [11,22,7,7,7,7,7,7,7,22,13]
输出：3
```

 

**提示：**

- `1 <= arr.length <= 5 * 10^4`
- `-10^8 <= arr[i] <= 10^8`

```java
class Solution {
    public int minJumps(int[] arr) {
        Map<Integer, List<Integer>> idxSameValue = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            idxSameValue.putIfAbsent(arr[i], new ArrayList<>());
            idxSameValue.get(arr[i]).add(i);
        }
        Set<Integer> visitedIndex = new HashSet<>();
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        visitedIndex.add(0);
        while (!queue.isEmpty()) {
            int[] idxStep = queue.poll();
            int idx = idxStep[0];
            int step = idxStep[1];
            if (idx == arr.length - 1) {
                return step;
            }
            int v = arr[idx];
            step++;
            if (idxSameValue.containsKey(v)) {
                for (int i : idxSameValue.get(v)) {
                    if (visitedIndex.add(i)) {
                        queue.offer(new int[]{i, step});
                    }
                }
                idxSameValue.remove(v);
            }
            if (idx + 1 < arr.length && visitedIndex.add(idx + 1)) {
                queue.offer(new int[]{idx + 1, step});
            }
            if (idx - 1 >= 0 && visitedIndex.add(idx - 1)) {
                queue.offer(new int[]{idx - 1, step});
            }
        }
        return -1;
    }
}
```

#### [1332. 删除回文子序列](https://leetcode-cn.com/problems/remove-palindromic-subsequences/)



给你一个字符串 `s`，它仅由字母 `'a'` 和 `'b'` 组成。每一次删除操作都可以从 `s` 中删除一个回文 **子序列**。

返回删除给定字符串中所有字符（字符串为空）的最小删除次数。

「子序列」定义：如果一个字符串可以通过删除原字符串某些字符而不改变原字符顺序得到，那么这个字符串就是原字符串的一个子序列。

「回文」定义：如果一个字符串向后和向前读是一致的，那么这个字符串就是一个回文。

 

**示例 1：**

```
输入：s = "ababa"
输出：1
解释：字符串本身就是回文序列，只需要删除一次。
```

**示例 2：**

```
输入：s = "abb"
输出：2
解释："abb" -> "bb" -> "". 
先删除回文子序列 "a"，然后再删除 "bb"。
```

**示例 3：**

```
输入：s = "baabb"
输出：2
解释："baabb" -> "b" -> "". 
先删除回文子序列 "baab"，然后再删除 "b"。
```

 

**提示：**

- `1 <= s.length <= 1000`
- `s` 仅包含字母 `'a'` 和 `'b'`

```java
class Solution {
    public int removePalindromeSub(String s) {
      int n = s.length();
        int i = 0, j = n - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return 2;
            i++;
            j--;
        }
        return 1;
    }
}
```

#### [2034. 股票价格波动](https://leetcode-cn.com/problems/stock-price-fluctuation/)

难度中等26

给你一支股票价格的数据流。数据流中每一条记录包含一个 **时间戳** 和该时间点股票对应的 **价格** 。

不巧的是，由于股票市场内在的波动性，股票价格记录可能不是按时间顺序到来的。某些情况下，有的记录可能是错的。如果两个有相同时间戳的记录出现在数据流中，前一条记录视为错误记录，后出现的记录 **更正** 前一条错误的记录。

请你设计一个算法，实现：

- **更新** 股票在某一时间戳的股票价格，如果有之前同一时间戳的价格，这一操作将 **更正** 之前的错误价格。
- 找到当前记录里 **最新股票价格** 。**最新股票价格** 定义为时间戳最晚的股票价格。
- 找到当前记录里股票的 **最高价格** 。
- 找到当前记录里股票的 **最低价格** 。

请你实现 `StockPrice` 类：

- `StockPrice()` 初始化对象，当前无股票价格记录。
- `void update(int timestamp, int price)` 在时间点 `timestamp` 更新股票价格为 `price` 。
- `int current()` 返回股票 **最新价格** 。
- `int maximum()` 返回股票 **最高价格** 。
- `int minimum()` 返回股票 **最低价格** 。

> 示例 1：
>
> 输入：
> ["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
> [[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
> 输出：
> [null, null, null, 5, 10, null, 5, null, 2]
>
> 解释：
> StockPrice stockPrice = new StockPrice();
> stockPrice.update(1, 10); // 时间戳为 [1] ，对应的股票价格为 [10] 。
> stockPrice.update(2, 5);  // 时间戳为 [1,2] ，对应的股票价格为 [10,5] 。
> stockPrice.current();     // 返回 5 ，最新时间戳为 2 ，对应价格为 5 。
> stockPrice.maximum();     // 返回 10 ，最高价格的时间戳为 1 ，价格为 10 。
> stockPrice.update(1, 3);  // 之前时间戳为 1 的价格错误，价格更新为 3 。
>                           // 时间戳为 [1,2] ，对应股票价格为 [3,5] 。
> stockPrice.maximum();     // 返回 5 ，更正后最高价格为 5 。
> stockPrice.update(4, 2);  // 时间戳为 [1,2,4] ，对应价格为 [3,5,2] 。
> stockPrice.minimum();     // 返回 2 ，最低价格时间戳为 4 ，价格为 2 。

**提示：**

- 1 <= timestamp, price <= 109
- update，current，maximum 和 minimum 总 调用次数不超过 105 。
- current，maximum 和 minimum 被调用时，update 操作 至少 已经被调用过 一次 。

```java
class StockPrice {

    int cur;
    Map<Integer, Integer> map = new HashMap<>();
    TreeMap<Integer, Integer> ts = new TreeMap<>();
    public void update(int timestamp, int price) {
        cur = Math.max(cur, timestamp);
        if (map.containsKey(timestamp)) {
            int old = map.get(timestamp);
            int cnt = ts.get(old);
            if (cnt == 1) ts.remove(old);
            else ts.put(old, cnt - 1);
        }
        map.put(timestamp, price);
        ts.put(price, ts.getOrDefault(price, 0) + 1);
    }
    
    public int current() {
        return map.get(cur);
    }
    
    public int maximum() {
        return ts.lastKey();
    }
    
    public int minimum() {
        return ts.firstKey();
    }

}

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */
```

#### [2045. 到达目的地的第二短时间](https://leetcode-cn.com/problems/second-minimum-time-to-reach-destination/)



城市用一个 **双向连通** 图表示，图中有 `n` 个节点，从 `1` 到 `n` 编号（包含 `1` 和 `n`）。图中的边用一个二维整数数组 `edges` 表示，其中每个 `edges[i] = [ui, vi]` 表示一条节点 `ui` 和节点 `vi` 之间的双向连通边。每组节点对由 **最多一条** 边连通，顶点不存在连接到自身的边。穿过任意一条边的时间是 `time` 分钟。

每个节点都有一个交通信号灯，每 `change` 分钟改变一次，从绿色变成红色，再由红色变成绿色，循环往复。所有信号灯都 **同时** 改变。你可以在 **任何时候** 进入某个节点，但是 **只能** 在节点 **信号灯是绿色时** 才能离开。如果信号灯是 **绿色** ，你 **不能** 在节点等待，必须离开。

**第二小的值** 是 **严格大于** 最小值的所有值中最小的值。

- 例如，`[2, 3, 4]` 中第二小的值是 `3` ，而 `[2, 2, 4]` 中第二小的值是 `4` 。

给你 `n`、`edges`、`time` 和 `change` ，返回从节点 `1` 到节点 `n` 需要的 **第二短时间** 。

**注意：**

- 你可以 **任意次** 穿过任意顶点，**包括** `1` 和 `n` 。
- 你可以假设在 **启程时** ，所有信号灯刚刚变成 **绿色** 。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/09/29/e1.png)       ![img](https://assets.leetcode.com/uploads/2021/09/29/e2.png)

```
输入：n = 5, edges = [[1,2],[1,3],[1,4],[3,4],[4,5]], time = 3, change = 5
输出：13
解释：
上面的左图展现了给出的城市交通图。
右图中的蓝色路径是最短时间路径。
花费的时间是：
- 从节点 1 开始，总花费时间=0
- 1 -> 4：3 分钟，总花费时间=3
- 4 -> 5：3 分钟，总花费时间=6
因此需要的最小时间是 6 分钟。

右图中的红色路径是第二短时间路径。
- 从节点 1 开始，总花费时间=0
- 1 -> 3：3 分钟，总花费时间=3
- 3 -> 4：3 分钟，总花费时间=6
- 在节点 4 等待 4 分钟，总花费时间=10
- 4 -> 5：3 分钟，总花费时间=13
因此第二短时间是 13 分钟。      
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2021/09/29/eg2.png)

```
输入：n = 2, edges = [[1,2]], time = 3, change = 2
输出：11
解释：
最短时间路径是 1 -> 2 ，总花费时间 = 3 分钟
最短时间路径是 1 -> 2 -> 1 -> 2 ，总花费时间 = 11 分钟
```

 

**提示：**

- `2 <= n <= 104`
- `n - 1 <= edges.length <= min(2 * 104, n * (n - 1) / 2)`
- `edges[i].length == 2`
- `1 <= ui, vi <= n`
- `ui != vi`
- 不含重复边
- 每个节点都可以从其他节点直接或者间接到达
- `1 <= time, change <= 103`

```java
class Solution {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        // path[i][0] 表示从 1 到 i 的最短路长度，path[i][1] 表示从 1 到 i 的严格次短路长度
        int[][] path = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(path[i], Integer.MAX_VALUE);
        }
        path[1][0] = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{1, 0});
        while (path[n][1] == Integer.MAX_VALUE) {
            int[] arr = queue.poll();
            int cur = arr[0], len = arr[1];
            for (int next : graph[cur]) {
                if (len + 1 < path[next][0]) {
                    path[next][0] = len + 1;
                    queue.offer(new int[]{next, len + 1});
                } else if (len + 1 > path[next][0] && len + 1 < path[next][1]) {
                    path[next][1] = len + 1;
                    queue.offer(new int[]{next, len + 1});
                }
            }
        }
        int ret = 0;
        for (int i = 0; i < path[n][1]; i++) {
            if (ret % (2 * change) >= change) {
                ret = ret + (2 * change - ret % (2 * change));
            }
            ret = ret + time;
        }
        return ret;
    }
}
```

#### [1688. 比赛中的配对次数](https://leetcode-cn.com/problems/count-of-matches-in-tournament/)

难度简单71收藏分享切换为英文接收动态反馈

给你一个整数 `n` ，表示比赛中的队伍数。比赛遵循一种独特的赛制：

- 如果当前队伍数是 **偶数** ，那么每支队伍都会与另一支队伍配对。总共进行 `n / 2` 场比赛，且产生 `n / 2` 支队伍进入下一轮。
- 如果当前队伍数为 **奇数** ，那么将会随机轮空并晋级一支队伍，其余的队伍配对。总共进行 `(n - 1) / 2` 场比赛，且产生 `(n - 1) / 2 + 1` 支队伍进入下一轮。

返回在比赛中进行的配对次数，直到决出获胜队伍为止。

 

**示例 1：**

```
输入：n = 7
输出：6
解释：比赛详情：
- 第 1 轮：队伍数 = 7 ，配对次数 = 3 ，4 支队伍晋级。
- 第 2 轮：队伍数 = 4 ，配对次数 = 2 ，2 支队伍晋级。
- 第 3 轮：队伍数 = 2 ，配对次数 = 1 ，决出 1 支获胜队伍。
总配对次数 = 3 + 2 + 1 = 6
```

**示例 2：**

```
输入：n = 14
输出：13
解释：比赛详情：
- 第 1 轮：队伍数 = 14 ，配对次数 = 7 ，7 支队伍晋级。
- 第 2 轮：队伍数 = 7 ，配对次数 = 3 ，4 支队伍晋级。 
- 第 3 轮：队伍数 = 4 ，配对次数 = 2 ，2 支队伍晋级。
- 第 4 轮：队伍数 = 2 ，配对次数 = 1 ，决出 1 支获胜队伍。
总配对次数 = 7 + 3 + 2 + 1 = 13
```

 

**提示：**

- `1 <= n <= 200`

```java
class Solution {
    public int numberOfMatches(int n) {
        return n-1;
    }
}
```

#### [2013. 检测正方形](https://leetcode-cn.com/problems/detect-squares/)



给你一个在 X-Y 平面上的点构成的数据流。设计一个满足下述要求的算法：

- **添加** 一个在数据流中的新点到某个数据结构中**。**可以添加 **重复** 的点，并会视作不同的点进行处理。
- 给你一个查询点，请你从数据结构中选出三个点，使这三个点和查询点一同构成一个 **面积为正** 的 **轴对齐正方形** ，**统计** 满足该要求的方案数目**。**

**轴对齐正方形** 是一个正方形，除四条边长度相同外，还满足每条边都与 x-轴 或 y-轴 平行或垂直。

实现 `DetectSquares` 类：

- `DetectSquares()` 使用空数据结构初始化对象
- `void add(int[] point)` 向数据结构添加一个新的点 `point = [x, y]`
- `int count(int[] point)` 统计按上述方式与点 `point = [x, y]` 共同构造 **轴对齐正方形** 的方案数。

 

**示例：**

![img](https://assets.leetcode.com/uploads/2021/09/01/image.png)

```
输入：
["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
[[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
输出：
[null, null, null, null, 1, 0, null, 2]

解释：
DetectSquares detectSquares = new DetectSquares();
detectSquares.add([3, 10]);
detectSquares.add([11, 2]);
detectSquares.add([3, 2]);
detectSquares.count([11, 10]); // 返回 1 。你可以选择：
                               //   - 第一个，第二个，和第三个点
detectSquares.count([14, 8]);  // 返回 0 。查询点无法与数据结构中的这些点构成正方形。
detectSquares.add([11, 2]);    // 允许添加重复的点。
detectSquares.count([11, 10]); // 返回 2 。你可以选择：
                               //   - 第一个，第二个，和第三个点
                               //   - 第一个，第三个，和第四个点
```

 

**提示：**

- `point.length == 2`
- `0 <= x, y <= 1000`
- 调用 `add` 和 `count` 的 **总次数** 最多为 `5000`

```java
class DetectSquares {

    Map<Integer, Map<Integer, Integer>> cnt;

    public DetectSquares() {
        cnt = new HashMap<Integer, Map<Integer, Integer>>();
    }

    public void add(int[] point) {
        int x = point[0], y = point[1];
        cnt.putIfAbsent(y, new HashMap<Integer, Integer>());
        Map<Integer, Integer> yCnt = cnt.get(y);
        yCnt.put(x, yCnt.getOrDefault(x, 0) + 1);
    }

    public int count(int[] point) {
        int res = 0;
        int x = point[0], y = point[1];
        if (!cnt.containsKey(y)) {
            return 0;
        }
        Map<Integer, Integer> yCnt = cnt.get(y);
        Set<Map.Entry<Integer, Map<Integer, Integer>>> entries = cnt.entrySet();
        for (Map.Entry<Integer, Map<Integer, Integer>> entry : entries) {
            int col = entry.getKey();
            Map<Integer, Integer> colCnt = entry.getValue();
            if (col != y) {
                // 根据对称性，这里可以不用取绝对值
                int d = col - y;
                res += colCnt.getOrDefault(x, 0) * yCnt.getOrDefault(x + d, 0) * colCnt.getOrDefault(x + d, 0);
                res += colCnt.getOrDefault(x, 0) * yCnt.getOrDefault(x - d, 0) * colCnt.getOrDefault(x - d, 0);
            }
        }
        return res;
    }
}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */
```

#### [2047. 句子中的有效单词数](https://leetcode-cn.com/problems/number-of-valid-words-in-a-sentence/)



句子仅由小写字母（`'a'` 到 `'z'`）、数字（`'0'` 到 `'9'`）、连字符（`'-'`）、标点符号（`'!'`、`'.'` 和 `','`）以及空格（`' '`）组成。每个句子可以根据空格分解成 **一个或者多个 token** ，这些 token 之间由一个或者多个空格 `' '` 分隔。

如果一个 token 同时满足下述条件，则认为这个 token 是一个有效单词：

- 仅由小写字母、连字符和/或标点（不含数字）。
- **至多一个** 连字符 `'-'` 。如果存在，连字符两侧应当都存在小写字母（`"a-b"` 是一个有效单词，但 `"-ab"` 和 `"ab-"` 不是有效单词）。
- **至多一个** 标点符号。如果存在，标点符号应当位于 token 的 **末尾** 。

这里给出几个有效单词的例子：`"a-b."`、`"afad"`、`"ba-c"`、`"a!"` 和 `"!"` 。

给你一个字符串 `sentence` ，请你找出并返回 `sentence` 中 **有效单词的数目** 。

 

**示例 1：**

```
输入：sentence = "cat and  dog"
输出：3
解释：句子中的有效单词是 "cat"、"and" 和 "dog"
```

**示例 2：**

```
输入：sentence = "!this  1-s b8d!"
输出：0
解释：句子中没有有效单词
"!this" 不是有效单词，因为它以一个标点开头
"1-s" 和 "b8d" 也不是有效单词，因为它们都包含数字
```

**示例 3：**

```
输入：sentence = "alice and  bob are playing stone-game10"
输出：5
解释：句子中的有效单词是 "alice"、"and"、"bob"、"are" 和 "playing"
"stone-game10" 不是有效单词，因为它含有数字
```

**示例 4：**

```
输入：sentence = "he bought 2 pencils, 3 erasers, and 1  pencil-sharpener."
输出：6
解释：句子中的有效单词是 "he"、"bought"、"pencils,"、"erasers,"、"and" 和 "pencil-sharpener."
```

 

**提示：**

- `1 <= sentence.length <= 1000`
- `sentence` 由小写英文字母、数字（`0-9`）、以及字符（`' '`、`'-'`、`'!'`、`'.'` 和 `','`）组成
- 句子中至少有 `1` 个 token

```java
class Solution {
    public int countValidWords(String sentence) {
        int n = sentence.length();
        int l = 0, r = 0;
        int ret = 0;
        while (true) {
            while (l < n && sentence.charAt(l) == ' ') {
                l++;
            }
            if (l >= n) {
                break;
            }
            r = l + 1;
            while (r < n && sentence.charAt(r) != ' ') {
                r++;
            }
            if (isValid(sentence.substring(l, r))) { // 判断根据空格分解出来的 token 是否有效
                ret++;
            }
            l = r + 1;
        }
        return ret;
    }

    public boolean isValid(String word) {
        int n = word.length();
        boolean hasHyphens = false;
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(word.charAt(i))) {
                return false;
            } else if (word.charAt(i) == '-') {
                if (hasHyphens || i == 0 || i == n - 1 || !Character.isLetter(word.charAt(i - 1)) || !Character.isLetter(word.charAt(i + 1))) {
                    return false;
                }
                hasHyphens = true;
            } else if (word.charAt(i) == '!' || word.charAt(i) == '.' || word.charAt(i) == ',') {
                if (i != n - 1) {
                    return false;
                }
            }
        }
        return true;
    }
    
}
```

#### [1996. 游戏中弱角色的数量](https://leetcode-cn.com/problems/the-number-of-weak-characters-in-the-game/)



你正在参加一个多角色游戏，每个角色都有两个主要属性：**攻击** 和 **防御** 。给你一个二维整数数组 `properties` ，其中 `properties[i] = [attacki, defensei]` 表示游戏中第 `i` 个角色的属性。

如果存在一个其他角色的攻击和防御等级 **都严格高于** 该角色的攻击和防御等级，则认为该角色为 **弱角色** 。更正式地，如果认为角色 `i` **弱于** 存在的另一个角色 `j` ，那么 `attackj > attacki` 且 `defensej > defensei` 。

返回 **弱角色** 的数量。

 

**示例 1：**

```
输入：properties = [[5,5],[6,3],[3,6]]
输出：0
解释：不存在攻击和防御都严格高于其他角色的角色。
```

**示例 2：**

```
输入：properties = [[2,2],[3,3]]
输出：1
解释：第一个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
```

**示例 3：**

```
输入：properties = [[1,5],[10,4],[4,3]]
输出：1
解释：第三个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
```

 

**提示：**

- `2 <= properties.length <= 105`
- `properties[i].length == 2`
- `1 <= attacki, defensei <= 105`

```java
class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (o1, o2) -> {
            return o1[0] == o2[0] ? (o1[1] - o2[1]) : (o2[0] - o1[0]);
        });
        int maxDef = 0;
        int ans = 0;
        for (int[] p : properties) {
            if (p[1] < maxDef) {
                ans++;
            } else {
                maxDef = p[1];
            }
        }
        return ans;
    }   
}
```

#### [1765. 地图中的最高点](https://leetcode-cn.com/problems/map-of-highest-peak/)



给你一个大小为 `m x n` 的整数矩阵 `isWater` ，它代表了一个由 **陆地** 和 **水域** 单元格组成的地图。

- 如果 `isWater[i][j] == 0` ，格子 `(i, j)` 是一个 **陆地** 格子。
- 如果 `isWater[i][j] == 1` ，格子 `(i, j)` 是一个 **水域** 格子。

你需要按照如下规则给每个单元格安排高度：

- 每个格子的高度都必须是非负的。
- 如果一个格子是是 **水域** ，那么它的高度必须为 `0` 。
- 任意相邻的格子高度差 **至多** 为 `1` 。当两个格子在正东、南、西、北方向上相互紧挨着，就称它们为相邻的格子。（也就是说它们有一条公共边）

找到一种安排高度的方案，使得矩阵中的最高高度值 **最大** 。

请你返回一个大小为 `m x n` 的整数矩阵 `height` ，其中 `height[i][j]` 是格子 `(i, j)` 的高度。如果有多种解法，请返回 **任意一个** 。

 

**示例 1：**

**![img](https://assets.leetcode.com/uploads/2021/01/10/screenshot-2021-01-11-at-82045-am.png)**

```
输入：isWater = [[0,1],[0,0]]
输出：[[1,0],[2,1]]
解释：上图展示了给各个格子安排的高度。
蓝色格子是水域格，绿色格子是陆地格。
```

**示例 2：**

**![img](https://assets.leetcode.com/uploads/2021/01/10/screenshot-2021-01-11-at-82050-am.png)**

```
输入：isWater = [[0,0,1],[1,0,0],[0,0,0]]
输出：[[1,1,0],[0,1,1],[1,2,2]]
解释：所有安排方案中，最高可行高度为 2 。
任意安排方案中，只要最高高度为 2 且符合上述规则的，都为可行方案。
```

 

**提示：**

- `m == isWater.length`
- `n == isWater[i].length`
- `1 <= m, n <= 1000`
- `isWater[i][j]` 要么是 `0` ，要么是 `1` 。
- 至少有 **1** 个水域格子。

```java
class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        int[][] ans = new int[m][n];
        Deque<int[]> d = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) d.addLast(new int[]{i, j});
                ans[i][j] = isWater[i][j] == 1 ? 0 : -1;
            }
        }
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!d.isEmpty()) {
            int[] info = d.pollFirst();
            int x = info[0], y = info[1];
            for (int[] di : dirs) {
                int nx = x + di[0], ny = y + di[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (ans[nx][ny] != -1) continue;
                ans[nx][ny] = ans[x][y] + 1;
                d.addLast(new int[]{nx, ny});
            }
        }
        return ans;
    }
}
```


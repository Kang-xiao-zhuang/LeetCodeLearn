#### [1446. 连续字符](https://leetcode-cn.com/problems/consecutive-characters/)



给你一个字符串 `s` ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。

请你返回字符串的能量。

 

**示例 1：**

```
输入：s = "leetcode"
输出：2
解释：子字符串 "ee" 长度为 2 ，只包含字符 'e' 。
```

**示例 2：**

```
输入：s = "abbcccddddeeeeedcba"
输出：5
解释：子字符串 "eeeee" 长度为 5 ，只包含字符 'e' 。
```

**示例 3：**

```
输入：s = "triplepillooooow"
输出：5
```

**示例 4：**

```
输入：s = "hooraaaaaaaaaaay"
输出：11
```

**示例 5：**

```
输入：s = "tourist"
输出：1
```

 

**提示：**

- `1 <= s.length <= 500`
- `s` 只包含小写英文字母。

**简单模拟**

```java
class Solution {
    public int maxPower(String s) {
        int ans = 1, cnt = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                cnt++;
                ans = Math.max(ans, cnt);
            } else {
                cnt = 1;
            }
        }
        return ans;
    }
}
```

#### [506. 相对名次](https://leetcode-cn.com/problems/relative-ranks/)



给你一个长度为 `n` 的整数数组 `score` ，其中 `score[i]` 是第 `i` 位运动员在比赛中的得分。所有得分都 **互不相同** 。

运动员将根据得分 **决定名次** ，其中名次第 `1` 的运动员得分最高，名次第 `2` 的运动员得分第 `2` 高，依此类推。运动员的名次决定了他们的获奖情况：

- 名次第 `1` 的运动员获金牌 `"Gold Medal"` 。
- 名次第 `2` 的运动员获银牌 `"Silver Medal"` 。
- 名次第 `3` 的运动员获铜牌 `"Bronze Medal"` 。
- 从名次第 `4` 到第 `n` 的运动员，只能获得他们的名次编号（即，名次第 `x` 的运动员获得编号 `"x"`）。

使用长度为 `n` 的数组 `answer` 返回获奖，其中 `answer[i]` 是第 `i` 位运动员的获奖情况。

 

**示例 1：**

```
输入：score = [5,4,3,2,1]
输出：["Gold Medal","Silver Medal","Bronze Medal","4","5"]
解释：名次为 [1st, 2nd, 3rd, 4th, 5th] 。
```

**示例 2：**

```
输入：score = [10,3,8,9,4]
输出：["Gold Medal","5","Bronze Medal","Silver Medal","4"]
解释：名次为 [1st, 5th, 3rd, 2nd, 4th] 。
```

 

**提示：**

- `n == score.length`
- `1 <= n <= 104`
- `0 <= score[i] <= 106`
- `score` 中的所有值 **互不相同**



```java
class Solution {
    public String[] findRelativeRanks(int[] score) {
        String[] ss = new String[]{"Gold Medal", "Silver Medal", "Bronze Medal"};
        int n = score.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = score[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            if (i >= 3) {
                ans[arr[i][1]] = Integer.toString(i + 1);
            } else {
                ans[arr[i][1]] = ss[i];
            }
        }
        return ans;
    }
}
```

#### [1005. K 次取反后最大化的数组和](https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations/)



给你一个整数数组 `nums` 和一个整数 `k` ，按以下方法修改该数组：

- 选择某个下标 `i` 并将 `nums[i]` 替换为 `-nums[i]` 。

重复这个过程恰好 `k` 次。可以多次选择同一个下标 `i` 。

以这种方式修改数组后，返回数组 **可能的最大和** 。

 

**示例 1：**

```
输入：nums = [4,2,3], k = 1
输出：5
解释：选择下标 1 ，nums 变为 [4,-2,3] 。
```

**示例 2：**

```
输入：nums = [3,-1,0,2], k = 3
输出：6
解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
```

**示例 3：**

```
输入：nums = [2,-3,-1,5,-4], k = 2
输出：13
解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
```

 

**提示：**

- `1 <= nums.length <= 104`
- `-100 <= nums[i] <= 100`
- `1 <= k <= 104`

**哈希表**

```java
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int sum = Arrays.stream(nums).sum();
        for (int i = -100; i < 0; ++i) {
            if (map.containsKey(i)) {
                int ops = Math.min(k, map.get(i));
                sum += (-i) * ops * 2;
                map.put(i, map.get(i) - ops);
                map.put(-i, map.getOrDefault(-i, 0) + ops);
                k -= ops;
                if (k == 0) {
                    break;
                }
            }
        }
        if (k > 0 && k % 2 == 1 && !map.containsKey(0)) {
            for (int i = 1; i <= 100; ++i) {
                if (map.containsKey(i)) {
                    sum -= i * 2;
                    break;
                }
            }
        }
        return sum;
    }
}
```

**优化排序**

```java
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        // 排序数组
        Arrays.sort(nums);
        int sum = 0;
        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            // 如果是负数并且k>0 置为负
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
            // 求和
            sum += nums[i];
        }
        // 再次排序 把负数放在数组第一位
        Arrays.sort(nums);
        // k==0 负数全部为正 已经是最大数
        // k!=0 负数全部为正  如果k还剩偶数个就自己抵消掉，不用删减，如果k还剩奇数个就减掉2倍最小正数
        return sum - (k % 2 == 0 ? 0 : 2 * nums[0]);
    }
}
```

**暴力解法**

```java
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        while (k > 0) {
            int index = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < nums[index]) {
                    index = i;
                }
            }
            nums[index] = -nums[index];
            k--;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}
```

#### [383. 赎金信](https://leetcode-cn.com/problems/ransom-note/)



为了不在赎金信中暴露字迹，从杂志上搜索各个需要的字母，组成单词来表达意思。

给你一个赎金信 (`ransomNote`) 字符串和一个杂志(`magazine`)字符串，判断 `ransomNote` 能不能由 `magazines` 里面的字符构成。

如果可以构成，返回 `true` ；否则返回 `false` 。

`magazine` 中的每个字符只能在 `ransomNote` 中使用一次。

 

**示例 1：**

```
输入：ransomNote = "a", magazine = "b"
输出：false
```

**示例 2：**

```
输入：ransomNote = "aa", magazine = "ab"
输出：false
```

**示例 3：**

```
输入：ransomNote = "aa", magazine = "aab"
输出：true
```

 

**提示：**

- `1 <= ransomNote.length, magazine.length <= 105`
- `ransomNote` 和 `magazine` 由小写英文字母组成

```java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        // 首先记录杂志出现的字符串
        int[] arr=new int[26];
        int r=ransomNote.length();
        int m=magazine.length();
        for(int i=0;i<m;i++){
            arr[magazine.charAt(i)-'a']++;
        }
        // 在赎金信中查找字符串，找到减1，找不到就返回false
        for(int i=0;i<r;i++){
            if(arr[ransomNote.charAt(i)-'a']>0){
                arr[ransomNote.charAt(i)-'a']--;
            }else{
                return false;
            }
        }
        return true;
    }
}
```

```java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
       char[] chars = new char[26];
        for (char c : magazine.toCharArray()) {
            chars[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (chars[c - 'a']-- == 0) {
                System.out.println(false);
                return false;
            }
        }
        return true;
    }
}
```

#### [372. 超级次方](https://leetcode-cn.com/problems/super-pow/)



你的任务是计算 `ab` 对 `1337` 取模，`a` 是一个正整数，`b` 是一个非常大的正整数且会以数组形式给出。

 

**示例 1：**

```
输入：a = 2, b = [3]
输出：8
```

**示例 2：**

```
输入：a = 2, b = [1,0]
输出：1024
```

**示例 3：**

```
输入：a = 1, b = [4,3,3,8,5,2]
输出：1
```

**示例 4：**

```
输入：a = 2147483647, b = [2,0,0]
输出：1198
```

**三叶**

```java
class Solution {
    int MOD = 1337;
    public int superPow(int a, int[] b) {
      return dfs(a, b, b.length - 1);
    }
    public int dfs(int a, int[] b, int u) {
        if (u == -1) return 1;
        return qpow(dfs(a, b, u - 1), 10) * qpow(a, b[u]) % MOD;
    }

    public int qpow(int a, int b) {
        int ans = 1;
        a %= MOD;
        while (b != 0) {
            if ((b & 1) != 0) ans = ans * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return ans;
    }
}
```

#### [1816. 截断句子](https://leetcode-cn.com/problems/truncate-sentence/)



**句子** 是一个单词列表，列表中的单词之间用单个空格隔开，且不存在前导或尾随空格。每个单词仅由大小写英文字母组成（不含标点符号）。

- 例如，`"Hello World"`、`"HELLO"` 和 `"hello world hello world"` 都是句子。

给你一个句子 `s` 和一个整数 `k` ，请你将 `s` **截断** ，使截断后的句子仅含 **前** `k` 个单词。返回 **截断** `s`** 后得到的句子*。*

 

**示例 1：**

```
输入：s = "Hello how are you Contestant", k = 4
输出："Hello how are you"
解释：
s 中的单词为 ["Hello", "how" "are", "you", "Contestant"]
前 4 个单词为 ["Hello", "how", "are", "you"]
因此，应当返回 "Hello how are you"
```

**示例 2：**

```
输入：s = "What is the solution to this problem", k = 4
输出："What is the solution"
解释：
s 中的单词为 ["What", "is" "the", "solution", "to", "this", "problem"]
前 4 个单词为 ["What", "is", "the", "solution"]
因此，应当返回 "What is the solution"
```

**示例 3：**

```
输入：s = "chopper is not a tanuki", k = 5
输出："chopper is not a tanuki"
```

 

**提示：**

- `1 <= s.length <= 500`
- `k` 的取值范围是 `[1, s 中单词的数目]`
- `s` 仅由大小写英文字母和空格组成
- `s` 中的单词之间由单个空格隔开
- 不存在前导或尾随空格

**模拟**

```java
class Solution {
    public String truncateSentence(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < s.length() && count < k; i++) {
            if (s.charAt(i) == ' ') {
                count++;
            }
            if (count < k) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
```

**模拟**

```java
class Solution {
    public String truncateSentence(String s, int k) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                count++;
                if (count == k) {
                    return s.substring(0, i);
                }
            }
        }
        return s;
    }
}
```

#### [1034. 边界着色](https://leetcode-cn.com/problems/coloring-a-border/)



给你一个大小为 `m x n` 的整数矩阵 `grid` ，表示一个网格。另给你三个整数 `row`、`col` 和 `color` 。网格中的每个值表示该位置处的网格块的颜色。

两个网格块属于同一 **连通分量** 需满足下述全部条件：

- 两个网格块颜色相同
- 在上、下、左、右任意一个方向上相邻

**连通分量的边界** 是指连通分量中满足下述条件之一的所有网格块：

- 在上、下、左、右四个方向上与不属于同一连通分量的网格块相邻
- 在网格的边界上（第一行/列或最后一行/列）

请你使用指定颜色 `color` 为所有包含网格块 `grid[row][col]` 的 **连通分量的边界** 进行着色，并返回最终的网格 `grid` 。

 

**示例 1：**

```
输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
输出：[[3,3],[3,2]]
```

**示例 2：**

```
输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
输出：[[1,3,3],[2,3,3]]
```

**示例 3：**

```
输入：grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
输出：[[2,2,2],[2,1,2],[2,2,2]]
```

 

**提示：**

- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 50`
- `1 <= grid[i][j], color <= 1000`
- `0 <= row < m`
- `0 <= col < n`

```java
class Solution {
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m][n];
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Deque<int[]> d = new ArrayDeque<>();
        d.addLast(new int[]{row, col});
        while (!d.isEmpty()) {
            int[] poll = d.pollFirst();
            int x = poll[0], y = poll[1], cnt = 0;
            for (int[] di : dirs) {
                int nx = x + di[0], ny = y + di[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }
                if (grid[x][y] != grid[nx][ny]) {
                    continue;
                } else {
                    cnt++;
                }
                if (ans[nx][ny] != 0) {
                    continue;
                }
                d.addLast(new int[]{nx, ny});
            }
            ans[x][y] = cnt == 4 ? grid[x][y] : color;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (ans[i][j] == 0) {
                    ans[i][j] = grid[i][j];
                }
            }
        }
        return ans;
    }
}
```

#### [689. 三个无重叠子数组的最大和](https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays/)



给你一个整数数组 `nums` 和一个整数 `k` ，找出三个长度为 `k` 、互不重叠、且 `3 * k` 项的和最大的子数组，并返回这三个子数组。

以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 **0** 开始）。如果有多个结果，返回字典序最小的一个。

 

**示例 1：**

```
输入：nums = [1,2,1,2,6,7,5,1], k = 2
输出：[0,3,5]
解释：子数组 [1, 2], [2, 6], [7, 5] 对应的起始下标为 [0, 3, 5]。
也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。
```

**示例 2：**

```
输入：nums = [1,2,1,2,1,2,1,2,1], k = 2
输出：[0,2,4]
```

 

**提示：**

- `1 <= nums.length <= 2 * 104`
- `1 <= nums[i] < 216`
- `1 <= k <= floor(nums.length / 3)`

```java
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        long[] sum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        long[][] f = new long[n + 10][4];
        for (int i = n - k + 1; i >= 1; i--) {
            for (int j = 1; j < 4; j++) {
                f[i][j] = Math.max(f[i + 1][j], f[i + k][j - 1] + sum[i + k - 1] - sum[i - 1]);
            }
        }
        int[] ans = new int[3];
        int i = 1, j = 3, idx = 0;
        while (j > 0) {
            if (f[i + 1][j] > f[i + k][j - 1] + sum[i + k - 1] - sum[i - 1]) {
                i++;
            } else {
                ans[idx++] = i - 1;
                i += k; j--;
            }
        }
        return ans;
    }
}
```

#### [794. 有效的井字游戏](https://leetcode-cn.com/problems/valid-tic-tac-toe-state/)



用字符串数组作为井字游戏的游戏板 `board`。当且仅当在井字游戏过程中，玩家有可能将字符放置成游戏板所显示的状态时，才返回 true。

该游戏板是一个 3 x 3 数组，由字符 `" "`，`"X"` 和 `"O"` 组成。字符 `" "` 代表一个空位。

以下是井字游戏的规则：

- 玩家轮流将字符放入空位（" "）中。
- 第一个玩家总是放字符 “X”，且第二个玩家总是放字符 “O”。
- “X” 和 “O” 只允许放置在空位中，不允许对已放有字符的位置进行填充。
- 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
- 当所有位置非空时，也算为游戏结束。
- 如果游戏结束，玩家不允许再放置字符。

```
示例 1:
输入: board = ["O  ", "   ", "   "]
输出: false
解释: 第一个玩家总是放置“X”。

示例 2:
输入: board = ["XOX", " X ", "   "]
输出: false
解释: 玩家应该是轮流放置的。

示例 3:
输入: board = ["XXX", "   ", "OOO"]
输出: false

示例 4:
输入: board = ["XOX", "O O", "XOX"]
输出: true
```

**说明:**

- 游戏板 `board` 是长度为 3 的字符串数组，其中每个字符串 `board[i]` 的长度为 3。
-  `board[i][j]` 是集合 `{" ", "X", "O"}` 中的一个字符。

**模拟**

```java
class Solution {
    public boolean validTicTacToe(String[] board) {
        int xCount = 0, oCount = 0;
        for (String row : board) {
            for (char c : row.toCharArray()) {
                xCount = (c == 'X') ? (xCount + 1) : xCount;
                oCount = (c == 'O') ? (oCount + 1) : oCount;
            }
        }
        if (oCount != xCount && oCount != xCount - 1) {
            return false;
        }
        if (win(board, 'X') && oCount != xCount - 1) {
            return false;
        }
        if (win(board, 'O') && oCount != xCount) {
            return false;
        }
        return true;
    }

    public boolean win(String[] board, char p) {
        for (int i = 0; i < 3; i++) {
            if (p == board[0].charAt(i) && p == board[1].charAt(i) && p == board[2].charAt(i)) {
                return true;
            }
            if (p == board[i].charAt(0) && p == board[i].charAt(1) && p == board[i].charAt(2)) {
                return true;
            }
        }
        if (p == board[0].charAt(0) && p == board[1].charAt(1) && p == board[2].charAt(2)) {
            return true;
        }
        if (p == board[0].charAt(2) && p == board[1].charAt(1) && p == board[2].charAt(0)) {
            return true;
        }
        return false;
    }
}
```

#### [748. 最短补全词](https://leetcode-cn.com/problems/shortest-completing-word/)



给你一个字符串 `licensePlate` 和一个字符串数组 `words` ，请你找出并返回 `words` 中的 **最短补全词** 。

**补全词** 是一个包含 `licensePlate` 中所有的字母的单词。在所有补全词中，最短的那个就是 **最短补全词** 。

在匹配 `licensePlate` 中的字母时：

- **忽略** `licensePlate` 中的 **数字和空格** 。
- **不区分大小写**。
- 如果某个字母在 `licensePlate` 中出现不止一次，那么该字母在补全词中的出现次数应当一致或者更多。

例如：`licensePlate`` = "aBc 12c"`，那么它的补全词应当包含字母 `'a'`、`'b'` （忽略大写）和两个 `'c'` 。可能的 **补全词** 有 `"abccdef"`、`"caaacab"` 以及 `"cbca"` 。

请你找出并返回 `words` 中的 **最短补全词** 。题目数据保证一定存在一个最短补全词。当有多个单词都符合最短补全词的匹配条件时取 `words` 中 **最靠前的** 那个。

 

**示例 1：**

```
输入：licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
输出："steps"
解释：最短补全词应该包括 "s"、"p"、"s"（忽略大小写） 以及 "t"。
"step" 包含 "t"、"p"，但只包含一个 "s"，所以它不符合条件。
"steps" 包含 "t"、"p" 和两个 "s"。
"stripe" 缺一个 "s"。
"stepple" 缺一个 "s"。
因此，"steps" 是唯一一个包含所有字母的单词，也是本例的答案。
```

**示例 2：**

```
输入：licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
输出："pest"
解释：licensePlate 只包含字母 "s" 。所有的单词都包含字母 "s" ，其中 "pest"、"stew"、和 "show" 三者最短。答案是 "pest" ，因为它是三个单词中在 words 里最靠前的那个。
```

**示例 3：**

```
输入：licensePlate = "Ah71752", words = ["suggest","letter","of","husband","easy","education","drug","prevent","writer","old"]
输出："husband"
```

**示例 4：**

```
输入：licensePlate = "OgEu755", words = ["enough","these","play","wide","wonder","box","arrive","money","tax","thus"]
输出："enough"
```

**示例 5：**

```
输入：licensePlate = "iMSlpe4", words = ["claim","consumer","student","camera","public","never","wonder","simple","thought","use"]
输出："simple"
```

 

**提示：**

- `1 <= licensePlate.length <= 7`
- `licensePlate` 由数字、大小写字母或空格 `' '` 组成
- `1 <= words.length <= 1000`
- `1 <= words[i].length <= 15`
- `words[i]` 由小写英文字母组成

```java
class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
         int[] count = getCount(licensePlate);
        String ans = null;
        for (String word : words) {
            int[] cur = getCount(word);
            boolean isOk = true;
            for (int i = 0; i < 26 && isOk; i++) {
                if (count[i] > cur[i]) {
                    isOk = false;
                }
            }
            if (isOk && (ans == null || ans.length() > word.length())) {
                ans = word;
            }
        }
        return ans;
    }

    /**
     * 获取个数
     *
     * @param s 字符串
     * @return 个数
     */
    public static int[] getCount(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                count[Character.toLowerCase(c) - 'a']++;
            }
        }
        return count;
    }
}
```

#### [911. 在线选举](https://leetcode-cn.com/problems/online-election/)

难度中等68

给你两个整数数组 `persons` 和 `times` 。在选举中，第 `i` 张票是在时刻为 `times[i]` 时投给候选人 `persons[i]` 的。

对于发生在时刻 `t` 的每个查询，需要找出在 `t` 时刻在选举中领先的候选人的编号。

在 `t` 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。

实现 `TopVotedCandidate` 类：

- `TopVotedCandidate(int[] persons, int[] times)` 使用 `persons` 和 `times` 数组初始化对象。
- `int q(int t)` 根据前面描述的规则，返回在时刻 `t` 在选举中领先的候选人的编号。

**示例：**

```
输入：
["TopVotedCandidate", "q", "q", "q", "q", "q", "q"]
[[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
输出：
[null, 0, 1, 1, 0, 0, 1]

解释：
TopVotedCandidate topVotedCandidate = new TopVotedCandidate([0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]);
topVotedCandidate.q(3); // 返回 0 ，在时刻 3 ，票数分布为 [0] ，编号为 0 的候选人领先。
topVotedCandidate.q(12); // 返回 1 ，在时刻 12 ，票数分布为 [0,1,1] ，编号为 1 的候选人领先。
topVotedCandidate.q(25); // 返回 1 ，在时刻 25 ，票数分布为 [0,1,1,0,0,1] ，编号为 1 的候选人领先。（在平局的情况下，1 是最近获得投票的候选人）。
topVotedCandidate.q(15); // 返回 0
topVotedCandidate.q(24); // 返回 0
topVotedCandidate.q(8); // 返回 1
```

 

**提示：**

- `1 <= persons.length <= 5000`
- `times.length == persons.length`
- `0 <= persons[i] < persons.length`
- `0 <= times[i] <= 109`
- `times` 是一个严格递增的有序数组
- `times[0] <= t <= 109`
- 每个测试用例最多调用 `104` 次 `q`

**二分**

```java
class TopVotedCandidate {
        List<int[]> list = new ArrayList<>();

        /**
         * https://leetcode-cn.com/problems/online-election/
         * 12.11
         *
         * @param persons 整数数组
         * @param times   整数数组
         */
        public TopVotedCandidate(int[] persons, int[] times) {
            int val = 0;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < times.length; i++) {
                map.put(persons[i], map.getOrDefault(persons[i], 0) + 1);
                if (map.get(persons[i]) >= val) {
                    val = map.get(persons[i]);
                    list.add(new int[]{times[i], persons[i]});
                }
            }
        }

        /**
         * @param t 时刻
         * @return 候选人的编号
         */
        public int q(int t) {
            int l = 0, r = list.size() - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (list.get(mid)[0] <= t) l = mid;
                else r = mid - 1;
            }
            return list.get(r)[0] <= t ? list.get(r)[1] : 0;
        }
    }

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
```

#### [709. 转换成小写字母](https://leetcode-cn.com/problems/to-lower-case/)



给你一个字符串 `s` ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。

 

**示例 1：**

```
输入：s = "Hello"
输出："hello"
```

**示例 2：**

```
输入：s = "here"
输出："here"
```

**示例 3：**

```
输入：s = "LOVELY"
输出："lovely"
```

 

**提示：**

- `1 <= s.length <= 100`
- `s` 由 ASCII 字符集中的可打印字符组成

·**API**

```java
class Solution {
    public String toLowerCase(String s) {
      return s.toLowerCase();
    }
}
```

#### [807. 保持城市天际线](https://leetcode-cn.com/problems/max-increase-to-keep-city-skyline/)



在二维数组`grid`中，`grid[i][j]`代表位于某处的建筑物的高度。 我们被允许增加任何数量（不同建筑物的数量可能不同）的建筑物的高度。 高度 0 也被认为是建筑物。

最后，从新数组的所有四个方向（即顶部，底部，左侧和右侧）观看的“天际线”必须与原始数组的天际线相同。 城市的天际线是从远处观看时，由所有建筑物形成的矩形的外部轮廓。 请看下面的例子。

建筑物高度可以增加的最大总和是多少？

```
例子：
输入： grid = [[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]
输出： 35
解释： 
The grid is:
[ [3, 0, 8, 4], 
  [2, 4, 5, 7],
  [9, 2, 6, 3],
  [0, 3, 1, 0] ]

从数组竖直方向（即顶部，底部）看“天际线”是：[9, 4, 8, 7]
从水平水平方向（即左侧，右侧）看“天际线”是：[8, 7, 9, 3]

在不影响天际线的情况下对建筑物进行增高后，新数组如下：

gridNew = [ [8, 4, 8, 7],
            [7, 4, 7, 7],
            [9, 4, 8, 7],
            [3, 3, 3, 3] ]
```

**说明:**

- `1 < grid.length = grid[0].length <= 50`。
-  `grid[i][j]` 的高度范围是： `[0, 100]`。
- 一座建筑物占据一个`grid[i][j]`：换言之，它们是 `1 x 1 x grid[i][j]` 的长方体。

**贪心**

```java
class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[] rowMax = new int[n];
        int[] colMax = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rowMax[i] = Math.max(rowMax[i], grid[i][j]);
                colMax[j] = Math.max(colMax[j], grid[i][j]);
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res += Math.min(rowMax[i], colMax[j]) - grid[i][j];
            }
        }
        return res;
    }
}
```

#### [630. 课程表 III](https://leetcode-cn.com/problems/course-schedule-iii/)



这里有 `n` 门不同的在线课程，按从 `1` 到 `n` 编号。给你一个数组 `courses` ，其中 `courses[i] = [durationi, lastDayi]` 表示第 `i` 门课将会 **持续** 上 `durationi` 天课，并且必须在不晚于 `lastDayi` 的时候完成。

你的学期从第 `1` 天开始。且不能同时修读两门及两门以上的课程。

返回你最多可以修读的课程数目。

 

**示例 1：**

```
输入：courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
输出：3
解释：
这里一共有 4 门课程，但是你最多可以修 3 门：
首先，修第 1 门课，耗费 100 天，在第 100 天完成，在第 101 天开始下门课。
第二，修第 3 门课，耗费 1000 天，在第 1100 天完成，在第 1101 天开始下门课程。
第三，修第 2 门课，耗时 200 天，在第 1300 天完成。
第 4 门课现在不能修，因为将会在第 3300 天完成它，这已经超出了关闭日期。
```

**示例 2：**

```
输入：courses = [[1,2]]
输出：1
```

**示例 3：**

```
输入：courses = [[3,2],[4,3]]
输出：0
```

 

**提示:**

- `1 <= courses.length <= 104`
- `1 <= durationi, lastDayi <= 104`

**贪心**

```java
class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        int sum = 0;
        for (int[] c : courses) {
            int d = c[0], e = c[1];
            sum += d;
            q.add(d);
            if (sum > e) {
                sum -= q.poll();
            }
        }
        return q.size();
    }
}
```


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


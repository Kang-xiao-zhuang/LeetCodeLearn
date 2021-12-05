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


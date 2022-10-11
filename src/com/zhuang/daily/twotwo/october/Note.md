#### [1694. 重新格式化电话号码](https://leetcode.cn/problems/reformat-phone-number/)

给你一个字符串形式的电话号码 `number` 。`number` 由数字、空格 `' '`、和破折号 `'-'` 组成。

请你按下述方式重新格式化电话号码。

- 首先，**删除** 所有的空格和破折号。

- 其次，将数组从左到右

   

  每 3 个一组

   

  分块，

  直到 

  剩下 4 个或更少数字。剩下的数字将按下述规定再分块：

  - 2 个数字：单个含 2 个数字的块。
  - 3 个数字：单个含 3 个数字的块。
  - 4 个数字：两个分别含 2 个数字的块。

最后用破折号将这些块连接起来。注意，重新格式化过程中 **不应该** 生成仅含 1 个数字的块，并且 **最多** 生成两个含 2 个数字的块。

返回格式化后的电话号码。

 

**示例 1：**

```
输入：number = "1-23-45 6"
输出："123-456"
解释：数字是 "123456"
步骤 1：共有超过 4 个数字，所以先取 3 个数字分为一组。第 1 个块是 "123" 。
步骤 2：剩下 3 个数字，将它们放入单个含 3 个数字的块。第 2 个块是 "456" 。
连接这些块后得到 "123-456" 。
```

**示例 2：**

```
输入：number = "123 4-567"
输出："123-45-67"
解释：数字是 "1234567".
步骤 1：共有超过 4 个数字，所以先取 3 个数字分为一组。第 1 个块是 "123" 。
步骤 2：剩下 4 个数字，所以将它们分成两个含 2 个数字的块。这 2 块分别是 "45" 和 "67" 。
连接这些块后得到 "123-45-67" 。
```

**示例 3：**

```
输入：number = "123 4-5678"
输出："123-456-78"
解释：数字是 "12345678" 。
步骤 1：第 1 个块 "123" 。
步骤 2：第 2 个块 "456" 。
步骤 3：剩下 2 个数字，将它们放入单个含 2 个数字的块。第 3 个块是 "78" 。
连接这些块后得到 "123-456-78" 。
```

**示例 4：**

```
输入：number = "12"
输出："12"
```

**示例 5：**

```
输入：number = "--17-5 229 35-39475 "
输出："175-229-353-94-75"
```

 

**提示：**

- `2 <= number.length <= 100`
- `number` 由数字和字符 `'-'` 及 `' '` 组成。
- `number` 中至少含 **2** 个数字。

**模拟**

```java
class Solution {
    public String reformatNumber(String number) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (Character.isDigit(ch)) {
                sb.append(ch);
            }
        }
        int n = sb.length();
        int pt = 0;
        StringBuilder ans = new StringBuilder();
        while (n > 0) {
            if (n > 4) {
                ans.append(sb.substring(pt, pt + 3)).append("-");
                pt += 3;
                n -= 3;
            } else {
                if (n == 4) {
                    ans.append(sb.substring(pt, pt + 2)).append("-").append(sb.substring(pt + 2, pt + 4));
                } else {
                    ans.append(sb.substring(pt, pt + n));
                }
                break;
            }
        }
        return ans.toString();
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/7f618c83c21240bcbe3c2a86f197a677.png)

#### [777. 在LR字符串中交换相邻字符](https://leetcode.cn/problems/swap-adjacent-in-lr-string/)

在一个由 `'L'` , `'R'` 和 `'X'` 三个字符组成的字符串（例如`"RXXLRXRXL"`）中进行移动操作。一次移动操作指用一个`"LX"`替换一个`"XL"`，或者用一个`"XR"`替换一个`"RX"`。现给定起始字符串`start`和结束字符串`end`，请编写代码，当且仅当存在一系列移动操作使得`start`可以转换成`end`时， 返回`True`。

 

**示例 :**

```
输入: start = "RXXLRXRXL", end = "XRLXXRRLX"
输出: True
解释:
我们可以通过以下几步将start转换成end:
RXXLRXRXL ->
XRXLRXRXL ->
XRLXRXRXL ->
XRLXXRRXL ->
XRLXXRRLX
```

 

**提示：**

- `1 <= len(start) = len(end) <= 10000`。
- `start`和`end`中的字符串仅限于`'L'`, `'R'`和`'X'`。

**双指针**

```java
class Solution {
    public boolean canTransform(String start, String end) {
        int n = start.length();
        // 双指针
        int i = 0;
        int j = 0;
        while (i < n && j < n) {
            while (i < n && start.charAt(i) == 'X') {
                i++;
            }
            while (j < n && end.charAt(j) == 'X') {
                j++;
            }
            if (i < n && j < n) {
                if (start.charAt(i) != end.charAt(j)) {
                    return false;
                }
                char c = start.charAt(i);
                if ((c == 'L' && i < j) || (c == 'R' && i > j)) {
                    return false;
                }
                i++;
                j++;
            }
        }
        while (i < n) {
            if (start.charAt(i) != 'X') {
                return false;
            }
            i++;
        }
        while (j < n) {
            if (end.charAt(j) != 'X') {
                return false;
            }
            j++;
        }
        return true;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/3cb7bd2a696347d286f8aa0fe16fc2c5.png)

#### [1784. 检查二进制字符串字段](https://leetcode.cn/problems/check-if-binary-string-has-at-most-one-segment-of-ones/)

给你一个二进制字符串 `s` ，该字符串 **不含前导零** 。

如果 `s` 包含 **零个或一个由连续的 `'1'` 组成的字段** ，返回 `true` 。否则，返回 `false` 。

如果 `s` 中 **由连续若干个 `'1'` 组成的字段** 数量不超过 `1`，返回 `true` 。否则，返回 `false` 。

 

**示例 1：**

```
输入：s = "1001"
输出：false
解释：由连续若干个 '1' 组成的字段数量为 2，返回 false
```

**示例 2：**

```
输入：s = "110"
输出：true
```

 

**提示：**

- `1 <= s.length <= 100`
- `s[i]` 为 `'0'` 或 `'1'`
- `s[0]` 为 `'1'`

```java
class Solution {
    public boolean checkOnesSegment(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            char a = s.charAt(i);
            char b = s.charAt(i + 1);
            //直接记录是否出现0 然后检查出现0是否还有1出现有就直接false
            if (a == '0' && b == '1') {
                return false;
            }
        }
        return true;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/c8c0065a82404262af37dd4e02c41406.png)

#### [921. 使括号有效的最少添加](https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid/)

只有满足下面几点之一，括号字符串才是有效的：

- 它是一个空字符串，或者
- 它可以被写成 `AB` （`A` 与 `B` 连接）, 其中 `A` 和 `B` 都是有效字符串，或者
- 它可以被写作 `(A)`，其中 `A` 是有效字符串。

给定一个括号字符串 `s` ，移动N次，你就可以在字符串的任何位置插入一个括号。

- 例如，如果 `s = "()))"` ，你可以插入一个开始括号为 `"(()))"` 或结束括号为 `"())))"` 。

返回 *为使结果字符串 `s` 有效而必须添加的最少括号数*。

 

**示例 1：**

```
输入：s = "())"
输出：1
```

**示例 2：**

```
输入：s = "((("
输出：3
```

 

**提示：**

- `1 <= s.length <= 1000`
- `s` 只包含 `'('` 和 `')'` 字符。

```java
class Solution {
    public int minAddToMakeValid(String s) {
        int score = 0;
        int ans = 0;
        for (char c : s.toCharArray()) {
            score += c == '(' ? 1 : -1;
            if (score < 0) {
                score = 0;
                ans++;
            }
        }
        return ans + score;
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/6bc347d4197f42a8897f5675091ac58c.png)

```java
class Solution {
    public int minAddToMakeValid(String s) {
        ArrayDeque<Character> deque = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == ')' && !deque.isEmpty() && deque.peek() == '(') {
                deque.pop();
            } else {
                deque.push(c);
            }
        }
        return deque.size();
    }
}
```






![在这里插入图片描述](https://img-blog.csdnimg.cn/5cecc9022f264b979814e7570870f415.png)

#### [811. 子域名访问计数](https://leetcode.cn/problems/subdomain-visit-count/)

网站域名 `"discuss.leetcode.com"` 由多个子域名组成。顶级域名为 `"com"` ，二级域名为 `"leetcode.com"` ，最低一级为 `"discuss.leetcode.com"` 。当访问域名 `"discuss.leetcode.com"` 时，同时也会隐式访问其父域名 `"leetcode.com" `以及 `"com"` 。

**计数配对域名** 是遵循 `"rep d1.d2.d3"` 或 `"rep d1.d2"` 格式的一个域名表示，其中 `rep` 表示访问域名的次数，`d1.d2.d3` 为域名本身。

- 例如，`"9001 discuss.leetcode.com"` 就是一个 **计数配对域名** ，表示 `discuss.leetcode.com` 被访问了 `9001` 次。

给你一个 **计数配对域名** 组成的数组 `cpdomains` ，解析得到输入中每个子域名对应的 **计数配对域名** ，并以数组形式返回。可以按 **任意顺序** 返回答案。

 

**示例 1：**

```
输入：cpdomains = ["9001 discuss.leetcode.com"]
输出：["9001 leetcode.com","9001 discuss.leetcode.com","9001 com"]
解释：例子中仅包含一个网站域名："discuss.leetcode.com"。
按照前文描述，子域名 "leetcode.com" 和 "com" 都会被访问，所以它们都被访问了 9001 次。
```

**示例 2：**

```
输入：cpdomains = ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
输出：["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
解释：按照前文描述，会访问 "google.mail.com" 900 次，"yahoo.com" 50 次，"intel.mail.com" 1 次，"wiki.org" 5 次。
而对于父域名，会访问 "mail.com" 900 + 1 = 901 次，"com" 900 + 50 + 1 = 951 次，和 "org" 5 次。
```

 

**提示：**

- `1 <= cpdomain.length <= 100`
- `1 <= cpdomain[i].length <= 100`
- `cpdomain[i]` 会遵循 `"repi d1i.d2i.d3i"` 或 `"repi d1i.d2i"` 格式
- `repi` 是范围 `[1, 104]` 内的一个整数
- `d1i`、`d2i` 和 `d3i` 由小写英文字母组成

**哈希**

```java
class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        for (String cpdomain : cpdomains) {
            Integer count = Integer.valueOf(Arrays.asList(cpdomain.split(" ")).get(0));
            String[] split = Arrays.asList(cpdomain.split(" ")).get(1).split("\\.");
            for (int i = 0; i < split.length; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(split[i]);
                for (int j = i + 1; j < split.length; j++) {
                    sb.append(".").append(split[j]);
                }
                String s = sb.toString();
                map.put( s, map.getOrDefault( s, 0) + count);
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            list.add(String.valueOf(entry.getValue()) + " " + entry.getKey());
        }
        return list;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/544eb4c60dff47d382dbf35a9240fa56.png)

#### [927. 三等分](https://leetcode.cn/problems/three-equal-parts/)

给定一个由 `0` 和 `1` 组成的数组 `arr` ，将数组分成  **3 个非空的部分** ，使得所有这些部分表示相同的二进制值。

如果可以做到，请返回**任何** `[i, j]`，其中 `i+1 < j`，这样一来：

- `arr[0], arr[1], ..., arr[i]` 为第一部分；
- `arr[i + 1], arr[i + 2], ..., arr[j - 1]` 为第二部分；
- `arr[j], arr[j + 1], ..., arr[arr.length - 1]` 为第三部分。
- 这三个部分所表示的二进制值相等。

如果无法做到，就返回 `[-1, -1]`。

注意，在考虑每个部分所表示的二进制时，应当将其看作一个整体。例如，`[1,1,0]` 表示十进制中的 `6`，而不会是 `3`。此外，前导零也是**被允许**的，所以 `[0,1,1]` 和 `[1,1]` 表示相同的值。

 

**示例 1：**

```
输入：arr = [1,0,1,0,1]
输出：[0,3]
```

**示例 2：**

```
输入：arr = [1,1,0,1,1]
输出：[-1,-1]
```

**示例 3:**

```
输入：arr = [1,1,0,0,1]
输出：[0,2]
```

 

**提示：**

- `3 <= arr.length <= 3 * 104`
- `arr[i]` 是 `0` 或 `1`

**CV**

```java
class Solution {
    public int[] threeEqualParts(int[] arr) {
        int sum = Arrays.stream(arr).sum();
        if (sum % 3 != 0) {
            return new int[]{-1, -1};
        }
        if (sum == 0) {
            return new int[]{0, 2};
        }

        int partial = sum / 3;
        int first = 0, second = 0, third = 0, cur = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                if (cur == 0) {
                    first = i;
                } else if (cur == partial) {
                    second = i;
                } else if (cur == 2 * partial) {
                    third = i;
                }
                cur++;
            }
        }

        int len = arr.length - third;
        if (first + len <= second && second + len <= third) {
            int i = 0;
            while (third + i < arr.length) {
                if (arr[first + i] != arr[second + i] || arr[first + i] != arr[third + i]) {
                    return new int[]{-1, -1};
                }
                i++;
            }
            return new int[]{first + len - 1, second + len};
        }
        return new int[]{-1, -1};
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/2f56c6837695471b9f1d6512b0f12b2d.png)

#### [1800. 最大升序子数组和](https://leetcode.cn/problems/maximum-ascending-subarray-sum/)

给你一个正整数组成的数组 `nums` ，返回 `nums` 中一个 **升序** 子数组的最大可能元素和。

子数组是数组中的一个连续数字序列。

已知子数组 `[numsl, numsl+1, ..., numsr-1, numsr]` ，若对所有 `i`（`l <= i < r`），`numsi < numsi+1` 都成立，则称这一子数组为 **升序** 子数组。注意，大小为 `1` 的子数组也视作 **升序** 子数组。

 

**示例 1：**

```
输入：nums = [10,20,30,5,10,50]
输出：65
解释：[5,10,50] 是元素和最大的升序子数组，最大元素和为 65 。
```

**示例 2：**

```
输入：nums = [10,20,30,40,50]
输出：150
解释：[10,20,30,40,50] 是元素和最大的升序子数组，最大元素和为 150 。 
```

**示例 3：**

```
输入：nums = [12,17,15,13,10,11,12]
输出：33
解释：[10,11,12] 是元素和最大的升序子数组，最大元素和为 33 。 
```

**示例 4：**

```
输入：nums = [100,10,1]
输出：100
```

 

**提示：**

- `1 <= nums.length <= 100`
- `1 <= nums[i] <= 100`

**模拟**

```java
class Solution {
    public int maxAscendingSum(int[] nums) {
        int sum = 0;
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] > nums[i-1]) {
                temp += nums[i];
                sum = Math.max(sum, temp);
            } else {
                temp = nums[i];
            }
        }
        return sum;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/37378c95b9ee4e8ba415dadba997b06a.png)

#### [870. 优势洗牌](https://leetcode.cn/problems/advantage-shuffle/)

给定两个大小相等的数组 `nums1` 和 `nums2`，`nums1` 相对于 `nums` 的*优势*可以用满足 `nums1[i] > nums2[i]` 的索引 `i` 的数目来描述。

返回 nums1 的**任意**排列，使其相对于 `nums2` 的优势最大化。

 

**示例 1：**

```
输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
输出：[2,11,7,15]
```

**示例 2：**

```
输入：nums1 = [12,24,8,32], nums2 = [13,25,32,11]
输出：[24,32,8,12]
```

 

**提示：**

- `1 <= nums1.length <= 105`
- `nums2.length == nums1.length`
- `0 <= nums1[i], nums2[i] <= 109`



**贪心**

```java
class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Integer[] idx1 = new Integer[n];
        Integer[] idx2 = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx1[i] = i;
            idx2[i] = i;
        }
        Arrays.sort(idx1, (i, j) -> nums1[i] - nums1[j]);
        Arrays.sort(idx2, (i, j) -> nums2[i] - nums2[j]);
        int[] ans = new int[n];
        int left = 0;
        int right = n - 1;
        for (int i = 0; i < n; i++) {
            if (nums1[idx1[i]] > nums2[idx2[left]]) {
                ans[idx2[left]] = nums1[idx1[i]];
                ++left;
            } else {
                ans[idx2[right]] = nums1[idx1[i]];
                --right;
            }
        }
        return ans;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/fcf1384bbd924c0ca34aef45d6bffdad.png)

**TreeSet**

```java
class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        TreeSet<Integer> treeSet = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            if (map.get(i) == 1) {
                treeSet.add(i);
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            Integer cur = treeSet.ceiling(nums2[i] + 1);
            if (cur == null) {
                cur = treeSet.ceiling(-1);
            }
            ans[i] = cur;
            map.put(cur, map.get(cur) - 1);
            if (map.get(cur) == 0) {
                treeSet.remove(cur);
            }
        }
        return ans;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/6fa5a301ee5342ceb7de8651c9258196.png)

**参考链接  https://leetcode.cn/problems/advantage-shuffle/solution/-by-muse-77-ajqp/**

```java
class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
         // 索引位置
        Integer[] orderPos = new Integer[nums2.length];
        for (int i = 0; i < nums2.length; i++) {
            orderPos[i] = i;
        }
        Arrays.sort(orderPos, Comparator.comparingInt(i -> nums2[i]));
        Arrays.sort(nums1);
        int head = 0;
        int tail = nums1.length - 1;
        for (int i = orderPos.length-1; i >= 0; i--) {
            if (nums1[tail] > nums2[orderPos[i]]) {
                nums2[orderPos[i]] = nums1[tail--];
            } else {
                nums2[orderPos[i]] = nums1[head++];
            }
        }
        return nums2;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/5c544ec900b34168bb07115f18cd59e7.png)

#### [856. 括号的分数](https://leetcode.cn/problems/score-of-parentheses/)

给定一个平衡括号字符串 `S`，按下述规则计算该字符串的分数：

- `()` 得 1 分。
- `AB` 得 `A + B` 分，其中 A 和 B 是平衡括号字符串。
- `(A)` 得 `2 * A` 分，其中 A 是平衡括号字符串。

 

**示例 1：**

```
输入： "()"
输出： 1
```

**示例 2：**

```
输入： "(())"
输出： 2
```

**示例 3：**

```
输入： "()()"
输出： 2
```

**示例 4：**

```
输入： "(()(()))"
输出： 6
```

 

**提示：**

1. `S` 是平衡括号字符串，且只含有 `(` 和 `)` 。
2. `2 <= S.length <= 50`

**栈**

```java
class Solution {
    public int scoreOfParentheses(String s) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.push(0);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                deque.push(0);
            } else {
                Integer val = deque.pop();
                int top = deque.pop() + Math.max(2 * val, +1);
                deque.push(top);
            }
        }
        return deque.peek();
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/d9ffeab3f27e42dda6b8ccf422b1ded6.png)

**分治递归**

```java
class Solution {
    public int scoreOfParentheses(String s) {
        if (s.length() == 2) {
            return 1;
        }
        int balance = 0;
        int n = s.length();
        int len = 0;
        for (int i = 0; i < n; i++) {
            balance += (s.charAt(i) == '(' ? 1 : -1);
            if (balance == 0) {
                len = i + 1;
                break;
            }
        }
        if (len == n) {
            return 2 * scoreOfParentheses(s.substring(1, n - 1));
        } else {
            return scoreOfParentheses(s.substring(0, len)) + scoreOfParentheses(s.substring(len));
        }
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/0dd16746ee234e8faf0d8670bd8c6ee4.png)

#### [801. 使序列递增的最小交换次数](https://leetcode.cn/problems/minimum-swaps-to-make-sequences-increasing/)

我们有两个长度相等且不为空的整型数组 `nums1` 和 `nums2` 。在一次操作中，我们可以交换 `nums1[i]` 和 `nums2[i]`的元素。

- 例如，如果 `nums1 = [1,2,3,8]` ， `nums2 =[5,6,7,4]` ，你可以交换 `i = 3` 处的元素，得到 `nums1 =[1,2,3,4]` 和 `nums2 =[5,6,7,8]` 。

返回 *使 `nums1` 和 `nums2` **严格递增** 所需操作的最小次数* 。

数组 `arr` **严格递增** 且 `arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1]` 。

**注意：**

- 用例保证可以实现操作。

 

**示例 1:**

```
输入: nums1 = [1,3,5,4], nums2 = [1,2,3,7]
输出: 1
解释: 
交换 A[3] 和 B[3] 后，两个数组如下:
A = [1, 3, 5, 7] ， B = [1, 2, 3, 4]
两个数组均为严格递增的。
```

**示例 2:**

```
输入: nums1 = [0,3,5,8,9], nums2 = [2,1,4,6,9]
输出: 1
```

 

**提示:**

- `2 <= nums1.length <= 105`

- `nums2.length == nums1.length`

- `0 <= nums1[i], nums2[i] <= 2 * 105`

  ```java
  class Solution {
      public int minSwap(int[] nums1, int[] nums2) {
          int n = nums1.length;
          int a = 0, b = 1;
          for (int i = 1; i < n; i++) {
              int at = a, bt = b;
              a = b = n;
              if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1])  {
                  a = Math.min(a, at);
                  b = Math.min(b, bt + 1);
              }
              if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                  a = Math.min(a, bt);
                  b = Math.min(b, at + 1);
              }
          }
          return Math.min(a, b);
      }
  }
  ```

  

#### [1790. 仅执行一次字符串交换能否使两个字符串相等](https://leetcode.cn/problems/check-if-one-string-swap-can-make-strings-equal/)

给你长度相等的两个字符串 `s1` 和 `s2` 。一次 **字符串交换** 操作的步骤如下：选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。

如果对 **其中一个字符串** 执行 **最多一次字符串交换** 就可以使两个字符串相等，返回 `true` ；否则，返回 `false` 。

 

**示例 1：**

```
输入：s1 = "bank", s2 = "kanb"
输出：true
解释：例如，交换 s2 中的第一个和最后一个字符可以得到 "bank"
```

**示例 2：**

```
输入：s1 = "attack", s2 = "defend"
输出：false
解释：一次字符串交换无法使两个字符串相等
```

**示例 3：**

```
输入：s1 = "kelb", s2 = "kelb"
输出：true
解释：两个字符串已经相等，所以不需要进行字符串交换
```

**示例 4：**

```
输入：s1 = "abcd", s2 = "dcba"
输出：false
```

 

**提示：**

- `1 <= s1.length, s2.length <= 100`
- `s1.length == s2.length`
- `s1` 和 `s2` 仅由小写英文字母组成

```java
class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        char[] c1=new char[2];
        char[] c2=new char[2];
        int k=0;
        for(int i=0;i<s1.length();i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                if(k>1)return false;
                c1[k]=s1.charAt(i);
                c2[k]=s2.charAt(i);
                k++;
            }
        }
        return c1[0]==c2[1]&&c1[1]==c2[0];
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/4ccb56894ed242e79c980d2d3db7965f.png)

```java
class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        int n = s1.length();
        List<Integer> diff = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (diff.size() >= 2) {
                    return false;
                }
                diff.add(i);
            }
        }
        if (diff.isEmpty()) {
            return true;
        }
        if (diff.size() != 2) {
            return false;
        }
        return s1.charAt(diff.get(0)) == s2.charAt(diff.get(1)) && s1.charAt(diff.get(1)) == s2.charAt(diff.get(0));
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/1a0d68e3ac4e4524bdab799f3ff6bccd.png)
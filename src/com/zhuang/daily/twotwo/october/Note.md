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

#### [817. 链表组件](https://leetcode.cn/problems/linked-list-components/)

难度中等119

给定链表头结点 `head`，该链表上的每个结点都有一个 **唯一的整型值** 。同时给定列表 `nums`，该列表是上述链表中整型值的一个子集。

返回列表 `nums` 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 `nums` 中）构成的集合。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/07/22/lc-linkedlistcom1.jpg)

```
输入: head = [0,1,2,3], nums = [0,1,3]
输出: 2
解释: 链表中,0 和 1 是相连接的，且 nums 中不包含 2，所以 [0, 1] 是 nums 的一个组件，同理 [3] 也是一个组件，故返回 2。
```

**示例 2：**

 ![img](https://assets.leetcode.com/uploads/2021/07/22/lc-linkedlistcom2.jpg)

```
输入: head = [0,1,2,3,4], nums = [0,3,1,4]
输出: 2
解释: 链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。
```

 

**提示：**

- 链表中节点数为`n`
- `1 <= n <= 104`
- `0 <= Node.val < n`
- `Node.val` 中所有值 **不同**
- `1 <= nums.length <= n`
- `0 <= nums[i] < n`
- `nums` 中所有值 **不同**

**哈希**

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
    public int numComponents(ListNode head, int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        boolean inSet=false;
        int ans=0;
        while (head!=null){
            if (set.contains(head.val)){
                if (!inSet){
                    inSet=true;
                    ans++;
                }
            }else {
                inSet=false;
            }
            head=head.next;
        }
        return ans;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/2aae07c13cec4c9693c6219ebf4ab425.png)

**虚拟节点**

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
    public int numComponents(ListNode head, int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 0;
        ListNode cur = new ListNode(-1, head);
        while (head != null) {
            if (set.contains(head.val) && !set.contains(cur.val)) {
                ans++;
            }
            cur = head;
            head = head.next;
        }
        return ans;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/d7aab83651cf45b496aafd79eb10e3e3.png)

#### [769. 最多能完成排序的块](https://leetcode.cn/problems/max-chunks-to-make-sorted/)

给定一个长度为 `n` 的整数数组 `arr` ，它表示在 `[0, n - 1]` 范围内的整数的排列。

我们将 `arr` 分割成若干 **块** (即分区)，并对每个块单独排序。将它们连接起来后，使得连接的结果和按升序排序后的原数组相同。

返回数组能分成的最多块数量。

 

**示例 1:**

```
输入: arr = [4,3,2,1,0]
输出: 1
解释:
将数组分成2块或者更多块，都无法得到所需的结果。
例如，分成 [4, 3], [2, 1, 0] 的结果是 [3, 4, 0, 1, 2]，这不是有序的数组。
```

**示例 2:**

```
输入: arr = [1,0,2,3,4]
输出: 4
解释:
我们可以把它分成两块，例如 [1, 0], [2, 3, 4]。
然而，分成 [1, 0], [2], [3], [4] 可以得到最多的块数。
```

 

**提示:**

- `n == arr.length`
- `1 <= n <= 10`
- `0 <= arr[i] < n`
- `arr` 中每个元素都 **不同**

**贪心**

```java
class Solution {
    public int maxChunksToSorted(int[] arr) {
        int m = 0, res = 0;
        for (int i = 0; i < arr.length; i++) {
            m = Math.max(m, arr[i]);
            if (m == i) {
                res++;
            }
        }
        return res;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/cddf2f56f4b64bcfae2bc48819c8f150.png)

#### [940. 不同的子序列 II](https://leetcode.cn/problems/distinct-subsequences-ii/)

给定一个字符串 `s`，计算 `s` 的 **不同非空子序列** 的个数。因为结果可能很大，所以返回答案需要对 **`10^9 + 7` 取余** 。

字符串的 **子序列** 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。

- 例如，`"ace"` 是 `"***a***b***c***d***e***"` 的一个子序列，但 `"aec"` 不是。

 

**示例 1：**

```
输入：s = "abc"
输出：7
解释：7 个不同的子序列分别是 "a", "b", "c", "ab", "ac", "bc", 以及 "abc"。
```

**示例 2：**

```
输入：s = "aba"
输出：6
解释：6 个不同的子序列分别是 "a", "b", "ab", "ba", "aa" 以及 "aba"。
```

**示例 3：**

```
输入：s = "aaa"
输出：3
解释：3 个不同的子序列分别是 "a", "aa" 以及 "aaa"。
```

 

**提示：**

- `1 <= s.length <= 2000`
- `s` 仅由小写英文字母组成

**CV**

```Java
class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1000000007;
        int[] last = new int[26];
        Arrays.fill(last, -1);

        int n = s.length();
        int[] f = new int[n];
        Arrays.fill(f, 1);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 26; ++j) {
                if (last[j] != -1) {
                    f[i] = (f[i] + f[last[j]]) % MOD;
                }
            }
            last[s.charAt(i) - 'a'] = i;
        }

        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            if (last[i] != -1) {
                ans = (ans + f[last[i]]) % MOD;
            }
        }
        return ans;
    }
}
```

#### [1441. 用栈操作构建数组](https://leetcode.cn/problems/build-an-array-with-stack-operations/)

给你一个数组 `target` 和一个整数 `n`。每次迭代，需要从 `list = { 1 , 2 , 3 ..., n }` 中依次读取一个数字。

请使用下述操作来构建目标数组 `target` ：

- `"Push"`：从 `list` 中读取一个新元素， 并将其推入数组中。
- `"Pop"`：删除数组中的最后一个元素。
- 如果目标数组构建完成，就停止读取更多元素。

题目数据保证目标数组严格递增，并且只包含 `1` 到 `n` 之间的数字。

请返回构建目标数组所用的操作序列。如果存在多个可行方案，返回任一即可。

 

**示例 1：**

```
输入：target = [1,3], n = 3
输出：["Push","Push","Pop","Push"]
解释： 
读取 1 并自动推入数组 -> [1]
读取 2 并自动推入数组，然后删除它 -> [1]
读取 3 并自动推入数组 -> [1,3]
```

**示例 2：**

```
输入：target = [1,2,3], n = 3
输出：["Push","Push","Push"]
```

**示例 3：**

```
输入：target = [1,2], n = 4
输出：["Push","Push"]
解释：只需要读取前 2 个数字就可以停止。
```

 

**提示：**

- `1 <= target.length <= 100`
- `1 <= n <= 100`
- `1 <= target[i] <= n`
- `target` 严格递增

**模拟**

```java
class Solution {
    public List<String> buildArray(int[] target, int n) {
        ArrayList<String> list = new ArrayList<>();
        int index=1;
        for (int num : target) {
            while (num!=index){
                list.add("Push");
                list.add("Pop");
                index++;
            }
            list.add("Push");
            index++;
        }
        return list;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/8464281233e24c22a5b9ea32baeabdc1.png)

#### [886. 可能的二分法](https://leetcode.cn/problems/possible-bipartition/)

给定一组 `n` 人（编号为 `1, 2, ..., n`）， 我们想把每个人分进**任意**大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。

给定整数 `n` 和数组 `dislikes` ，其中 `dislikes[i] = [ai, bi]` ，表示不允许将编号为 `ai` 和 `bi`的人归入同一组。当可以用这种方法将所有人分进两组时，返回 `true`；否则返回 `false`。

 



**示例 1：**

```
输入：n = 4, dislikes = [[1,2],[1,3],[2,4]]
输出：true
解释：group1 [1,4], group2 [2,3]
```

**示例 2：**

```
输入：n = 3, dislikes = [[1,2],[1,3],[2,3]]
输出：false
```

**示例 3：**

```
输入：n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
输出：false
```

 

**提示：**

- `1 <= n <= 2000`
- `0 <= dislikes.length <= 104`
- `dislikes[i].length == 2`
- `1 <= dislikes[i][j] <= n`
- `ai < bi`
- `dislikes` 中每一组都 **不同**

**广度优先搜索**

```java
class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        int[] color = new int[n + 1];
        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i <= n; ++i) {
            g[i] = new ArrayList<Integer>();
        }
        for (int[] p : dislikes) {
            g[p[0]].add(p[1]);
            g[p[1]].add(p[0]);
        }
        for (int i = 1; i <= n; ++i) {
            if (color[i] == 0) {
                Queue<Integer> queue = new ArrayDeque<Integer>();
                queue.offer(i);
                color[i] = 1;
                while (!queue.isEmpty()) {
                    int t = queue.poll();
                    for (int next : g[t]) {
                        if (color[next] > 0 && color[next] == color[t]) {
                            return false;
                        }
                        if (color[next] == 0) {
                            color[next] = 3 ^ color[t];
                            queue.offer(next);
                        }
                    }
                }
            }
        }
        return true;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/3e7066038740484a93baf2ffdd6d4a2f.png)

#### [904. 水果成篮](https://leetcode.cn/problems/fruit-into-baskets/)

你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 `fruits` 表示，其中 `fruits[i]` 是第 `i` 棵树上的水果 **种类** 。

你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：

- 你只有 **两个** 篮子，并且每个篮子只能装 **单一类型** 的水果。每个篮子能够装的水果总量没有限制。
- 你可以选择任意一棵树开始采摘，你必须从 **每棵** 树（包括开始采摘的树）上 **恰好摘一个水果** 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
- 一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。

给你一个整数数组 `fruits` ，返回你可以收集的水果的 **最大** 数目。

 

**示例 1：**

```
输入：fruits = [1,2,1]
输出：3
解释：可以采摘全部 3 棵树。
```

**示例 2：**

```
输入：fruits = [0,1,2,2]
输出：3
解释：可以采摘 [1,2,2] 这三棵树。
如果从第一棵树开始采摘，则只能采摘 [0,1] 这两棵树。
```

**示例 3：**

```
输入：fruits = [1,2,3,2,2]
输出：4
解释：可以采摘 [2,3,2,2] 这四棵树。
如果从第一棵树开始采摘，则只能采摘 [1,2] 这两棵树。
```

**示例 4：**

```
输入：fruits = [3,3,3,1,2,1,1,2,3,3,4]
输出：5
解释：可以采摘 [1,2,1,1,2] 这五棵树。
```

 

**提示：**

- `1 <= fruits.length <= 105`
- `0 <= fruits[i] < fruits.length`

**哈希+滑动窗口**

```java
class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int ans = 0;
        for (int right = 0; right < n; ++right) {
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);
            while (map.size() > 2) {
                map.put(fruits[left], map.get(fruits[left])-1);
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/530a4c86ba31461f851f40adc8c2aca0.png)

#### [902. 最大为 N 的数字组合](https://leetcode.cn/problems/numbers-at-most-n-given-digit-set/)

难度困难182

给定一个按 **非递减顺序** 排列的数字数组 `digits` 。你可以用任意次数 `digits[i]` 来写的数字。例如，如果 `digits = ['1','3','5']`，我们可以写数字，如 `'13'`, `'551'`, 和 `'1351315'`。

返回 *可以生成的小于或等于给定整数 `n` 的正整数的个数* 。

 

**示例 1：**

```
输入：digits = ["1","3","5","7"], n = 100
输出：20
解释：
可写出的 20 个数字是：
1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
```

**示例 2：**

```
输入：digits = ["1","4","9"], n = 1000000000
输出：29523
解释：
我们可以写 3 个一位数字，9 个两位数字，27 个三位数字，
81 个四位数字，243 个五位数字，729 个六位数字，
2187 个七位数字，6561 个八位数字和 19683 个九位数字。
总共，可以使用D中的数字写出 29523 个整数。
```

**示例 3:**

```
输入：digits = ["7"], n = 8
输出：1
```

 

**提示：**

- `1 <= digits.length <= 9`
- `digits[i].length == 1`
- `digits[i]` 是从 `'1'` 到 `'9'` 的数
- `digits` 中的所有值都 **不同** 
- `digits` 按 **非递减顺序** 排列
- `1 <= n <= 109`

**CV**

```java
class Solution {
    public int atMostNGivenDigitSet(String[] digits, int n) {
        String s = Integer.toString(n);
        int m = digits.length, k = s.length();
        int[][] dp = new int[k + 1][2];
        dp[0][1] = 1;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < m; j++) {
                if (digits[j].charAt(0) == s.charAt(i - 1)) {
                    dp[i][1] = dp[i - 1][1];
                } else if (digits[j].charAt(0) < s.charAt(i - 1)) {
                    dp[i][0] += dp[i - 1][1];
                } else {
                    break;
                }
            }
            if (i > 1) {
                dp[i][0] += m + dp[i - 1][0] * m;
            }
        }
        return dp[k][0] + dp[k][1];
    }
}
```

#### [1700. 无法吃午餐的学生数量](https://leetcode.cn/problems/number-of-students-unable-to-eat-lunch/)

学校的自助午餐提供圆形和方形的三明治，分别用数字 `0` 和 `1` 表示。所有学生站在一个队列里，每个学生要么喜欢圆形的要么喜欢方形的。
餐厅里三明治的数量与学生的数量相同。所有三明治都放在一个 **栈** 里，每一轮：

- 如果队列最前面的学生 **喜欢** 栈顶的三明治，那么会 **拿走它** 并离开队列。
- 否则，这名学生会 **放弃这个三明治** 并回到队列的尾部。

这个过程会一直持续到队列里所有学生都不喜欢栈顶的三明治为止。

给你两个整数数组 `students` 和 `sandwiches` ，其中 `sandwiches[i]` 是栈里面第 `i` 个三明治的类型（`i = 0` 是栈的顶部）， `students[j]` 是初始队列里第 `j` 名学生对三明治的喜好（`j = 0` 是队列的最开始位置）。请你返回无法吃午餐的学生数量。

 

**示例 1：**

```
输入：students = [1,1,0,0], sandwiches = [0,1,0,1]
输出：0 
解释：
- 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [1,0,0,1]。
- 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [0,0,1,1]。
- 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [0,1,1]，三明治栈为 sandwiches = [1,0,1]。
- 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [1,1,0]。
- 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [1,0]，三明治栈为 sandwiches = [0,1]。
- 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [0,1]。
- 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [1]，三明治栈为 sandwiches = [1]。
- 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = []，三明治栈为 sandwiches = []。
所以所有学生都有三明治吃。
```

**示例 2：**

```
输入：students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
输出：3
```

 

**提示：**

- `1 <= students.length, sandwiches.length <= 100`
- `students.length == sandwiches.length`
- `sandwiches[i]` 要么是 `0` ，要么是 `1` 。
- `students[i]` 要么是 `0` ，要么是 `1` 。

**模拟**

```java
class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> queueStu = new LinkedList<>();
        Deque<Integer> stackSanwich = new ArrayDeque<>();

        for (int student : students) {
            queueStu.offer(student);
        }

        for (int sandwich : sandwiches) {
            stackSanwich.offer(sandwich);
        }
        int dislikeCount = 0;
        while (!queueStu.isEmpty() && !stackSanwich.isEmpty()) {
            if (dislikeCount == stackSanwich.size()) {
                break;
            }
            int tempStu = queueStu.poll();
            // 首个学生比较三明治的顶部
            if (tempStu == stackSanwich.peek()) {
                dislikeCount = 0;
                // 弹出
                stackSanwich.pop();
            } else {
                // 不同 加入到队列尾部
                queueStu.offer(tempStu);
                dislikeCount++;
            }
        }
        return stackSanwich.isEmpty() ? 0 : stackSanwich.size();
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/d0c634319dd347048814943cb6c45a13.png)

**计数**

```java
class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        // 统计0 和 1 数量
        int countZero = 0;
        int countOne = 0;
        for (int student : students) {
            if (student == 0) {
                countZero++;
            } else {
                countOne++;
            }
        }
        for (int sandwich : sandwiches) {
            if (sandwich == 0) {
                if (countZero == 0) {
                    return countOne;
                }
                countZero--;
            } else {
                if (countOne == 0) {
                    return countZero;
                }
                countOne--;
            }
        }
        return 0;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/e4bf9bf59de940f0b429a51d25e0edd8.png)

#### [779. 第K个语法符号](https://leetcode.cn/problems/k-th-symbol-in-grammar/)

我们构建了一个包含 `n` 行( **索引从 1 开始** )的表。首先在第一行我们写上一个 `0`。接下来的每一行，将前一行中的`0`替换为`01`，`1`替换为`10`。

- 例如，对于 `n = 3` ，第 `1` 行是 `0` ，第 `2` 行是 `01` ，第3行是 `0110` 。

给定行数 `n` 和序数 `k`，返回第 `n` 行中第 `k` 个字符。（ `k` **从索引 1 开始**）


**示例 1:**

```
输入: n = 1, k = 1
输出: 0
解释: 第一行：0
```

**示例 2:**

```
输入: n = 2, k = 1
输出: 0
解释: 
第一行: 0 
第二行: 01
```

**示例 3:**

```
输入: n = 2, k = 2
输出: 1
解释:
第一行: 0
第二行: 01
```

 

**提示:**

- `1 <= n <= 30`
- `1 <= k <= 2n - 1`

```java
class Solution {
    public int kthGrammar(int n, int k) {
        return Integer.bitCount(k-1)&1;
    }
}
```

#### [901. 股票价格跨度](https://leetcode.cn/problems/online-stock-span/)

难度中等199收藏分享切换为英文接收动态反馈

编写一个 `StockSpanner` 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。

今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。

例如，如果未来7天股票的价格是 `[100, 80, 60, 70, 60, 75, 85]`，那么股票跨度将是 `[1, 1, 1, 2, 1, 4, 6]`。

 

**示例：**

```
输入：["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
输出：[null,1,1,1,2,1,4,6]
解释：
首先，初始化 S = StockSpanner()，然后：
S.next(100) 被调用并返回 1，
S.next(80) 被调用并返回 1，
S.next(60) 被调用并返回 1，
S.next(70) 被调用并返回 2，
S.next(60) 被调用并返回 1，
S.next(75) 被调用并返回 4，
S.next(85) 被调用并返回 6。

注意 (例如) S.next(75) 返回 4，因为截至今天的最后 4 个价格
(包括今天的价格 75) 小于或等于今天的价格。
```

 

**提示：**

1. 调用 `StockSpanner.next(int price)` 时，将有 `1 <= price <= 10^5`。
2. 每个测试用例最多可以调用 `10000` 次 `StockSpanner.next`。
3. 在所有测试用例中，最多调用 `150000` 次 `StockSpanner.next`。
4. 此问题的总时间限制减少了 50%。

```java
class StockSpanner {

    List<Integer> list;
    public StockSpanner() {
        list=new ArrayList<>();
    }
    
    public int next(int price) {
        list.add(price);
        for(int i=list.size()-1;i>=0;i--){
            if(list.get(i)>price){
                return list.size()-i-1;
            }
        }
        return list.size();
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/6034bf9447944a07b4d62bb1edf8e3f9.png)

```java
class StockSpanner {
	Stack<Integer> days;
	Stack<Integer> prices;

	public StockSpanner() {
		days = new Stack<>();
		prices = new Stack<>();
	}

	public int next(int price) {
		// 每次pop出来的都是想左递增的连续天数
		int d = 1;// 表示就算只有今天算进去了的话，也有一天
		while (!prices.isEmpty() && prices.peek() <= price) {
			d += days.pop();
			prices.pop();
		}
		days.push(d);
		prices.push(price);
		return d;
	}
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
```

![](https://img-blog.csdnimg.cn/31b7c75de35240dfb3b038db566ba8b6.png)

#### [1235. 规划兼职工作](https://leetcode.cn/problems/maximum-profit-in-job-scheduling/)

你打算利用空闲时间来做兼职工作赚些零花钱。

这里有 `n` 份兼职工作，每份工作预计从 `startTime[i]` 开始到 `endTime[i]` 结束，报酬为 `profit[i]`。

给你一份兼职工作表，包含开始时间 `startTime`，结束时间 `endTime` 和预计报酬 `profit` 三个数组，请你计算并返回可以获得的最大报酬。

注意，时间上出现重叠的 2 份工作不能同时进行。

如果你选择的工作在时间 `X` 结束，那么你可以立刻进行在时间 `X` 开始的下一份工作。

 

**示例 1：**

**![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/10/19/sample1_1584.png)**

```
输入：startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
输出：120
解释：
我们选出第 1 份和第 4 份工作， 
时间范围是 [1-3]+[3-6]，共获得报酬 120 = 50 + 70。
```

**示例 2：**

**![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/10/19/sample22_1584.png)**

```
输入：startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
输出：150
解释：
我们选择第 1，4，5 份工作。 
共获得报酬 150 = 20 + 70 + 60。
```

**示例 3：**

**![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/10/19/sample3_1584.png)**

```
输入：startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
输出：6
```

 

**提示：**

- `1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4`
- `1 <= startTime[i] < endTime[i] <= 10^9`
- `1 <= profit[i] <= 10^4`

**CV**

```java
class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int k = binarySearch(jobs, i - 1, jobs[i - 1][0]);
            dp[i] = Math.max(dp[i - 1], dp[k] + jobs[i - 1][2]);
        }
        return dp[n];
    }

    public int binarySearch(int[][] jobs, int right, int target) {
        int left = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (jobs[mid][1] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

#### [1768. 交替合并字符串](https://leetcode.cn/problems/merge-strings-alternately/)

给你两个字符串 `word1` 和 `word2` 。请你从 `word1` 开始，通过交替添加字母来合并字符串。如果一个字符串比另一个字符串长，就将多出来的字母追加到合并后字符串的末尾。

返回 **合并后的字符串** 。

 

**示例 1：**

```
输入：word1 = "abc", word2 = "pqr"
输出："apbqcr"
解释：字符串合并情况如下所示：
word1：  a   b   c
word2：    p   q   r
合并后：  a p b q c r
```

**示例 2：**

```
输入：word1 = "ab", word2 = "pqrs"
输出："apbqrs"
解释：注意，word2 比 word1 长，"rs" 需要追加到合并后字符串的末尾。
word1：  a   b 
word2：    p   q   r   s
合并后：  a p b q   r   s
```

**示例 3：**

```
输入：word1 = "abcd", word2 = "pq"
输出："apbqcd"
解释：注意，word1 比 word2 长，"cd" 需要追加到合并后字符串的末尾。
word1：  a   b   c   d
word2：    p   q 
合并后：  a p b q c   d
```

 

**提示：**

- `1 <= word1.length, word2.length <= 100`
- `word1` 和 `word2` 由小写英文字母组成

```java
class Solution {
    public String mergeAlternately(String word1, String word2) {
        int m=word1.length();
        int n=word2.length();
        int i=0;
        int j=0;
        StringBuilder sb = new StringBuilder();
        while (i<m||j<n){
            if (i<m){
                sb.append(word1.charAt(i++));
            }
            if (j<n){
                sb.append(word2.charAt(j++));
            }
        }
        return sb.toString();
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/014c487f9a0f4cd8a57ebd47c4990e64.png)

#### [915. 分割数组](https://leetcode.cn/problems/partition-array-into-disjoint-intervals/)

给定一个数组 `nums` ，将其划分为两个连续子数组 `left` 和 `right`， 使得：

- `left` 中的每个元素都小于或等于 `right` 中的每个元素。
- `left` 和 `right` 都是非空的。
- `left` 的长度要尽可能小。

*在完成这样的分组后返回 `left` 的 **长度*** 。

用例可以保证存在这样的划分方法。

 

**示例 1：**

```
输入：nums = [5,0,3,8,6]
输出：3
解释：left = [5,0,3]，right = [8,6]
```

**示例 2：**

```
输入：nums = [1,1,1,0,6,12]
输出：4
解释：left = [1,1,1,0]，right = [6,12]
```

 

**提示：**

- `2 <= nums.length <= 105`
- `0 <= nums[i] <= 106`
- 可以保证至少有一种方法能够按题目所描述的那样对 `nums` 进行划分。

```java
class Solution {
    public int partitionDisjoint(int[] nums) {
        int n = nums.length;
        // 全局最大值
        int leftMax = nums[0];
        // 局部最大值
        int curMax = nums[0];
        int leftPos = 0;
        for (int i = 1; i < n - 1; i++) {
            curMax = Math.max(curMax, nums[i]);
            if (nums[i] < leftMax) {
                leftMax = curMax;
                leftPos = i;
            }
        }
        return leftPos + 1;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/3668fc7326ee4ab3a6f553be4464ce17.png)

```java
class Solution {
    public int partitionDisjoint(int[] nums) {
        int n = nums.length;
        int[] minRight = new int[n];
        minRight[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minRight[i] = Math.min(nums[i], minRight[i + 1]);
        }

        int maxLeft = 0;
        for (int i = 0; i < n - 1; i++) {
            maxLeft = Math.max(maxLeft, nums[i]);
            if (maxLeft <= minRight[i + 1]) {
                return i + 1;
            }
        }
        return n - 1;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/3bb25fc8e93f4aa1948805c73e594fae.png)

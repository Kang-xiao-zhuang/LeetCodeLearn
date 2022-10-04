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
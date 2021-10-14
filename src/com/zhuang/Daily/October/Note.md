#### [1436. 旅行终点站](https://leetcode-cn.com/problems/destination-city/)

给你一份旅游线路图，该线路图中的旅行线路用数组 `paths` 表示，其中 `paths[i] = [cityAi, cityBi]` 表示该线路将会从 `cityAi` 直接前往 `cityBi` 。请你找出这次旅行的终点站，即没有任何可以通往其他城市的线路的城市*。*

题目数据保证线路图会形成一条不存在循环的线路，因此恰有一个旅行终点站。

 

**示例 1：**

```
输入：paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
输出："Sao Paulo" 
解释：从 "London" 出发，最后抵达终点站 "Sao Paulo" 。本次旅行的路线是 "London" -> "New York" -> "Lima" -> "Sao Paulo" 。
```

**示例 2：**

```
输入：paths = [["B","C"],["D","B"],["C","A"]]
输出："A"
解释：所有可能的线路是：
"D" -> "B" -> "C" -> "A". 
"B" -> "C" -> "A". 
"C" -> "A". 
"A". 
显然，旅行终点站是 "A" 。
```

**示例 3：**

```
输入：paths = [["A","Z"]]
输出："Z"
```

 

**提示：**

- `1 <= paths.length <= 100`
- `paths[i].length == 2`
- `1 <= cityAi.length, cityBi.length <= 10`
- `cityAi != cityBi`
- 所有字符串均由大小写英文字母和空格字符组成。

**简单模拟**

```java
class Solution {
    public String destCity(List<List<String>> paths) {
        HashMap<String, String> map = new HashMap<>();
        for (List<String> path : paths) {
            map.put(path.get(0), path.get(1));
        }
        String res = paths.get(0).get(1);
        while (map.containsKey(res)) {
            res = map.get(res);
        }
        return res;
    }
}
```

#### [405. 数字转换为十六进制数](https://leetcode-cn.com/problems/convert-a-number-to-hexadecimal/)

给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 [补码运算](https://baike.baidu.com/item/补码/6854613?fr=aladdin) 方法。

**注意:**

1. 十六进制中所有字母(`a-f`)都必须是小写。
2. 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符`'0'`来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
3. 给定的数确保在32位有符号整数范围内。
4. **不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。**

**示例 1：**

```
输入:
26

输出:
"1a"
```

**示例 2：**

```
输入:
-1

输出:
"ffffffff"
```

**位运算**
```java
class Solution {
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 7; i >= 0; i--) {
            int value = (num >> (4 * i) & 0xf);
            if (sb.length() > 0 || value > 0) {
                char c = value < 10 ? (char) ('0' + value) : (char) ('a' + value - 10);
                sb.append(c);
            }
        }
        return sb.toString();
    }   
}
```

#### [166. 分数到小数](https://leetcode-cn.com/problems/fraction-to-recurring-decimal/)

给定两个整数，分别表示分数的分子 `numerator` 和分母 `denominator`，以 **字符串形式返回小数** 。

如果小数部分为循环小数，则将循环的部分括在括号内。

如果存在多个答案，只需返回 **任意一个** 。

对于所有给定的输入，**保证** 答案字符串的长度小于 `104` 。

 

**示例 1：**

```
输入：numerator = 1, denominator = 2
输出："0.5"
```

**示例 2：**

```
输入：numerator = 2, denominator = 1
输出："2"
```

**示例 3：**

```
输入：numerator = 2, denominator = 3
输出："0.(6)"
```

**示例 4：**

```
输入：numerator = 4, denominator = 333
输出："0.(012)"
```

**示例 5：**

```
输入：numerator = 1, denominator = 5
输出："0.2"
```

 

**提示：**

- `-231 <= numerator, denominator <= 231 - 1`
- `denominator != 0`

**长除法**

```java
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
      long n = numerator, d = denominator;
        if (n % d == 0) {
            return String.valueOf(n / d);
        }
        StringBuffer sb = new StringBuffer();
        // 负数情况
        if (n < 0 ^ d < 0) {
            sb.append('-');
        }
        // 取绝对值 整数部分
        n = Math.abs(n);
        d = Math.abs(d);
        long integerPart = n / d;
        sb.append(integerPart);
        sb.append('.');
        // 小数部分
        StringBuffer fractionPart = new StringBuffer();
        HashMap<Long, Integer> reminderIndexMap = new HashMap<>();
        long reminder = n % d;
        int index = 0;
        while (reminder != 0 && !reminderIndexMap.containsKey(reminder)) {
            reminderIndexMap.put(reminder, index);
            reminder *= 10;
            fractionPart.append(reminder / d);
            reminder %= d;
            index++;
        }
        // 有循环节点
        if (reminder != 0) {
            int insertIndex = reminderIndexMap.get(reminder);
            fractionPart.insert(insertIndex, '(');
            fractionPart.append(')');
        }
        sb.append(fractionPart.toString());
        return sb.toString();
    }
}
```

#### [482. 密钥格式化](https://leetcode-cn.com/problems/license-key-formatting/)

有一个密钥字符串 S ，只包含字母，数字以及 '-'（破折号）。其中， N 个 '-' 将字符串分成了 N+1 组。

给你一个数字 K，请你重新格式化字符串，使每个分组恰好包含 K 个字符。特别地，第一个分组包含的字符个数必须小于等于 K，但至少要包含 1 个字符。两个分组之间需要用 '-'（破折号）隔开，并且将所有的小写字母转换为大写字母。

给定非空字符串 S 和数字 K，按照上面描述的规则进行格式化。

 

**示例 1：**

```
输入：S = "5F3Z-2e-9-w", K = 4
输出："5F3Z-2E9W"
解释：字符串 S 被分成了两个部分，每部分 4 个字符；
     注意，两个额外的破折号需要删掉。
```

**示例 2：**

```
输入：S = "2-5g-3-J", K = 2
输出："2-5G-3J"
解释：字符串 S 被分成了 3 个部分，按照前面的规则描述，第一部分的字符可以少于给定的数量，其余部分皆为 2 个字符。
```

 

**提示:**

1. S 的长度可能很长，请按需分配大小。K 为正整数。
2. S 只包含字母数字（a-z，A-Z，0-9）以及破折号'-'
3. S 非空

**倒序遍历**

```java
class Solution {
    public String licenseKeyFormatting(String s, int k) {
      StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1, cnt = 0; i >= 0; i--) {
            if (s.charAt(i) != '-') {
                cnt++;
                sb.append(Character.toUpperCase(s.charAt(i)));
                // 每k个部分分割
                if (cnt % k == 0) {
                    sb.append("-");
                }
            }
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '-') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.reverse().toString();
    }
}
```

#### [284. 窥探迭代器](https://leetcode-cn.com/problems/peeking-iterator/)

请你设计一个迭代器，除了支持 `hasNext` 和 `next` 操作外，还支持 `peek` 操作。

实现 `PeekingIterator` 类：

- `PeekingIterator(int[] nums)` 使用指定整数数组 `nums` 初始化迭代器。
- `int next()` 返回数组中的下一个元素，并将指针移动到下个元素处。
- `bool hasNext()` 如果数组中存在下一个元素，返回 `true` ；否则，返回 `false` 。
- `int peek()` 返回数组中的下一个元素，但 **不** 移动指针。

 

**示例：**

```
输入：
["PeekingIterator", "next", "peek", "next", "next", "hasNext"]
[[[1, 2, 3]], [], [], [], [], []]
输出：
[null, 1, 2, 2, 3, false]

解释：
PeekingIterator peekingIterator = new PeekingIterator([1, 2, 3]); // [1,2,3]
peekingIterator.next();    // 返回 1 ，指针移动到下一个元素 [1,2,3]
peekingIterator.peek();    // 返回 2 ，指针未发生移动 [1,2,3]
peekingIterator.next();    // 返回 2 ，指针移动到下一个元素 [1,2,3]
peekingIterator.next();    // 返回 3 ，指针移动到下一个元素 [1,2,3]
peekingIterator.hasNext(); // 返回 False
```

 

**提示：**

- `1 <= nums.length <= 1000`
- `1 <= nums[i] <= 1000`
- 对 `next` 和 `peek` 的调用均有效
- `next`、`hasNext` 和 `peek `最多调用 `1000` 次

 

**进阶：**你将如何拓展你的设计？使之变得通用化，从而适应所有的类型，而不只是整数型？

**实现接口**

```java
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

class PeekingIterator implements Iterator<Integer> {
    private Iterator iterator;
    private Integer next;
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
      this.iterator = iterator;
      next=iterator.next();
	}
	
    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
      return next;
	}
	
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
      Integer res=next;
      next=iterator.hasNext() ? (Integer) iterator.next() : null;
      return res;
	}
	
	@Override
	public boolean hasNext() {
	    return next!= null;
	}
}
```

#### [414. 第三大的数](https://leetcode-cn.com/problems/third-maximum-number/)

给你一个非空数组，返回此数组中 **第三大的数** 。如果不存在，则返回数组中最大的数。

 

**示例 1：**

```
输入：[3, 2, 1]
输出：1
解释：第三大的数是 1 。
```

**示例 2：**

```
输入：[1, 2]
输出：2
解释：第三大的数不存在, 所以返回最大的数 2 。
```

**示例 3：**

```
输入：[2, 2, 3, 1]
输出：1
解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
```

 

**提示：**

- `1 <= nums.length <= 104`
- `-231 <= nums[i] <= 231 - 1`

 

**进阶：**你能设计一个时间复杂度 `O(n)` 的解决方案吗？

**有序集合**

```java
class Solution {
    public int thirdMax(int[] nums) {
       TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
            if (set.size() > 3) {
                set.remove(set.first());
            }
        }
        return set.size() == 3 ? set.first() : set.last();
    } 
}
```

**排序**

```java
class Solution {
    public int thirdMax(int[] nums) {
       HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        return list.size() < 3 ? list.get(list.size() - 1) : list.get(list.size() - 3);
    } 
}
```

**一次遍历**

```java
class Solution {
    public int thirdMax(int[] nums) {
      long a = Long.MIN_VALUE, b = Long.MIN_VALUE, c = Long.MIN_VALUE;
        for (long num : nums) {
            if (num > a) {
                c = b;
                b = a;
                a = num;
            } else if (a > num && num > b) {
                c = b;
                b = num;
            } else if (b > num && num > c) {
                c = num;
            }
        }
        return c == Long.MIN_VALUE ? (int) a : (int) c;
    }
}
```

#### [434. 字符串中的单词数](https://leetcode-cn.com/problems/number-of-segments-in-a-string/)


统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。

请注意，你可以假定字符串里不包括任何不可打印的字符。

**示例:**

```
输入: "Hello, my name is John"
输出: 5
解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
```

**普通模拟**

```java
class Solution {
    public int countSegments(String s) {
      int count = 0;
        for (String s1 : s.split(" ")) {
            if (!s1.equals("")) {
                count++;
            }
        }
        return count;
    }
}
```

```java
class Solution {
    public int countSegments(String s) {
      int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((i == 0 || s.charAt(i - 1) == ' ')&&s.charAt(i) != ' '){
                count++;
            }
        }
        return count;
    }
}
```

#### [187. 重复的DNA序列](https://leetcode-cn.com/problems/repeated-dna-sequences/)

所有 DNA 都由一系列缩写为 `'A'`，`'C'`，`'G'` 和 `'T'` 的核苷酸组成，例如：`"ACGAATTCCG"`。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。

编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 `s` 中出现次数超过一次。

 

**示例 1：**

```
输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
输出：["AAAAACCCCC","CCCCCAAAAA"]
```

**示例 2：**

```
输入：s = "AAAAAAAAAAAAA"
输出：["AAAAAAAAAA"]
```

 

**提示：**

- `0 <= s.length <= 105`
- `s[i]` 为 `'A'`、`'C'`、`'G'` 或 `'T'`

**暴力哈希**

```java
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
         ArrayList<String> res = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String str = s.substring(i, i + 10);
            map.put(str, map.getOrDefault(str, 0) + 1);
            if (map.get(str) == 2) {
                res.add(str);
            }
        }
        return res;
    }
}
```

#### [352. 将数据流变为多个不相交区间](https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals/)

**未完成**

 给你一个由非负整数 `a1, a2, ..., an` 组成的数据流输入，请你将到目前为止看到的数字总结为不相交的区间列表。

实现 `SummaryRanges` 类：

- `SummaryRanges()` 使用一个空数据流初始化对象。
- `void addNum(int val)` 向数据流中加入整数 `val` 。
- `int[][] getIntervals()` 以不相交区间 `[starti, endi]` 的列表形式返回对数据流中整数的总结。

 

**示例：**

```
输入：
["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
[[], [1], [], [3], [], [7], [], [2], [], [6], []]
输出：
[null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]

解释：
SummaryRanges summaryRanges = new SummaryRanges();
summaryRanges.addNum(1);      // arr = [1]
summaryRanges.getIntervals(); // 返回 [[1, 1]]
summaryRanges.addNum(3);      // arr = [1, 3]
summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
summaryRanges.addNum(7);      // arr = [1, 3, 7]
summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
```

 

**提示：**

- `0 <= val <= 104`
- 最多调用 `addNum` 和 `getIntervals` 方法 `3 * 104` 次

 

**进阶：**如果存在大量合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办?

**二分查找**

```java
class SummaryRanges {
    List<int[]> list = new ArrayList<>();
    public void addNum(int val) {
        int n = list.size();
        if (n == 0) {
            list.add(new int[]{val, val});
            return ;
        }
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (list.get(mid)[0] <= val) l = mid;
            else r = mid - 1;
        }
        int[] cur = list.get(r);
        if (cur[0] > val) {
            if (val + 1 == cur[0]) {
                cur[0] = val;
            } else {
                list.add(r, new int[]{val, val});
            }
            return ;
        }
        if (cur[0] <= val && val <= cur[1]) {
            // pass
        } else if (r == n - 1) {
            if (cur[1] + 1 == val) {
                cur[1] = val;
            } else {
                list.add(new int[]{val, val});
            }
        } else {
            int[] next = list.get(r + 1);
            if (cur[1] + 1 == val && val == next[0] - 1) {
                cur[1] = next[1];
                list.remove(r + 1);
            } else if (cur[1] + 1 == val) {
                cur[1] = val;
            } else if (next[0] - 1 == val) {
                next[0] = val;
            } else {
                list.add(r + 1, new int[]{val, val});
            }
        }
    }
    public int[][] getIntervals() {
        int n = list.size();
        int[][] ans = new int[n][2];
        for (int i = 0; i < n; i++) ans[i] = list.get(i);
        return ans;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */
```

#### [441. 排列硬币](https://leetcode-cn.com/problems/arranging-coins/)



你总共有 `n` 枚硬币，并计划将它们按阶梯状排列。对于一个由 `k` 行组成的阶梯，其第 `i` 行必须正好有 `i` 枚硬币。阶梯的最后一行 **可能** 是不完整的。

给你一个数字 `n` ，计算并返回可形成 **完整阶梯行** 的总行数。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/04/09/arrangecoins1-grid.jpg)

```
输入：n = 5
输出：2
解释：因为第三行不完整，所以返回 2 。
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2021/04/09/arrangecoins2-grid.jpg)

```
输入：n = 8
输出：3
解释：因为第四行不完整，所以返回 3 。
```

 

**提示：**

- `1 <= n <= 231 - 1`

**二分法**

```java
class Solution {
    public int arrangeCoins(int n) {
       long left = 1, right = n;
        while (left < right) {
            long mid = (left + right + 1) >> 1;
            if (mid * (mid + 1) / 2 <= n) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return (int) left;
    }
}
```

**迭代法**

```java
class Solution {
    public int arrangeCoins(int n) {
      int i = 1;
        while (n >= i) {
            n -= i;
            i++;
        }
        return i - 1;
    }
}
```

**数学法**

```java
class Solution {
    public int arrangeCoins(int n) {
       return (int) ((Math.sqrt((long) 8 * n + 1) - 1) / 2);
    }
}
```

#### [273. 整数转换英文表示](https://leetcode-cn.com/problems/integer-to-english-words/)

**未完成**

将非负整数 `num` 转换为其对应的英文表示。

 

**示例 1：**

```
输入：num = 123
输出："One Hundred Twenty Three"
```

**示例 2：**

```
输入：num = 12345
输出："Twelve Thousand Three Hundred Forty Five"
```

**示例 3：**

```
输入：num = 1234567
输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
```

**示例 4：**

```
输入：num = 1234567891
输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
```

 

**提示：**

- `0 <= num <= 231 - 1`

**迭代法**

```java
class Solution {
     String[] singles = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
     String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
     String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
     String[] thousands = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
         if (num == 0) {
            return "Zero";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 3, unit = 1000000000; i >= 0; i--, unit /= 1000) {
            int curNum = num / unit;
            if (curNum != 0) {
                num -= curNum * unit;
                sb.append(toEnglish(curNum)).append(thousands[i]).append(" ");
            }
        }
        return sb.toString().trim();
    }

    /**
     * @param num 非负整数
     * @return 指定字符串
     */
    public String toEnglish(int num) {
        StringBuffer cur = new StringBuffer();
        int hundred = num / 100;
        num %= 100;
        if (hundred != 0) {
            cur.append(singles[hundred]).append(" Hundred ");
        }
        int ten = num / 10;
        if (ten >= 2) {
            cur.append(tens[ten]).append(" ");
            num%=10;
        }
        if (num > 0 && num < 10) {
            cur.append(singles[num]).append(" ");
        } else if (num >= 10) {
            cur.append(teens[num - 10]).append(" ");
        }
        return cur.toString();
    }
}
```

**递归法**

```java
class Solution {
     String[] singles = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
     String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
     String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
     String[] thousands = {"", "Thousand", "Million", "Billion"};
     String numberToWords(int num) {
         if (num == 0) {
            return "Zero";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 3, unit = 1000000000; i >= 0; i--, unit /= 1000) {
            int curNum = num / unit;
            if (curNum != 0) {
                num -= curNum * unit;
                StringBuffer cur = new StringBuffer();
                recursion(cur, curNum);
                cur.append(thousands[i]).append(" ");
                sb.append(cur);
            }
        }
        return sb.toString().trim();
    }
    /**
     * 递归的方法
     *
     * @param cur StringBuffer
     * @param num 非负整数
     */
    private void recursion(StringBuffer cur, int num) {
        if (num == 0) {
            return;
        } else if (num < 10) {
            cur.append(singles[num]).append(" ");
        } else if (num < 20) {
            cur.append(teens[num - 10]).append(" ");
        } else if (num < 100) {
            cur.append(tens[num / 10]).append(" ");
            recursion(cur, num % 10);
        } else {
            cur.append(singles[num / 100]).append(" Hundred ");
            recursion(cur, num % 100);
        }
    }
}
```

#### [29. 两数相除](https://leetcode-cn.com/problems/divide-two-integers/)

**未完成**

给定两个整数，被除数 `dividend` 和除数 `divisor`。将两数相除，要求不使用乘法、除法和 mod 运算符。

返回被除数 `dividend` 除以除数 `divisor` 得到的商。

整数除法的结果应当截去（`truncate`）其小数部分，例如：`truncate(8.345) = 8` 以及 `truncate(-2.7335) = -2`

 

**示例 1:**

```
输入: dividend = 10, divisor = 3
输出: 3
解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
```

**示例 2:**

```
输入: dividend = 7, divisor = -3
输出: -2
解释: 7/-3 = truncate(-2.33333..) = -2
```

 

**提示：**

- 被除数和除数均为 32 位有符号整数。
- 除数不为 0。
- 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。

**二分查找**

```java
class Solution {
    public int divide(int dividend, int divisor) {
         // 考虑被除数为最小值的情况
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        // 考虑除数最小值的情况
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // 考虑被除数为0的情况
        if (dividend == 0) {
            return 0;
        }
        // 一般情况使用二分查找
        boolean rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = !rev;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }
        int left = 1, right = Integer.MAX_VALUE, ans = 0;
        while (left <= right) {
            // 注意溢出，并且不能使用除法
            int mid = left + ((right - left) >> 1);
            boolean check = quickAdd(divisor, mid, dividend);
            if (check) {
                ans = mid;
                // 注意溢出
                if (mid == Integer.MAX_VALUE) {
                    break;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return rev ? -ans : ans;
    }
    /**
     * @param y 数1
     * @param z 数2
     * @param x 数3
     * @return 布尔值
     */
    private boolean quickAdd(int y, int z, int x) {
        // x和y是负数,z是正数
        // 判断z*y>=x;
        int result = 0, add = y;
        while (z != 0) {
            if ((z & 1) != 0) {
                // 保证result + add >=x
                if (result < x - add) {
                    return false;
                }
                result += add;
            }
            if (z != 1) {
                // 保证 add+add>=x
                if (add < x - add) {
                    return false;
                }
                add += add;
            }
            // 不能使用除法
            z >>= 1;
        }
        return true;
    }
}
```

#### [412. Fizz Buzz](https://leetcode-cn.com/problems/fizz-buzz/)

写一个程序，输出从 1 到 *n* 数字的字符串表示。

1. 如果 *n* 是3的倍数，输出“Fizz”；

2. 如果 *n* 是5的倍数，输出“Buzz”；
3. 如果 *n* 同时是3和5的倍数，输出 “FizzBuzz”。

**示例：**

```
n = 15,

返回:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]
```

**简单模拟即可**

```java
class Solution {
    public List<String> fizzBuzz(int n) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            StringBuffer sb = new StringBuffer();
            if (i % 3 == 0) {
                sb.append("Fizz");
            }
            if (i % 5 == 0) {
                sb.append("Buzz");
            }
            if (sb.length() == 0) {
                sb.append(i);
            }
            list.add(sb.toString());
        }
        return list;
    }
}
```

#### [剑指 Offer II 069. 山峰数组的顶部](https://leetcode-cn.com/problems/B1IidL/)



符合下列属性的数组 `arr` 称为 **山峰数组**（**山脉数组）** ：

- `arr.length >= 3`

- 存在

   

  ```
  i
  ```

  （

  ```
  0 < i < arr.length - 1
  ```

  ）使得：

  - `arr[0] < arr[1] < ... arr[i-1] < arr[i]`
  - `arr[i] > arr[i+1] > ... > arr[arr.length - 1]`

给定由整数组成的山峰数组 `arr` ，返回任何满足 `arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1]` 的下标 `i` ，即山峰顶部。

 

**示例 1：**

```
输入：arr = [0,1,0]
输出：1
```

**示例 2：**

```
输入：arr = [1,3,5,4,2]
输出：2
```

**示例 3：**

```
输入：arr = [0,10,5,2]
输出：1
```

**示例 4：**

```
输入：arr = [3,4,5,1]
输出：2
```

**示例 5：**

```
输入：arr = [24,69,100,99,79,78,67,36,26,19]
输出：2
```

 

**提示：**

- `3 <= arr.length <= 104`
- `0 <= arr[i] <= 106`
- 题目数据保证 `arr` 是一个山脉数组

 

**进阶：**很容易想到时间复杂度 `O(n)` 的解决方案，你可以设计一个 `O(log(n))` 的解决方案吗？

**模拟**

```java
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int ans = -1;
        for (int i = 1; i < n - 1; ++i) {
            if (arr[i] > arr[i + 1]) {
                ans = i;
                break;
            }
        }
        return ans;
    }
}
```

**二分查找**

```java
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int left = 1, right = n - 2, ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > arr[mid + 1]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
```


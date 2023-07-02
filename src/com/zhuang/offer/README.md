#### [剑指 Offer 05. 替换空格](https://leetcode.cn/problems/ti-huan-kong-ge-lcof/)

请实现一个函数，把字符串 `s` 中的每个空格替换成"%20"。



**示例 1：**

```
输入：s = "We are happy."
输出："We%20are%20happy."
```



**限制：**

```
0 <= s 的长度 <= 10000
```

```java
class Solution {
   public String replaceSpace(String s) {
      return s.replace(" ", "%20");
   }
}
```
![](https://img-blog.csdnimg.cn/80e03d63bc784bd498b5f63518720839.png)

用StringBuilder也可以，直接替换

```java
class Solution {
   public String replaceSpace(String s) {
      StringBuilder sb = new StringBuilder();
      for (char c : s.toCharArray()) {
         if (c == ' ') {
            sb.append("%20");
         }else {
            sb.append(c);
         }
      }
      return sb.toString();
   }
}
```

![](https://img-blog.csdnimg.cn/3725cba2ea7c415f8f8fc874f6843553.png)

python代码

```python
class Solution:
    def replaceSpace(self, s: str) -> str:
        res = []
        for c in s:
            if c == ' ': res.append("%20")
            else: res.append(c)
        return "".join(res)
```

![](https://img-blog.csdnimg.cn/6a9fd77340ec4f8c856e60673a83dd7f.png)

koltin代码

```kotlin
class Solution {
   fun replaceSpace(s: String): String {
      return s.replace(" ", "%20")
   }
}
```

#### [剑指 Offer 58 - II. 左旋转字符串](https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/)



字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。



**示例 1：**

```
输入: s = "abcdefg", k = 2
输出: "cdefgab"
```

**示例 2：**

```
输入: s = "lrloseumgh", k = 6
输出: "umghlrlose"
```

```java
class Solution {
   public String reverseLeftWords(String s, int n) {
      if (n == 0) {
         return s;
      }
      return s.substring(n) + s.substring(0, n);
   }
}
```

![](https://img-blog.csdnimg.cn/3b64199ecd624c7ab459ad2b6778f456.png)

另一种方法，使用字符串拼接，先从n到末尾拼接，再拼接0到n的字符串

```java
class Solution {
   public String reverseLeftWords(String s, int n) {
      if (n == 0) {
         return s;
      }
      StringBuilder sb = new StringBuilder();
      for (int i = n; i < s.length(); i++) {
         sb.append(s.charAt(i));
      }
      for (int i = 0; i < n; i++) {
         sb.append(s.charAt(i));
      }
      return sb.toString();
   }
}
```

![](https://img-blog.csdnimg.cn/ab09e155be494106a94b0a307c6ce706.png)

#### [剑指 Offer 20. 表示数值的字符串](https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/)

请实现一个函数用来判断字符串是否表示**数值**（包括整数和小数）。

**数值**（按顺序）可以分成以下几个部分：

1. 若干空格
2. 一个 **小数** 或者 **整数**
3. （可选）一个 `'e'` 或 `'E'` ，后面跟着一个 **整数**
4. 若干空格

**小数**（按顺序）可以分成以下几个部分：

1. （可选）一个符号字符（`'+'` 或 `'-'`）
2. 下述格式之一：
   1. 至少一位数字，后面跟着一个点 `'.'`
   2. 至少一位数字，后面跟着一个点 `'.'` ，后面再跟着至少一位数字
   3. 一个点 `'.'` ，后面跟着至少一位数字

**整数**（按顺序）可以分成以下几个部分：

1. （可选）一个符号字符（`'+'` 或 `'-'`）
2. 至少一位数字

部分**数值**列举如下：

- `["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]`

部分**非数值**列举如下：

- `["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]`



**示例 1：**

```
输入：s = "0"
输出：true
```

**示例 2：**

```
输入：s = "e"
输出：false
```

**示例 3：**

```
输入：s = "."
输出：false
```

**示例 4：**

```
输入：s = "    .1  "
输出：true
```



**提示：**

- `1 <= s.length <= 20`
- `s` 仅含英文字母（大写和小写），数字（`0-9`），加号 `'+'` ，减号 `'-'` ，空格 `' '` 或者点 `'.'` 。



**解题**

有限状态机是一种计算模型，包含一系列状态：

- 有一种特殊的状态，被称作「初始状态」。
- 还有一系列状态被称为「接受状态」，它们组成了一个特殊的集合。其中，一个状态可能既是「初始状态」，也是「接受状态」。

起初，这个自动机处于「初始状态」。随后，它顺序地读取字符串中的每一个字符，并根据当前状态和读入的字符，按照某个事先约定好的「转移规则」，从当前状态转移到下一个状态；当状态转移完成后，它就读取下一个字符。当字符串全部读取完毕后，如果自动机处于某个「接受状态」，则判定该字符串「被接受」；否则，判定该字符串「被拒绝」。



注意：如果输入的过程中某一步转移失败了，即不存在对应的「转移规则」，此时计算将提前中止。在这种情况下我们也判定该字符串「被拒绝」。

一个自动机，总能够回答某种形式的「对于给定的输入字符串 S，判断其是否满足条件 P」的问题。在本题中，条件 P 即为「构成合法的表示数值的字符串」。

自动机驱动的编程，可以被看做一种暴力枚举方法的延伸：它穷尽了在任何一种情况下，对应任何的输入，需要做的事情。

**思路和算法**

根据上面的描述，现在可以定义自动机的「状态集合」了。那么怎么挖掘出所有可能的状态呢？一个常用的技巧是，用「当前处理到字符串的哪个部分」当作状态的表述。根据这一技巧，不难挖掘出所有状态：

> 起始的空格
> 符号位
> 整数部分
> 左侧有整数的小数点
> 左侧无整数的小数点（根据前面的第二条额外规则，需要对左侧有无整数的两种小数点做区分）
> 小数部分
> 字符 e
> 指数部分的符号位
> 指数部分的整数部分
> 末尾的空格

![](https://img-blog.csdnimg.cn/627cfb6ba4244e11b94d2dcd58de57a0.png)

```java
class Solution {
   public boolean isNumber(String s) {
      // 转移map
      Map<State, Map<CharType, State>> transfer = new HashMap<>();
      Map<CharType, State> initialMap = new HashMap<CharType, State>() {{
         // 起始的空格 指向  空格 符号位 整数部分 小数点（左无整数）
         put(CharType.CHAR_SPACE, State.STATE_INITIAL);
         put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
         put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
         put(CharType.CHAR_SIGN, State.STATE_INT_SIGN);
      }};
      transfer.put(State.STATE_INITIAL, initialMap);
      Map<CharType, State> intSignMap = new HashMap<CharType, State>() {{
         // 符号位 指向 整数部分 小数点（左无整数）
         put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
         put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
      }};
      transfer.put(State.STATE_INT_SIGN, intSignMap);
      Map<CharType, State> integerMap = new HashMap<CharType, State>() {{
         // 整数部分 指向 整数部分 小数点（左有整数） 字符e 末尾空格
         put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
         put(CharType.CHAR_EXP, State.STATE_EXP);
         put(CharType.CHAR_POINT, State.STATE_POINT);
         put(CharType.CHAR_SPACE, State.STATE_END);
      }};
      transfer.put(State.STATE_INTEGER, integerMap);
      Map<CharType, State> pointMap = new HashMap<CharType, State>() {{
         // 小数点（左有整数） 指向 小数部分 字符e 末尾空格
         put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
         put(CharType.CHAR_EXP, State.STATE_EXP);
         put(CharType.CHAR_SPACE, State.STATE_END);
      }};
      transfer.put(State.STATE_POINT, pointMap);
      Map<CharType, State> pointWithoutIntMap = new HashMap<CharType, State>() {{
         // 小数点（左无整数） 指向 小数点（左无整数）
         put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
      }};
      transfer.put(State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap);
      Map<CharType, State> fractionMap = new HashMap<CharType, State>() {{
         // 小数部分 指向 字符e 末尾空格 小数部分
         put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
         put(CharType.CHAR_EXP, State.STATE_EXP);
         put(CharType.CHAR_SPACE, State.STATE_END);
      }};
      transfer.put(State.STATE_FRACTION, fractionMap);
      Map<CharType, State> expMap = new HashMap<CharType, State>() {{
         // 字符e 指向 指数符号 指数数字
         put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
         put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
      }};
      transfer.put(State.STATE_EXP, expMap);
      Map<CharType, State> expSignMap = new HashMap<CharType, State>() {{
         // 指数符号 指向 指数数字
         put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
      }};
      transfer.put(State.STATE_EXP_SIGN, expSignMap);
      Map<CharType, State> expNumberMap = new HashMap<CharType, State>() {{
         // 指数数字 指向 末尾空格 指数数字
         put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
         put(CharType.CHAR_SPACE, State.STATE_END);
      }};
      transfer.put(State.STATE_EXP_NUMBER, expNumberMap);
      Map<CharType, State> endMap = new HashMap<CharType, State>() {{
         // 末尾空格 指向 末尾空格
         put(CharType.CHAR_SPACE, State.STATE_END);
      }};
      transfer.put(State.STATE_END, endMap);

      int length = s.length();
      State state = State.STATE_INITIAL;

      for (int i = 0; i < length; i++) {
         CharType type = toCharType(s.charAt(i));
         if (!transfer.get(state).containsKey(type)) {
            return false;
         } else {
            state = transfer.get(state).get(type);
         }
      }
      return state == State.STATE_INTEGER || state == State.STATE_POINT || state == State.STATE_FRACTION || state == State.STATE_EXP_NUMBER || state == State.STATE_END;
   }

   public CharType toCharType(char ch) {
      if (ch >= '0' && ch <= '9') {
         return CharType.CHAR_NUMBER;
      } else if (ch == 'e' || ch == 'E') {
         return CharType.CHAR_EXP;
      } else if (ch == '.') {
         return CharType.CHAR_POINT;
      } else if (ch == '+' || ch == '-') {
         return CharType.CHAR_SIGN;
      } else if (ch == ' ') {
         return CharType.CHAR_SPACE;
      } else {
         return CharType.CHAR_ILLEGAL;
      }
   }

   // 所有状态
   enum State {
      STATE_INITIAL,
      STATE_INT_SIGN,
      STATE_INTEGER,
      STATE_POINT,
      STATE_POINT_WITHOUT_INT,
      STATE_FRACTION,
      STATE_EXP,
      STATE_EXP_SIGN,
      STATE_EXP_NUMBER,
      STATE_END
   }

   // 数值字符串
   enum CharType {
      CHAR_NUMBER,
      CHAR_EXP,
      CHAR_POINT,
      CHAR_SIGN,
      CHAR_SPACE,
      CHAR_ILLEGAL
   }
}
```

![](https://img-blog.csdnimg.cn/4177d9ce54b846578c4e47619a7ccec7.png)

#### [剑指 Offer 67. 把字符串转换成整数](https://leetcode.cn/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/)

写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。



首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0。

**说明：**

假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231, 231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。

**示例 1:**

```
输入: "42"
输出: 42
```

**示例 2:**

```
输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
```

**示例 3:**

```
输入: "4193 with words"
输出: 4193
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
```

**示例 4:**

```
输入: "words and 987"
输出: 0
解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     因此无法执行有效的转换。
```

**示例 5:**

```
输入: "-91283472332"
输出: -2147483648
解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 
     因此返回 INT_MIN (−231) 。
```

**自动机**

以用下面的表格来表示这个自动机：

|               | ' '   | +/-    | number    | other |
| ------------- | ----- | ------ | --------- | ----- |
| **start**     | start | signed | in_number | end   |
| **signed**    | end   | end    | in_number | end   |
| **in_number** | end   | end    | in_number | end   |
| **end**       | end   | end    | end       | end   |

```java
class Solution {
   public int strToInt(String str) {
      Automaton automaton = new Automaton();
      int length = str.length();
      for (int i = 0; i < length; ++i) {
         automaton.get(str.charAt(i));
      }
      return (int) (automaton.sign * automaton.ans);
   }
}

class Automaton {
   public int sign = 1;
   public long ans = 0;
   private String state = "start";
   private Map<String, String[]> table = new HashMap<String, String[]>() {{
      put("start", new String[]{"start", "signed", "in_number", "end"});
      put("signed", new String[]{"end", "end", "in_number", "end"});
      put("in_number", new String[]{"end", "end", "in_number", "end"});
      put("end", new String[]{"end", "end", "end", "end"});
   }};

   public void get(char c) {
      state = table.get(state)[get_col(c)];
      if ("in_number".equals(state)) {
         ans = ans * 10 + c - '0';
         ans = sign == 1 ? Math.min(ans, (long) Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
      } else if ("signed".equals(state)) {
         sign = c == '+' ? 1 : -1;
      }
   }

   private int get_col(char c) {
      if (c == ' ') {
         return 0;
      }
      if (c == '+' || c == '-') {
         return 1;
      }
      if (Character.isDigit(c)) {
         return 2;
      }
      return 3;
   }
}
```

![](https://img-blog.csdnimg.cn/8a3f6bc7713c4cb9959223b9e72a9020.png)



#### [剑指 Offer 06. 从尾到头打印链表](https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/)

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。



**示例 1：**

```
输入：head = [1,3,2]
输出：[2,3,1]
```



**限制：**

```
0 <= 链表长度 <= 10000
```

使用集合加入节点的值，最后倒序遍历即可

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
   public int[] reversePrint(ListNode head) {
      ArrayList<Integer> list = new ArrayList<>();
      while (head != null) {
         list.add(head.val);
         head = head.next;
      }
      int[] res = new int[list.size()];
      for (int i = 0; i < list.size(); i++) {
         res[i] = list.get(list.size() - i - 1);
      }
      return res;
   }
}
```

![](https://img-blog.csdnimg.cn/646fda0b7e394ff9886b358a4a155f62.png)

#### [剑指 Offer 24. 反转链表](https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/)

定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。



**示例:**

```
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```



**限制：**

```
0 <= 节点个数 <= 5000
```

**双指针**

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
   public ListNode reverseList(ListNode head) {
      ListNode cur = head, pre = null;
      while(cur != null) {
         ListNode tmp = cur.next; // 暂存后继节点 cur.next
         cur.next = pre;          // 修改 next 引用指向
         pre = cur;               // pre 暂存 cur
         cur = tmp;               // cur 访问下一节点
      }
      return pre;
   }
}
```

![](https://img-blog.csdnimg.cn/156d6c7c18394bf6b5d06cd4cc2a9c41.png)

#### [剑指 Offer 35. 复杂链表的复制](https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/)

请实现 `copyRandomList` 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 `next` 指针指向下一个节点，还有一个 `random` 指针指向链表中的任意节点或者 `null`。



**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e1.png)

```
输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
```

**示例 2：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e2.png)

```
输入：head = [[1,1],[2,1]]
输出：[[1,1],[2,1]]
```

**示例 3：**

**![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e3.png)**

```
输入：head = [[3,null],[3,0],[3,null]]
输出：[[3,null],[3,0],[3,null]]
```

**示例 4：**

```
输入：head = []
输出：[]
解释：给定的链表为空（空指针），因此返回 null。
```



**提示：**

- `-10000 <= Node.val <= 10000`
- `Node.random` 为空（null）或指向链表中的节点。
- 节点数目不超过 1000 。

**哈希表**

```java
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
   public Node copyRandomList(Node head) {
      if (head == null) {
         return null;
      }
      Node cur = head;
      HashMap<Node, Node> map = new HashMap<>();
      // 复制各个节点
      while (cur != null) {
         map.put(cur, new Node(cur.val));
         cur = cur.next;
      }
      // 构建新的链表next和random指向
      cur = head;
      while (cur != null) {
         map.get(cur).next = map.get(cur.next);
         map.get(cur).random = map.get(cur.random);
         cur = cur.next;
      }
      // 返回新链表头节点
      return map.get(head);
   }
}
```

![](https://img-blog.csdnimg.cn/1d3bd6d7c8ef42d4b7738c618f4057e2.png)

#### [剑指 Offer 18. 删除链表的节点](https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/)

给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。

返回删除后的链表的头节点。

**注意：**此题对比原题有改动

**示例 1:**

```
输入: head = [4,5,1,9], val = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
```

**示例 2:**

```
输入: head = [4,5,1,9], val = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
```

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
   public ListNode deleteNode(ListNode head, int val) {
      if (head == null) return null;
      if (head.val == val) return head.next;
      ListNode cur = head;
      while (cur.next != null && cur.next.val != val) {
         cur = cur.next;
      }
      if (cur.next != null) cur.next = cur.next.next;
      return head;
   }
}
```

![](https://img-blog.csdnimg.cn/4c1ec23eb7c24c7eb9c5f1bb845e7cdc.png)

```java
class Solution {
   public ListNode deleteNode(ListNode head, int val) {
      if (head == null) return null;
      if (head.val == val) return head.next;
      ListNode pre = head, cur = head.next;
      while (cur != null && cur.val != val) {
         pre = cur;
         cur = cur.next;
      }
      if (cur != null) pre.next = cur.next;
      return head;
   }
}
```

![](https://img-blog.csdnimg.cn/e9b6b612eb9d4f009eec0a091843ac4b.png)



#### [剑指 Offer 57. 和为s的两个数字](https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/)

输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。



**示例 1：**

```
输入：nums = [2,7,11,15], target = 9
输出：[2,7] 或者 [7,2]
```

**示例 2：**

```
输入：nums = [10,26,30,31,47,60], target = 40
输出：[10,30] 或者 [30,10]
```



**限制：**

- `1 <= nums.length <= 10^5`
- `1 <= nums[i] <= 10^6`

**双指针**

```java
class Solution {
   public int[] twoSum(int[] nums, int target) {
      // 双指针
      int i = 0, j = nums.length - 1;
      while (i < j) {
         if (nums[i] == target - nums[j]) {
            return new int[]{nums[i], nums[j]};
         }
         if (nums[i] + nums[j] < target) {
            i++;
         } else if (nums[i] + nums[j] > target) {
            j--;
         }
      }
      return new int[0];
   }
}
```

![](https://img-blog.csdnimg.cn/dfe5eaca327241228206413afb36c691.png)



#### [剑指 Offer 21. 调整数组顺序使奇数位于偶数前面](https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/)

输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。



**示例：**

```
输入：nums = [1,2,3,4]
输出：[1,3,2,4] 
注：[3,1,2,4] 也是正确的答案之一。
```



**提示：**

1. `0 <= nums.length <= 50000`
2. `0 <= nums[i] <= 10000`

**双重遍历**

```java
class Solution {
   public int[] exchange(int[] nums) {
      // 双重遍历
      int n = nums.length;
      int[] res = new int[n];
      int index = 0;
      for (int num : nums) {
         if (num % 2 == 1) {
            res[index++] = num;
         }
      }
      for (int num : nums) {
         if (num % 2 == 0) {
            res[index++] = num;
         }
      }
      return res;
   }
}
```

![](https://img-blog.csdnimg.cn/b719f2cd8537468e936549146909a76d.png)

**双指针交换**

```java
class Solution {
   public int[] exchange(int[] nums) {
      // 双指针交换
      int left = 0, right = nums.length - 1;
      int n = nums.length;
      int[] res = new int[n];
      for (int num : nums) {
         if (num % 2 == 1) {
            res[left++] = num;
         } else {
            res[right--] = num;
         }
      }
      return res;
   }
}
```

![](https://img-blog.csdnimg.cn/a279e5c269de4146ad836475529398ce.png)





#### [剑指 Offer 58 - I. 翻转单词顺序](https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/)

输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。



**示例 1：**

```
输入: "the sky is blue"
输出: "blue is sky the"
```

**示例 2：**

```
输入: "  hello world!  "
输出: "world! hello"
解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
```

**示例 3：**

```
输入: "a good   example"
输出: "example good a"
解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
```



**说明：**

- 无空格字符构成一个单词。
- 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
- 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。

**注意：**本题与主站 151 题相同：https://leetcode-cn.com/problems/reverse-words-in-a-string/

**注意：**此题对比原题有改动



**逆序遍历**

```java
class Solution {
   public String reverseWords(String s) {
      // 除去开头和末尾的空白字符
      s = s.trim();
      // 正则表达式
      String[] split = s.split("\\s+");
      List<String> list = Arrays.asList(split);
      String res = "";
      for (int i = list.size() - 1; i >= 0; i--) {
         res += list.get(i);
         res += " ";
      }
      return res.trim();
   }
}
```

![](https://img-blog.csdnimg.cn/535954891eca43d8b4497941ca4d154a.png)

**拼接**

```java
class Solution {
   public String reverseWords(String s) {
      // 除去开头和末尾的空白字符 分割字符串
      String[] split = s.trim().split(" ");
      StringBuilder sb = new StringBuilder();
      for (int i = split.length - 1; i >= 0; i--) {
         if (split[i].equals("")) continue;
         sb.append(split[i] + " ");
      }
      return sb.toString().trim();
   }
}
```

![](https://img-blog.csdnimg.cn/1fb6ba4cb39d485cb60d49592a98765d.png)

#### [剑指 Offer 52. 两个链表的第一个公共节点](https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/)

输入两个链表，找出它们的第一个公共节点。

如下面的两个链表**：**

[![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png)](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png)

在节点 c1 开始相交。



**示例 1：**

[![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_1.png)](https://assets.leetcode.com/uploads/2018/12/13/160_example_1.png)

```
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
```



**示例 2：**

[![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_2.png)](https://assets.leetcode.com/uploads/2018/12/13/160_example_2.png)

```
输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Reference of the node with value = 2
输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
```



**示例 3：**

[![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_3.png)](https://assets.leetcode.com/uploads/2018/12/13/160_example_3.png)

```
输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
解释：这两个链表不相交，因此返回 null。
```



**注意：**

- 如果两个链表没有交点，返回 `null`.
- 在返回结果后，两个链表仍须保持原有的结构。
- 可假定整个链表结构中没有循环。
- 程序尽量满足 O(*n*) 时间复杂度，且仅用 O(*1*) 内存。
- 本题与主站 160 题相同：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
class Solution {
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<ListNode>();
		ListNode temp = headA;
		while (temp != null) {
			visited.add(temp);
			temp = temp.next;
		}
		temp = headB;
		while (temp != null) {
			if (visited.contains(temp)) {
				return temp;
			}
			temp = temp.next;
		}
		return null;
    }
}
```

![](https://img-blog.csdnimg.cn/a4623351f85549dcab4f6031a578ea35.png)

#### [剑指 Offer 25. 合并两个排序的链表](https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/)

输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。

**示例1：**

```
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
```

**限制：**

```
0 <= 链表长度 <= 1000
```

注意：本题与主站 21 题相同：https://leetcode-cn.com/problems/merge-two-sorted-lists/

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
      if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
```


# 已完成

[165. 比较版本号](https://leetcode-cn.com/problems/compare-version-numbers/)

[![hwDRJO.png](https://z3.ax1x.com/2021/09/01/hwDRJO.png)](https://imgtu.com/i/hwDRJO)

**分割字符串**

```java
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        for (int i = 0; i < v1.length || i < v2.length; i++) {
            int a = 0, b = 0;
            if (i < v1.length) {
                a = Integer.parseInt(v1[i]);
            }
            if (i < v2.length) {
                b = Integer.parseInt(v2[i]);
            }
            if (a > b) {
                return 1;
            }
            if (a < b) {
                return -1;
            }
        }
        return 0;
    }
}
```

**双指针法**

```java
class Solution {
    public int compareVersion(String version1, String version2) {
        int n = version1.length(), m = version2.length();
        for (int i = 0, j = 0; i < m || j < n; i++, j++) {
            int a = 0, b = 0;
            while (i < n && version1.charAt(i) != '.') {
                a = 10 * a + (version1.charAt(i++) - '0');
            }
            while (j < m && version2.charAt(j) != '.') {
                b = 10 * b + (version2.charAt(j++) - '0');
            }
            if (a != b) {
                return a > b ? 1 : -1;
            }
        }
        return 0;
    }
}
```

[剑指 Offer 22. 链表中倒数第k个节点](https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/)

[![hD8eh9.png](https://z3.ax1x.com/2021/09/02/hD8eh9.png)](https://imgtu.com/i/hD8eh9)

**顺序查找法**

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
    public ListNode getKthFromEnd(ListNode head, int k) {
        int n = 0;
        ListNode node = null;

        for (node = head; node != null; node = node.next) {
            n++;
        }
        for (node = head; n > k; n--) {
            node = node.next;
        }
        return node;
    }
}
```

**快慢指针法**

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
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && k > 0) {
            fast = fast.next;
            k--;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
```

**单指针法**

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
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        while (fast != null) {
            fast = fast.next;
            if (k == 0) {
                head = head.next;
            } else {
                k--;
            }
        }
        return head;
    }
}
```

[面试题 17.14. 最小K个数](https://leetcode-cn.com/problems/smallest-k-lcci/)

[![hyd18f.png](https://z3.ax1x.com/2021/09/03/hyd18f.png)](https://imgtu.com/i/hyd18f)

**API法**

```java
class Solution {
    public int[] smallestK(int[] arr, int k) {
         Arrays.sort(arr);
         return Arrays.copyOfRange(arr, 0, k);
    }
}
```

**优先队列**

```java
class Solution {
    public int[] smallestK(int[] arr, int k) {
         // 利用优先队列的特性，先输出小的值
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i : arr) {
            queue.offer(i);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }
        return res;
    }
}
```

[剑指 Offer 10- I. 斐波那契数列 ](https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/)

[![hchIlF.png](https://z3.ax1x.com/2021/09/04/hchIlF.png)](https://imgtu.com/i/hchIlF)

**两个动态规划方法，快速矩阵幂后续补上**

```java
class Solution {
    public int fib(int n) {
      int a = 0, b = 1, sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }
}
```

```java
class Solution {
    public int fib(int n) {
      int mod = (int) 1e9 + 7;
        if (n < 2) {
            return n;
        }
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            // 滚动数组
            int c = a + b;
            c %= mod;
            a = b;
            b = c;
        }
        return b;
    }
}
```

[704. 二分查找](https://leetcode-cn.com/problems/binary-search/)

[![hfDvz4.png](https://z3.ax1x.com/2021/09/06/hfDvz4.png)](https://imgtu.com/i/hfDvz4)

```java
class Solution {
    public int search(int[] nums, int target) {
         // 定义左指针
        int left = 0;
        // 定义右指针
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // 未找到值
        return -1;
    }
}
```

[1221. 分割平衡字符串](https://leetcode-cn.com/problems/split-a-string-in-balanced-strings/)

[![h547VK.png](https://z3.ax1x.com/2021/09/07/h547VK.png)](https://imgtu.com/i/h547VK)

**计数法**
```java
class Solution {
    public int balancedStringSplit(String s) {
        int res = 0, diff = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                diff++;
            } else {
                diff--;
            }
            if (diff == 0) {
                res++;
            }
        }
        return res;
    }
}
```
**栈方法**
```java
class Solution {
    public int balancedStringSplit(String s) {
        int res = 0;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty() && stack.peek() != s.charAt(i)) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
            if (stack.isEmpty()) {
                res++;
            }
        }
        return res;
    }
}
```
[1894. 找到需要补充粉笔的学生编号](https://leetcode-cn.com/problems/find-the-student-that-will-replace-the-chalk/)

[![hXkOhj.png](https://z3.ax1x.com/2021/09/10/hXkOhj.png)](https://imgtu.com/i/hXkOhj)

**模拟**

```java
class Solution {
    public int chalkReplacer(int[] chalk, int k) {
        int len = chalk.length;
        long sum = 0;
        for (int num : chalk) {
            // 计算数组中的粉笔总和
            sum += num;
        }
        // 对k取模
        k %= sum;
        int res = -1;
        for (int i = 0; i < len; i++) {
            if (chalk[i] > k) {
                res = i;
                break;
            }
            k -= chalk[i];
        }
        return res;
    }
}
```

[678. 有效的括号字符串](https://leetcode-cn.com/problems/valid-parenthesis-string/)

[![4S4hv9.png](https://z3.ax1x.com/2021/09/12/4S4hv9.png)](https://imgtu.com/i/4S4hv9)



**栈方法**

```java
class Solution {
    public boolean checkValidString(String s) {
      Deque<Integer> leftStack = new LinkedList<>();
        Deque<Integer> startStack = new LinkedList<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftStack.push(i);
            } else if (c == '*') {
                startStack.push(i);
            } else {
                if (!leftStack.isEmpty()) {
                    leftStack.pop();
                } else if (!startStack.isEmpty()) {
                    startStack.pop();
                } else {
                    return false;
                }
            }
        }
            while (!leftStack.isEmpty() && !startStack.isEmpty()) {
                int leftIndex = leftStack.pop();
                int startIndex = startStack.pop();
                if (leftIndex > startIndex) {
                    return false;
                }
            }
        return leftStack.isEmpty();
    }
}
```

**贪心方法**

```java
class Solution {
    public boolean checkValidString(String s) {
     // 定义最小值和最大值变量
        int minCount = 0;
        int maxCount = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            // 如果遇到左括号，则最小值最大值分别加1
            if (c == '(') {
                minCount++;
                maxCount++;
                // 如果遇到右括号，则最大值和最小值分别减1
            } else if (c == ')') {
                minCount--;
                minCount = Math.max(minCount, 0);
                maxCount--;
                // 如果maxCount为负 说明不匹配 返回false
                if (maxCount < 0) {
                    return false;
                }
            } else {
                // 遇到*
                minCount--;
                minCount = Math.max(minCount, 0);
                maxCount++;
            }
        }
        return minCount == 0;
      }
}
```



[447. 回旋镖的数量](https://leetcode-cn.com/problems/number-of-boomerangs/)

[![4CVfbQ.png](https://z3.ax1x.com/2021/09/13/4CVfbQ.png)](https://imgtu.com/i/4CVfbQ)

**哈希表**

```java
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                // 横坐标的差值
                int dx = points[i][0] - points[j][0];
                // 纵坐标的差值
                int dy = points[i][1] - points[j][1];
                int dis = dx * dx + dy * dy;
                map.put(dis, map.getOrDefault(dis, 0) + 1);
            }
            for (int val : map.values()) {
                res += val * (val - 1);
            }
        }
        return res;
    }
}
```

[524. 通过删除字母匹配到字典里最长单词](https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/)

[![4FcwfU.png](https://z3.ax1x.com/2021/09/14/4FcwfU.png)](https://imgtu.com/i/4FcwfU)

**双指针**

```java
class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        String res = "";
        for (String str : dictionary) {
            int i = 0, j = 0;
            while (i < str.length() && j < s.length()) {
                if (str.charAt(i) == s.charAt(j)) {
                    i++;
                }
                j++;
            }
            if (i == str.length()) {
                /*
                int compareTo(String anotherString)
                如果参数字符串等于此字符串，则返回值 0；
                如果此字符串小于字符串参数，则返回一个小于 0 的值；
                如果此字符串大于字符串参数，则返回一个大于 0 的值。
                 */
                if (str.length() > res.length() || (str.length() == res.length() && str.compareTo(res) < 0)) {
                    res = str;
                }
            }
        }
        return res;
    }
}
```

**排序+双指针**

```java
class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
         Collections.sort(dictionary, (a, b) -> {
            if (a.length() != b.length()) {
                return b.length() - a.length();
            }
            return a.compareTo(b);
        });
        int n = s.length();
        for (String str : dictionary) {
            int i = 0, j = 0;
            while (i < str.length() && j < s.length()) {
                if (str.charAt(i) == s.charAt(j)) {
                    i++;
                }
                j++;
            }
            if (i == str.length()) {
                return str;
            }
        }
        return "";
    }
}
```

#### [36. 有效的数独](https://leetcode-cn.com/problems/valid-sudoku/)

请你判断一个 `9x9` 的数独是否有效。只需要 **根据以下规则** ，验证已经填入的数字是否有效即可。

1. 数字 `1-9` 在每一行只能出现一次。
2. 数字 `1-9` 在每一列只能出现一次。
3. 数字 `1-9` 在每一个以粗实线分隔的 `3x3` 宫内只能出现一次。（请参考示例图）

数独部分空格内已填入了数字，空白格用 `'.'` 表示。

**注意：**

- 一个有效的数独（部分已被填充）不一定是可解的。
- 只需要根据以上规则，验证已经填入的数字是否有效即可。

 

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/04/12/250px-sudoku-by-l2g-20050714svg.png)

```
输入：board = 
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
输出：true
```

**示例 2：**

```
输入：board = 
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
输出：false
解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
```

 

**提示：**

- `board.length == 9`
- `board[i].length == 9`
- `board[i][j]` 是一位数字或者 `'.'`

**模拟**

```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
         // 定义三个二维数组
        int[][] rows = new int[9][9];
        int[][] cols = new int[9][9];
        int[][] boxes = new int[9][9];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int index_box = (i / 3) * 3 + j / 3;
                    if (rows[i][num] == 1) {
                        return false;
                    } else {
                        rows[i][num] = 1;
                    }
                    if (cols[j][num] == 1) {
                        return false;
                    } else {
                        cols[j][num] = 1;
                    }
                    if (boxes[index_box][num] == 1) {
                        return false;
                    } else {
                        boxes[index_box][num] = 1;
                    }
                }
            }
        }
        return true;
    }
}
```

**哈希表**

```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashMap<Integer, Set<Integer>> rows = new HashMap<>();
        HashMap<Integer, Set<Integer>> cols = new HashMap<>();
        HashMap<Integer, Set<Integer>> boxes = new HashMap<>();

        for (int i = 0; i < 9; i++) {
            rows.put(i, new HashSet<>());
            cols.put(i, new HashSet<>());
            boxes.put(i, new HashSet<>());
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int u = c - '0';
                int index = (i / 3) * 3 + j / 3;
                if (rows.get(i).contains(u) || cols.get(j).contains(u) || boxes.get(index).contains(u)) {
                    return false;
                }
                rows.get(i).add(u);
                cols.get(j).add(u);
                boxes.get(index).add(u);
            }
        }
        return true;
    }
}
```

#### [292. Nim 游戏](https://leetcode-cn.com/problems/nim-game/)

你和你的朋友，两个人一起玩 [Nim 游戏](https://baike.baidu.com/item/Nim游戏/6737105)：

- 桌子上有一堆石头。
- 你们轮流进行自己的回合，你作为先手。
- 每一回合，轮到的人拿掉 1 - 3 块石头。
- 拿掉最后一块石头的人就是获胜者。

假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 `n` 的情况下赢得游戏。如果可以赢，返回 `true`；否则，返回 `false` 。

 

**示例 1：**

```
输入：n = 4
输出：false 
解释：如果堆中有 4 块石头，那么你永远不会赢得比赛；
     因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
```

**示例 2：**

```
输入：n = 1
输出：true
```

**示例 3：**

```
输入：n = 2
输出：true
```

 

**提示：**

- `1 <= n <= 231 - 1`

**数学推理**

```java
class Solution {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
```

**提示：**

- `1 <= n <= 1000`

#### [58. 最后一个单词的长度](https://leetcode-cn.com/problems/length-of-last-word/)

给你一个字符串 `s`，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。

**单词** 是指仅由字母组成、不包含任何空格字符的最大子字符串。

 

**示例 1：**

```
输入：s = "Hello World"
输出：5
```

**示例 2：**

```
输入：s = "   fly me   to   the moon  "
输出：4
```

**示例 3：**

```
输入：s = "luffy is still joyboy"
输出：6
```

 

**提示：**

- `1 <= s.length <= 104`
- `s` 仅有英文字母和空格 `' '` 组成
- `s` 中至少存在一个单词

**从尾遍历即可**

```java
class Solution {
    public int lengthOfLastWord(String s) {
      int len=0;
        for (int i=s.length() - 1; i >= 0; i--){
            if (s.charAt(i) !=' '){
                len ++;
            }else if (len!=0){
                return len;
            }
        }
        return len;
    }
}
```



#### [326. 3的幂](https://leetcode-cn.com/problems/power-of-three/)

给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 `true` ；否则，返回 `false` 。

整数 `n` 是 3 的幂次方需满足：存在整数 `x` 使得 `n == 3x`

 

**示例 1：**

```
输入：n = 27
输出：true
```

**示例 2：**

```
输入：n = 0
输出：false
```

**示例 3：**

```
输入：n = 9
输出：true
```

**示例 4：**

```
输入：n = 45
输出：false
```

 

**提示：**

- `-231 <= n <= 231 - 1`

 

**进阶：**

- 你能不使用循环或者递归来完成本题吗？



**试除法**

```java
class Solution {
    public boolean isPowerOfThree(int n) {
         while (n != 0 && n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
}
```

**约数**

```java
class Solution {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
```

#### [371. 两整数之和](https://leetcode-cn.com/problems/sum-of-two-integers/)

给你两个整数 `a` 和 `b` ，**不使用** 运算符 `+` 和 `-` ，计算并返回两整数之和。

 

**示例 1：**

```
输入：a = 1, b = 2
输出：3
```

**示例 2：**

```
输入：a = 2, b = 3
输出：5
```

 **位运算**

```java
class Solution {
    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }
}
```



**提示：**

- `-1000 <= a, b <= 1000`

# 未完成

[470. 用 Rand7() 实现 Rand10()](https://leetcode-cn.com/problems/implement-rand10-using-rand7/)

[![hRwP4H.png](https://z3.ax1x.com/2021/09/05/hRwP4H.png)](https://imgtu.com/i/hRwP4H)

```java
/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {
    public int rand10() {
        while (true) {
            int num = (rand7() - 1) * 7 + rand7();
            // 生成的如果是41~49，则重新生成
            if (num <= 40) {
                return num % 10 + 1;
            }
        }
    }
}
```

[502. IPO](https://leetcode-cn.com/problems/ipo/)

[![hHmuDO.png](https://z3.ax1x.com/2021/09/08/hHmuDO.png)](https://imgtu.com/i/hHmuDO)

```java
class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int curr = 0;
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; ++i) {
            arr[i][0] = capital[i];
            arr[i][1] = profits[i];
        }
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
        for (int i = 0; i < k; ++i) {
            while (curr < n && arr[curr][0] <= w) {
                pq.add(arr[curr][1]);
                curr++;
            }
            if (!pq.isEmpty()) {
                w += pq.poll();
            } else {
                break;
            }
        }
        return w;
    }
}
```

[68. 文本左右对齐](https://leetcode-cn.com/problems/text-justification/)

[![hqt379.png](https://z3.ax1x.com/2021/09/09/hqt379.png)](https://imgtu.com/i/hqt379)

```java
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; ) {
            // list 装载当前行的所有 word
            list.clear();
            list.add(words[i]);
            int cur = words[i++].length();
            while (i < n && cur + 1 + words[i].length() <= maxWidth) {
                cur += 1 + words[i].length();
                list.add(words[i++]);
            }

            // 当前行为最后一行，特殊处理为左对齐
            if (i == n) {
                StringBuilder sb = new StringBuilder(list.get(0));
                for (int k = 1; k < list.size(); k++) {
                    sb.append(" ").append(list.get(k));
                }
                while (sb.length() < maxWidth) sb.append(" ");
                ans.add(sb.toString());
                break;
            }

            // 如果当前行只有一个 word，特殊处理为左对齐
            int cnt = list.size();
            if (cnt == 1) {
                String str = list.get(0);
                while (str.length() != maxWidth) str += " ";
                ans.add(str);
                continue;
            }

            /**
             * 其余为一般情况
             * wordWidth : 当前行单词总长度;
             * spaceWidth : 当前行空格总长度;
             * spaceItem : 往下取整后的单位空格长度
             */
            int wordWidth = cur - (cnt - 1);
            int spaceWidth = maxWidth - wordWidth;
            int spaceItemWidth = spaceWidth / (cnt - 1);
            String spaceItem = "";
            for (int k = 0; k < spaceItemWidth; k++) spaceItem += " ";
            StringBuilder sb = new StringBuilder();
            for (int k = 0, sum = 0; k < cnt; k++) {
                String item = list.get(k);
                sb.append(item);
                if (k == cnt - 1) break;
                sb.append(spaceItem);
                sum += spaceItemWidth;
                // 剩余的间隙数量（可填入空格的次数）
                int remain = cnt - k - 1 - 1;
                // 剩余间隙数量 * 最小单位空格长度 + 当前空格长度 < 单词总长度，则在当前间隙多补充一个空格
                if (remain * spaceItemWidth + sum < spaceWidth) {
                    sb.append(" ");
                    sum++;
                }
            }
            ans.add(sb.toString());
        }
        return ans;
    }
}
```

[600. 不含连续1的非负整数](https://leetcode-cn.com/problems/non-negative-integers-without-consecutive-ones/)

[![hxAcmq.png](https://z3.ax1x.com/2021/09/11/hxAcmq.png)]()

```java
class Solution {
    public int findIntegers(int n) {
      int[] dp = new int[31];
        dp[0] = dp[1] = 1;
        for (int i = 2; i < 31; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int pre = 0, res = 0;
        for (int i = 29; i >= 0; --i) {
            int val = 1 << i;
            if ((n & val) != 0) {
                n -= val;
                res += dp[i + 1];
                if (pre == 1) {
                    break;
                }
                pre = 1;
            } else {
                pre = 0;
            }

            if (i == 0) {
                ++res;
            }
        }
        return res;
    }
}
```

[162. 寻找峰值](https://leetcode-cn.com/problems/find-peak-element/)

[![4E2fDe.png](https://z3.ax1x.com/2021/09/15/4E2fDe.png)](https://imgtu.com/i/4E2fDe)

**二分法**

```java
class Solution {
    public int findPeakElement(int[] nums) {
           int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

#### [212. 单词搜索 II](https://leetcode-cn.com/problems/word-search-ii/)

给定一个 `m x n` 二维字符网格 `board` 和一个单词（字符串）列表 `words`，找出所有同时在二维网格和字典中出现的单词。

单词必须按照字母顺序，通过 **相邻的单元格** 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/11/07/search1.jpg)

```
输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
输出：["eat","oath"]
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2020/11/07/search2.jpg)

```
输入：board = [["a","b"],["c","d"]], words = ["abcb"]
输出：[]
```

 

**提示：**

- `m == board.length`
- `n == board[i].length`
- `1 <= m, n <= 12`
- `board[i][j]` 是一个小写英文字母
- `1 <= words.length <= 3 * 104`
- `1 <= words[i].length <= 10`
- `words[i]` 由小写英文字母组成
- `words` 中的所有字符串互不相同

**回溯+递归**

```java
class Solution {
	public List<String> findWords(char[][] board, String[] words) {
		List<String> ans = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {
			String str = words[i];
			if (exist(board, str)) {
				ans.add(str);
			}
		}
		return ans;
	}

	public boolean exist(char[][] board, String word) {
		int[] dics = new int[128];
		char[] wordc = word.toCharArray();
		char head = wordc[0];
		Queue<Integer[]> heads = new LinkedList<>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				dics[board[i][j]]++;
				if (board[i][j] == head) {
					heads.add(new Integer[] { i, j });
				}
			}
		}
		for (int i = 0; i < wordc.length; i++) {
			if (--dics[wordc[i]] < 0) {
				return false;
			}
		}
		while (!heads.isEmpty()) {
			Integer[] pos = heads.poll();
			boolean has = exist(pos[0], pos[1], board, wordc, 0);
			if (has)
				return true;
		}
		return false;
	}

	private boolean exist(Integer x, Integer y, char[][] board, char[] wordc, int i) {
		if (x < 0 || x >= board.length || y < 0 || y >= board[0].length)
			return false;
		// 以 x,y为起点，在board，上有以i为起点 wordc后续的字符串吗？
		if (board[x][y] != wordc[i]) {
			return false;
		}
		if (i == wordc.length - 1) {
			return true;
		}
		char temp = board[x][y];
		board[x][y] = '!';
		if (exist(x + 1, y, board, wordc, i + 1)) {
			board[x][y] = temp;
			return true;
		}
		if (exist(x - 1, y, board, wordc, i + 1)) {
			board[x][y] = temp;
			return true;
		}
		if (exist(x, y + 1, board, wordc, i + 1)) {
			board[x][y] = temp;
			return true;
		}
		if (exist(x, y - 1, board, wordc, i + 1)) {
			board[x][y] = temp;
			return true;
		}
		board[x][y] = temp;
		return false;
	}
}
```

#### [650. 只有两个键的键盘](https://leetcode-cn.com/problems/2-keys-keyboard/)

最初记事本上只有一个字符 `'A'` 。你每次可以对这个记事本进行两种操作：

- `Copy All`（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
- `Paste`（粘贴）：粘贴 **上一次** 复制的字符。

给你一个数字 `n` ，你需要使用最少的操作次数，在记事本上输出 **恰好** `n` 个 `'A'` 。返回能够打印出 `n` 个 `'A'` 的最少操作次数。

 

**示例 1：**

```
输入：3
输出：3
解释：
最初, 只有一个字符 'A'。
第 1 步, 使用 Copy All 操作。
第 2 步, 使用 Paste 操作来获得 'AA'。
第 3 步, 使用 Paste 操作来获得 'AAA'。
```

**示例 2：**

```
输入：n = 1
输出：0
```
**数学思路**

 ```java
 class Solution {
     public int minSteps(int n) {
      int ans = 0;
         for (int i = 2; i * i <= n; i++) {
             while (n % i == 0) {
                 n /= i;
                 ans += i;
             }
         }
             if (n > 1) {
                 ans += n;
             }
         return ans;
     }
 }
 ```

#### [673. 最长递增子序列的个数](https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/)

给定一个未排序的整数数组，找到最长递增子序列的个数。

**示例 1:**

```
输入: [1,3,5,4,7]
输出: 2
解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
```

**示例 2:**

```
输入: [2,2,2,2,2]
输出: 5
解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
```

**注意:** 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。

**动态规划**

```java
class Solution {
    public int findNumberOfLIS(int[] nums) {
      int n = nums.length, maxLen = 0, ans = 0;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        for (int i = 0; i < n; ++i) {
            dp[i] = 1;
            cnt[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j]; // 重置计数
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                ans = cnt[i]; // 重置计数
            } else if (dp[i] == maxLen) {
                ans += cnt[i];
            }
        }
        return ans;
    }
}
```

**贪心 + 前缀和 + 二分查找 后续补上**

#### [725. 分隔链表](https://leetcode-cn.com/problems/split-linked-list-in-parts/)

给你一个头结点为 `head` 的单链表和一个整数 `k` ，请你设计一个算法将链表分隔为 `k` 个连续的部分。

每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。

这 `k` 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。

返回一个由上述 `k` 部分组成的数组。

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/06/13/split1-lc.jpg)

```
输入：head = [1,2,3], k = 5
输出：[[1],[2],[3],[],[]]
解释：
第一个元素 output[0] 为 output[0].val = 1 ，output[0].next = null 。
最后一个元素 output[4] 为 null ，但它作为 ListNode 的字符串表示是 [] 。
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2021/06/13/split2-lc.jpg)

```
输入：head = [1,2,3,4,5,6,7,8,9,10], k = 3
输出：[[1,2,3,4],[5,6,7],[8,9,10]]
解释：
输入被分成了几个连续的部分，并且每部分的长度相差不超过 1 。前面部分的长度大于等于后面部分的长度。
```

 

**提示：**

- 链表中节点的数目在范围 `[0, 1000]`
- `0 <= Node.val <= 1000`
- `1 <= k <= 50`

**分割链表**

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
    public ListNode[] splitListToParts(ListNode head, int k) {
        int n = 0;
        ListNode temp = head;
        while (temp != null) {
            n++;
            temp = temp.next;
        }
        int quotient = n / k, remainder = n % k;

        ListNode[] parts = new ListNode[k];
        ListNode curr = head;
        for (int i = 0; i < k && curr != null; i++) {
            parts[i] = curr;
            int partSize = quotient + (i < remainder ? 1 : 0);
            for (int j = 1; j < partSize; j++) {
                curr = curr.next;
            }
            ListNode next = curr.next;
            curr.next = null;
            curr = next;
        }
        return parts;
    }
}
```

#### [430. 扁平化多级双向链表](https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list/)

多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。

给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。

**示例 1：**

```
输入：head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
输出：[1,2,3,7,8,11,12,9,10,4,5,6]
```

**示例 2：**

```
输入：head = [1,2,null,3]
输出：[1,3,2]
解释：

输入的多级列表如下图所示：

  1---2---NULL
  |
  3---NULL
```

**示例 3：**

```
输入：head = []
输出：[]
```

 

**如何表示测试用例中的多级链表？**

以 **示例 1** 为例：

```
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL
```

序列化其中的每一级之后：

```
[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]
```

为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。

```
[1,2,3,4,5,6,null]
[null,null,7,8,9,10,null]
[null,11,12,null]
```

合并所有序列化结果，并去除末尾的 null 。

```
[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
```

 

**提示：**

- 节点数目不超过 1000
- `1 <= Node.val <= 10^5`

**迭代解法**

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
         Node dummy = new Node(0);
        dummy.next = head;
        while (head != null) {
            // head没有child节点，指针直接向后移动
            if (head.child == null) {
                head = head.next;
                // head没有child节点
            } else {
                Node tmp = head.next;
                Node child = head.child;
                head.next = child;
                child.prev = head;
                head.child = null;
                Node last = head;
                while (last.next != null) {
                    last = last.next;
                }
                last.next = tmp;
                if (tmp != null) {
                    tmp.prev = last;
                }
                head = head.next;
            }
        }
        return dummy.next;
    }
}
```

**递归解法**

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
        Node dummy = new Node(0);
        dummy.next = head;
        while (head != null) {
            if (head.child == null) {
                head = head.next;
            } else {
                Node tmp = head.next;
                Node chead = flatten(head.child);
                head.next = chead;
                chead.prev = head;
                head.child = null;
                while (head.next != null) {
                    head = head.next;
                }
                head.next = tmp;
                if (tmp != null) {
                    tmp.prev = head;
                }
                head = tmp;
            }
        }
        return dummy.next;
    }
}
```

**深度优先搜索**

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
        dfs(head);
        return head;
    }

    public Node dfs(Node node) {
        Node cur = node;
        // 记录链表的最后一个节点
        Node last = null;
        while (cur != null) {
            Node next = cur.next;
            // 如果有子节点，先处理子节点
            if (cur.child != null) {
                Node childLast = dfs(cur.child);
                next = cur.next;
                // node 和 child相连
                cur.next = cur.child;
                cur.child.prev = cur;
                // 如果next不为空，就将last与next相连
                if (next != null) {
                    childLast.next = next;
                    next.prev = childLast;
                }
                // 将child置为空
                cur.child = null;
                last = childLast;
            } else {
                last = cur;
            }
            cur = next;
        }
        return last;
    }
}
```

#### [583. 两个字符串的删除操作](https://leetcode-cn.com/problems/delete-operation-for-two-strings/)

给定两个单词 *word1* 和 *word2*，找到使得 *word1* 和 *word2* 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。

 

**示例：**

```
输入: "sea", "eat"
输出: 2
解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
```

 

**提示：**

1. 给定单词的长度不超过500。
2. 给定单词中的字符只含有小写字母。

**动态规划**

```java
class Solution {
    public int minDistance(String word1, String word2) {
      int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int lcs = dp[m][n];
        return m - lcs + n - lcs;
    }
}
```

#### [639. 解码方法 II](https://leetcode-cn.com/problems/decode-ways-ii/)

一条包含字母 `A-Z` 的消息通过以下的方式进行了编码：

```
'A' -> 1
'B' -> 2
...
'Z' -> 26
```

要 **解码** 一条已编码的消息，所有的数字都必须分组，然后按原来的编码方案反向映射回字母（可能存在多种方式）。例如，`"11106"` 可以映射为：

- `"AAJF"` 对应分组 `(1 1 10 6)`
- `"KJF"` 对应分组 `(11 10 6)`

注意，像 `(1 11 06)` 这样的分组是无效的，因为 `"06"` 不可以映射为 `'F'` ，因为 `"6"` 与 `"06"` 不同。

**除了** 上面描述的数字字母映射方案，编码消息中可能包含 `'*'` 字符，可以表示从 `'1'` 到 `'9'` 的任一数字（不包括 `'0'`）。例如，编码字符串 `"1*"` 可以表示 `"11"`、`"12"`、`"13"`、`"14"`、`"15"`、`"16"`、`"17"`、`"18"` 或 `"19"` 中的任意一条消息。对 `"1*"` 进行解码，相当于解码该字符串可以表示的任何编码消息。

给你一个字符串 `s` ，由数字和 `'*'` 字符组成，返回 **解码** 该字符串的方法 **数目** 。

由于答案数目可能非常大，返回对 `109 + 7` **取余** 的结果。

 

**示例 1：**

```
输入：s = "*"
输出：9
解释：这一条编码消息可以表示 "1"、"2"、"3"、"4"、"5"、"6"、"7"、"8" 或 "9" 中的任意一条。
可以分别解码成字符串 "A"、"B"、"C"、"D"、"E"、"F"、"G"、"H" 和 "I" 。
因此，"*" 总共有 9 种解码方法。
```

**示例 2：**

```
输入：s = "1*"
输出：18
解释：这一条编码消息可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条。
每种消息都可以由 2 种方法解码（例如，"11" 可以解码成 "AA" 或 "K"）。
因此，"1*" 共有 9 * 2 = 18 种解码方法。
```

**示例 3：**

```
输入：s = "2*"
输出：15
解释：这一条编码消息可以表示 "21"、"22"、"23"、"24"、"25"、"26"、"27"、"28" 或 "29" 中的任意一条。
"21"、"22"、"23"、"24"、"25" 和 "26" 由 2 种解码方法，但 "27"、"28" 和 "29" 仅有 1 种解码方法。
因此，"2*" 共有 (6 * 2) + (3 * 1) = 12 + 3 = 15 种解码方法。
```

 

**提示：**

- `1 <= s.length <= 105`
- `s[i]` 是 `0 - 9` 中的一位数字或字符 `'*'`

**动态规划**

```java
class Solution {
    static final int MOD = 1000000007;

    public int numDecodings(String s) {
        int n = s.length();
        // a = f[i-2], b = f[i-1], c = f[i]
        long a = 0, b = 1, c = 0;
        for (int i = 1; i <= n; ++i) {
            c = b * check1digit(s.charAt(i - 1)) % MOD;
            if (i > 1) {
                c = (c + a * check2digits(s.charAt(i - 2), s.charAt(i - 1))) % MOD;
            }
            a = b;
            b = c;
        }
        return (int) c;
    }

    public int check1digit(char ch) {
        if (ch == '0') {
            return 0;
        }
        return ch == '*' ? 9 : 1;
    }

    public int check2digits(char c0, char c1) {
        if (c0 == '*' && c1 == '*') {
            return 15;
        }
        if (c0 == '*') {
            return c1 <= '6' ? 2 : 1;
        }
        if (c1 == '*') {
            if (c0 == '1') {
                return 9;
            }
            if (c0 == '2') {
                return 6;
            }
            return 0;
        }
        return (c0 != '0' && (c0 - '0') * 10 + (c1 - '0') <= 26) ? 1 : 0;
    }
}
```

#### [437. 路径总和 III](https://leetcode-cn.com/problems/path-sum-iii/)

给定一个二叉树的根节点 `root` ，和一个整数 `targetSum` ，求该二叉树里节点值之和等于 `targetSum` 的 **路径** 的数目。

**路径** 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/04/09/pathsum3-1-tree.jpg)

```
输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
输出：3
解释：和等于 8 的路径有 3 条，如图所示。
```

**示例 2：**

```
输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：3
```

 

**提示:**

- 二叉树的节点个数的范围是 `[0,1000]`
- `-109 <= Node.val <= 109` 
- `-1000 <= targetSum <= 1000` 

**深度优先搜索**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int res = rootSum(root, targetSum);
        res += pathSum(root.left, targetSum);
        res += pathSum(root.right, targetSum);
        return res;
    }

    /**
     * @param root      根节点
     * @param targetSum 整数
     * @return 数目
     */
    private static int rootSum(TreeNode root, int targetSum) {
        int res = 0;
        if (root == null) {
            return 0;
        }
        int val = root.val;
        if (val == targetSum) {
            res++;
        }
        res += rootSum(root.left, targetSum - val);
        res += rootSum(root.right, targetSum - val);
        return res;
    }
}
```

**前缀和**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        HashMap<Integer, Integer> prefix = new HashMap<>();
        prefix.put(0,1);
        return dfs(root,prefix,0,targetSum);
    }

    /**
     * 
     * @param root 根节点
     * @param prefix 前缀和
     * @param cur 当前的值
     * @param targetSum 目标值
     * @return 节点值之和等于targetSum的路径的数目
     */
    private static int dfs(TreeNode root, HashMap<Integer, Integer> prefix, int cur, int targetSum) {
        if (root==null){
            return 0;
        }
        int res=0;
        cur+=root.val;
        res=prefix.getOrDefault(cur-targetSum,0);
        prefix.put(cur,prefix.getOrDefault(cur,0)+1);
        res+=dfs(root.left,prefix,cur,targetSum);
        res+=dfs(root.right,prefix,cur,targetSum);
        prefix.put(cur,prefix.getOrDefault(cur,0)-1);
        return res;
    }
}
```


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


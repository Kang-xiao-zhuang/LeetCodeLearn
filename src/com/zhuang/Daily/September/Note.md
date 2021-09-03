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


package com.zhuang.offer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 双指针模块
 */
public class Solution003 {
    public static void main(String[] args) {
        Solution003 solution003 = new Solution003();
        solution003.reverseWords("hello   world");
    }

    /**
     * https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/
     * 单指针
     *
     * @param head ListNode
     * @param val  int
     * @return ListNode
     */
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

    // 双指针
    public ListNode deleteNode2(ListNode head, int val) {
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

    /**
     * https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/
     *
     * @param nums   int[]
     * @param target int
     * @return int[]
     */
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

    /**
     * https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
     *
     * @param nums int[]
     * @return int[]
     */
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

    public int[] exchange2(int[] nums) {
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

    /**
     * https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
     *
     * @param headA ListNode
     * @param headB ListNode
     * @return ListNode
     */
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

    /**
     * https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/
     *
     * @param s String
     * @return String
     */
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

    public String reverseWords2(String s) {
        // 除去开头和末尾的空白字符 分割字符串
        String[] split = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = split.length - 1; i >= 0; i--) {
            if (split[i].equals("")) continue;
            sb.append(split[i] + " ");
        }
        return sb.toString().trim();
    }


    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

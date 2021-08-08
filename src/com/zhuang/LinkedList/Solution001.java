package com.zhuang.LinkedList;

import java.util.HashSet;

/**
 * @Description 链表学习
 * @Author Zhuang
 * @Date 2021/7/28 9:31
 * @Version 1.0
 **/
public class Solution001 {
    /**
     * https://leetcode-cn.com/problems/linked-list-cycle/
     * 哈希法
     *
     * @param head 节点
     * @return 布尔值
     */
    public static boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 双指针法
     *
     * @param head 节点
     * @return 布尔值
     */
    public static boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        // 定义快慢指针
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * https://leetcode-cn.com/problems/remove-linked-list-elements/
     *
     * @param head 节点
     * @param val  值
     * @return 节点
     */
    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = removeElements(head.next, val);
        if (head.val == val) {
            return head.next;
        } else {
            return head;
        }
    }

    /**
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/
     *
     * @param l1 节点1
     * @param l2 节点2
     * @return 节点
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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

    /**
     * https://leetcode-cn.com/problems/reverse-linked-list/
     *
     * @param head 节点
     * @return ListNode
     */
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return cur;
    }

    /**
     * 递归法
     *
     * @param head 节点
     * @return ListNode
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
     *
     * @param head 节点
     * @return ListNode
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode temp = head;
        while (temp.next != null) {
            if (temp.val == temp.next.val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return head;
    }

    /**
     * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
     *
     * @param head 节点
     * @return ListNode
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    /**
     * Definition for singly-linked list
     */
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}

package com.zhuang.linkedList;

import java.util.*;

/**
 * @Classname Solution003
 * @Description 链表学习
 * @Date 2021/8/16 10:24
 * @Author by Zhuang
 */
public class Solution003 {

    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/SLwz0R/
     *
     * @param head 头节点
     * @param n    倒数第n个节点
     * @return ListNode
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = new ListNode(0, head);
        ListNode slow = pre, fast = head;
        while (n-- > 0 && fast != null) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        System.out.println(pre.next.toString());
        return pre.next;
    }

    /**
     * 哑点解法
     *
     * @param head 头节点
     * @param n    倒数第n个节点
     * @return ListNode
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;

        while (n-- > 0) {
            fast = fast.next;
        }
        ListNode pre = null;
        while (fast != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }
        pre.next = slow.next;
        slow.next = null;
        return dummy.next;
    }


    /**
     * 栈的方法
     *
     * @param head 头节点
     * @param n    倒数第n个节点
     * @return ListNode
     */
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        ListNode pre = stack.pop();
        pre.next = pre.next.next;
        return dummy.next;
    }

    /**
     * https://leetcode-cn.com/problems/palindrome-linked-list/
     *
     * @param head 头节点
     * @return 返回值
     */
    public boolean isPalindrome(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        // 将链表的值复制到数组中
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }

        // 判断是否回文
        int start = 0;
        int end = list.size() - 1;
        while (start < end) {
            if (!list.get(start).equals(list.get(end))) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    /**
     * 翻转链表与自身比较
     *
     * @param head 头节点
     * @return 返回值
     */
    public boolean isPalindrome2(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        // 将链表的值复制到数组中
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            list2.add(cur.val);
            cur = cur.next;
        }
        Collections.reverse(list);
        return list.equals(list2);
    }

    /**
     * 栈的方法
     *
     * @param head 头节点
     * @return 返回值
     */
    public boolean isPalindrome3(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        for (ListNode pre = head; pre != null; pre = pre.next) {
            stack.push(pre.val);
        }
        for (ListNode pre = head; pre != null; pre = pre.next) {
            if (pre.val != stack.pop()) {
                return false;
            }
        }
        return true;
    }


    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
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

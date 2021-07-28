package com.zhuang.LinkedList;

import java.util.HashSet;

/**
 * @Deacription 链表学习
 * @Author Zhuang
 * @Date 2021/7/28 9:31
 * @Version 1.0
 **/
public class Solution001 {
    public static void main(String[] args) {
    }

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

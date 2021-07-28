package com.zhuang.LinkedList;

/**
 * @Deacription TODO
 * @Author Zhuang
 * @Date 2021/7/28 11:41
 * @Version 1.0
 **/
public class Solution002 {
    public static void main(String[] args) {
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
     *  https://leetcode-cn.com/problems/merge-two-sorted-lists/
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
     * Definition for singly-linked list.
     */
    static class ListNode {
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


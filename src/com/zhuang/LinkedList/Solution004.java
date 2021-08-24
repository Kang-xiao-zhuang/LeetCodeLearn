package com.zhuang.LinkedList;

/**
 * @Classname Solution004
 * @Description 链表学习
 * @Date 2021/8/24 9:34
 * @Author by Zhuang
 */
public class Solution004 {


    /**
     * https://leetcode-cn.com/problems/middle-of-the-linked-list/
     * 双指针法
     *
     * @param head 头节点
     * @return 节点
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 数组法
     *
     * @param head 头节点
     * @return 节点
     */
    public ListNode middleNode2(ListNode head) {
        ListNode[] node = new ListNode[100];
        // 记录长度
        int count = 0;
        while (head != null) {
            node[count++] = head;
            head = head.next;
        }
        return node[count / 2];
    }

    /**
     * 单指针法
     *
     * @param head 头节点
     * @return 节点
     */
    public ListNode middleNode3(ListNode head) {
        // 记录节点个数 第一次循环
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        int num = 0;
        temp = head;
        // 第二次循环 个数超过count一半 终止，返回temp的节点
        while (num < count / 2) {
            num++;
            temp = temp.next;
        }
        return temp;
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

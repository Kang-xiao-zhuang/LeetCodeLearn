package com.zhuang.LinkedList;


import java.util.ArrayList;
import java.util.Stack;

/**
 * @Deacription 链表学习
 * @Author Zhuang
 * @Date 2021/7/31 9:31
 * @Version 1.0
 **/
public class Solution002 {
    /**
     * https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
     * list的方法
     *
     * @param head 节点
     * @return int数组
     */
    public int[] reversePrint(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(list.size() - i - 1);
        }
        return array;
    }

    /**
     * 不要要多余的空间的方法
     *
     * @param head 节点
     * @return int数组
     */
    public int[] reversePrint2(ListNode head) {
        ListNode tmp = head;
        int count = 0;
        while (tmp != null) {
            count++;
            tmp = tmp.next;
        }
        int[] array = new int[count];
        tmp = head;
        for (int i = count - 1; i >= 0; i--) {
            array[i] = tmp.val;
            tmp = tmp.next;
        }
        return array;
    }

    public int[] reversePrint3(ListNode head) {
        Stack<ListNode> stack = new Stack<ListNode>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        int[] array = new int[stack.size()];
        for (int i = 0; i < stack.size(); i++) {
            array[i] = stack.pop().val;
        }
        return array;
    }

    /**
     * Definition for singly-linked list
     */
    static class ListNode {
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

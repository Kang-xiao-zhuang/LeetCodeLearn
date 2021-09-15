package com.zhuang.LeetCodeHot100;
/**
 * @Classname ListNode
 * @Description 链表类
 * @Date 2021/9/15 17:19
 * @Author by Zhuang
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
}

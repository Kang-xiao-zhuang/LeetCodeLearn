package com.zhuang.daily.twozero.may;

/**
 * description: Solution001
 * date: 2022/10/9 9:26
 * author: Zhuang
 * version: 1.0
 */
public class Solution001 {

    public static void main(String[] args) {
        Solution001 solution001 = new Solution001();

    }

    /**
     * https://leetcode.cn/problems/merge-two-sorted-lists/
     * 2020.5.1
     *
     * @param list1 链表1
     * @param list2 链表2
     * @return ListNode
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 虚拟头节点
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (list1 != null && list2 != null) {
            // 链表1节点值 小于 链表2节点值
            if (list1.val <= list2.val) {
                // 前驱节点移动
                prev.next = list1;
                // 向后移动
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }
        prev.next = list1 == null ? list2 : list1;
        return prehead.next;
    }
}

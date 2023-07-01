package com.zhuang.offer;

import java.util.ArrayList;

public class Solution002 {
    public static void main(String[] args) {
        Solution002 solution002 = new Solution002();
        int[] ints = new int[]{2, 3, 4};
    }

    /**
     * https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
     *
     * @param head 头节点
     * @return 数组
     */
    public int[] reversePrint(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(list.size() - i - 1);
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/
     *
     * @param head ListNode
     * @return ListNode
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode cur = head, pre = null;
        while (cur != null) {
            ListNode tmp = cur.next; // 暂存后继节点 cur.next
            cur.next = pre;          // 修改 next 引用指向
            pre = cur;               // pre 暂存 cur
            cur = tmp;               // cur 访问下一节点
        }
        return pre;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

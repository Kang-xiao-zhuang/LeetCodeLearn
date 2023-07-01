package com.zhuang.offer;

import java.util.ArrayList;
import java.util.HashMap;

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

    /**
     * https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/
     *
     * @param head Node
     * @return Node
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        HashMap<Node, Node> map = new HashMap<>();
        // 复制各个节点
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        // 构建新的链表next和random指向
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        // 返回新链表头节点
        return map.get(head);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // Definition for a Node.
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}

package com.zhuang.offer;

import java.util.Arrays;
import java.util.List;

/**
 * 双指针模块
 */
public class Solution003 {
    public static void main(String[] args) {
        Solution003 solution003 = new Solution003();
        solution003.reverseWords("hello   world");
    }

    /**
     * https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/
     * 单指针
     *
     * @param head ListNode
     * @param val  int
     * @return ListNode
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return null;
        if (head.val == val) return head.next;
        ListNode cur = head;
        while (cur.next != null && cur.next.val != val) {
            cur = cur.next;
        }
        if (cur.next != null) cur.next = cur.next.next;
        return head;
    }

    // 双指针
    public ListNode deleteNode2(ListNode head, int val) {
        if (head == null) return null;
        if (head.val == val) return head.next;
        ListNode pre = head, cur = head.next;
        while (cur != null && cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        if (cur != null) pre.next = cur.next;
        return head;
    }


    /**
     * https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/
     *
     * @param s String
     * @return String
     */
    public String reverseWords(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则表达式
        String[] split = s.split("\\s+");
        List<String> list = Arrays.asList(split);
        String res = "";
        for (int i = list.size() - 1; i >= 0; i--) {
            res += list.get(i);
            res += " ";
        }
        return res.trim();
    }

    public String reverseWords2(String s) {
        // 除去开头和末尾的空白字符 分割字符串
        String[] split = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = split.length - 1; i >= 0; i--) {
            if (split[i].equals("")) continue;
            sb.append(split[i] + " ");
        }
        return sb.toString().trim();
    }


    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

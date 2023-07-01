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

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

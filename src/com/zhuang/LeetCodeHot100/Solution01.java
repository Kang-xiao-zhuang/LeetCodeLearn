package com.zhuang.LeetCodeHot100;

import java.util.HashMap;

/**
 * @Classname Solution01
 * @Description LeetCode 热题 HOT 1-5
 * @Date 2021/9/15 17:19
 * @Author by Zhuang
 */
public class Solution01 {
    public static void main(String[] args) {

    }

    /**
     * https://leetcode-cn.com/problems/two-sum/
     * 第1题
     * 暴力法
     *
     * @param nums   整数数组
     * @param target 整数目标值
     * @return 数组下标
     */
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 第1题
     * 哈希表
     *
     * @param nums   整数数组
     * @param target 整数目标值
     * @return 数组下标
     */
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * https://leetcode-cn.com/problems/add-two-numbers/
     * 第2题
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 链表
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //虚拟节点 也叫哨兵节点
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        int sum = 0;
        while (l1 != null || l2 != null) {
            int a = l1 != null ? l1.val : 0;
            int b = l2 != null ? l2.val : 0;
            sum = a + b + sum;
            // 满10进1
            temp.next = new ListNode(sum % 10);
            sum /= 10;
            // 节点后移
            temp = temp.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (sum > 0) {
            temp.next = new ListNode(sum);
        }
        return dummy.next;
    }
}

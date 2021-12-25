package com.zhuang.Daily.December;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Classname Solution004
 * @Description 2021.12.24-2021.12.31每日一题
 * @Date 2021/12/24 22:16
 * @Author by dell
 */

public class Solution004 {

    /**
     * https://leetcode-cn.com/problems/maximum-number-of-eaten-apples/
     * 12.24
     *
     * @param apples 数组
     * @param days   天数
     * @return 最大数目
     */
    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int n = apples.length, time = 0, ans = 0;
        while (time < n || !q.isEmpty()) {
            if (time < n && apples[time] > 0) q.add(new int[]{time + days[time] - 1, apples[time]});
            while (!q.isEmpty() && q.peek()[0] < time) q.poll();
            if (!q.isEmpty()) {
                int[] cur = q.poll();
                if (--cur[1] > 0 && cur[0] > time) q.add(cur);
                ans++;
            }
            time++;
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/even-odd-tree/
     * 12.25
     *
     * @param root 根节点
     * @return 判断是否奇偶树
     */
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int prev = level % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                int value = node.val;
                if (level % 2 == value % 2) {
                    return false;
                }
                if ((level % 2 == 0 && value <= prev) || (level % 2 == 1 && value >= prev)) {
                    return false;
                }
                prev = value;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            level++;
        }
        return true;
    }
}

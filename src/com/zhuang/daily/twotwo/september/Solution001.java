package com.zhuang.daily.twotwo.september;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution001 {
    public static void main(String[] args) {
        String[] logs = {"d1/", "d2/", "../", "d21/", "./"};
        minOperations(logs);
    }


    /**
     * https://leetcode.cn/problems/crawler-log-folder/
     * 9.9
     *
     * @param logs 字符串列表
     * @return 返回主文件夹所需的最小步数
     */
    public static int minOperations(String[] logs) {
        int depth = 0;
        for (String log : logs) {
            if ("./".equals(log)) {
                continue;
            } else if ("../".equals(log)) {
                if (depth > 0) {
                    depth--;
                }
            } else {
                depth++;
            }
        }
        return depth;
    }

    /**
     * https://leetcode.cn/problems/trim-a-binary-search-tree/
     * 9.10
     *
     * @param root 根节点
     * @param low  最小边界
     * @param high 最大边界
     * @return TreeNode
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            return trimBST(root.left, low, high);
        } else {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }

    /**
     * https://leetcode.cn/problems/minimum-cost-to-hire-k-workers/
     * 9.11
     * CV官解
     *
     * @param quality 数组
     * @param wage    数组
     * @param k       k名工人
     * @return 组成满足上述条件的付费群体所需的最小金额
     */
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Integer[] h = new Integer[n];
        for (int i = 0; i < n; i++) {
            h[i] = i;
        }
        Arrays.sort(h, (a, b) -> {
            return quality[b] * wage[a] - quality[a] * wage[b];
        });
        double res = 1e9;
        double totalq = 0.0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        for (int i = 0; i < k - 1; i++) {
            totalq += quality[h[i]];
            pq.offer(quality[h[i]]);
        }
        for (int i = k - 1; i < n; i++) {
            int idx = h[i];
            totalq += quality[idx];
            pq.offer(quality[idx]);
            double totalc = ((double) wage[idx] / quality[idx]) * totalq;
            res = Math.min(res, totalc);
            totalq -= pq.poll();
        }
        return res;
    }
}

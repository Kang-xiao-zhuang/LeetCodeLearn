package com.zhuang.daily.twozero.april;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * description: Solution002
 * date: 2022/10/17 15:14
 * author: Zhuang
 * version: 1.0
 */
public class Solution002 {




    /**
     * https://leetcode.cn/problems/binary-tree-right-side-view/
     * 2020.4.22
     *
     * @param root 根节点
     * @return 集合
     */
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {
                    // 将当层的最后一个节点放入到结果列表
                    list.add(node.val);
                }
            }
        }
        return list;
    }


    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(res, root, 0);
        return res;
    }

    private void dfs(List<Integer> res, TreeNode node, int level) {
        if(node != null) {
            if(res.size() == level) {
                res.add(node.val);
            }
            dfs(res, node.right, level + 1);
            dfs(res, node.left, level + 1);
        }
    }
}

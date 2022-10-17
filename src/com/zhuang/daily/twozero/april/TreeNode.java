package com.zhuang.daily.twozero.april;

/**
 * description: TreeNode
 * date: 2022/10/17 15:05
 * author: Zhuang
 * version: 1.0
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

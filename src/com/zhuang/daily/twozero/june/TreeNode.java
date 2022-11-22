package com.zhuang.daily.twozero.june;

/**
 * description: TreeNode
 * date: 2022/11/22 15:16
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

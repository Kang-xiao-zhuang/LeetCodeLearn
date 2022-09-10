package com.zhuang.daily.twotwo.september;

/**
 * @Classname TreeNode
 * @Description Definition for a binary tree node.
 * @Date 2021/12/25 11:10
 * @Author by dell
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
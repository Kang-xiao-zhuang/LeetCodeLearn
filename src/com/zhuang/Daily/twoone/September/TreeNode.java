package com.zhuang.Daily.twoone.September;

/**
 * @Classname TreeNode
 * @Description TreeNode
 * @Date 2021/9/8 8:36
 * @Author by Zhuang
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

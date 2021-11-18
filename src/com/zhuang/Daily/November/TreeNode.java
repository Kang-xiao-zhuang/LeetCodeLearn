package com.zhuang.Daily.November;

/**
 * @Classname TreeNode
 * @Description TreeNode
 * @Date 2021/11/18 7:22
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

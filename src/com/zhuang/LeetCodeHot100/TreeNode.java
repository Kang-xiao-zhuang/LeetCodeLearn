package com.zhuang.LeetCodeHot100;

/**
 * @Classname TreeNode
 * @Description 树节点类
 * @Date 2021/10/15 10:00
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

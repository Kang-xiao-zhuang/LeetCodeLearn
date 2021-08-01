package com.zhuang.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname Solution001
 * @Description 树的学习
 * @Date 2021/7/31 10:20
 * @Created by dell
 */

public class Solution001 {
    public static void main(String[] args) {

    }

    ArrayList<Integer> list = new ArrayList<Integer>();

    /**
     *  https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
     *
     * @param root 根节点
     * @return list集合
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return list;
        }
        list.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return list;
    }

    /**
     * Definition for a binary tree node
     */
    static class TreeNode {
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
}




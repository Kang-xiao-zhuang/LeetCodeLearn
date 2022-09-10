package com.zhuang.tree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @Classname Solution002
 * @Description 树的学习
 * @Date 2021/8/9 8:54
 * @Author by Zhuang
 */
public class Solution002 {

    /**
     * https://leetcode-cn.com/problems/validate-binary-search-tree/
     *
     * @param root 根节点
     * @return 布尔
     */
    public boolean isValidBST(TreeNode root) {
        // 从根节点开始，上下界都为空
        return recurse(root, null, null);
    }

    public boolean recurse(TreeNode node, Integer lower, Integer upper) {
        // 根节点为空，符合
        if (node == null) {
            return true;
        }
        // 节点不为空 判断节点上的值是否在上下界内
        int val = node.val;
        if (lower != null && val <= lower) {
            return false;
        }
        if (upper != null && val >= upper) {
            return false;
        }
        //将当前节点的值替换为下界， 继续检查右边的子节点
        if (!recurse(node.right, val, upper)) {
            return false;
        }
        // 将当前的子节点替换为上界，继续检查左边的子节点
        if (!recurse(node.left, lower, val)) {
            return false;
        }

        return true;
    }


    /**
     * 初始化 三个栈 分别保存树节点 上界 下界
     */
    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
    LinkedList<Integer> lowers = new LinkedList<Integer>();
    LinkedList<Integer> uppers = new LinkedList<Integer>();

    /**
     * 栈的方法 深度优先搜索
     *
     * @param root 根节点
     * @return 布尔
     */
    public boolean isValidBST2(TreeNode root) {
        Integer lower = null, upper = null;
        update(root, lower, upper);
        while (!stack.isEmpty()) {
//            从栈中取出节点
            root = stack.pop();
            lower = lowers.pop();
            upper = uppers.pop();
            if (root == null) {
                continue;
            }
            int val = root.val;
            if (lower != null && val <= lower) {
                return false;
            }
            if (upper != null && val >= upper) {
                return false;
            }
            // 比较过后继续把左子节点和右子节点加入栈
            update(root.right, val, upper);
            update(root.left, lower, val);
        }
        return true;
    }

    /**
     * 辅助函数 将当前节点 节点所对应的上下界存入对应的栈中
     *
     * @param root  根节点
     * @param lower 下界
     * @param upper 上界
     */
    public void update(TreeNode root, Integer lower, Integer upper) {
        stack.add(root);
        lowers.add(lower);
        uppers.add(upper);
    }


    /**
     * 中序遍历的方法
     *
     * @param root 根节点
     * @return 布尔
     */
    public boolean isValidBST3(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        // pre来存储上一个中序遍历的树节点的值
        Integer pre = null;

        while (!stack.isEmpty() || root != null) {
            // 左子节点加入栈 直到没有左子节点
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // 将当前子树最左边的节点从stack中取出 比较节点的值是否大于pre 如果小于 不是二叉搜索树
            root = stack.pop();
            if (pre != null && root.val <= pre) {
                return false;
            }
            // 将pre设为当前节点的值 将root设为当前节点的右子节点
            pre = root.val;
            root = root.right;
        }
        return true;
    }
    
    /**
     * Definition for a binary tree node.
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
}

package com.zhuang.Tree;

/**
 * @Classname Solution003
 * @Description 树的学习
 * @Date 2021/8/17 16:27
 * @Author by Zhuang
 */
public class Solution003 {
    /**
     * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
     *
     * @param nums 数组
     * @return 节点
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    /**
     * 辅助函数
     *
     * @param nums  数组
     * @param left  左节点
     * @param right 右节点
     * @return 节点
     */
    private TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        // 总是选择中间节点左边节点作为根节点
        int mid = (left + right) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }

    /**
     * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
     *
     * @param nums 数组
     * @return 节点
     */
    public TreeNode sortedArrayToBST2(int[] nums) {
        return helper2(nums, 0, nums.length - 1);
    }

    /**
     * 辅助函数
     *
     * @param nums  数组
     * @param left  左节点
     * @param right 右节点
     * @return 节点
     */
    private TreeNode helper2(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        // 总是选择中间节点右边节点作为根节点
        int mid = (left + right + 1) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
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

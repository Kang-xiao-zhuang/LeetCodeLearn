package com.zhuang.daily.twotwo.september;

public class Solution001 {
    public static void main(String[] args) {
        String[] logs = {"d1/", "d2/", "../", "d21/", "./"};
        minOperations(logs);
    }


    /**
     * https://leetcode.cn/problems/crawler-log-folder/
     * 9.9
     *
     * @param logs 字符串列表
     * @return 返回主文件夹所需的最小步数
     */
    public static int minOperations(String[] logs) {
        int depth = 0;
        for (String log : logs) {
            if ("./".equals(log)) {
                continue;
            } else if ("../".equals(log)) {
                if (depth > 0) {
                    depth--;
                }
            } else {
                depth++;
            }
        }
        return depth;
    }

    /**
     * https://leetcode.cn/problems/trim-a-binary-search-tree/
     * 9.10
     *
     * @param root 根节点
     * @param low  最小边界
     * @param high 最大边界
     * @return TreeNode
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            return trimBST(root.left, low, high);
        } else {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }
}

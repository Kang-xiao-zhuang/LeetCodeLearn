package com.zhuang.LeetCodeHot100;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Classname Solution04
 * @Description LeetCode 热题 HOT
 * @Date 2021/10/3 12:57
 * @Author by Zhuang
 */

public class Solution04 {
    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        exist(board, "ABCCED");

        numTrees(3);
    }

    /**
     * https://leetcode-cn.com/problems/word-search/
     * 第79题
     *
     * @param board 二维字符网格
     * @param word  字符串单词
     * @return 单词是否存在网格中
     */
    public static boolean exist(char[][] board, String word) {
        int rows = board.length, cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean flag = check(board, visited, i, j, word, 0);
                if (flag) {
                    System.out.println(true);
                    return true;
                }
            }
        }
        System.out.println(false);
        return false;
    }

    /**
     * @param board   二维数组
     * @param visited 布尔数组
     * @param i       行
     * @param j       列
     * @param word    单词
     * @param k       索引
     * @return 布尔值
     */
    private static boolean check(char[][] board, boolean[][] visited, int i, int j, String word, int k) {
        if (board[i][j] != word.charAt(k)) {
            return false;
        } else if (k == word.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;
        for (int[] direction : directions) {
            int newi = i + direction[0], newj = j + direction[1];
            if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                if (!visited[newi][newj]) {
                    boolean flag = check(board, visited, newi, newj, word, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
     * 第94题
     * 递归
     *
     * @param root 根节点
     * @return 中序遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private static void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    /**
     * 二叉树的中序遍历
     * 集合＋栈
     *
     * @param root 根节点
     * @return List集合
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        // 栈 先进后出
        // 前序遍历，出栈顺序：根左右; 入栈顺序：右左根
        // 中序遍历，出栈顺序：左根右; 入栈顺序：右根左
        // 后序遍历，出栈顺序：左右根; 入栈顺序：根右左
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        // root为空且stack为空，遍历结束
        while (root != null || !stack.isEmpty()) {
            // 先根后左入栈
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // 此时root==null，说明上一步的root没有左子树
            // 1. 执行左出栈。因为此时root==null，导致root.right一定为null
            // 2. 执行下一次外层while代码块，根出栈。此时root.right可能存在
            // 3a. 若root.right存在，右入栈，再出栈
            // 3b. 若root.right不存在，重复步骤2
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }

    /**
     * https://leetcode-cn.com/problems/unique-binary-search-trees/
     * 第96题
     * 动态规划
     *
     * @param n 整数
     * @return 二叉搜索树的种树
     */
    public static int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        System.out.println(G[n]);
        return G[n];
    }
}

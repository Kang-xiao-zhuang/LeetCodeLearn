package com.zhuang.LeetCodeHot100;

import java.util.*;

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

    /**
     * https://leetcode-cn.com/problems/validate-binary-search-tree/
     * 第98题
     * 递归法
     *
     * @param root 根节点
     * @return 判断是否为二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        return recurse(root, null, null);
    }

    /**
     * @param node  根节点
     * @param lower 较小值 即根节点左边的值
     * @param upper 较大值 即根节点右边的值
     * @return 是否为二叉搜索树
     */
    private boolean recurse(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }
        int val = node.val;
        // 当前值大于较大值时，为false
        if (lower != null && val <= lower) {
            return false;
        }
        // 当前值小于较小值时，为false
        if (upper != null && val >= upper) {
            return false;
        }
        // 当前节点的右节点，值，右节点的值
        if (!recurse(node.right, val, upper)) {
            return false;
        }
        // 当前节点的左节点，左节点的值，值
        if (!recurse(node.left, lower, val)) {
            return true;
        }
        return true;
    }

    public boolean isValidBST2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        // pre来存储上一个中序遍历的树节点的值
        Integer pre = null;
        while (!stack.isEmpty() || root != null) {
            // 左子节点加入栈， 直到没有左子节点
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
     * https://leetcode-cn.com/problems/symmetric-tree/
     * 第101题
     * 迭代法
     *
     * @param root 根节点
     * @return 是否镜像对称
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            queue.offer(node1.left);
            queue.offer(node2.right);
            queue.offer(node2.left);
            queue.offer(node1.right);
        }
        return true;
    }

    /**
     * 递归法
     *
     * @param root 节点
     * @return 布尔值
     */
    public static boolean isSymmetric2(TreeNode root) {
        return isMirror(root, root);
    }

    /**
     * @param node1 节点1
     * @param node2 节点2
     * @return 布尔
     */
    public static boolean isMirror(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        return (node1.val == node2.val) && isMirror(node1.right, node2.left) && isMirror(node1.left, node2.right);
    }


    ArrayList<List<Integer>> resList = new ArrayList<List<Integer>>();

    /**
     * 第101题
     *
     * @param root 根节点
     * @return List集合
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        order(root, 0);
        return resList;
    }

    public void order(TreeNode node, Integer deep) {
        if (node == null) {
            return;
        }
        deep++;
        if (resList.size() < deep) {
            // 当层级增加，list的Item也增加，利用list索引值进行层级界定
            ArrayList<Integer> item = new ArrayList<>();
            resList.add(item);
        }
        resList.get(deep - 1).add(node.val);
        order(node.left, deep);
        order(node.right, deep);
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        ArrayList<List<Integer>> list = new ArrayList<List<Integer>>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<>();
            int CurLevelSize = queue.size();
            for (int i = 1; i <= CurLevelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            list.add(level);
        }
        return list;
    }

    /**
     * 第104题
     *
     * @param root 根节点
     * @return 最大深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left_height = maxDepth(root.left);
            int right_height = maxDepth(root.right);
            return Math.max(left_height, right_height) + 1;
        }
    }
}

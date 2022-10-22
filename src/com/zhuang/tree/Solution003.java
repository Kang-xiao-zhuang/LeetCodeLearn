package com.zhuang.tree;

import javafx.util.Pair;

import java.util.*;

/**
 * @Classname Solution003
 * @Description 树的学习
 * @Date 2021/8/17 16:27
 * @Author by Zhuang
 */
public class Solution003 {


    public static void main(String[] args) {

    }


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
     * https://leetcode.cn/problems/maximum-width-of-binary-tree/
     *
     * @param root 二叉树的根节点
     * @return 树的 最大宽度
     */
    public int widthOfBinaryTree(TreeNode root) {
        int res = 1;
        ArrayList<Pair<TreeNode, Integer>> pairs = new ArrayList<>();
        pairs.add(new Pair<>(root, 1));
        while (!pairs.isEmpty()) {
            ArrayList<Pair<TreeNode, Integer>> temp = new ArrayList<>();
            for (Pair<TreeNode, Integer> pair : pairs) {
                TreeNode node = pair.getKey();
                int index = pair.getValue();
                if (node.left != null) {
                    temp.add(new Pair<>(node.left, index * 2));
                }
                if (node.right != null) {
                    temp.add(new Pair<>(node.right, index * 2 + 1));
                }
            }
            res = Math.max(res, pairs.get(pairs.size() - 1).getValue() - pairs.get(0).getValue() + 1);
            pairs = temp;
        }
        return res;
    }

    Map<Integer, Integer> levelMin = new HashMap<>();

    public int widthOfBinaryTree2(TreeNode root) {
        return dfs(root, 1, 1);
    }

    public int dfs(TreeNode node, int depth, int index) {
        if (node == null) {
            return 0;
        }
        levelMin.putIfAbsent(depth, index); // 每一层最先访问到的节点会是最左边的节点，即每一层编号的最小值
        return Math.max(index - levelMin.get(depth) + 1, Math.max(dfs(node.left, depth + 1, index * 2), dfs(node.right, depth + 1, index * 2 + 1)));
    }

    /**
     * https://leetcode.cn/problems/n-ary-tree-preorder-traversal/
     *
     * @param root 根节点
     * @return List<Integer>
     */
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    public void helper(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        for (Node ch : root.children) {
            helper(ch, res);
        }
    }


    public List<Integer> preorder2(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<Node> deque = new LinkedList<>();
        deque.push(root);
        // 前序遍历  根左右  入栈顺序   右左根
        while (!deque.isEmpty()) {
            Node node = deque.pop();
            if (node != null) {
                // node节点只要有孩子节点
                if (node.children != null) {
                    // 翻转子链表
                    Collections.reverse(node.children);
                    for (Node child : node.children) {
                        deque.push(child);
                    }
                }
                deque.push(node);
                deque.push(null);
            } else {
                node = deque.pop();
                res.add(node.val);
            }
        }
        return res;
    }

    public List<Integer> preorder3(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; --i) {
                stack.push(node.children.get(i));
            }
        }
        return res;
    }

}

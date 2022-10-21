package com.zhuang.tree;


import java.util.*;

/**
 * @Description 树的学习
 * @Author Zhuang
 * @Date 2021/8/2 13:51
 * @Version 1.0
 **/
public class Solution001 {
    public static void main(String[] args) {

    }

    /**
     * 集合＋栈的使用
     * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
     * 二叉树的前序遍历
     *
     * @param root 根节点
     * @return list集合
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        pre(root, list);
        return list;
    }

    private static void pre(TreeNode root, List<Integer> list) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                list.add(root.val);
                stack.add(root);
                root = root.left;
            }
            root = stack.pop().right;
        }
    }

    /**
     * 递归法
     * 二叉树的前序遍历
     *
     * @param root 根节点
     * @return List集合
     */
    public static List<Integer> preorderTraversal2(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    private static void preorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        preorder(root.left, list);
        preorder(root.right, list);
    }

    /**
     * Morris 遍历
     * 二叉树的前序遍历
     *
     * @param root 节点
     * @return List集合
     */
    public List<Integer> preorderTraversal3(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        TreeNode p1 = root, p2 = null;
        // 根节点不为空的时候
        while (p1 != null) {
            // 将根节点的左子节点赋给p2
            p2 = p1.left;
            if (p2 != null) {
                // p2的右子节点不为空且不等于p1
                while (p2.right != null && p2.right != p1) {
                    p2 = p2.right;
                }
                if (p2.right == null) {
                    list.add(p1.val);
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                } else {
                    p2.right = null;
                }
            } else {
                // 根节点的左子节点为空的时候
                list.add(p1.val);
            }
            // 根节点往右子节点赋值
            p1 = p1.right;
        }
        return list;
    }

    /**
     * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
     * 二叉树的中序遍历
     * 递归法
     *
     * @param root 根节点
     * @return List集合
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
     * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
     * 递归法
     *
     * @param root 根节点
     * @return List集合
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    private static void postorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        postorder(root.left, list);
        postorder(root.right, list);
        list.add(root.val);
    }

    /**
     * 迭代法 集合＋栈
     *
     * @param root 根节点
     * @return List集合
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                list.addFirst(root.val);
                root = root.right;
            } else {
                root = stack.pop();
                root = root.left;
            }
        }

        return list;
    }

    /**
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
     *
     * @param root 根节点
     * @return List集合
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
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
     * 递归法
     *
     * @param root 根节点
     * @return List集合
     */
    ArrayList<List<Integer>> resList = new ArrayList<List<Integer>>();

    public List<List<Integer>> levelOrder2(TreeNode root) {
        order(root, 0);
        return resList;
    }

    /**
     * @param node 节点
     * @param deep 深度
     */
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

    /**
     * https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/
     *
     * @param root 节点
     * @return List集合
     */
    public List<Double> averageOfLevels(TreeNode root) {
        ArrayList<Double> averages = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            double sum = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            averages.add(sum / size);
        }
        return averages;
    }

    /**
     * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
     * 递归法
     *
     * @param root 节点
     * @return int
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left_height = maxDepth(root.left);
            int right_height = maxDepth(root.right);
            return Math.max(left_height, right_height) + 1;
        }
    }

    /**
     * https://leetcode-cn.com/problems/symmetric-tree/
     *
     * @param root 节点
     * @return 布尔
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

    /**
     * https://leetcode-cn.com/problems/path-sum/
     *
     * @param root      节点
     * @param targetSum 目标值
     * @return 布尔
     */
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> value = new LinkedList<>();
        queue.offer(root);
        value.offer(root.val);
        while (!queue.isEmpty()) {
            TreeNode now = queue.poll();
            Integer temp = value.poll();
            if (now.left == null && now.right == null) {
                if (temp == targetSum) {
                    return true;
                }
                continue;
            }
            if (now.left != null) {
                queue.offer(now.left);
                value.offer(now.left.val + temp);
            }
            if (now.right != null) {
                queue.offer(now.right);
                value.offer(now.right.val + temp);
            }
        }
        return false;
    }

    /**
     * 递归
     *
     * @param root 根节点
     * @param sum  目标值
     * @return 布尔
     */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum2(root.left, sum - root.val) || hasPathSum2(root.right, sum - root.val);
    }

    /**
     * https://leetcode-cn.com/problems/invert-binary-tree/
     *
     * @param root 节点
     * @return TreeNode
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        swap(root);
        return root;
    }

    public void swap(TreeNode root) {
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }
}




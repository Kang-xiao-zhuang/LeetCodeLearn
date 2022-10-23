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

    /**
     * https://leetcode.cn/problems/n-ary-tree-postorder-traversal/
     *
     * @param root 根节点
     * @return List<Integer>
     */
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        helperPostOrder(root, res);
        return res;
    }

    public void helperPostOrder(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        for (Node ch : root.children) {
            helper(ch, res);
        }
        res.add(root.val);
    }

    /**
     * https://leetcode.cn/problems/balanced-binary-tree/
     *
     * @param root 根节点
     * @return 布尔
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }

    public boolean isBalanced2(TreeNode root) {
        return height(root) >= 0;
    }

    public int height2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    /**
     * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
     * 2020 5.10
     *
     * @param root 根节点
     * @param p    节点
     * @param q    节点
     * @return 找到该树中两个指定节点的最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }
        if (root == p || root == q) {
            // 直接返回公共祖先
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            // 左右都存在  返回公共祖先
            return root;
        } else if (left != null) {
            // 返回到这个值一路返回到最底层
            return left;
        } else if (right != null) {
            return right;
        }
        return null;
    }

    /**
     * https://leetcode.cn/problems/same-tree/
     *
     * @param p 根节点
     * @param q 根节点
     * @return 检验这两棵树是否相同
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);
        while (!queue.isEmpty()) {
            // 弹出节点比较
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            if (node1 == null && node2 == null) {
                continue;
            }
            // 判断值为空 相等
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            // 节点入队
            queue.offer(node1.left);
            queue.offer(node2.left);

            queue.offer(node1.right);
            queue.offer(node2.right);
        }

        return true;
    }
}

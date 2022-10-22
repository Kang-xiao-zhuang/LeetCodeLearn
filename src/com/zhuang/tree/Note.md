#### [662. 二叉树最大宽度](https://leetcode.cn/problems/maximum-width-of-binary-tree/)

给你一棵二叉树的根节点 `root` ，返回树的 **最大宽度** 。

树的 **最大宽度** 是所有层中最大的 **宽度** 。

每一层的 **宽度** 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 `null` 节点，这些 `null` 节点也计入长度。

题目数据保证答案将会在 **32 位** 带符号整数范围内。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/05/03/width1-tree.jpg)

```
输入：root = [1,3,2,5,3,null,9]
输出：4
解释：最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2022/03/14/maximum-width-of-binary-tree-v3.jpg)

```
输入：root = [1,3,2,5,null,null,9,6,null,7]
输出：7
解释：最大宽度出现在树的第 4 层，宽度为 7 (6,null,null,null,null,null,7) 。
```

**示例 3：**

![img](https://assets.leetcode.com/uploads/2021/05/03/width3-tree.jpg)

```
输入：root = [1,3,2,5]
输出：2
解释：最大宽度出现在树的第 2 层，宽度为 2 (3,2) 。
```

 

**提示：**

- 树中节点的数目范围是 `[1, 3000]`
- `-100 <= Node.val <= 100`

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
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
            res = Math.max(res, pairs.get(pairs.size()-1).getValue() - pairs.get(0).getValue() + 1);
            pairs = temp;
        }
        return res;
    }
}
```

**深度优先搜索**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    Map<Integer, Integer> levelMin = new HashMap<>();

    public int widthOfBinaryTree(TreeNode root) {
        return dfs(root, 1, 1);
    }

    public int dfs(TreeNode node, int depth, int index) {
        if (node == null) {
            return 0;
        }
        levelMin.putIfAbsent(depth, index); // 每一层最先访问到的节点会是最左边的节点，即每一层编号的最小值
        return Math.max(index - levelMin.get(depth) + 1, Math.max(dfs(node.left, depth + 1, index * 2), dfs(node.right, depth + 1, index * 2 + 1)));
    }
}
```

#### [589. N 叉树的前序遍历](https://leetcode.cn/problems/n-ary-tree-preorder-traversal/)

给定一个 n 叉树的根节点  `root` ，返回 *其节点值的 **前序遍历*** 。

n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 `null` 分隔（请参见示例）。


**示例 1：**

![img](https://assets.leetcode.com/uploads/2018/10/12/narytreeexample.png)

```
输入：root = [1,null,3,2,4,null,5,6]
输出：[1,3,5,6,2,4]
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2019/11/08/sample_4_964.png)

```
输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
```

 

**提示：**

- 节点总数在范围 `[0, 104]`内
- `0 <= Node.val <= 104`
- n 叉树的高度小于或等于 `1000`

 

**进阶：**递归法很简单，你可以使用迭代法完成此题吗?

**递归 **

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
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
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/7a78868a51f941f09cb2b74609d60ec7.png)

**迭代**

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<Integer> preorder(Node root) {
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
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/4c729a3de370410290f0b6a1b6b02e61.png)

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Deque<Node> stack = new ArrayDeque<Node>();
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
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/978984dd4c6d4a2ba9f19ee9934c6f96.png)
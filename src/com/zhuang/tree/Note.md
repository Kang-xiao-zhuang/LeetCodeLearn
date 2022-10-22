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


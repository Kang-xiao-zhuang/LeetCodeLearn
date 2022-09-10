#### [1598. 文件夹操作日志搜集器](https://leetcode.cn/problems/crawler-log-folder/)

每当用户执行变更文件夹操作时，LeetCode 文件系统都会保存一条日志记录。

下面给出对变更操作的说明：

- `"../"` ：移动到当前文件夹的父文件夹。如果已经在主文件夹下，则 **继续停留在当前文件夹** 。
- `"./"` ：继续停留在当前文件夹**。**
- `"x/"` ：移动到名为 `x` 的子文件夹中。题目数据 **保证总是存在文件夹 `x`** 。

给你一个字符串列表 `logs` ，其中 `logs[i]` 是用户在 `ith` 步执行的操作。

文件系统启动时位于主文件夹，然后执行 `logs` 中的操作。

执行完所有变更文件夹操作后，请你找出 **返回主文件夹所需的最小步数** 。

 

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/09/26/sample_11_1957.png)

```
输入：logs = ["d1/","d2/","../","d21/","./"]
输出：2
解释：执行 "../" 操作变更文件夹 2 次，即可回到主文件夹
```

**示例 2：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/09/26/sample_22_1957.png)

```
输入：logs = ["d1/","d2/","./","d3/","../","d31/"]
输出：3
```

**示例 3：**

```
输入：logs = ["d1/","../","../","../"]
输出：0
```

 

**提示：**

- `1 <= logs.length <= 103`
- `2 <= logs[i].length <= 10`
- `logs[i]` 包含小写英文字母，数字，`'.'` 和 `'/'`
- `logs[i]` 符合语句中描述的格式
- 文件夹名称由小写英文字母和数字组成





一个变量记录 `depth` 当前目录的层次深度，`depth` 初始化为 0

- 如果当前的操作为"../"：移动到当前文件夹的父文件夹，如果已经在主文件夹，继续停留在当前文件夹，如果`depth`>0，则`depth`减一，否则`depth`保持不变
- 如果当前的操作为"./"，继续停留在当前文件夹，保持`depth`不变
- 如果当前的操作为"x/"，移动到名为x的下一层文件夹，将`depth`+1

```java
class Solution {
    public int minOperations(String[] logs) {
        int depth=0;
        for (String log : logs) {
            if ("./".equals(log)){
                continue;
            }else if ("../".equals(log)){
                if (depth>0){
                    depth--;
                }
            }
            else {
                depth++;
            }
        }
        return depth;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/0559300be5ef444ca18120a616ddf310.png)

#### [669. 修剪二叉搜索树](https://leetcode.cn/problems/trim-a-binary-search-tree/)

给你二叉搜索树的根节点 `root` ，同时给定最小边界`low` 和最大边界 `high`。通过修剪二叉搜索树，使得所有节点的值在`[low, high]`中。修剪树 **不应该** 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 **唯一的答案** 。

所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/09/09/trim1.jpg)

```
输入：root = [1,0,2], low = 1, high = 2
输出：[1,null,2]
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2020/09/09/trim2.jpg)

```
输入：root = [3,0,4,null,2,null,null,1], low = 1, high = 3
输出：[3,2,null,1]
```

 

**提示：**

- 树中节点数在范围 `[1, 104]` 内
- `0 <= Node.val <= 104`
- 树中每个节点的值都是 **唯一** 的
- 题目数据保证输入是一棵有效的二叉搜索树
- `0 <= low <= high <= 104`

![在这里插入图片描述](https://img-blog.csdnimg.cn/8fa5a6cf0d494719ad053f3eb6c86a9d.png)
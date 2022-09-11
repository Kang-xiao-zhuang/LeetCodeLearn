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

#### [857. 雇佣 K 名工人的最低成本](https://leetcode.cn/problems/minimum-cost-to-hire-k-workers/)

有 `n` 名工人。 给定两个数组 `quality` 和 `wage` ，其中，`quality[i]` 表示第 `i` 名工人的工作质量，其最低期望工资为 `wage[i]` 。

现在我们想雇佣 `k` 名工人组成一个*工资组。*在雇佣 一组 `k` 名工人时，我们必须按照下述规则向他们支付工资：

1. 对工资组中的每名工人，应当按其工作质量与同组其他工人的工作质量的比例来支付工资。
2. 工资组中的每名工人至少应当得到他们的最低期望工资。

给定整数 `k` ，返回 *组成满足上述条件的付费群体所需的最小金额* 。在实际答案的 `10-5` 以内的答案将被接受。。

 



**示例 1：**

```
输入： quality = [10,20,5], wage = [70,50,30], k = 2
输出： 105.00000
解释： 我们向 0 号工人支付 70，向 2 号工人支付 35。
```

**示例 2：**

```
输入： quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
输出： 30.66667
解释： 我们向 0 号工人支付 4，向 2 号和 3 号分别支付 13.33333。
```

 

**提示：**

- `n == quality.length == wage.length`
- `1 <= k <= n <= 104`
- `1 <= quality[i], wage[i] <= 104`

CV

```java
class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Integer[] h = new Integer[n];
        for (int i = 0; i < n; i++) {
            h[i] = i;
        }
        Arrays.sort(h, (a, b) -> {
            return quality[b] * wage[a] - quality[a] * wage[b];
        });
        double res = 1e9;
        double totalq = 0.0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        for (int i = 0; i < k - 1; i++) {
            totalq += quality[h[i]];
            pq.offer(quality[h[i]]);
        }
        for (int i = k - 1; i < n; i++) {
            int idx = h[i];
            totalq += quality[idx];
            pq.offer(quality[idx]);
            double totalc = ((double) wage[idx] / quality[idx]) * totalq;
            res = Math.min(res, totalc);
            totalq -= pq.poll();
        }
        return res;
    }   
}
```


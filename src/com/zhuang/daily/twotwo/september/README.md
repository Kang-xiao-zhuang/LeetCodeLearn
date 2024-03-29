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

#### [1608. 特殊数组的特征值](https://leetcode.cn/problems/special-array-with-x-elements-greater-than-or-equal-x/)

给你一个非负整数数组 `nums` 。如果存在一个数 `x` ，使得 `nums` 中恰好有 `x` 个元素 **大于或者等于** `x` ，那么就称 `nums` 是一个 **特殊数组** ，而 `x` 是该数组的 **特征值** 。

注意： `x` **不必** 是 `nums` 的中的元素。

如果数组 `nums` 是一个 **特殊数组** ，请返回它的特征值 `x` 。否则，返回 `-1` 。可以证明的是，如果 `nums` 是特殊数组，那么其特征值 `x` 是 **唯一的** 。

 

**示例 1：**

```
输入：nums = [3,5]
输出：2
解释：有 2 个元素（3 和 5）大于或等于 2 。
```

**示例 2：**

```
输入：nums = [0,0]
输出：-1
解释：没有满足题目要求的特殊数组，故而也不存在特征值 x 。
如果 x = 0，应该有 0 个元素 >= x，但实际有 2 个。
如果 x = 1，应该有 1 个元素 >= x，但实际有 0 个。
如果 x = 2，应该有 2 个元素 >= x，但实际有 0 个。
x 不能取更大的值，因为 nums 中只有两个元素。
```

**示例 3：**

```
输入：nums = [0,4,3,0,4]
输出：3
解释：有 3 个元素大于或等于 3 。
```

**示例 4：**

```
输入：nums = [3,6,7,7,0]
输出：-1
```

 

**提示：**

- `1 <= nums.length <= 100`
- `0 <= nums[i] <= 1000`

排序

```java
class Solution {
    public int specialArray(int[] nums) {
         Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        for (int i = 1; i <= n; ++i) {
            if (nums[i - 1] >= i && (i == n || nums[i] < i)) {
                return i;
            }
        }
        return -1;
    }   
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/1da9d244f01a498b8cbb88b38f633bec.png)

**计数模拟**

```java
class Solution {
    public int specialArray(int[] nums) {
        int[] cnts = new int[1010];
        for (int x : nums) {
            cnts[x]++;
        }
        for (int i = 1009, tot = 0; i >= 0; i--) {
            tot += cnts[i];
            if (i == tot) {
                return i;
            }
        }
        return -1;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/d1891513953d463e91a9b1d6a8dfa506.png)

**暴力枚举**

```java
class Solution {
    public int specialArray(int[] nums) {
        int n = nums.length;
        for(int i = 0; i <= n; i++) {
            int cnt = 0;
            for(int num : nums) {
                if(num >= i) cnt++;
            }
            if(cnt == i) return i;
        }
        return -1;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/2d5f7c8496274e758a71b3bd28dc5c11.png)

#### [670. 最大交换](https://leetcode.cn/problems/maximum-swap/)

难度中等281收藏分享切换为英文接收动态反馈

给定一个非负整数，你**至多**可以交换一次数字中的任意两位。返回你能得到的最大值。

**示例 1 :**

```
输入: 2736
输出: 7236
解释: 交换数字2和数字7。
```

**示例 2 :**

```
输入: 9973
输出: 9973
解释: 不需要交换。
```

**注意:**

1. 给定数字的范围是 [0, 10的8次方]



```java
class Solution {
    public int maximumSwap(int num) {
       char[] charArray = String.valueOf(num).toCharArray();
        int n = charArray.length;
        int maxNum = num;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(charArray, i, j);
                maxNum = Math.max(maxNum, Integer.parseInt(new String(charArray)));
                swap(charArray, i, j);
            }
        }
        return maxNum;
    }

    public void swap(char[] charArray, int i, int j) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/543b103b3eec4ce3894e454f827a686e.png)

#### [1619. 删除某些元素后的数组均值](https://leetcode.cn/problems/mean-of-array-after-removing-some-elements/)



给你一个整数数组 `arr` ，请你删除最小 `5%` 的数字和最大 `5%` 的数字后，剩余数字的平均值。

与 **标准答案** 误差在 `10-5` 的结果都被视为正确结果。

 

**示例 1：**

```
输入：arr = [1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3]
输出：2.00000
解释：删除数组中最大和最小的元素后，所有元素都等于 2，所以平均值为 2 。
```

**示例 2：**

```
输入：arr = [6,2,7,5,1,2,0,3,10,2,5,0,5,5,0,8,7,6,8,0]
输出：4.00000
```

**示例 3：**

```
输入：arr = [6,0,7,0,7,5,7,8,3,4,0,7,8,1,6,8,1,1,2,4,8,1,9,5,4,3,8,5,10,8,6,6,1,0,6,10,8,2,3,4]
输出：4.77778
```

**示例 4：**

```
输入：arr = [9,7,8,7,7,8,4,4,6,8,8,7,6,8,8,9,2,6,0,0,1,10,8,6,3,3,5,1,10,9,0,7,10,0,10,4,1,10,6,9,3,6,0,0,2,7,0,6,7,2,9,7,7,3,0,1,6,1,10,3]
输出：5.27778
```

**示例 5：**

```
输入：arr = [4,8,4,10,0,7,1,3,7,8,8,3,4,1,6,2,1,1,8,0,9,8,0,3,9,10,3,10,1,10,7,3,2,1,4,9,10,7,6,4,0,8,5,1,2,1,6,2,5,0,7,10,9,10,3,7,10,5,8,5,7,6,7,6,10,9,5,10,5,5,7,2,10,7,7,8,2,0,1,1]
输出：5.29167
```

 

**提示：**

- `20 <= arr.length <= 1000`
- `arr.length` 是 `20` 的 **倍数** 
- `0 <= arr[i] <= 105`

**排序**

```java
class Solution {
    public double trimMean(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        int partialSum = 0;
        for (int i = n / 20; i < 19 * n / 20; i++) {
            partialSum += arr[i];
        }
        return partialSum / (n * 0.9);
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/3e004feb626a4970ab3ed03e5cb2ebb7.png)

#### [672. 灯泡开关 Ⅱ](https://leetcode.cn/problems/bulb-switcher-ii/)

房间中有 `n` 只已经打开的灯泡，编号从 `1` 到 `n` 。墙上挂着 **4 个开关** 。

这 4 个开关各自都具有不同的功能，其中：

- **开关 1 ：**反转当前所有灯的状态（即开变为关，关变为开）
- **开关 2 ：**反转编号为偶数的灯的状态（即 `2, 4, ...`）
- **开关 3 ：**反转编号为奇数的灯的状态（即 `1, 3, ...`）
- **开关 4 ：**反转编号为 `j = 3k + 1` 的灯的状态，其中 `k = 0, 1, 2, ...`（即 `1, 4, 7, 10, ...`）

你必须 **恰好** 按压开关 `presses` 次。每次按压，你都需要从 4 个开关中选出一个来执行按压操作。

给你两个整数 `n` 和 `presses` ，执行完所有按压之后，返回 **不同可能状态** 的数量。

 

**示例 1：**

```
输入：n = 1, presses = 1
输出：2
解释：状态可以是：
- 按压开关 1 ，[关]
- 按压开关 2 ，[开]
```

**示例 2：**

```
输入：n = 2, presses = 1
输出：3
解释：状态可以是：
- 按压开关 1 ，[关, 关]
- 按压开关 2 ，[开, 关]
- 按压开关 3 ，[关, 开]
```

**示例 3：**

```
输入：n = 3, presses = 1
输出：4
解释：状态可以是：
- 按压开关 1 ，[关, 关, 关]
- 按压开关 2 ，[关, 开, 关]
- 按压开关 3 ，[开, 关, 开]
- 按压开关 4 ，[关, 开, 开]
```

 

**提示：**

- `1 <= n <= 1000`
- `0 <= presses <= 1000`

**找规律**

```java
class Solution {
    public int flipLights(int n, int presses) {
        //不按开关
        if (presses == 0) {
            return 1;
        }
        //特殊情况处理
        if (n == 1) {
            return 2;
        } else if (n == 2) {
            //特殊情况
            return presses == 1 ? 3 : 4;
        } else {
            //n >= 3
            return presses == 1 ? 4 : presses == 2 ? 7 : 8;
        }
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/2fe390ffc34a4f4da84ef76d985a9c8c.png)

#### [850. 矩形面积 II](https://leetcode.cn/problems/rectangle-area-ii/)

我们给出了一个（轴对齐的）二维矩形列表 `rectangles` 。 对于 `rectangle[i] = [x1, y1, x2, y2]`，其中（x1，y1）是矩形 `i` 左下角的坐标， `(xi1, yi1)` 是该矩形 **左下角** 的坐标， `(xi2, yi2)` 是该矩形 **右上角** 的坐标。

计算平面中所有 `rectangles` 所覆盖的 **总面积** 。任何被两个或多个矩形覆盖的区域应只计算 **一次** 。

返回 ***总面积*** 。因为答案可能太大，返回 `109 + 7` 的 **模** 。

 

**示例 1：**

![img](https://s3-lc-upload.s3.amazonaws.com/uploads/2018/06/06/rectangle_area_ii_pic.png)

```
输入：rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
输出：6
解释：如图所示，三个矩形覆盖了总面积为6的区域。
从(1,1)到(2,2)，绿色矩形和红色矩形重叠。
从(1,0)到(2,3)，三个矩形都重叠。
```

**示例 2：**

```
输入：rectangles = [[0,0,1000000000,1000000000]]
输出：49
解释：答案是 1018 对 (109 + 7) 取模的结果， 即 49 。
```

 

**提示：**

- `1 <= rectangles.length <= 200`
- `rectanges[i].length = 4`
- `0 <= xi1, yi1, xi2, yi2 <= 109`
- 矩形叠加覆盖后的总面积不会超越 `2^63 - 1` ，这意味着可以用一个 64 位有符号整数来保存面积结果

```java
class Solution {
    public int rectangleArea(int[][] rectangles) {
        final int MOD = 1000000007;
        int n = rectangles.length;
        Set<Integer> set = new HashSet<Integer>();
        for (int[] rect : rectangles) {
            // 下边界
            set.add(rect[1]);
            // 上边界
            set.add(rect[3]);
        }
        List<Integer> hbound = new ArrayList<Integer>(set);
        Collections.sort(hbound);
        int m = hbound.size();
        // 「思路与算法部分」的 length 数组并不需要显式地存储下来
        // length[i] 可以通过 hbound[i+1] - hbound[i] 得到
        int[] seg = new int[m - 1];

        List<int[]> sweep = new ArrayList<int[]>();
        for (int i = 0; i < n; ++i) {
            // 左边界
            sweep.add(new int[]{rectangles[i][0], i, 1});
            // 右边界
            sweep.add(new int[]{rectangles[i][2], i, -1});
        }
        Collections.sort(sweep, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else if (a[1] != b[1]) {
                return a[1] - b[1];
            } else {
                return a[2] - b[2];
            }
        });

        long ans = 0;
        for (int i = 0; i < sweep.size(); ++i) {
            int j = i;
            while (j + 1 < sweep.size() && sweep.get(i)[0] == sweep.get(j + 1)[0]) {
                ++j;
            }
            if (j + 1 == sweep.size()) {
                break;
            }
            // 一次性地处理掉一批横坐标相同的左右边界
            for (int k = i; k <= j; ++k) {
                int[] arr = sweep.get(k);
                int idx = arr[1], diff = arr[2];
                int left = rectangles[idx][1], right = rectangles[idx][3];
                for (int x = 0; x < m - 1; ++x) {
                    if (left <= hbound.get(x) && hbound.get(x + 1) <= right) {
                        seg[x] += diff;
                    }
                }
            }
            int cover = 0;
            for (int k = 0; k < m - 1; ++k) {
                if (seg[k] > 0) {
                    cover += (hbound.get(k + 1) - hbound.get(k));
                }
            }
            ans += (long) cover * (sweep.get(j + 1)[0] - sweep.get(j)[0]);
            i = j;
        }
        return (int) (ans % MOD);
    }
}
```

#### [1624. 两个相同字符之间的最长子字符串](https://leetcode.cn/problems/largest-substring-between-two-equal-characters/)

给你一个字符串 `s`，请你返回 **两个相同字符之间的最长子字符串的长度** *，*计算长度时不含这两个字符。如果不存在这样的子字符串，返回 `-1` 。

**子字符串** 是字符串中的一个连续字符序列。

 

**示例 1：**

```
输入：s = "aa"
输出：0
解释：最优的子字符串是两个 'a' 之间的空子字符串。
```

**示例 2：**

```
输入：s = "abca"
输出：2
解释：最优的子字符串是 "bc" 。
```

**示例 3：**

```
输入：s = "cbzxy"
输出：-1
解释：s 中不存在出现出现两次的字符，所以返回 -1 。
```

**示例 4：**

```
输入：s = "cabbac"
输出：4
解释：最优的子字符串是 "abba" ，其他的非最优解包括 "bb" 和 "" 。
```

 

**提示：**

- `1 <= s.length <= 300`
- `s` 只含小写英文字母

**直接遍历**

```java
class Solution {
    public int maxLengthBetweenEqualCharacters(String s) {
         int[] firestIndex = new int[26];
        Arrays.fill(firestIndex, -1);
        int maxLen = -1;
        for (int i = 0; i < s.length(); i++) {
            if (firestIndex[s.charAt(i) - 'a'] < 0) {
                firestIndex[s.charAt(i) - 'a'] = i;
            } else {
                maxLen = Math.max(maxLen, i - firestIndex[s.charAt(i) - 'a'] - 1);
            }
        }
        return maxLen;
    }   
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/574821eb5adb4991b8f9ef9f5bd96acb.png)

#### [827. 最大人工岛](https://leetcode.cn/problems/making-a-large-island/)

给你一个大小为 `n x n` 二进制矩阵 `grid` 。**最多** 只能将一格 `0` 变成 `1` 。

返回执行此操作后，`grid` 中最大的岛屿面积是多少？

**岛屿** 由一组上、下、左、右四个方向相连的 `1` 形成。

 

**示例 1:**

```
输入: grid = [[1, 0], [0, 1]]
输出: 3
解释: 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
```

**示例 2:**

```
输入: grid = [[1, 1], [1, 0]]
输出: 4
解释: 将一格0变成1，岛屿的面积扩大为 4。
```

**示例 3:**

```
输入: grid = [[1, 1], [1, 1]]
输出: 4
解释: 没有0可以让我们变成1，面积依然为 4。
```

 

**提示：**

- `n == grid.length`
- `n == grid[i].length`
- `1 <= n <= 500`
- `grid[i][j]` 为 `0` 或 `1`



```java
class Solution {
    static int[] d = {0, -1, 0, 1, 0};

    public int largestIsland(int[][] grid) {
        int n = grid.length, res = 0;
        int[][] tag = new int[n][n];
        Map<Integer, Integer> area = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && tag[i][j] == 0) {
                    int t = i * n + j + 1;
                    area.put(t, dfs(grid, i, j, tag, t));
                    res = Math.max(res, area.get(t));
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int z = 1;
                    Set<Integer> connected = new HashSet<Integer>();
                    for (int k = 0; k < 4; k++) {
                        int x = i + d[k], y = j + d[k + 1];
                        if (!valid(n, x, y) || tag[x][y] == 0 || connected.contains(tag[x][y])) {
                            continue;
                        }
                        z += area.get(tag[x][y]);
                        connected.add(tag[x][y]);
                    }
                    res = Math.max(res, z);
                }
            }
        }
        return res;
    }

    public int dfs(int[][] grid, int x, int y, int[][] tag, int t) {
        int n = grid.length, res = 1;
        tag[x][y] = t;
        for (int i = 0; i < 4; i++) {
            int x1 = x + d[i], y1 = y + d[i + 1];
            if (valid(n, x1, y1) && grid[x1][y1] == 1 && tag[x1][y1] == 0) {
                res += dfs(grid, x1, y1, tag, t);
            }
        }
        return res;
    }

    public boolean valid(int n, int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
```

#### [1636. 按照频率将数组升序排序](https://leetcode.cn/problems/sort-array-by-increasing-frequency/)

给你一个整数数组 `nums` ，请你将数组按照每个值的频率 **升序** 排序。如果有多个值的频率相同，请你按照数值本身将它们 **降序** 排序。 

请你返回排序后的数组。

 

**示例 1：**

```
输入：nums = [1,1,2,2,2,3]
输出：[3,1,1,2,2,2]
解释：'3' 频率为 1，'1' 频率为 2，'2' 频率为 3 。
```

**示例 2：**

```
输入：nums = [2,3,1,3,2]
输出：[1,3,3,2,2]
解释：'2' 和 '3' 频率都为 2 ，所以它们之间按照数值本身降序排序。
```

**示例 3：**

```
输入：nums = [-1,1,-6,4,5,-6,1,4,1]
输出：[5,-1,4,4,-6,-6,1,1,1]
```

 

**提示：**

- `1 <= nums.length <= 100`
- `-100 <= nums[i] <= 100`

```java
class Solution {
    public int[] frequencySort(int[] nums) {
         Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        list.sort((a, b) -> {
            int cnt1 = cnt.get(a), cnt2 = cnt.get(b);
            return cnt1 != cnt2 ? cnt1 - cnt2 : b - a;
        });
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            nums[i] = list.get(i);
        }
        return nums;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/59146ed28dd04a8ca075b94b26b19fd5.png)

#### [698. 划分为k个相等的子集](https://leetcode.cn/problems/partition-to-k-equal-sum-subsets/)

给定一个整数数组 `nums` 和一个正整数 `k`，找出是否有可能把这个数组分成 `k` 个非空子集，其总和都相等。

 

**示例 1：**

```
输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
输出： True
说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
```

**示例 2:**

```
输入: nums = [1,2,3,4], k = 3
输出: false
```

 

**提示：**

- `1 <= k <= len(nums) <= 16`
- `0 < nums[i] < 10000`
- 每个元素的频率在 `[1,4]` 范围内

```java
class Solution {
        public boolean canPartitionKSubsets(int[] nums, int k) {
            int n = nums.length;
            if (n < k) return false;
            int sum = Arrays.stream(nums).sum();
            if (sum % k != 0) return false;
            Arrays.sort(nums);
            int[] dp = new int[1 << n], curSum = new int[1 << n];
            dp[0] = 1;
            for (int i = 0; i < 1 << n; i++) {
                if (dp[i] == 0) continue;
                for (int j = 0; j < n; j++) {
                    if (curSum[i] + nums[j] > sum / k) break;
                    if (((i >> j) & 1) == 0 && dp[i | (1 << j)] == 0) {
                        curSum[i | (1 << j)] = (curSum[i] + nums[j]) % (sum / k);
                        dp[i | (1 << j)] = 1;
                    }
                }
            }
            return dp[(1 << n) - 1] == 1;
        }
}
```

#### [854. 相似度为 K 的字符串](https://leetcode.cn/problems/k-similar-strings/)

对于某些非负整数 `k` ，如果交换 `s1` 中两个字母的位置恰好 `k` 次，能够使结果字符串等于 `s2` ，则认为字符串 `s1` 和 `s2` 的 **相似度为** `k` **。**

给你两个字母异位词 `s1` 和 `s2` ，返回 `s1` 和 `s2` 的相似度 `k` 的最小值。

 

**示例 1：**

```
输入：s1 = "ab", s2 = "ba"
输出：1
```

**示例 2：**

```
输入：s1 = "abc", s2 = "bca"
输出：2
```

 

**提示：**

- `1 <= s1.length <= 20`
- `s2.length == s1.length`
- `s1` 和 `s2` 只包含集合 `{'a', 'b', 'c', 'd', 'e', 'f'}` 中的小写字母
- `s2` 是 `s1` 的一个字母异位词



```java
class Solution {
    public int kSimilarity(String s1, String s2) {
        int n = s1.length();
        Queue<Pair<String, Integer>> queue = new ArrayDeque<Pair<String, Integer>>();
        Set<String> visit = new HashSet<String>();
        queue.offer(new Pair<String, Integer>(s1, 0));
        visit.add(s1);
        int step = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                Pair<String, Integer> pair = queue.poll();
                String cur = pair.getKey();
                int pos = pair.getValue();
                if (cur.equals(s2)) {
                    return step;
                }
                while (pos < n && cur.charAt(pos) == s2.charAt(pos)) {
                    pos++;
                }
                for (int j = pos + 1; j < n; j++) {
                    if (s2.charAt(j) == cur.charAt(j)) {
                        continue;
                    }
                    if (s2.charAt(pos) == cur.charAt(j)) {
                        String next = swap(cur, pos, j);
                        if (!visit.contains(next)) {
                            visit.add(next);
                            queue.offer(new Pair<String, Integer>(next, pos + 1));
                        }
                    }
                }
            }
            step++;
        } 
        return step;
    }

    public String swap(String cur, int i, int j) {
        char[] arr = cur.toCharArray();
        char c = arr[i];
        arr[i] = arr[j];
        arr[j] = c;
        return new String(arr);
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/c82f2f54c9674c5583c82a2d95974552.png)

#### [1640. 能否连接形成数组](https://leetcode.cn/problems/check-array-formation-through-concatenation/)

给你一个整数数组 `arr` ，数组中的每个整数 **互不相同** 。另有一个由整数数组构成的数组 `pieces`，其中的整数也 **互不相同** 。请你以 **任意顺序** 连接 `pieces` 中的数组以形成 `arr` 。但是，**不允许** 对每个数组 `pieces[i]` 中的整数重新排序。

如果可以连接 `pieces` 中的数组形成 `arr` ，返回 `true` ；否则，返回 `false` 。

 

**示例 1：**

```
输入：arr = [15,88], pieces = [[88],[15]]
输出：true
解释：依次连接 [15] 和 [88]
```

**示例 2：**

```
输入：arr = [49,18,16], pieces = [[16,18,49]]
输出：false
解释：即便数字相符，也不能重新排列 pieces[0]
```

**示例 3：**

```
输入：arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
输出：true
解释：依次连接 [91]、[4,64] 和 [78]
```

 

**提示：**

- `1 <= pieces.length <= arr.length <= 100`
- `sum(pieces[i].length) == arr.length`
- `1 <= pieces[i].length <= arr.length`
- `1 <= arr[i], pieces[i][j] <= 100`
- `arr` 中的整数 **互不相同**
- `pieces` 中的整数 **互不相同**（也就是说，如果将 `pieces` 扁平化成一维数组，数组中的所有整数互不相同）



**暴力**

```java
class Solution {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        int[] map = new int[101];
        for (int i = 0; i < pieces.length; i++) {
            map[pieces[i][0]] = i;
        }
        int p = 0;
        while (p < arr.length) {
            int num = arr[p];
            int[] piece = pieces[map[num]];
            int j = 0;
            while (j < piece.length) {
                if (piece[j] != arr[p]) {
                    return false;
                }
                j++;
                p++;
            }
        }
        return true;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/91dfac30ed914867a1490508cdfbfee6.png)

#### [707. 设计链表](https://leetcode.cn/problems/design-linked-list/)

设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：`val` 和 `next`。`val` 是当前节点的值，`next` 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 `prev` 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。

在链表类中实现这些功能：

- get(index)：获取链表中第 `index` 个节点的值。如果索引无效，则返回`-1`。
- addAtHead(val)：在链表的第一个元素之前添加一个值为 `val` 的节点。插入后，新节点将成为链表的第一个节点。
- addAtTail(val)：将值为 `val` 的节点追加到链表的最后一个元素。
- addAtIndex(index,val)：在链表中的第 `index` 个节点之前添加值为 `val` 的节点。如果 `index` 等于链表的长度，则该节点将附加到链表的末尾。如果 `index` 大于链表长度，则不会插入节点。如果`index`小于0，则在头部插入节点。
- deleteAtIndex(index)：如果索引 `index` 有效，则删除链表中的第 `index` 个节点。

 

**示例：**

```
MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
linkedList.get(1);            //返回2
linkedList.deleteAtIndex(1);  //现在链表是1-> 3
linkedList.get(1);            //返回3
```

 

**提示：**

- 所有`val`值都在 `[1, 1000]` 之内。
- 操作次数将在 `[1, 1000]` 之内。
- 请不要使用内置的 LinkedList 库。

```java
class MyLinkedList {
    int size;
    ListNode head;
    ListNode tail;

    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode curr;
        if (index + 1 < size - index) {
            curr = head;
            for (int i = 0; i <= index; i++) {
                curr = curr.next;
            }
        } else {
            curr = tail;
            for (int i = 0; i < size - index; i++) {
                curr = curr.prev;
            }
        }
        return curr.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        index = Math.max(0, index);
        ListNode pred, succ;
        if (index < size - index) {
            pred = head;
            for (int i = 0; i < index; i++) {
                pred = pred.next;
            }
            succ = pred.next;
        } else {
            succ = tail;
            for (int i = 0; i < size - index; i++) {
                succ = succ.prev;
            }
            pred = succ.prev;
        }
        size++;
        ListNode toAdd = new ListNode(val);
        toAdd.prev = pred;
        toAdd.next = succ;
        pred.next = toAdd;
        succ.prev = toAdd;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        ListNode pred, succ;
        if (index < size - index) {
            pred = head;
            for (int i = 0; i < index; i++) {
                pred = pred.next;
            }
            succ = pred.next.next;
        } else {
            succ = tail;
            for (int i = 0; i < size - index - 1; i++) {
                succ = succ.prev;
            }
            pred = succ.prev.prev;
        }
        size--;
        pred.next = succ;
        succ.prev = pred;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode prev;

    public ListNode(int val) {
        this.val = val;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
```

#### [1652. 拆炸弹](https://leetcode.cn/problems/defuse-the-bomb/)

难度简单66收藏分享切换为英文接收动态反馈

你有一个炸弹需要拆除，时间紧迫！你的情报员会给你一个长度为 `n` 的 **循环** 数组 `code` 以及一个密钥 `k` 。

为了获得正确的密码，你需要替换掉每一个数字。所有数字会 **同时** 被替换。

- 如果 `k > 0` ，将第 `i` 个数字用 **接下来** `k` 个数字之和替换。
- 如果 `k < 0` ，将第 `i` 个数字用 **之前** `k` 个数字之和替换。
- 如果 `k == 0` ，将第 `i` 个数字用 `0` 替换。

由于 `code` 是循环的， `code[n-1]` 下一个元素是 `code[0]` ，且 `code[0]` 前一个元素是 `code[n-1]` 。

给你 **循环** 数组 `code` 和整数密钥 `k` ，请你返回解密后的结果来拆除炸弹！

 

**示例 1：**

```
输入：code = [5,7,1,4], k = 3
输出：[12,10,16,13]
解释：每个数字都被接下来 3 个数字之和替换。解密后的密码为 [7+1+4, 1+4+5, 4+5+7, 5+7+1]。注意到数组是循环连接的。
```

**示例 2：**

```
输入：code = [1,2,3,4], k = 0
输出：[0,0,0,0]
解释：当 k 为 0 时，所有数字都被 0 替换。
```

**示例 3：**

```
输入：code = [2,4,9,3], k = -2
输出：[12,5,6,13]
解释：解密后的密码为 [3+9, 2+3, 4+2, 9+4] 。注意到数组是循环连接的。如果 k 是负数，那么和为 之前 的数字。
```

 

**提示：**

- `n == code.length`
- `1 <= n <= 100`
- `1 <= code[i] <= 100`
- `-(n - 1) <= k <= n - 1`

```java
class Solution {
    public int[] decrypt(int[] code, int k) {
         int n = code.length;
        int[] ans = new int[n];
        if (k == 0) {
            return ans;
        }
        if (k > 0) {
            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= k; j++) {
                    int index = (i + j) % n;
                    ans[i] += code[index];
                }
            }
        }
        if (k < 0) {
            for (int i = 0; i < n; i++) {
                for (int j = -1; j >= k; j--) {
                    int index = (i + j+ n) % n;
                    ans[i] += code[index];
                }
            }
        }
        return ans;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/b15ec84575f84d46ad06e6d78b73b61f.png)

#### [788. 旋转数字](https://leetcode.cn/problems/rotated-digits/)

我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。要求每位数字都要被旋转。

如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。0, 1, 和 8 被旋转后仍然是它们自己；2 和 5 可以互相旋转成对方（在这种情况下，它们以不同的方向旋转，换句话说，2 和 5 互为镜像）；6 和 9 同理，除了这些以外其他的数字旋转以后都不再是有效的数字。

现在我们有一个正整数 `N`, 计算从 `1` 到 `N` 中有多少个数 X 是好数？

 

**示例：**

```
输入: 10
输出: 4
解释: 
在[1, 10]中有四个好数： 2, 5, 6, 9。
注意 1 和 10 不是好数, 因为他们在旋转之后不变。
```

 

**提示：**

- N 的取值范围是 `[1, 10000]`。

**模拟**

```java
class Solution {
    static int[] check = {0, 0, 1, -1, -1, 1, 1, -1, 0, 1};
    public int rotatedDigits(int n) {
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            String s = String.valueOf(i);
            boolean valid = true;
            boolean diff = false;
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (check[c - '0'] == -1) {
                    valid = false;
                } else if (check[c - '0'] == 1) {
                    diff = true;
                }
            }
            if (valid && diff) {
                ++ans;
            }
        }
        return ans;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/0b32e863d4294db3a102c15332a9d392.png)

#### [面试题 17.19. 消失的两个数字](https://leetcode.cn/problems/missing-two-lcci/)

给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？

以任意顺序返回这两个数字均可。

**示例 1:**

```
输入: [1]
输出: [2,3]
```

**示例 2:**

```
输入: [2,3]
输出: [1,4]
```

**提示：**

- `nums.length <= 30000`

```java
class Solution {
    public int[] missingTwo(int[] nums) {
        int xorsum = 0;
        int n = nums.length + 2;
        for (int num : nums) {
            xorsum ^= num;
        }
        for (int i = 1; i <= n; i++) {
            xorsum ^= i;
        }
        // 防止溢出
        int lsb = (xorsum == Integer.MIN_VALUE ? xorsum : xorsum & (-xorsum));
        int type1 = 0, type2 = 0;
        for (int num : nums) {
            if ((num & lsb) != 0) {
                type1 ^= num;
            } else {
                type2 ^= num;
            }
        }
        for (int i = 1; i <= n; i++) {
            if ((i & lsb) != 0) {
                type1 ^= i;
            } else {
                type2 ^= i;
            }
        }
        return new int[]{type1, type2};
    }
}
```

#### [面试题 01.02. 判定是否互为字符重排](https://leetcode.cn/problems/check-permutation-lcci/)

给定两个字符串 `s1` 和 `s2`，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。

**示例 1：**

```
输入: s1 = "abc", s2 = "bca"
输出: true 
```

**示例 2：**

```
输入: s1 = "abc", s2 = "bad"
输出: false
```

**说明：**

- `0 <= len(s1) <= 100`
- `0 <= len(s2) <= 100`

**模拟**

```java
class Solution {
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();
        Arrays.sort(char1);
        Arrays.sort(char2);
        return Arrays.equals(char1, char2);
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/6fc86ade1df941e3a03601aea006dddc.png)

**计数模拟**

```java
class Solution {
    public boolean CheckPermutation(String s1, String s2) {
         if (s1.length() != s2.length()) {
            return false;
        }
        int[] cnts = new int[256];
        int total = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (++cnts[s1.charAt(i)] == 1) {
                total++;
            }
            if (--cnts[s2.charAt(i)] == 0) {
                total--;
            }
        }
        return total == 0;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/6e804b6ffafa420a822a53d82db052b0.png)

#### [面试题 17.09. 第 k 个数](https://leetcode.cn/problems/get-kth-magic-number-lcci/)

有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。

**示例 1:**

```
输入: k = 5

输出: 9
```

```java
class Solution {
    public int getKthMagicNumber(int k) {
        int[] nums = {3, 5, 7};
        // 是否访问 相同算一次
        HashSet<Long> visited = new HashSet<>();
        PriorityQueue<Long> queue = new PriorityQueue<>();
        // 初始值为1
        visited.add(1L);
        queue.offer(1L);
        // 第k个元素
        int target = 0;
        for (int i = 0; i < k; i++) {
            // 弹出堆顶元素
            long poll = queue.poll();
            target = (int) poll;
            for (int num : nums) {
                long cur = poll * num;
                if (!visited.contains(cur)) {
                    visited.add(cur);
                    queue.offer(cur);
                }
            }
        }
        return target;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/5f498e52ebdc4569a47d63892aa8f28d.png)

#### [面试题 01.09. 字符串轮转](https://leetcode.cn/problems/string-rotation-lcci/)

字符串轮转。给定两个字符串`s1`和`s2`，请编写代码检查`s2`是否为`s1`旋转而成（比如，`waterbottle`是`erbottlewat`旋转后的字符串）。

**示例1:**

```
 输入：s1 = "waterbottle", s2 = "erbottlewat"
 输出：True
```

**示例2:**

```
 输入：s1 = "aa", s2 = "aba"
 输出：False
```



**提示：**

1. 字符串长度在[0, 100000]范围内。

**说明:**

1. 你能只调用一次检查子串的方法吗？

**模拟**

```java
class Solution {
    public boolean isFlipedString(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        if (m != n) {
            return false;
        }
        if (n == 0) {
            return true;
        }
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (s1.charAt((i + j) % n) != s2.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/8d7aadd5cba34046875b185dad5f3c00.png)

```java
class Solution {
    public boolean isFlipedString(String s1, String s2) {
        return s1.length() == s2.length() && (s1 + s1).contains(s2);
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/da09b8fcd4dd4da48defafe1f0f6fd61.png)

#### [面试题 01.08. 零矩阵](https://leetcode.cn/problems/zero-matrix-lcci/)

编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。

 

**示例 1：**

```
输入：
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
输出：
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
```

**示例 2：**

```
输入：
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
输出：
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
```

```java
class Solution {
    public void setZeroes(int[][] matrix) {
        //用2个标记数组 记录每一行和每一列是否有0出现 出现就标记数组的位置为true
        int m=matrix.length,n=matrix[0].length;
        boolean[] row=new boolean[m];
        boolean[] col=new boolean[n];
       
       for(int i=0;i<m;i++){
           for(int j=0;j<n;j++){
               if(matrix[i][j]==0){
                   row[i]=col[j]=true;
               }
           }
       }
       // 最后更新原数组
       for(int i=0;i<m;i++){
           for(int j=0;j<n;j++){
               if(row[i]||col[j]){
                   matrix[i][j]=0;
               }
           }
       }
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/e433ddefeb364a80aeecef8be6d7c75f.png)

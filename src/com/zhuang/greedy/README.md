## 贪心

**贪心算法的一般解题步骤**

贪心算法一般分为如下四步：

- 将问题分解为若干个子问题
- 找出适合的贪心策略
- 求解每一个子问题的最优解
- 将局部最优解堆叠成全局最优解

只要想清楚 `局部最优 `是什么，如果推导出`全局最优`，问题就解决了

### [455. 分发饼干](https://leetcode.cn/problems/assign-cookies/)

假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。

对每个孩子 `i`，都有一个胃口值 `g[i]`，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 `j`，都有一个尺寸 `s[j]` 。如果 `s[j] >= g[i]`，我们可以将这个饼干 `j` 分配给孩子 `i` ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。

**示例 1:**

```
输入: g = [1,2,3], s = [1,1]
输出: 1
解释: 
你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
所以你应该输出1。
```

**示例 2:**

```
输入: g = [1,2], s = [1,2,3]
输出: 2
解释: 
你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
你拥有的饼干数量和尺寸都足以让所有孩子满足。
所以你应该输出2.
```

**提示：**

- `1 <= g.length <= 3 * 104`
- `0 <= s.length <= 3 * 104`
- `1 <= g[i], s[j] <= 231 - 1`

#### 思路

满足更多的小孩，也不能浪费饼干，大饼干可以满足小胃口和大胃口的孩子，`优先考虑大胃口`

局部最优：大饼干给大胃口，尽可能多喂饱大胃口

全局最优：喂饱尽可能多的小孩

使用贪心方法，将饼干数组和小孩数组排序，倒序遍历小孩数组，大饼干满足胃口大的。统计小孩的个数

```java
    /**
     * https://leetcode.cn/problems/assign-cookies/
     *
     * @param g 小孩数组
     * @param s 饼干数组
     * @return 满足的小孩数量
     */
    public int findContentChildren(int[] g, int[] s) {
        // 排序
        Arrays.sort(g);
        Arrays.sort(s);
        int start = s.length - 1;
        int count = 0;
        // 先满足大胃口
        for (int index = g.length - 1; index >= 0; index--) {
            if (start >= 0 && g[index] <= s[start]) {
                start--;
                count++;
            }
        }
        return count;
    }
```

或者先满足小胃口的，也可以做到

```java
    public int findContentChildren2(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int start = 0;
        int count = 0;
        for (int i = 0; i < s.length && start < g.length; i++) {
            if (s[i] >= g[start]) {
                start++;
                count++;
            }
        }
        return count;
    }
```


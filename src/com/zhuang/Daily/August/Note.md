开始补做2020.3月份开始的力扣每日一题，争取每个月都拿勋章，注意，要讲究融会贯通，效率之上💪💪💪

## 2020.3

1. [225. 用队列实现栈 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/implement-stack-using-queues/)
2. [206. 反转链表 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/reverse-linked-list/)
3. [面试题 10.01. 合并排序的数组 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/sorted-merge-lcci/)
4. [994. 腐烂的橘子 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/rotting-oranges/)
5. [1103. 分糖果 II - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/distribute-candies-to-people/)
6. [剑指 Offer 57 - II. 和为s的连续正数序列 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/)
7. [剑指 Offer 59 - II. 队列的最大值 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/)
8. [322. 零钱兑换 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/coin-change/)
9. [121. 买卖股票的最佳时机 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/)
10. [543. 二叉树的直径 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/diameter-of-binary-tree/)
11. [1013. 将数组分成和相等的三个部分 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/partition-array-into-three-parts-with-equal-sum/)
12. [1071. 字符串的最大公因子 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/greatest-common-divisor-of-strings/)
13. [169. 多数元素 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/majority-element/)
14. [300. 最长递增子序列 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/longest-increasing-subsequence/)
15. [695. 岛屿的最大面积 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/max-area-of-island/)
16. [面试题 01.06. 字符串压缩 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/compress-string-lcci/)
17. [1160. 拼写单词 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/find-words-that-can-be-formed-by-characters/)
18. [836. 矩形重叠 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/rectangle-overlap/)
19. [409. 最长回文串 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/longest-palindrome/)
20. [剑指 Offer 40. 最小的k个数 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/)
21. [365. 水壶问题 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/water-and-jug-problem/)
22. [945. 使数组唯一的最小增量 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique/)
23. [876. 链表的中间结点 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/middle-of-the-linked-list/)
24. [面试题 17.16. 按摩师 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/the-masseuse-lcci/)
25. [892. 三维形体的表面积 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/surface-area-of-3d-shapes/)
26. [999. 可以被一步捕获的棋子数 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/available-captures-for-rook/)
27. [914. 卡牌分组 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards/)
28. [820. 单词的压缩编码 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/short-encoding-of-words/)
29. [1162. 地图分析 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/as-far-from-land-as-possible/)
30. [剑指 Offer 62. 圆圈中最后剩下的数字 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/)
31. [912. 排序数组 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/sort-an-array/)























[551. 学生出勤记录 I - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/student-attendance-record-i/)
[![fhRptg.png](https://z3.ax1x.com/2021/08/17/fhRptg.png)](https://imgtu.com/i/fhRptg)

- 一次for遍历

```java
class Solution {
    public boolean checkRecord(String s) {
        int absent = 0, late = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == 'A') {
                absent++;
                if (absent >= 2) {
                    return false;
                }
            }
            if (c == 'L') {
                late++;
                if (late >= 3) {
                    return false;
                }
            } else {
                late = 0;
            }
        }
        System.out.println(true);
        return true;
    }
}
```
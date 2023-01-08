#### [2351. 第一个出现两次的字母](https://leetcode.cn/problems/first-letter-to-appear-twice/)

给你一个由小写英文字母组成的字符串 `s` ，请你找出并返回第一个出现 **两次** 的字母。

**注意：**

- 如果 `a` 的 **第二次** 出现比 `b` 的 **第二次** 出现在字符串中的位置更靠前，则认为字母 `a` 在字母 `b` 之前出现两次。
- `s` 包含至少一个出现两次的字母。

 

**示例 1：**

```
输入：s = "abccbaacz"
输出："c"
解释：
字母 'a' 在下标 0 、5 和 6 处出现。
字母 'b' 在下标 1 和 4 处出现。
字母 'c' 在下标 2 、3 和 7 处出现。
字母 'z' 在下标 8 处出现。
字母 'c' 是第一个出现两次的字母，因为在所有字母中，'c' 第二次出现的下标是最小的。
```

**示例 2：**

```
输入：s = "abcdd"
输出："d"
解释：
只有字母 'd' 出现两次，所以返回 'd' 。
```

 

**提示：**

- `2 <= s.length <= 100`
- `s` 由小写英文字母组成
- `s` 包含至少一个重复字母

```java
class Solution {
    public char repeatedCharacter(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char res = 0;
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) == 2) {
                res = c;
                break;
            }
        }
        return res;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/c0427bdf592d4f2f84dc234872343821.png)





#### [1801. 积压订单中的订单总数](https://leetcode.cn/problems/number-of-orders-in-the-backlog/)

难度中等42

给你一个二维整数数组 `orders` ，其中每个 `orders[i] = [pricei, amounti, orderTypei]` 表示有 `amounti` 笔类型为 `orderTypei` 、价格为 `pricei` 的订单。

订单类型 `orderTypei` 可以分为两种：

- `0` 表示这是一批采购订单 `buy`
- `1` 表示这是一批销售订单 `sell`

注意，`orders[i]` 表示一批共计 `amounti` 笔的独立订单，这些订单的价格和类型相同。对于所有有效的 `i` ，由 `orders[i]` 表示的所有订单提交时间均早于 `orders[i+1]` 表示的所有订单。

存在由未执行订单组成的 **积压订单** 。积压订单最初是空的。提交订单时，会发生以下情况：

- 如果该订单是一笔采购订单 `buy` ，则可以查看积压订单中价格 **最低** 的销售订单 `sell` 。如果该销售订单 `sell` 的价格 **低于或等于** 当前采购订单 `buy` 的价格，则匹配并执行这两笔订单，并将销售订单 `sell` 从积压订单中删除。否则，采购订单 `buy` 将会添加到积压订单中。
- 反之亦然，如果该订单是一笔销售订单 `sell` ，则可以查看积压订单中价格 **最高** 的采购订单 `buy` 。如果该采购订单 `buy` 的价格 **高于或等于** 当前销售订单 `sell` 的价格，则匹配并执行这两笔订单，并将采购订单 `buy` 从积压订单中删除。否则，销售订单 `sell` 将会添加到积压订单中。

输入所有订单后，返回积压订单中的 **订单总数** 。由于数字可能很大，所以需要返回对 `109 + 7` 取余的结果。

 

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/03/21/ex1.png)

```
输入：orders = [[10,5,0],[15,2,1],[25,1,1],[30,4,0]]
输出：6
解释：输入订单后会发生下述情况：
- 提交 5 笔采购订单，价格为 10 。没有销售订单，所以这 5 笔订单添加到积压订单中。
- 提交 2 笔销售订单，价格为 15 。没有采购订单的价格大于或等于 15 ，所以这 2 笔订单添加到积压订单中。
- 提交 1 笔销售订单，价格为 25 。没有采购订单的价格大于或等于 25 ，所以这 1 笔订单添加到积压订单中。
- 提交 4 笔采购订单，价格为 30 。前 2 笔采购订单与价格最低（价格为 15）的 2 笔销售订单匹配，从积压订单中删除这 2 笔销售订单。第 3 笔采购订单与价格最低的 1 笔销售订单匹配，销售订单价格为 25 ，从积压订单中删除这 1 笔销售订单。积压订单中不存在更多销售订单，所以第 4 笔采购订单需要添加到积压订单中。
最终，积压订单中有 5 笔价格为 10 的采购订单，和 1 笔价格为 30 的采购订单。所以积压订单中的订单总数为 6 。
```

**示例 2：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/03/21/ex2.png)

```
输入：orders = [[7,1000000000,1],[15,3,0],[5,999999995,0],[5,1,1]]
输出：999999984
解释：输入订单后会发生下述情况：
- 提交 109 笔销售订单，价格为 7 。没有采购订单，所以这 109 笔订单添加到积压订单中。
- 提交 3 笔采购订单，价格为 15 。这些采购订单与价格最低（价格为 7 ）的 3 笔销售订单匹配，从积压订单中删除这 3 笔销售订单。
- 提交 999999995 笔采购订单，价格为 5 。销售订单的最低价为 7 ，所以这 999999995 笔订单添加到积压订单中。
- 提交 1 笔销售订单，价格为 5 。这笔销售订单与价格最高（价格为 5 ）的 1 笔采购订单匹配，从积压订单中删除这 1 笔采购订单。
最终，积压订单中有 (1000000000-3) 笔价格为 7 的销售订单，和 (999999995-1) 笔价格为 5 的采购订单。所以积压订单中的订单总数为 1999999991 ，等于 999999984 % (109 + 7) 。
```

 

**提示：**

- `1 <= orders.length <= 105`
- `orders[i].length == 3`
- `1 <= pricei, amounti <= 109`
- `orderTypei` 为 `0` 或 `1`

```java
class Solution {
    public int getNumberOfBacklogOrders(int[][] orders) {
        final int MOD = 1000000007;
        PriorityQueue<int[]> buyOrders = new PriorityQueue<int[]>((a, b) -> b[0] - a[0]);
        PriorityQueue<int[]> sellOrders = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
        for (int[] order : orders) {
            int price = order[0], amount = order[1], orderType = order[2];
            if (orderType == 0) {
                while (amount > 0 && !sellOrders.isEmpty() && sellOrders.peek()[0] <= price) {
                    int[] sellOrder = sellOrders.poll();
                    int sellAmount = Math.min(amount, sellOrder[1]);
                    amount -= sellAmount;
                    sellOrder[1] -= sellAmount;
                    if (sellOrder[1] > 0) {
                        sellOrders.offer(sellOrder);
                    }
                }
                if (amount > 0) {
                    buyOrders.offer(new int[]{price, amount});
                }
            } else {
                while (amount > 0 && !buyOrders.isEmpty() && buyOrders.peek()[0] >= price) {
                    int[] buyOrder = buyOrders.poll();
                    int buyAmount = Math.min(amount, buyOrder[1]);
                    amount -= buyAmount;
                    buyOrder[1] -= buyAmount;
                    if (buyOrder[1] > 0) {
                        buyOrders.offer(buyOrder);
                    }
                }
                if (amount > 0) {
                    sellOrders.offer(new int[]{price, amount});
                }
            }
        }
        int total = 0;
        for (PriorityQueue<int[]> pq : Arrays.asList(buyOrders, sellOrders)) {
            while (!pq.isEmpty()) {
                int[] order = pq.poll();
                total = (total + order[1]) % MOD;
            }
        }
        return total;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/8f98da328cc2490ba0c88bb80cce0980.png)

#### [2042. 检查句子中的数字是否递增](https://leetcode.cn/problems/check-if-numbers-are-ascending-in-a-sentence/)

句子是由若干 **token** 组成的一个列表，**token** 间用 **单个** 空格分隔，句子没有前导或尾随空格。每个 token 要么是一个由数字 `0-9` 组成的不含前导零的 **正整数** ，要么是一个由小写英文字母组成的 **单词** 。

- 示例，`"a puppy has 2 eyes 4 legs"` 是一个由 7 个 token 组成的句子：`"2"` 和 `"4"` 是数字，其他像 `"puppy"` 这样的 tokens 属于单词。

给你一个表示句子的字符串 `s` ，你需要检查 `s` 中的 **全部** 数字是否从左到右严格递增（即，除了最后一个数字，`s` 中的 **每个** 数字都严格小于它 **右侧** 的数字）。

如果满足题目要求，返回 `true` ，否则，返回 `false` 。

 

**示例 1：**

![example-1](https://assets.leetcode.com/uploads/2021/09/30/example1.png)

```
输入：s = "1 box has 3 blue 4 red 6 green and 12 yellow marbles"
输出：true
解释：句子中的数字是：1, 3, 4, 6, 12 。
这些数字是按从左到右严格递增的 1 < 3 < 4 < 6 < 12 。
```

**示例 2：**

```
输入：s = "hello world 5 x 5"
输出：false
解释：句子中的数字是：5, 5 。这些数字不是严格递增的。
```

**示例 3：**

![example-3](https://assets.leetcode.com/uploads/2021/09/30/example3.png)

```
输入：s = "sunset is at 7 51 pm overnight lows will be in the low 50 and 60 s"
输出：false
解释：s 中的数字是：7, 51, 50, 60 。这些数字不是严格递增的。
```

**示例 4：**

```
输入：s = "4 5 11 26"
输出：true
解释：s 中的数字是：4, 5, 11, 26 。
这些数字是按从左到右严格递增的：4 < 5 < 11 < 26 。
```

 

**提示：**

- `3 <= s.length <= 200`
- `s` 由小写英文字母、空格和数字 `0` 到 `9` 组成（包含 `0` 和 `9`）
- `s` 中数字 token 的数目在 `2` 和 `100` 之间（包含 `2` 和 `100`）
- `s` 中的 token 之间由单个空格分隔
- `s` 中至少有 **两个** 数字
- `s` 中的每个数字都是一个 **小于** `100` 的 **正** 数，且不含前导零
- `s` 不含前导或尾随空格

```java
class Solution {
    public boolean areNumbersAscending(String s) {
        int pre = 0, pos = 0;
        while (pos < s.length()) {
            if (Character.isDigit(s.charAt(pos))) {
                int cur = 0;
                while (pos < s.length() && Character.isDigit(s.charAt(pos))) {
                    cur = cur * 10 + s.charAt(pos) - '0';
                    pos++;
                }
                if (cur <= pre) {
                    return false;
                }
                pre = cur;
            } else {
                pos++;
            }
        }
        return true;
    }
}
```

#### [1802. 有界数组中指定下标处的最大值](https://leetcode.cn/problems/maximum-value-at-a-given-index-in-a-bounded-array/)

给你三个正整数 `n`、`index` 和 `maxSum` 。你需要构造一个同时满足下述所有条件的数组 `nums`（下标 **从 0 开始** 计数）：

- `nums.length == n`
- `nums[i]` 是 **正整数** ，其中 `0 <= i < n`
- `abs(nums[i] - nums[i+1]) <= 1` ，其中 `0 <= i < n-1`
- `nums` 中所有元素之和不超过 `maxSum`
- `nums[index]` 的值被 **最大化**

返回你所构造的数组中的 `nums[index]` 。

注意：`abs(x)` 等于 `x` 的前提是 `x >= 0` ；否则，`abs(x)` 等于 `-x` 。

 

**示例 1：**

```
输入：n = 4, index = 2,  maxSum = 6
输出：2
解释：数组 [1,1,2,1] 和 [1,2,2,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
```

**示例 2：**

```
输入：n = 6, index = 1,  maxSum = 10
输出：3
```

 

**提示：**

- `1 <= n <= maxSum <= 109`
- `0 <= index < n`

```Java
class Solution {
    public int maxValue(int n, int index, int maxSum) {
        long l = 0, r = maxSum;
        //因为数组中所有的数均为正整数，所以减去n，剩余的表示可以填的数
        maxSum -= n;
        while(l<r){
            //m表示index指向位置的高度
            long m = l+r+1>>1;

            //计算当index的位置高度为m时，数组所有元素的和
            long count = m*m;
            //如果左边越界，就减去左边多的
            if(m>index) count -= (m-index-1)*(m-index)/2;
            //如果右边越界，就减去右边多的
            if(m>(n-index)) count -= (m-(n-index-1)-1)*(m-(n-index-1))/2;

            //二分法判断
            if(count>maxSum) r = m-1;
            else l = m;
        }
        return (int)l+1;
    }
}
```

#### [1803. 统计异或值在范围内的数对有多少](https://leetcode.cn/problems/count-pairs-with-xor-in-a-range/)

给你一个整数数组 `nums` （下标 **从 0 开始** 计数）以及两个整数：`low` 和 `high` ，请返回 **漂亮数对** 的数目。

**漂亮数对** 是一个形如 `(i, j)` 的数对，其中 `0 <= i < j < nums.length` 且 `low <= (nums[i] XOR nums[j]) <= high` 。

 

**示例 1：**

```
输入：nums = [1,4,2,7], low = 2, high = 6
输出：6
解释：所有漂亮数对 (i, j) 列出如下：
    - (0, 1): nums[0] XOR nums[1] = 5 
    - (0, 2): nums[0] XOR nums[2] = 3
    - (0, 3): nums[0] XOR nums[3] = 6
    - (1, 2): nums[1] XOR nums[2] = 6
    - (1, 3): nums[1] XOR nums[3] = 3
    - (2, 3): nums[2] XOR nums[3] = 5
```

**示例 2：**

```
输入：nums = [9,8,4,2,1], low = 5, high = 14
输出：8
解释：所有漂亮数对 (i, j) 列出如下：
    - (0, 2): nums[0] XOR nums[2] = 13
    - (0, 3): nums[0] XOR nums[3] = 11
    - (0, 4): nums[0] XOR nums[4] = 8
    - (1, 2): nums[1] XOR nums[2] = 12
    - (1, 3): nums[1] XOR nums[3] = 10
    - (1, 4): nums[1] XOR nums[4] = 9
    - (2, 3): nums[2] XOR nums[3] = 6
    - (2, 4): nums[2] XOR nums[4] = 5
```

 

**提示：**

- `1 <= nums.length <= 2 * 104`
- `1 <= nums[i] <= 2 * 104`
- `1 <= low <= high <= 2 * 104`

```java
class Solution {
// 字典树的根节点
    private Trie root = null;
    // 最高位的二进制位编号为 14
    private static final int HIGH_BIT = 14;

    public int countPairs(int[] nums, int low, int high) {
        return f(nums, high) - f(nums, low - 1);
    }

    public int f(int[] nums, int x) {
        root = new Trie();
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            add(nums[i - 1]);
            res += get(nums[i], x);
        }
        return res;
    }

    public void add(int num) {
        Trie cur = root;
        for (int k = HIGH_BIT; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (cur.son[bit] == null) {
                cur.son[bit] = new Trie();
            }
            cur = cur.son[bit];
            cur.sum++;
        }
    }

    public int get(int num, int x) {
        Trie cur = root;
        int sum = 0;
        for (int k = HIGH_BIT; k >= 0; k--) {
            int r = (num >> k) & 1;
            if (((x >> k) & 1) != 0) {
                if (cur.son[r] != null) {
                    sum += cur.son[r].sum;
                }
                if (cur.son[r ^ 1] == null) {
                    return sum;
                }
                cur = cur.son[r ^ 1];
            } else {
                if (cur.son[r] == null) {
                    return sum;
                }
                cur = cur.son[r];
            }
        }
        sum += cur.sum;
        return sum;
    }
}

class Trie {
    // son[0] 表示左子树，son[1] 表示右子树
    Trie[] son = new Trie[2];
    int sum;

    public Trie() {
        sum = 0;
    }
}
```

#### [2180. 统计各位数字之和为偶数的整数个数](https://leetcode.cn/problems/count-integers-with-even-digit-sum/)

给你一个正整数 `num` ，请你统计并返回 **小于或等于** `num` 且各位数字之和为 **偶数** 的正整数的数目。

正整数的 **各位数字之和** 是其所有位上的对应数字相加的结果。

 

**示例 1：**

```
输入：num = 4
输出：2
解释：
只有 2 和 4 满足小于等于 4 且各位数字之和为偶数。    
```

**示例 2：**

```
输入：num = 30
输出：14
解释：
只有 14 个整数满足小于等于 30 且各位数字之和为偶数，分别是： 
2、4、6、8、11、13、15、17、19、20、22、24、26 和 28 。
```

 

**提示：**

- `1 <= num <= 1000`

```java
class Solution {
    public int countEven(int num) {
        int res = 0;
        for (int i = 1; i <= num; i++) {
            int x = i, sum = 0;
            while (x != 0) {
                sum += x % 10;
                x /= 10;
            }
            if (sum % 2 == 0) {
                res++;
            }
        }
        return res;
    }
}
```

#### [1658. 将 x 减到 0 的最小操作数](https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/)

给你一个整数数组 `nums` 和一个整数 `x` 。每一次操作时，你应当移除数组 `nums` 最左边或最右边的元素，然后从 `x` 中减去该元素的值。请注意，需要 **修改** 数组以供接下来的操作使用。

如果可以将 `x` **恰好** 减到 `0` ，返回 **最小操作数** ；否则，返回 `-1` 。

 

**示例 1：**

```
输入：nums = [1,1,4,2,3], x = 5
输出：2
解释：最佳解决方案是移除后两个元素，将 x 减到 0 。
```

**示例 2：**

```
输入：nums = [5,6,7,8,9], x = 4
输出：-1
```

**示例 3：**

```
输入：nums = [3,2,20,1,1,3], x = 10
输出：5
解释：最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
```

 

**提示：**

- `1 <= nums.length <= 105`
- `1 <= nums[i] <= 104`
- `1 <= x <= 109`

```java
class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();

        if (sum < x) {
            return -1;
        }

        int right = 0;
        int lsum = 0, rsum = sum;
        int ans = n + 1;

        for (int left = -1; left < n; ++left) {
            if (left != -1) {
                lsum += nums[left];
            }
            while (right < n && lsum + rsum > x) {
                rsum -= nums[right];
                ++right;
            }
            if (lsum + rsum == x) {
                ans = Math.min(ans, (left + 1) + (n - right));
            }
        }

        return ans > n ? -1 : ans;
    }
}
```

#### [2185. 统计包含给定前缀的字符串](https://leetcode.cn/problems/counting-words-with-a-given-prefix/)

给你一个字符串数组 `words` 和一个字符串 `pref` 。

返回 `words` 中以 `pref` 作为 **前缀** 的字符串的数目。

字符串 `s` 的 **前缀** 就是 `s` 的任一前导连续字符串。

 

**示例 1：**

```
输入：words = ["pay","attention","practice","attend"], pref = "at"
输出：2
解释：以 "at" 作为前缀的字符串有两个，分别是："attention" 和 "attend" 。
```

**示例 2：**

```
输入：words = ["leetcode","win","loops","success"], pref = "code"
输出：0
解释：不存在以 "code" 作为前缀的字符串。
```

 

**提示：**

- `1 <= words.length <= 100`
- `1 <= words[i].length, pref.length <= 100`
- `words[i]` 和 `pref` 由小写英文字母组成

```Java
class Solution {
    public int prefixCount(String[] words, String pref) {
        int res = 0;
        for (String word : words) {
            if (word.startsWith(pref)) {
                res++;
            }
        }
        return res;
    }
}
```


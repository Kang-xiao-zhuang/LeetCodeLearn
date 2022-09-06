#### [704. 二分查找](https://leetcode.cn/problems/binary-search/)

难度简单971收藏分享切换为英文接收动态反馈

给定一个 `n` 个元素有序的（升序）整型数组 `nums` 和一个目标值 `target` ，写一个函数搜索 `nums` 中的 `target`，如果目标值存在返回下标，否则返回 `-1`。


**示例 1:**

```
输入: nums = [-1,0,3,5,9,12], target = 9
输出: 4
解释: 9 出现在 nums 中并且下标为 4
```

**示例 2:**

```
输入: nums = [-1,0,3,5,9,12], target = 2
输出: -1
解释: 2 不存在 nums 中因此返回 -1
```

 

**提示：**

1. 你可以假设 `nums` 中的所有元素是不重复的。
2. `n` 将在 `[1, 10000]`之间。
3. `nums` 的每个元素都将在 `[-9999, 9999]`之间。



普通的解法，二分法

```java
class Solution {
    public int search(int[] nums, int target) {
        // 定义左指针
        int low=0;
        // 定义右指针
        int high=nums.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
            return -1;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/203011941e7247bb9e8f69c18f9f808f.png)





#### [374. 猜数字大小](https://leetcode.cn/problems/guess-number-higher-or-lower/)

难度简单247收藏分享切换为英文接收动态反馈

猜数字游戏的规则如下：

- 每轮游戏，我都会从 **1** 到 ***n*** 随机选择一个数字。 请你猜选出的是哪个数字。
- 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。

你可以通过调用一个预先定义好的接口 `int guess(int num)` 来获取猜测结果，返回值一共有 3 种可能的情况（`-1`，`1` 或 `0`）：

- -1：我选出的数字比你猜的数字小 `pick < num`
- 1：我选出的数字比你猜的数字大 `pick > num`
- 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！`pick == num`

返回我选出的数字。

 

**示例 1：**

```
输入：n = 10, pick = 6
输出：6
```

**示例 2：**

```
输入：n = 1, pick = 1
输出：1
```

**示例 3：**

```
输入：n = 2, pick = 1
输出：1
```

**示例 4：**

```
输入：n = 2, pick = 2
输出：2
```

 

**提示：**

- `1 <= n <= 231 - 1`
- `1 <= pick <= n`



二分法

记作选中的数字为`target`，猜测的数字为`x`，根据题目描述，如果 guess(x) ≤0 则说明x≥`target`，否则x<`target`

二分时，记当前区间为[low,high]，初始时low=1，high=n。记区间中间元素为mid，若有guess(mid)≤0 则说明 `target`∈[low,mid]，否则`target`∈[mid+1,high]。当区间左右端点相同时，则说明我们找到了答案，退出循环。

```java
/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is lower than the guess number
 *			      1 if num is higher than the guess number
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int low=1;
        int high=n;
        while(low<high){
            int mid=low+(high-low)/2;
            if(guess(mid)==1){
                low=mid+1;
            }else{
                high=mid;
            }
        }
        return low;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/21bc91fc2b6841d5ba96d020d2bfc3a3.png)
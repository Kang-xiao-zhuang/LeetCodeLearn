#### [704. 二分查找](https://leetcode.cn/problems/binary-search/)


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

#### [35. 搜索插入位置](https://leetcode.cn/problems/search-insert-position/)

给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

请必须使用时间复杂度为 `O(log n)` 的算法。

 

**示例 1:**

```
输入: nums = [1,3,5,6], target = 5
输出: 2
```

**示例 2:**

```
输入: nums = [1,3,5,6], target = 2
输出: 1
```

**示例 3:**

```
输入: nums = [1,3,5,6], target = 7
输出: 4
```

 

**提示:**

- `1 <= nums.length <= 104`
- `-104 <= nums[i] <= 104`
- `nums` 为 **无重复元素** 的 **升序** 排列数组
- `-104 <= target <= 104`

枚举即可



```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            if(nums[i]>=target){
                return i;
            }
        }
        return nums.length;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/6fddf6aaaca54a66959b3488eb3ad270.png)

双指针法

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        // 双指针
        int low=0;
        int high=nums.length-1;
        while(low<=high){
            int mid=(low+high)>>1;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return low;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/255d69bbfc5b4d54adc8cd519ee7f8c5.png)

#### [852. 山脉数组的峰顶索引](https://leetcode.cn/problems/peak-index-in-a-mountain-array/)

符合下列属性的数组 `arr` 称为 **山脉数组** ：

- `arr.length >= 3`
- 存在`i`（`0 < i < arr.length - 1`）使得：
  - `arr[0] < arr[1] < ... arr[i-1] < arr[i]`
  - `arr[i] > arr[i+1] > ... > arr[arr.length - 1]`

给你由整数组成的山脉数组 `arr` ，返回任何满足 `arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1]` 的下标 `i` 。

 

**示例 1：**

```
输入：arr = [0,1,0]
输出：1
```

**示例 2：**

```
输入：arr = [0,2,1,0]
输出：1
```

**示例 3：**

```
输入：arr = [0,10,5,2]
输出：1
```

**示例 4：**

```
输入：arr = [3,4,5,1]
输出：2
```

**示例 5：**

```
输入：arr = [24,69,100,99,79,78,67,36,26,19]
输出：2
```

 

**提示：**

- `3 <= arr.length <= 104`
- `0 <= arr[i] <= 106`
- 题目数据保证 `arr` 是一个山脉数组

 

**进阶：**很容易想到时间复杂度 `O(n)` 的解决方案，你可以设计一个 `O(log(n))` 的解决方案吗？



枚举

山峰的数组是这么定义的

从小到大，大到一个峰值，开始慢慢减小，所以枚举前后的数值，不断维护`ans`的值即可



```java
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int n=arr.length;
        int ans=-1;
        for(int i=1;i<n-1;i++){
            if(arr[i]>arr[i+1]){
                ans=i;
                break;
            }
        }
        return ans;
    }
}
```

**复杂度分析**

时间复杂度：O(n)，其中 n是数组 arr 的长度。我们最多需要对数组 arr 进行一次遍历。

空间复杂度：O(1)



![在这里插入图片描述](https://img-blog.csdnimg.cn/cbec03c3c4cc4bbe882a1a7ec9710641.png)





二分法

```java
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int left = 1, right = n - 2, ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > arr[mid + 1]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
```

**复杂度分析**

时间复杂度：O(logn)，其中 n 是数组 arr 的长度。我们需要进行二分查找的次数为 O(logn)

空间复杂度：O(1)



![在这里插入图片描述](https://img-blog.csdnimg.cn/08b3885a69ef4ba3bfb00aa765be0a3b.png)





#### [367. 有效的完全平方数](https://leetcode.cn/problems/valid-perfect-square/)

给定一个 **正整数** `num` ，编写一个函数，如果 `num` 是一个完全平方数，则返回 `true` ，否则返回 `false` 。

**进阶：不要** 使用任何内置的库函数，如 `sqrt` 。

 

**示例 1：**

```
输入：num = 16
输出：true
```

**示例 2：**

```
输入：num = 14
输出：false
```

 

**提示：**

- `1 <= num <= 2^31 - 1`



```java
class Solution {
    public boolean isPerfectSquare(int num) {
        long res=num;
        while(res*res>num){
            res=(res+num/res)/2;
        }
        return res*res==num;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/80692fea9e2f494b9a13db414dd2469d.png)

暴力

```java
class Solution {
    public boolean isPerfectSquare(int num) {
        long x = 1, square = 1;
        while (square <= num) {
            if (square == num) {
                return true;
            }
            ++x;
            square = x * x;
        }
        return false;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/8123c2c953ec4aa0b258e1fe51eec3aa.png)

使用类库

```java
class Solution {
    public boolean isPerfectSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/656751180c8241789470519682de6847.png)

二分法

```java
class Solution {
    public boolean isPerfectSquare(int num) {
       int left = 1;
        int right = num;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int temp = num / mid;
            if (mid * mid == num) {
                return true;
            } else if (temp < mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/5c76ea420d2b482c9b59fd3c18ad4eb3.png)

#### [1385. 两个数组间的距离值](https://leetcode.cn/problems/find-the-distance-value-between-two-arrays/)

给你两个整数数组 `arr1` ， `arr2` 和一个整数 `d` ，请你返回两个数组之间的 **距离值** 。

「**距离值**」 定义为符合此距离要求的元素数目：对于元素 `arr1[i]` ，不存在任何元素 `arr2[j]` 满足 `|arr1[i]-arr2[j]| <= d` 。

 

**示例 1：**

```
输入：arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
输出：2
解释：
对于 arr1[0]=4 我们有：
|4-10|=6 > d=2 
|4-9|=5 > d=2 
|4-1|=3 > d=2 
|4-8|=4 > d=2 
所以 arr1[0]=4 符合距离要求

对于 arr1[1]=5 我们有：
|5-10|=5 > d=2 
|5-9|=4 > d=2 
|5-1|=4 > d=2 
|5-8|=3 > d=2
所以 arr1[1]=5 也符合距离要求

对于 arr1[2]=8 我们有：
|8-10|=2 <= d=2
|8-9|=1 <= d=2
|8-1|=7 > d=2
|8-8|=0 <= d=2
存在距离小于等于 2 的情况，不符合距离要求 

故而只有 arr1[0]=4 和 arr1[1]=5 两个符合距离要求，距离值为 2
```

**示例 2：**

```
输入：arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
输出：2
```

**示例 3：**

```
输入：arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
输出：1
```

 

**提示：**

- `1 <= arr1.length, arr2.length <= 500`
- `-10^3 <= arr1[i], arr2[j] <= 10^3`
- `0 <= d <= 100`

模拟

```java
class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int cnt = 0;
        for (int i : arr1) {
            boolean ok = true;
            for (int j : arr2) {
                ok &= Math.abs(i - j) > d;
            }
            cnt += ok ? 1 : 0;
        }
        return cnt;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/32a650bf155f4967af4da859891dfaf5.png)

二分法

```java
class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
         Arrays.sort(arr2);
        int cnt = 0;
        for (int i : arr1) {
            int p = binarySearch(arr2, i);
            boolean ok = true;
            if (p < arr2.length) {
                ok &= arr2[p] - i > d;
            }
            if (p - 1 >= 0 && p - 1 <= arr2.length) {
                ok &= i - arr2[p - 1] > d;
            }
            cnt += ok ? 1 : 0;
        }
        return cnt;
    }

    private int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        if (arr[high] < target) {
            return high + 1;
        }
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/a855e464c16a444d9b5727e35bd8b9cc.png)

#### [69. x 的平方根 ](https://leetcode.cn/problems/sqrtx/)

给你一个非负整数 `x` ，计算并返回 `x` 的 **算术平方根** 。

由于返回类型是整数，结果只保留 **整数部分** ，小数部分将被 **舍去 。**

**注意：**不允许使用任何内置指数函数和算符，例如 `pow(x, 0.5)` 或者 `x ** 0.5` 。

 

**示例 1：**

```
输入：x = 4
输出：2
```

**示例 2：**

```
输入：x = 8
输出：2
解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
```

 

**提示：**

- `0 <= x <= 231 - 1`



二分

```java
class Solution {
    public int mySqrt(int x) {
        int left=0;
        int right=x;
        int res=-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if((long)mid*mid<=x){
                res=mid;
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return res;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/f07ac269fdde416b8e5b4b08af7b0d1a.png)

#### [744. 寻找比目标字母大的最小字母](https://leetcode.cn/problems/find-smallest-letter-greater-than-target/)

给你一个排序后的字符列表 `letters` ，列表中只包含小写英文字母。另给出一个目标字母 `target`，请你寻找在这一有序列表里比目标字母大的最小字母。

在比较时，字母是依序循环出现的。举个例子：

- 如果目标字母 `target = 'z'` 并且字符列表为 `letters = ['a', 'b']`，则答案返回 `'a'`

 

**示例 1：**

```
输入: letters = ["c", "f", "j"]，target = "a"
输出: "c"
```

**示例 2:**

```
输入: letters = ["c","f","j"], target = "c"
输出: "f"
```

**示例 3:**

```
输入: letters = ["c","f","j"], target = "d"
输出: "f"
```

 

**提示：**

- `2 <= letters.length <= 104`
- `letters[i]` 是一个小写字母
- `letters` 按非递减顺序排序
- `letters` 最少包含两个不同的字母
- `target` 是一个小写字母



遍历即可，数组是排序过的，循环比较即可

```java
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        for (char letter : letters) {
            if (letter > target) {
                return letter;
            }
        }
        return letters[0];
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/c203307e3ba14001b4eaca64c10df6a7.png)



记录出现的字符



```java
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
         boolean[] seen = new boolean[26];
        for (char c : letters) {
            seen[c - 'a'] = true;
        }
        while (true) {
            target++;
            if (target > 'z') {
                target = 'a';
            }
            if (seen[target - 'a']) {
                return target;
            }
        }
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/7ffb9e4f16184d96b8c07d7a8ab967ce.png)

二分法

```java
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
         int left = 0;
        int right = letters.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // 索引%数组长度可以得到索引值，可以通过索引获取到数组中的数
        return letters[left % letters.length];
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/69de2ae08f57482792c3f8e29c3e238a.png)

#### [278. 第一个错误的版本](https://leetcode.cn/problems/first-bad-version/)

你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。

假设你有 `n` 个版本 `[1, 2, ..., n]`，你想找出导致之后所有版本出错的第一个错误的版本。

你可以通过调用 `bool isBadVersion(version)` 接口来判断版本号 `version` 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。

**示例 1：**

```
输入：n = 5, bad = 4
输出：4
解释：
调用 isBadVersion(3) -> false 
调用 isBadVersion(5) -> true 
调用 isBadVersion(4) -> true
所以，4 是第一个错误的版本。
```

**示例 2：**

```
输入：n = 1, bad = 1
输出：1
```

 

**提示：**

- `1 <= bad <= n <= 231 - 1`

二分

```java
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
         int left=1;
        int right=n;
        while(left<right){
         //   int mid=left+(right-left)/2;
            int mid=left+(right-left>>1);
            if(isBadVersion(mid)){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return left;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20a1d49ea63a4a88a2020b9b101de98d.png)

#### [34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/)

给你一个按照非递减顺序排列的整数数组 `nums`，和一个目标值 `target`。请你找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 `target`，返回 `[-1, -1]`。

你必须设计并实现时间复杂度为 `O(log n)` 的算法解决此问题。

 

**示例 1：**

```
输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
```

**示例 2：**

```
输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
```

**示例 3：**

```
输入：nums = [], target = 0
输出：[-1,-1]
```

 

**提示：**

- `0 <= nums.length <= 105`
- `-109 <= nums[i] <= 109`
- `nums` 是一个非递减数组
- `-109 <= target <= 109`

官方题解                      

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rigfhtIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rigfhtIdx && rigfhtIdx < nums.length && nums[leftIdx] == target && nums[rigfhtIdx] == target) {
            return new int[]{leftIdx, rigfhtIdx};
        }
        return new int[]{-1, -1};
    }

    /**
     * @param nums   数组
     * @param target 目标值
     * @param lower  布尔
     * @return int
     */
    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/c5d9690225c94ea3af2050d095462df6.png)

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        res[0] = binarySearch(nums, target, true);
        res[1] = binarySearch(nums, target, false);
        return res;
    }

    /**
     * @param nums        数组
     * @param target      目标值
     * @param leftOrRight 为true找左边界 false找右边界
     * @return int
     */
    public static int binarySearch(int[] nums, int target, boolean leftOrRight) {
        int cnt = -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                cnt = mid;
                //处理target == nums[mid]
                if (leftOrRight) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return cnt;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/cc5330344dd44cd9a9d7c0f63d8e098f.png)

#### [441. 排列硬币](https://leetcode.cn/problems/arranging-coins/)

你总共有 `n` 枚硬币，并计划将它们按阶梯状排列。对于一个由 `k` 行组成的阶梯，其第 `i` 行必须正好有 `i` 枚硬币。阶梯的最后一行 **可能** 是不完整的。

给你一个数字 `n` ，计算并返回可形成 **完整阶梯行** 的总行数。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/04/09/arrangecoins1-grid.jpg)

```
输入：n = 5
输出：2
解释：因为第三行不完整，所以返回 2 。
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2021/04/09/arrangecoins2-grid.jpg)

```
输入：n = 8
输出：3
解释：因为第四行不完整，所以返回 3 。
```

 

**提示：**

- `1 <= n <= 231 - 1`

迭代

```java
class Solution {
    public int arrangeCoins(int n) {
        int i = 1;
        while (n >= i) {
            n -= i;
            i++;
        }
        return i - 1;
    }
}
```



![在这里插入图片描述](https://img-blog.csdnimg.cn/1bc75823abab4b8aafb909c259fc3cc6.png)

二分

```java
class Solution {
    public int arrangeCoins(int n) {
        long left = 1, right = n;
        while (left < right) {
            long mid = (left + right + 1) >> 1;
            if (mid * (mid + 1) / 2 <= n) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return (int) left;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/fe45f7f9db244f22b710c28aa2667d2b.png)

#### [1539. 第 k 个缺失的正整数](https://leetcode.cn/problems/kth-missing-positive-number/)

给你一个 **严格升序排列** 的正整数数组 `arr` 和一个整数 `k` 。

请你找到这个数组里第 `k` 个缺失的正整数。

 

**示例 1：**

```
输入：arr = [2,3,4,7,11], k = 5
输出：9
解释：缺失的正整数包括 [1,5,6,8,9,10,12,13,...] 。第 5 个缺失的正整数为 9 。
```

**示例 2：**

```
输入：arr = [1,2,3,4], k = 2
输出：6
解释：缺失的正整数包括 [5,6,7,...] 。第 2 个缺失的正整数为 6 。
```

 

**提示：**

- `1 <= arr.length <= 1000`
- `1 <= arr[i] <= 1000`
- `1 <= k <= 1000`
- 对于所有 `1 <= i < j <= arr.length` 的 `i` 和 `j` 满足 `arr[i] < arr[j]` 

 

**进阶：**

你可以设计一个时间复杂度小于 O(n) 的算法解决此问题吗？



迭代

```java
class Solution {
    public int findKthPositive(int[] arr, int k) {
         for (int i : arr) {
            if (i <= k) {
                k++;
            }
        }
        return k;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/6c77784cefbf4c3dbfe5969d3ec4d6b9.png)

```java
class Solution {
    public int findKthPositive(int[] arr, int k) {
        if (arr[0] > k) {
            return k;
        }

        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            int x = mid < arr.length ? arr[mid] : Integer.MAX_VALUE;
            if (x - mid - 1 >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return k - (arr[l - 1] - (l - 1) - 1) + arr[l - 1];
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/d62017fee8d7436f9a6712bc43262ab3.png)

#### [167. 两数之和 II - 输入有序数组](https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/)

给你一个下标从 **1** 开始的整数数组 `numbers` ，该数组已按 **非递减顺序排列** ，请你从数组中找出满足相加之和等于目标数 `target` 的两个数。如果设这两个数分别是 `numbers[index1]` 和 `numbers[index2]` ，则 `1 <= index1 < index2 <= numbers.length` 。

以长度为 2 的整数数组 `[index1, index2]` 的形式返回这两个整数的下标 `index1` 和 `index2`。

你可以假设每个输入 **只对应唯一的答案** ，而且你 **不可以** 重复使用相同的元素。

你所设计的解决方案必须只使用常量级的额外空间。

**示例 1：**

```
输入：numbers = [2,7,11,15], target = 9
输出：[1,2]
解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
```

**示例 2：**

```
输入：numbers = [2,3,4], target = 6
输出：[1,3]
解释：2 与 4 之和等于目标数 6 。因此 index1 = 1, index2 = 3 。返回 [1, 3] 。
```

**示例 3：**

```
输入：numbers = [-1,0], target = -1
输出：[1,2]
解释：-1 与 0 之和等于目标数 -1 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
```

 

**提示：**

- `2 <= numbers.length <= 3 * 104`
- `-1000 <= numbers[i] <= 1000`
- `numbers` 按 **非递减顺序** 排列
- `-1000 <= target <= 1000`
- **仅存在一个有效答案**



**双指针法**

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
         int l = 0;
        int r = numbers.length - 1;
        while (l <= r) {
            if (numbers[l] + numbers[r] == target) {
                return new int[]{l + 1, r + 1};
            } else if (numbers[l] + numbers[r] > target) {
                r--;
            } else {
                l++;
            }
        }
        return new int[]{-1, -1};
    }   
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/a855e766d6ce4d9fa5ed6226e93ac293.png)

#### [1351. 统计有序矩阵中的负数](https://leetcode.cn/problems/count-negative-numbers-in-a-sorted-matrix/)

给你一个 `m * n` 的矩阵 `grid`，矩阵中的元素无论是按行还是按列，都以非递增顺序排列。 请你统计并返回 `grid` 中 **负数** 的数目。

 

**示例 1：**

```
输入：grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
输出：8
解释：矩阵中共有 8 个负数。
```

**示例 2：**

```
输入：grid = [[3,2],[1,0]]
输出：0
```

 

**提示：**

- `m == grid.length`
- `n == grid[i].length`
- `1 <= m, n <= 100`
- `-100 <= grid[i][j] <= 100`

 

**进阶：**你可以设计一个时间复杂度为 `O(n + m)` 的解决方案吗？



**暴力**

```java
class Solution {
    public int countNegatives(int[][] grid) {
         int cnt = 0;
        for (int[] x : grid) {
            for (int y : x) {
                if (y < 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/0958231b7d364efc820eacd0a8a346c7.png)

#### [74. 搜索二维矩阵](https://leetcode.cn/problems/search-a-2d-matrix/)

编写一个高效的算法来判断 `m x n` 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

- 每行中的整数从左到右按升序排列。
- 每行的第一个整数大于前一行的最后一个整数。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/10/05/mat.jpg)

```
输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
输出：true
```

**示例 2：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/25/mat2.jpg)

```
输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
输出：false
```

 

**提示：**

- `m == matrix.length`
- `n == matrix[i].length`
- `1 <= m, n <= 100`
- `-104 <= matrix[i][j], target <= 104`



**二分**

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (matrix[mid / n][mid % n] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return matrix[left / n][right % n] == target;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/5456819955b14bebba8b638f06d5ce27.png)

#### [1337. 矩阵中战斗力最弱的 K 行](https://leetcode.cn/problems/the-k-weakest-rows-in-a-matrix/)

给你一个大小为 `m * n` 的矩阵 `mat`，矩阵由若干军人和平民组成，分别用 1 和 0 表示。

请你返回矩阵中战斗力最弱的 `k` 行的索引，按从最弱到最强排序。

如果第 ***i*** 行的军人数量少于第 ***j*** 行，或者两行军人数量相同但 ***i*** 小于 ***j***，那么我们认为第 ***i*** 行的战斗力比第 ***j*** 行弱。

军人 **总是** 排在一行中的靠前位置，也就是说 1 总是出现在 0 之前。

 

**示例 1：**

```
输入：mat = 
[[1,1,0,0,0],
 [1,1,1,1,0],
 [1,0,0,0,0],
 [1,1,0,0,0],
 [1,1,1,1,1]], 
k = 3
输出：[2,0,3]
解释：
每行中的军人数目：
行 0 -> 2 
行 1 -> 4 
行 2 -> 1 
行 3 -> 2 
行 4 -> 5 
从最弱到最强对这些行排序后得到 [2,0,3,1,4]
```

**示例 2：**

```
输入：mat = 
[[1,0,0,0],
 [1,1,1,1],
 [1,0,0,0],
 [1,0,0,0]], 
k = 2
输出：[0,2]
解释： 
每行中的军人数目：
行 0 -> 1 
行 1 -> 4 
行 2 -> 1 
行 3 -> 1 
从最弱到最强对这些行排序后得到 [0,2,3,1]
```

 

**提示：**

- `m == mat.length`
- `n == mat[i].length`
- `2 <= n, m <= 100`
- `1 <= k <= m`
- `matrix[i][j]` 不是 0 就是 1



**排序**

```java
class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] all = new int[m][2];
        for (int i = 0; i < m; i++) {
            int cur = 0;
            for (int j = 0; j < n; j++) {
                cur += mat[i][j];
                all[i] = new int[]{cur, i};
            }
        }
        Arrays.sort(all, (a, b) -> {
            return a[0] != b[0] ? a[0] - b[0] : a[1] - b[1];
        });
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = all[i][1];
        }
        return ans;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/5b22bccb30a94637b64fecab8b80cf47.png)

**优先队列**

```java
class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        List<int[]> power = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            int l = 0, r = n - 1, pos = -1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (mat[i][mid] == 0) {
                    r = mid - 1;
                } else {
                    pos = mid;
                    l = mid + 1;
                }
            }
            power.add(new int[]{pos + 1, i});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> {
            if (pair1[0] != pair2[0]) {
                return pair1[0] - pair2[0];
            } else {
                return pair1[1] - pair2[1];
            }
        });
        for (int[] pair : power) {
            pq.offer(pair);
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = Objects.requireNonNull(pq.poll())[1];
        }
        return ans;
        }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/ab44f8a0cf124ff3ae81091551a8f436.png)

#### [1346. 检查整数及其两倍数是否存在](https://leetcode.cn/problems/check-if-n-and-its-double-exist/)

给你一个整数数组 `arr`，请你检查是否存在两个整数 `N` 和 `M`，满足 `N` 是 `M` 的两倍（即，`N = 2 * M`）。

更正式地，检查是否存在两个下标 `i` 和 `j` 满足：

- `i != j`
- `0 <= i, j < arr.length`
- `arr[i] == 2 * arr[j]`

 

**示例 1：**

```
输入：arr = [10,2,5,3]
输出：true
解释：N = 10 是 M = 5 的两倍，即 10 = 2 * 5 。
```

**示例 2：**

```
输入：arr = [7,1,14,11]
输出：true
解释：N = 14 是 M = 7 的两倍，即 14 = 2 * 7 。
```

**示例 3：**

```
输入：arr = [3,1,7,11]
输出：false
解释：在该情况下不存在 N 和 M 满足 N = 2 * M 。
```

 

**提示：**

- `2 <= arr.length <= 500`
- `-10^3 <= arr[i] <= 10^3`

**暴力**

```java
class Solution {
    public boolean checkIfExist(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i != j && arr[i] == 2 * arr[j]) return true;
            }
        }
        return false;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/090e7202beaf4df3ae79d3a9e6371189.png)

**哈希**

```java
class Solution {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int i : arr) {
            if (set.contains(i) || set.contains(i * 4)) {
                return true;
            }
            set.add(i * 2);
        }
        return false;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/d3366d42b66e47f4b1037038c81852d3.png)

#### [633. 平方数之和](https://leetcode.cn/problems/sum-of-square-numbers/)

难度中等387

给定一个非负整数 `c` ，你要判断是否存在两个整数 `a` 和 `b`，使得 `a2 + b2 = c` 。

 

**示例 1：**

```
输入：c = 5
输出：true
解释：1 * 1 + 2 * 2 = 5
```

**示例 2：**

```
输入：c = 3
输出：false
```

 

**提示：**

- `0 <= c <= 231 - 1`

**sqrt方法**

```java
class Solution {
    public boolean judgeSquareSum(int c) {
        int max = (int) Math.sqrt(c);
        for (int a = 0; a <= max; a++) {
            int b = (int) Math.sqrt(c - a * a);
            if (a * a + b * b == c) {
                return true;
            }
        }
        return false;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/76c05804c5a048b981e63cf950484bba.png)

**双指针法**

```c
class Solution {
    public boolean judgeSquareSum(int c) {
        long left = 0;
        long right = (long) Math.sqrt(c);
        while (left <= right) {
            long sum = left * left + right * right;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }
}
```

#### [350. 两个数组的交集 II](https://leetcode.cn/problems/intersection-of-two-arrays-ii/)

给你两个整数数组 `nums1` 和 `nums2` ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。

 

**示例 1：**

```
输入：nums1 = [1,2,2,1], nums2 = [2,2]
输出：[2,2]
```

**示例 2:**

```
输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出：[4,9]
```

 

**提示：**

- `1 <= nums1.length, nums2.length <= 1000`
- `0 <= nums1[i], nums2[i] <= 1000`

 

***\*进阶\**：**

- 如果给定的数组已经排好序呢？你将如何优化你的算法？
- 如果 `nums1` 的大小比 `nums2` 小，哪种方法更优？
- 如果 `nums2` 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？

```java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        // 新建数组的大小为两个数组中较小的容量
        int[] intersection=new int[Math.min(nums1.length,nums2.length)];
        // 定义指针
        int index1=0;
        int index2 = 0;
        int index=0;
        while (index1< nums1.length&& index2< nums2.length){
            if (nums1[index1] <nums2[index2]){
                index1++;
            }else if (nums1[index1] >nums2[index2]) {
                index2++;
            }else {
                intersection[index]=nums1[index1];
                index1++;
                index2++;
                index++;
            }
        }
        return Arrays.copyOfRange(intersection,0,index);
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/eca6569144a44729a388796ff40002f3.png)

#### [1855. 下标对中的最大距离](https://leetcode.cn/problems/maximum-distance-between-a-pair-of-values/)

给你两个 **非递增** 的整数数组 `nums1` 和 `nums2` ，数组下标均 **从 0 开始** 计数。

下标对 `(i, j)` 中 `0 <= i < nums1.length` 且 `0 <= j < nums2.length` 。如果该下标对同时满足 `i <= j` 且 `nums1[i] <= nums2[j]` ，则称之为 **有效** 下标对，该下标对的 **距离** 为 `j - i` 。

返回所有 **有效** 下标对 `(i, j)` 中的 **最大距离** 。如果不存在有效下标对，返回 `0` 。

一个数组 `arr` ，如果每个 `1 <= i < arr.length` 均有 `arr[i-1] >= arr[i]` 成立，那么该数组是一个 **非递增** 数组。

 

**示例 1：**

```
输入：nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
输出：2
解释：有效下标对是 (0,0), (2,2), (2,3), (2,4), (3,3), (3,4) 和 (4,4) 。
最大距离是 2 ，对应下标对 (2,4) 。
```

**示例 2：**

```
输入：nums1 = [2,2,2], nums2 = [10,10,1]
输出：1
解释：有效下标对是 (0,0), (0,1) 和 (1,1) 。
最大距离是 1 ，对应下标对 (0,1) 。
```

**示例 3：**

```
输入：nums1 = [30,29,19,5], nums2 = [25,25,25,25,25]
输出：2
解释：有效下标对是 (2,2), (2,3), (2,4), (3,3) 和 (3,4) 。
最大距离是 2 ，对应下标对 (2,4) 。
```

 

**提示：**

- `1 <= nums1.length <= 105`
- `1 <= nums2.length <= 105`
- `1 <= nums1[i], nums2[j] <= 105`
- `nums1` 和 `nums2` 都是 **非递增** 数组



**遍历**

```java
class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int cnt = 0;
        int res = 0;
        for (int i = 0; i < nums2.length; i++) {
            while (cnt < nums1.length && nums1[cnt] > nums2[i]) {
                cnt++;
            }
            if (cnt < nums1.length) {
                res = Math.max(res, i - cnt);
            }
        }
        return res;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/4af35baebfb644b4b4411ff85655ab5c.png)

**二分**

```java
class Solution {
    public int maxDistance(int[] nums1, int[] nums2) {
        int res = 0;
        for (int i = 0; i < nums1.length; i++) {
            int left = 0;
            int right = nums2.length - 1;
            while (left < right) {
                int mid = left + right + 1 >> 1;
                if (nums2[mid] >= nums1[i]) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
                if (i <= left) {
                    res = Math.max(res, left - i);
                }
            }
        }
        return res;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/aaed6a1e82c341148e537c05c4d8a503.png)

#### [33. 搜索旋转排序数组](https://leetcode.cn/problems/search-in-rotated-sorted-array/)

整数数组 `nums` 按升序排列，数组中的值 **互不相同** 。

在传递给函数之前，`nums` 在预先未知的某个下标 `k`（`0 <= k < nums.length`）上进行了 **旋转**，使数组变为 `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]`（下标 **从 0 开始** 计数）。例如， `[0,1,2,4,5,6,7]` 在下标 `3` 处经旋转后可能变为 `[4,5,6,7,0,1,2]` 。

给你 **旋转后** 的数组 `nums` 和一个整数 `target` ，如果 `nums` 中存在这个目标值 `target` ，则返回它的下标，否则返回 `-1` 。

你必须设计一个时间复杂度为 `O(log n)` 的算法解决此问题。

 

**示例 1：**

```
输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4
```

**示例 2：**

```
输入：nums = [4,5,6,7,0,1,2], target = 3
输出：-1
```

**示例 3：**

```
输入：nums = [1], target = 0
输出：-1
```

 

**提示：**

- `1 <= nums.length <= 5000`
- `-104 <= nums[i] <= 104`
- `nums` 中的每个值都 **独一无二**
- 题目数据保证 `nums` 在预先未知的某个下标上进行了旋转
- `-104 <= target <= 104`



**二分**

```java
class Solution {
    public int search(int[] nums, int target) {
         int len = nums.length;
        if (len == 0) {
            return -1;
        }
        if (len == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[len - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/33bad6f7d28b47ec83cdbacf58134d48.png)

#### [153. 寻找旋转排序数组中的最小值](https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/)

已知一个长度为 `n` 的数组，预先按照升序排列，经由 `1` 到 `n` 次 **旋转** 后，得到输入数组。例如，原数组 `nums = [0,1,2,4,5,6,7]` 在变化后可能得到：

- 若旋转 `4` 次，则可以得到 `[4,5,6,7,0,1,2]`
- 若旋转 `7` 次，则可以得到 `[0,1,2,4,5,6,7]`

注意，数组 `[a[0], a[1], a[2], ..., a[n-1]]` **旋转一次** 的结果为数组 `[a[n-1], a[0], a[1], a[2], ..., a[n-2]]` 。

给你一个元素值 **互不相同** 的数组 `nums` ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 **最小元素** 。

你必须设计一个时间复杂度为 `O(log n)` 的算法解决此问题。

 

**示例 1：**

```
输入：nums = [3,4,5,1,2]
输出：1
解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
```

**示例 2：**

```
输入：nums = [4,5,6,7,0,1,2]
输出：0
解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
```

**示例 3：**

```
输入：nums = [11,13,15,17]
输出：11
解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
```

 

**提示：**

- `n == nums.length`
- `1 <= n <= 5000`
- `-5000 <= nums[i] <= 5000`
- `nums` 中的所有整数 **互不相同**
- `nums` 原来是一个升序排序的数组，并进行了 `1` 至 `n` 次旋转

**最小堆**

```java
class Solution {
    public int findMin(int[] nums) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
        queue.offer(nums[i]);
    }
    return queue.peek();
    }
}
```

**二分**

```java
class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(nums[left]);
        return nums[left];
        }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/587bb8faebdb4ea38b0beb4ab85b15c1.png)

#### [209. 长度最小的子数组](https://leetcode.cn/problems/minimum-size-subarray-sum/)

给定一个含有 `n` 个正整数的数组和一个正整数 `target` **。**

找出该数组中满足其和 `≥ target` 的长度最小的 **连续子数组** `[numsl, numsl+1, ..., numsr-1, numsr]` ，并返回其长度**。**如果不存在符合条件的子数组，返回 `0` 。

 

**示例 1：**

```
输入：target = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。
```

**示例 2：**

```
输入：target = 4, nums = [1,4,4]
输出：1
```

**示例 3：**

```
输入：target = 11, nums = [1,1,1,1,1,1,1,1]
输出：0
```

 

**提示：**

- `1 <= target <= 109`
- `1 <= nums.length <= 105`
- `1 <= nums[i] <= 105`

 

**进阶：**

- 如果你已经实现 `O(n)` 时间复杂度的解法, 请尝试设计一个 `O(n log(n))` 时间复杂度的解法。



**暴力遍历**

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum >= target) {
                return 1;
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    min = Math.min(min, j - i + 1);
                    break;
                }
            }
        }
        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/db9a13d1d8ef4905b33d8bc23efad2d1.png)



**双指针**

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int low = 0;
        int high = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while (high < nums.length) {
            sum += nums[high++];
            while (sum >= target) {
                min = Math.min(min, high - low);
                sum -= nums[low++];
            }
        }
        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/e2bc4ea650b64977a0eaed3e99e032fc.png)

#### [611. 有效三角形的个数](https://leetcode.cn/problems/valid-triangle-number/)

给定一个包含非负整数的数组 `nums` ，返回其中可以组成三角形三条边的三元组个数。

 

**示例 1:**

```
输入: nums = [2,2,3,4]
输出: 3
解释:有效的组合是: 
2,3,4 (使用第一个 2)
2,3,4 (使用第二个 2)
2,2,3
```

**示例 2:**

```
输入: nums = [4,2,3,4]
输出: 4
```

 

**提示:**

- `1 <= nums.length <= 1000`
- `0 <= nums[i] <= 1000`

**暴力法**

```java
class Solution {
    public int triangleNumber(int[] nums) {
       Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1, k = 0; k < j; j--) {
                while (k < j && nums[k] + nums[j] <= nums[i]) {
                    k++;
                }
                ans += j - k;
            }
        }
        return ans;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/da4f36eb5db14b548cb57b6661469878.png)

**二分**

```java
class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                // 第三个数判断范围
                int left = 0;
                int right = j - 1;
                while (left < right) {
                    int mid = left + right >> 1;
                    if (nums[mid] + nums[j] > nums[i]) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                if (left == right && nums[right] + nums[j] > nums[i]) {
                    ans += j - right;
                }
            }
        }
        return ans;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/cae97fe15ec04e089c88df63d2a04f19.png)

**排序**

```java
class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i - 1, k = 0; k < j; j--) {
                while (k < j && nums[k] + nums[j] <= nums[i]) {
                    k++;
                }
                ans += j - k;
            }
        }
        return ans;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/b84c74ace1834b73bb57efb43d97bbff.png)

#### [1894. 找到需要补充粉笔的学生编号](https://leetcode.cn/problems/find-the-student-that-will-replace-the-chalk/)

一个班级里有 `n` 个学生，编号为 `0` 到 `n - 1` 。每个学生会依次回答问题，编号为 `0` 的学生先回答，然后是编号为 `1` 的学生，以此类推，直到编号为 `n - 1` 的学生，然后老师会重复这个过程，重新从编号为 `0` 的学生开始回答问题。

给你一个长度为 `n` 且下标从 `0` 开始的整数数组 `chalk` 和一个整数 `k` 。一开始粉笔盒里总共有 `k` 支粉笔。当编号为 `i` 的学生回答问题时，他会消耗 `chalk[i]` 支粉笔。如果剩余粉笔数量 **严格小于** `chalk[i]` ，那么学生 `i` 需要 **补充** 粉笔。

请你返回需要 **补充** 粉笔的学生 **编号** 。

 

**示例 1：**

```
输入：chalk = [5,1,5], k = 22
输出：0
解释：学生消耗粉笔情况如下：
- 编号为 0 的学生使用 5 支粉笔，然后 k = 17 。
- 编号为 1 的学生使用 1 支粉笔，然后 k = 16 。
- 编号为 2 的学生使用 5 支粉笔，然后 k = 11 。
- 编号为 0 的学生使用 5 支粉笔，然后 k = 6 。
- 编号为 1 的学生使用 1 支粉笔，然后 k = 5 。
- 编号为 2 的学生使用 5 支粉笔，然后 k = 0 。
编号为 0 的学生没有足够的粉笔，所以他需要补充粉笔。
```

**示例 2：**

```
输入：chalk = [3,4,1,2], k = 25
输出：1
解释：学生消耗粉笔情况如下：
- 编号为 0 的学生使用 3 支粉笔，然后 k = 22 。
- 编号为 1 的学生使用 4 支粉笔，然后 k = 18 。
- 编号为 2 的学生使用 1 支粉笔，然后 k = 17 。
- 编号为 3 的学生使用 2 支粉笔，然后 k = 15 。
- 编号为 0 的学生使用 3 支粉笔，然后 k = 12 。
- 编号为 1 的学生使用 4 支粉笔，然后 k = 8 。
- 编号为 2 的学生使用 1 支粉笔，然后 k = 7 。
- 编号为 3 的学生使用 2 支粉笔，然后 k = 5 。
- 编号为 0 的学生使用 3 支粉笔，然后 k = 2 。
编号为 1 的学生没有足够的粉笔，所以他需要补充粉笔。
```

 

**提示：**

- `chalk.length == n`
- `1 <= n <= 105`
- `1 <= chalk[i] <= 105`
- `1 <= k <= 109`

```java
class Solution {
    public int chalkReplacer(int[] chalk, int k) {
        int len = chalk.length;
        // 注意溢出
        long sum = 0;
        for (int num : chalk) {
            // 计算数组中的粉笔总和
            sum += num;
        }
        // 对k取模
        k %= sum;
        int res = -1;
        for (int i = 0; i < len; i++) {
            if (chalk[i] > k) {
                res = i;
                break;
            }
            k -= chalk[i];
        }
        System.out.println(res);
        return res;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/31466899c3a1434fa3f6896a569d1f97.png)

#### [658. 找到 K 个最接近的元素](https://leetcode.cn/problems/find-k-closest-elements/)

给定一个 **排序好** 的数组 `arr` ，两个整数 `k` 和 `x` ，从数组中找到最靠近 `x`（两数之差最小）的 `k` 个数。返回的结果必须要是按升序排好的。

整数 `a` 比整数 `b` 更接近 `x` 需要满足：

- `|a - x| < |b - x|` 或者
- `|a - x| == |b - x|` 且 `a < b`

 

**示例 1：**

```
输入：arr = [1,2,3,4,5], k = 4, x = 3
输出：[1,2,3,4]
```

**示例 2：**

```
输入：arr = [1,2,3,4,5], k = 4, x = -1
输出：[1,2,3,4]
```

 

**提示：**

- `1 <= k <= arr.length`
- `1 <= arr.length <= 104`
- `arr` 按 **升序** 排列
- `-104 <= arr[i], x <= 104`

```java
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int len = arr.length;
        int left = 0;
        int right = len - 1;

        int removeNums = len - k;
        while (removeNums > 0) {
            if (x - arr[left] <= arr[right] - x) {
                right--;
            } else {
                left++;
            }
            removeNums--;
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            res.add(arr[i]);
        }
        System.out.println(res.toString());
        return res;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/125b439ae4d74c7c94d2bf56ebb9854a.png)

#### [300. 最长递增子序列](https://leetcode.cn/problems/longest-increasing-subsequence/)

给你一个整数数组 `nums` ，找到其中最长严格递增子序列的长度。

**子序列** 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，`[3,6,2,7]` 是数组 `[0,3,1,6,2,2,7]` 的子序列。

**示例 1：**

```
输入：nums = [10,9,2,5,3,7,101,18]
输出：4
解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
```

**示例 2：**

```
输入：nums = [0,1,0,3,2,3]
输出：4
```

**示例 3：**

```
输入：nums = [7,7,7,7,7,7,7]
输出：1
```

 

**提示：**

- `1 <= nums.length <= 2500`
- `-104 <= nums[i] <= 104`

 

**进阶：**

- 你能将算法的时间复杂度降低到 `O(n log(n))` 吗?

**双栈**

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
       // 定义两个栈
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        // 栈1加入数组首元素
        s1.add(nums[0]);
        // 遍历数组
        for (int i = 1; i < nums.length; i++) {
            // 栈顶元素小于数组值，加加入到栈1
            if (s1.peek() < nums[i]) {
                s1.add(nums[i]);
                // 栈顶元素大于数组值，且栈不包含数组值
            } else if (s1.peek() > nums[i] && !s1.contains(nums[i])) {
                // 栈1弹出数组值
                while (!s1.isEmpty() && s1.peek() >= nums[i]) {
                    // 将栈1弹出的值加入到栈2
                    s2.add(s1.pop());
                }
                // 栈2弹出值
                s2.pop();
                // 继续添加下一个数组值
                s1.add(nums[i]);
                while (!s2.isEmpty()) {
                  // 栈1加入栈2弹出的值
                    s1.add(s2.pop());
                }
            }
        }
        return s1.size();
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/8ef1209e94574df08ca83b34eb077b5e.png)

#### [1760. 袋子里最少数目的球](https://leetcode.cn/problems/minimum-limit-of-balls-in-a-bag/)

给你一个整数数组 `nums` ，其中 `nums[i]` 表示第 `i` 个袋子里球的数目。同时给你一个整数 `maxOperations` 。

你可以进行如下操作至多 `maxOperations` 次：

- 选择任意一个袋子，并将袋子里的球分到 2 个新的袋子中，每个袋子里都有

   

  正整数

   个球。

  - 比方说，一个袋子里有 `5` 个球，你可以把它们分到两个新袋子里，分别有 `1` 个和 `4` 个球，或者分别有 `2` 个和 `3` 个球。

你的开销是单个袋子里球数目的 **最大值** ，你想要 **最小化** 开销。

请你返回进行上述操作后的最小开销。

 

**示例 1：**

```
输入：nums = [9], maxOperations = 2
输出：3
解释：
- 将装有 9 个球的袋子分成装有 6 个和 3 个球的袋子。[9] -> [6,3] 。
- 将装有 6 个球的袋子分成装有 3 个和 3 个球的袋子。[6,3] -> [3,3,3] 。
装有最多球的袋子里装有 3 个球，所以开销为 3 并返回 3 。
```

**示例 2：**

```
输入：nums = [2,4,8,2], maxOperations = 4
输出：2
解释：
- 将装有 8 个球的袋子分成装有 4 个和 4 个球的袋子。[2,4,8,2] -> [2,4,4,4,2] 。
- 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,4,4,4,2] -> [2,2,2,4,4,2] 。
- 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,2,2,4,4,2] -> [2,2,2,2,2,4,2] 。
- 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2] 。
装有最多球的袋子里装有 2 个球，所以开销为 2 并返回 2 。
```

**示例 3：**

```
输入：nums = [7,17], maxOperations = 2
输出：7
```

 

**提示：**

- `1 <= nums.length <= 105`
- `1 <= maxOperations, nums[i] <= 109`



**二分**

```java
class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
        int ans = 0;
        int left = 1, right = 1000000000;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int cnt = 0;
            for (int num : nums) {
                if (num > mid) {
                    cnt += num / mid;
                    if (num % mid == 0) {
                        cnt -= 1;
                    }
                    if (cnt > maxOperations) {
                        break;
                    }
                }
            }
            if (cnt > maxOperations) {
                left = mid + 1;
            } else {
                ans = mid;
                right = mid - 1;
            }
        }
        return ans;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/fd8a0c3480b34d42895f2aadd7d6decb.png)

#### [875. 爱吃香蕉的珂珂](https://leetcode.cn/problems/koko-eating-bananas/)

珂珂喜欢吃香蕉。这里有 `n` 堆香蕉，第 `i` 堆中有 `piles[i]` 根香蕉。警卫已经离开了，将在 `h` 小时后回来。

珂珂可以决定她吃香蕉的速度 `k` （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 `k` 根。如果这堆香蕉少于 `k` 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。 

珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。

返回她可以在 `h` 小时内吃掉所有香蕉的最小速度 `k`（`k` 为整数）。

 



**示例 1：**

```
输入：piles = [3,6,7,11], h = 8
输出：4
```

**示例 2：**

```
输入：piles = [30,11,23,4,20], h = 5
输出：30
```

**示例 3：**

```
输入：piles = [30,11,23,4,20], h = 6
输出：23
```

 

**提示：**

- `1 <= piles.length <= 104`
- `piles.length <= h <= 109`
- `1 <= piles[i] <= 109`

```java
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = 0;
        for (int pile : piles) {
            high = Math.max(high, pile);
        }
        int k = high;
        while (low < high) {
            int speed = (high - low) / 2 + low;
            long time = getTime(piles, speed);
            if (time <= h) {
                k = speed;
                high = speed;
            } else {
                low = speed + 1;
            }
        }
        return k;
    }

    public long getTime(int[] piles, int speed) {
        long time = 0;
        for (int pile : piles) {
            int curTime = (pile + speed - 1) / speed;
            time += curTime;
        }
        return time;
    }
}
```

#### [1552. 两球之间的磁力](https://leetcode.cn/problems/magnetic-force-between-two-balls/)

在代号为 C-137 的地球上，Rick 发现如果他将两个球放在他新发明的篮子里，它们之间会形成特殊形式的磁力。Rick 有 `n` 个空的篮子，第 `i` 个篮子的位置在 `position[i]` ，Morty 想把 `m` 个球放到这些篮子里，使得任意两球间 **最小磁力** 最大。

已知两个球如果分别位于 `x` 和 `y` ，那么它们之间的磁力为 `|x - y|` 。

给你一个整数数组 `position` 和一个整数 `m` ，请你返回最大化的最小磁力。

 

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/08/16/q3v1.jpg)

```
输入：position = [1,2,3,4,7], m = 3
输出：3
解释：将 3 个球分别放入位于 1，4 和 7 的三个篮子，两球间的磁力分别为 [3, 3, 6]。最小磁力为 3 。我们没办法让最小磁力大于 3 。
```

**示例 2：**

```
输入：position = [5,4,3,2,1,1000000000], m = 2
输出：999999999
解释：我们使用位于 1 和 1000000000 的篮子时最小磁力最大。
```

 

**提示：**

- `n == position.length`
- `2 <= n <= 10^5`
- `1 <= position[i] <= 10^9`
- 所有 `position` 中的整数 **互不相同** 。
- `2 <= m <= position.length`

**二分**

```java
class Solution {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int left = 1, right = position[position.length - 1] - position[0], ans = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(mid, position, m)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public boolean check(int x, int[] position, int m) {
        int pre = position[0], cnt = 1;
        for (int i = 1; i < position.length; ++i) {
            if (position[i] - pre >= x) {
                pre = position[i];
                cnt += 1;
            }
        }
        return cnt >= m;
    }
}
```

#### [287. 寻找重复数](https://leetcode.cn/problems/find-the-duplicate-number/)4

给定一个包含 `n + 1` 个整数的数组 `nums` ，其数字都在 `[1, n]` 范围内（包括 `1` 和 `n`），可知至少存在一个重复的整数。

假设 `nums` 只有 **一个重复的整数** ，返回 **这个重复的数** 。

你设计的解决方案必须 **不修改** 数组 `nums` 且只用常量级 `O(1)` 的额外空间。

 

**示例 1：**

```
输入：nums = [1,3,4,2,2]
输出：2
```

**示例 2：**

```
输入：nums = [3,1,3,4,2]
输出：3
```

 

**提示：**

- `1 <= n <= 105`
- `nums.length == n + 1`
- `1 <= nums[i] <= n`
- `nums` 中 **只有一个整数** 出现 **两次或多次** ，其余整数均只出现 **一次**

 

**进阶：**

- 如何证明 `nums` 中至少存在一个重复的数字?
- 你可以设计一个线性级时间复杂度 `O(n)` 的解决方案吗？

**计数**

```java
class Solution {
    public int findDuplicate(int[] nums) {
        int[] counter = new int[nums.length + 1];
        int res = 0;
        for (int num : nums) {
            counter[num]++;
        }
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] >= 2) {
                res = i;
            }
        }
        return res;
    }   
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/ee26992d63d74e78ae64d3bd7af99530.png)

**哈希**

```java
class Solution {
    public int findDuplicate(int[] nums) {
         HashSet<Integer> set = new HashSet<>();
        int res = 0;
        for (int num : nums) {
            if (set.add(num)) {
                set.add(num);
            } else {
                res = num;
            }
        }
        return res;
    }   
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/d2c2be26026f4b21870d83d68c780d53.png)

**二分**

```java
class Solution {
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int res = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
                res = mid;
            }
        }
        return res;
    }   
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/de9ff7f0bed449dbb8670dc3076f78c5.png)

**map**

```java
class Solution {
    public int findDuplicate(int[] nums) {
         HashMap<Integer, Integer> map = new HashMap<>();
        int res=0;
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0)+1);
        }
        for (Integer integer : map.keySet()) {
            Integer integer1 = map.get(integer);
            if (integer1>=2){
                res=integer;
            }
        }
        return res;
    }   
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/d22d47459c484c508dd85ea1dc8c3164.png)

#### [1283. 使结果不超过阈值的最小除数](https://leetcode.cn/problems/find-the-smallest-divisor-given-a-threshold/)



给你一个整数数组 `nums` 和一个正整数 `threshold` ，你需要选择一个正整数作为除数，然后将数组里每个数都除以它，并对除法结果求和。

请你找出能够使上述结果小于等于阈值 `threshold` 的除数中 **最小** 的那个。

每个数除以除数后都向上取整，比方说 7/3 = 3 ， 10/2 = 5 。

题目保证一定有解。

 

**示例 1：**

```
输入：nums = [1,2,5,9], threshold = 6
输出：5
解释：如果除数为 1 ，我们可以得到和为 17 （1+2+5+9）。
如果除数为 4 ，我们可以得到和为 7 (1+1+2+3) 。如果除数为 5 ，和为 5 (1+1+1+2)。
```

**示例 2：**

```
输入：nums = [2,3,5,7,11], threshold = 11
输出：3
```

**示例 3：**

```
输入：nums = [19], threshold = 5
输出：4
```

 

**提示：**

- `1 <= nums.length <= 5 * 10^4`
- `1 <= nums[i] <= 10^6`
- `nums.length <= threshold <= 10^6`

**二分**

```java
class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1;
        int right = Arrays.stream(nums).max().getAsInt();
        while (left < right) {
            int mid = left + (right - left) / 2;
            int num = sum(nums, mid);
            if (num > threshold) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private int sum(int[] nums, int mid) {
        int sum = 0;
        for (int num : nums) {
            sum += (num + mid - 1) / mid;
        }
        return sum;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20eb73516c3143629224ef21763000ef.png)

#### [1870. 准时到达的列车最小时速](https://leetcode.cn/problems/minimum-speed-to-arrive-on-time/)

给你一个浮点数 `hour` ，表示你到达办公室可用的总通勤时间。要到达办公室，你必须按给定次序乘坐 `n` 趟列车。另给你一个长度为 `n` 的整数数组 `dist` ，其中 `dist[i]` 表示第 `i` 趟列车的行驶距离（单位是千米）。

每趟列车均只能在整点发车，所以你可能需要在两趟列车之间等待一段时间。

- 例如，第 `1` 趟列车需要 `1.5` 小时，那你必须再等待 `0.5` 小时，搭乘在第 2 小时发车的第 `2` 趟列车。

返回能满足你准时到达办公室所要求全部列车的 **最小正整数** 时速（单位：千米每小时），如果无法准时到达，则返回 `-1` 。

生成的测试用例保证答案不超过 `107` ，且 `hour` 的 **小数点后最多存在两位数字** 。

 

**示例 1：**

```
输入：dist = [1,3,2], hour = 6
输出：1
解释：速度为 1 时：
- 第 1 趟列车运行需要 1/1 = 1 小时。
- 由于是在整数时间到达，可以立即换乘在第 1 小时发车的列车。第 2 趟列车运行需要 3/1 = 3 小时。
- 由于是在整数时间到达，可以立即换乘在第 4 小时发车的列车。第 3 趟列车运行需要 2/1 = 2 小时。
- 你将会恰好在第 6 小时到达。
```

**示例 2：**

```
输入：dist = [1,3,2], hour = 2.7
输出：3
解释：速度为 3 时：
- 第 1 趟列车运行需要 1/3 = 0.33333 小时。
- 由于不是在整数时间到达，故需要等待至第 1 小时才能搭乘列车。第 2 趟列车运行需要 3/3 = 1 小时。
- 由于是在整数时间到达，可以立即换乘在第 2 小时发车的列车。第 3 趟列车运行需要 2/3 = 0.66667 小时。
- 你将会在第 2.66667 小时到达。
```

**示例 3：**

```
输入：dist = [1,3,2], hour = 1.9
输出：-1
解释：不可能准时到达，因为第 3 趟列车最早是在第 2 小时发车。
```

 

**提示：**

- `n == dist.length`
- `1 <= n <= 105`
- `1 <= dist[i] <= 105`
- `1 <= hour <= 109`
- `hours` 中，小数点后最多存在两位数字

```java
class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {
        if (dist.length > Math.ceil(hour)) {
            return -1;
        }
        int left = 1;
        int right = Integer.MAX_VALUE;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (check(dist, hour, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    private boolean check(int[] dist, double hour, int speed) {
        double cnt = 0.0;
        for (int i = 0; i < dist.length - 1; i++) {
            // 除法的向上取整
            cnt += (dist[i] + speed - 1) / speed;
        }
        // 加上最后一个站点所需的时间
        cnt += (double) dist[dist.length - 1] / speed;
        return cnt <= hour;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/fe1c8acaff5f4364abf4e8f339df3e5b.png)

#### [1898. 可移除字符的最大数目](https://leetcode.cn/problems/maximum-number-of-removable-characters/)

给你两个字符串 `s` 和 `p` ，其中 `p` 是 `s` 的一个 **子序列** 。同时，给你一个元素 **互不相同** 且下标 **从 0 开始** 计数的整数数组 `removable` ，该数组是 `s` 中下标的一个子集（`s` 的下标也 **从 0 开始** 计数）。

请你找出一个整数 `k`（`0 <= k <= removable.length`），选出 `removable` 中的 **前** `k` 个下标，然后从 `s` 中移除这些下标对应的 `k` 个字符。整数 `k` 需满足：在执行完上述步骤后， `p` 仍然是 `s` 的一个 **子序列** 。更正式的解释是，对于每个 `0 <= i < k` ，先标记出位于 `s[removable[i]]` 的字符，接着移除所有标记过的字符，然后检查 `p` 是否仍然是 `s` 的一个子序列。

返回你可以找出的 **最大** `k` ，满足在移除字符后 `p` 仍然是 `s` 的一个子序列。

字符串的一个 **子序列** 是一个由原字符串生成的新字符串，生成过程中可能会移除原字符串中的一些字符（也可能不移除）但不改变剩余字符之间的相对顺序。

 

**示例 1：**

```
输入：s = "abcacb", p = "ab", removable = [3,1,0]
输出：2
解释：在移除下标 3 和 1 对应的字符后，"abcacb" 变成 "accb" 。
"ab" 是 "accb" 的一个子序列。
如果移除下标 3、1 和 0 对应的字符后，"abcacb" 变成 "ccb" ，那么 "ab" 就不再是 s 的一个子序列。
因此，最大的 k 是 2 。
```

**示例 2：**

```
输入：s = "abcbddddd", p = "abcd", removable = [3,2,1,4,5,6]
输出：1
解释：在移除下标 3 对应的字符后，"abcbddddd" 变成 "abcddddd" 。
"abcd" 是 "abcddddd" 的一个子序列。
```

**示例 3：**

```
输入：s = "abcab", p = "abc", removable = [0,1,2,3,4]
输出：0
解释：如果移除数组 removable 的第一个下标，"abc" 就不再是 s 的一个子序列。
```

 

**提示：**

- `1 <= p.length <= s.length <= 105`
- `0 <= removable.length < s.length`
- `0 <= removable[i] < s.length`
- `p` 是 `s` 的一个 **子字符串**
- `s` 和 `p` 都由小写英文字母组成
- `removable` 中的元素 **互不相同**

```java
class Solution {
    public int maximumRemovals(String s, String p, int[] removable) {
        char[] str1 = s.toCharArray();
        char[] str2 = p.toCharArray();
        int ans = 0;//我们要返回的值，答案
        int left = 1;//左边界
        int right = removable.length;//右边界
        while (left <= right) {//开始循环找答案
            int mid = left + right >> 1;//位运算高效求中点
            //这里调用的方法下面讲，他的作用就是删除前mid个removable所指的位置的字符后
            if (search(mid, str1, str2, removable)) {//是否还能满足p是s的子序列，如果满足返回true
                ans = mid;//进入条件则说明满足，当前的mid就是候选答案之一，最后一个满足要求的mid就一定是答案
                left = mid + 1;//当前mid满足的话我们就要去尝试更大的数值，以缩小答案的范围
            } else {//不满足
                right = mid - 1;//那我们就要去尝试更小的数，寻找能满足的
            }
            str1 = s.toCharArray();//我们在search1方法中改变了str0的值，所以要重新赋值
        }
        return ans;
    }

    private boolean search(int mid, char[] str1, char[] str2, int[] removable) {
        for (int i = 0; i < mid; i++) {
            str1[removable[i]] = '1';
        }
        int j = 0;
        for (int i = 0; i < str1.length; i++) {
            if (j < str2.length) {
                if (str2[j] == str1[i]) {
                    j++;
                }
            } else {
                // 说明p是s的子序列
                return true;
            }
        }
        if (j == str2.length) {
            return true;
        }
        return false;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/ebc5d8ee03c44ac99c338432045f01d1.png)

#### [1482. 制作 m 束花所需的最少天数](https://leetcode.cn/problems/minimum-number-of-days-to-make-m-bouquets/)

给你一个整数数组 `bloomDay`，以及两个整数 `m` 和 `k` 。

现需要制作 `m` 束花。制作花束时，需要使用花园中 **相邻的 `k` 朵花** 。

花园中有 `n` 朵花，第 `i` 朵花会在 `bloomDay[i]` 时盛开，**恰好** 可以用于 **一束** 花中。

请你返回从花园中摘 `m` 束花需要等待的最少的天数。如果不能摘到 `m` 束花则返回 **-1** 。

 

**示例 1：**

```
输入：bloomDay = [1,10,3,10,2], m = 3, k = 1
输出：3
解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
现在需要制作 3 束花，每束只需要 1 朵。
1 天后：[x, _, _, _, _]   // 只能制作 1 束花
2 天后：[x, _, _, _, x]   // 只能制作 2 束花
3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
```

**示例 2：**

```
输入：bloomDay = [1,10,3,10,2], m = 3, k = 2
输出：-1
解释：要制作 3 束花，每束需要 2 朵花，也就是一共需要 6 朵花。而花园中只有 5 朵花，无法满足制作要求，返回 -1 。
```

**示例 3：**

```
输入：bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
输出：12
解释：要制作 2 束花，每束需要 3 朵。
花园在 7 天后和 12 天后的情况如下：
7 天后：[x, x, x, x, _, x, x]
可以用前 3 朵盛开的花制作第一束花。但不能使用后 3 朵盛开的花，因为它们不相邻。
12 天后：[x, x, x, x, x, x, x]
显然，我们可以用不同的方式制作两束花。
```

**示例 4：**

```
输入：bloomDay = [1000000000,1000000000], m = 1, k = 1
输出：1000000000
解释：需要等 1000000000 天才能采到花来制作花束
```

**示例 5：**

```
输入：bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2
输出：9
```

 

**提示：**

- `bloomDay.length == n`
- `1 <= n <= 10^5`
- `1 <= bloomDay[i] <= 10^9`
- `1 <= m <= 10^6`
- `1 <= k <= n`

**二分**

```java
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {

        if( m > bloomDay.length / k) return -1; // 此时无论如何都制作不出来m束花

        // 只要 m <= bloomDay.length / k, 成立，无论如何都能制作出来
        // 调用两次stream()方法求最值的效率较低，可以使用常规方法替代！
        int low = Arrays.stream(bloomDay).min().getAsInt(); // 花开的最小天数
        int high = Arrays.stream(bloomDay).max().getAsInt(); // 花开的最大天数

        // 如果可以制作m束花，天数一定在low和high之间，因此使用二分查找
        while(low < high){
            int mid = low + (high-low)/2;
            if(make(bloomDay,m,k,mid)){
                high = mid;
            }else low = mid+1;
        }
        return low;
        
    }

    public boolean make(int[] bloomDay, int m, int k,int days){
        int flowers = 0; // 代表可用的花的个数
        int makeFlowers = 0; // 代表当前天数days可以制作出的花的数量
        for (int j : bloomDay) {
            if (j <= days) {
                flowers++; // 只要开花所需天数小于等于days, 则说明当前花可用
                if (flowers == k) {   // 当前花的数量满足可以制作一束花的数量k时，则制作出的花的数量makeFlowers++;并重置makeFlowers
                    makeFlowers++;
                    flowers = 0;  // 重置当前可用花的数量
                }
            } else flowers = 0; // 因为需要连续的k朵花，因此只要中间有一朵花没开, flowers就重置为0

        }
        return makeFlowers >= m; // 只要 makeFlowers >= m 就说明可以满足要求
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/3028ae6fdd7e44549341b0acac713a88.png)

#### [1818. 绝对差值和](https://leetcode.cn/problems/minimum-absolute-sum-difference/)

给你两个正整数数组 `nums1` 和 `nums2` ，数组的长度都是 `n` 。

数组 `nums1` 和 `nums2` 的 **绝对差值和** 定义为所有 `|nums1[i] - nums2[i]|`（`0 <= i < n`）的 **总和**（**下标从 0 开始**）。

你可以选用 `nums1` 中的 **任意一个** 元素来替换 `nums1` 中的 **至多** 一个元素，以 **最小化** 绝对差值和。

在替换数组 `nums1` 中最多一个元素 **之后** ，返回最小绝对差值和。因为答案可能很大，所以需要对 `109 + 7` **取余** 后返回。

`|x|` 定义为：

- 如果 `x >= 0` ，值为 `x` ，或者
- 如果 `x <= 0` ，值为 `-x`

 

**示例 1：**

```
输入：nums1 = [1,7,5], nums2 = [2,3,5]
输出：3
解释：有两种可能的最优方案：
- 将第二个元素替换为第一个元素：[1,7,5] => [1,1,5] ，或者
- 将第二个元素替换为第三个元素：[1,7,5] => [1,5,5]
两种方案的绝对差值和都是 |1-2| + (|1-3| 或者 |5-3|) + |5-5| = 3
```

**示例 2：**

```
输入：nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
输出：0
解释：nums1 和 nums2 相等，所以不用替换元素。绝对差值和为 0
```

**示例 3****：**

```
输入：nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
输出：20
解释：将第一个元素替换为第二个元素：[1,10,4,4,2,7] => [10,10,4,4,2,7]
绝对差值和为 |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20
```

 

**提示：**

- `n == nums1.length`
- `n == nums2.length`
- `1 <= n <= 105`
- `1 <= nums1[i], nums2[i] <= 105`

```java
class Solution {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        final int MOD = 1000000007;
        int n = nums1.length;
        int[] rec = new int[n];
        System.arraycopy(nums1, 0, rec, 0, n);
        Arrays.sort(rec);
        int sum = 0, maxn = 0;
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            sum = (sum + diff) % MOD;
            int j = binarySearch(rec, nums2[i]);
            if (j < n) {
                maxn = Math.max(maxn, diff - (rec[j] - nums2[i]));
            }
            if (j > 0) {
                maxn = Math.max(maxn, diff - (nums2[i] - rec[j - 1]));
            }
        }
        return (sum - maxn + MOD) % MOD;
    }

    public int binarySearch(int[] rec, int target) {
        int low = 0, high = rec.length - 1;
        if (rec[high] < target) {
            return high + 1;
        }
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (rec[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
```

#### [275. H 指数 II](https://leetcode.cn/problems/h-index-ii/)

给你一个整数数组 `citations` ，其中 `citations[i]` 表示研究者的第 `i` 篇论文被引用的次数，`citations` 已经按照 **升序排列** 。计算并返回该研究者的 **`h` 指数**。

[h 指数的定义](https://baike.baidu.com/item/h-index/3991452?fr=aladdin)：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （`n` 篇论文中）**总共**有 `h` 篇论文分别被引用了**至少** `h` 次。且其余的 *`n - h`* 篇论文每篇被引用次数 **不超过** *`h`* 次。

**提示：**如果 `h` 有多种可能的值，**`h` 指数** 是其中最大的那个。

请你设计并实现对数时间复杂度的算法解决此问题。

 

**示例 1：**

```
输入：citations = [0,1,3,5,6]
输出：3 
解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
     由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3 。
```

**示例 2：**

```
输入：citations = [1,2,100]
输出：2
```

 

**提示：**

- `n == citations.length`
- `1 <= n <= 105`
- `0 <= citations[i] <= 1000`
- `citations` 按 **升序排列**

**二分**

```java
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] >= n - mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return n - left;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/4ceaf0fc8eef44f3890b759a1f7e5667.png)

#### [240. 搜索二维矩阵 II](https://leetcode.cn/problems/search-a-2d-matrix-ii/)

编写一个高效的算法来搜索 `*m* x *n*` 矩阵 `matrix` 中的一个目标值 `target` 。该矩阵具有以下特性：

- 每行的元素从左到右升序排列。
- 每列的元素从上到下升序排列。

 

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/25/searchgrid2.jpg)

```
输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
输出：true
```

**示例 2：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/25/searchgrid.jpg)

```
输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
输出：false
```

 

**提示：**

- `m == matrix.length`
- `n == matrix[i].length`
- `1 <= n, m <= 300`
- `-109 <= matrix[i][j] <= 109`
- 每行的所有元素从左到右升序排列
- 每列的所有元素从上到下升序排列
- `-109 <= target <= 109`

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] row : matrix) {
            for (int element : row) {
                if (element == target) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/1718b1d264754d29b5128d6fe4b1890c.png)

**二分**

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] row : matrix) {
            int index = binarySearch(row, target);
            if (index >= 0) {
                return true;
            }
        }
        return false;
    }

    public int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
```

**BST**

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int r = 0, c = n - 1;
        while (r < m && c >= 0) {
            if (matrix[r][c] < target) {
                r++;
            } else if (matrix[r][c] > target) {
                c--;
            } else {
                return true;
            }
        }
        return false;
    }
}
```

#### [1838. 最高频元素的频数](https://leetcode.cn/problems/frequency-of-the-most-frequent-element/)

元素的 **频数** 是该元素在一个数组中出现的次数。

给你一个整数数组 `nums` 和一个整数 `k` 。在一步操作中，你可以选择 `nums` 的一个下标，并将该下标对应元素的值增加 `1` 。

执行最多 `k` 次操作后，返回数组中最高频元素的 **最大可能频数** *。*

 

**示例 1：**

```
输入：nums = [1,2,4], k = 5
输出：3
解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
4 是数组中最高频元素，频数是 3 。
```

**示例 2：**

```
输入：nums = [1,4,8,13], k = 5
输出：2
解释：存在多种最优解决方案：
- 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
- 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
- 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
```

**示例 3：**

```
输入：nums = [3,9,6], k = 2
输出：1
```

 

**提示：**

- `1 <= nums.length <= 105`
- `1 <= nums[i] <= 105`
- `1 <= k <= 105`

```java
class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        long total = 0;
        int l = 0, res = 1;
        for (int r = 1; r < n; ++r) {
            total += (long) (nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                total -= nums[r] - nums[l];
                ++l;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
```

#### [540. 有序数组中的单一元素](https://leetcode.cn/problems/single-element-in-a-sorted-array/)

给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。

请你找出并返回只出现一次的那个数。

你设计的解决方案必须满足 `O(log n)` 时间复杂度和 `O(1)` 空间复杂度。

 

**示例 1:**

```
输入: nums = [1,1,2,3,3,4,4,8,8]
输出: 2
```

**示例 2:**

```
输入: nums =  [3,3,7,7,10,11,11]
输出: 10
```

 



**提示:**

- `1 <= nums.length <= 105`
- `0 <= nums[i] <= 105`

```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int ans = 0;
        for(int num : nums){
            ans = ans^num;
        }
        return ans;
    }
}
```

**二分**

```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (nums[mid] == nums[mid ^ 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }
}
```

**哈希**

```java
class Solution {
    public int singleNonDuplicate(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
```

#### [222. 完全二叉树的节点个数](https://leetcode.cn/problems/count-complete-tree-nodes/)

给你一棵 **完全二叉树** 的根节点 `root` ，求出该树的节点个数。

[完全二叉树](https://baike.baidu.com/item/完全二叉树/7773232?fr=aladdin) 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 `h` 层，则该层包含 `1~ 2h` 个节点。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/01/14/complete.jpg)

```
输入：root = [1,2,3,4,5,6]
输出：6
```

**示例 2：**

```
输入：root = []
输出：0
```

**示例 3：**

```
输入：root = [1]
输出：1
```

 

**提示：**

- 树中节点的数目范围是`[0, 5 * 104]`
- `0 <= Node.val <= 5 * 104`
- 题目数据保证输入的树是 **完全二叉树**

 

**进阶：**遍历树来统计节点是一种时间复杂度为 `O(n)` 的简单解决方案。你可以设计一个更快的算法吗？

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
    public int countNodes(TreeNode root) {
         if (root == null){
        return 0;
    }
    return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
```

#### [1712. 将数组分成三个子数组的方案数](https://leetcode.cn/problems/ways-to-split-array-into-three-subarrays/)

我们称一个分割整数数组的方案是 **好的** ，当它满足：

- 数组被分成三个 **非空** 连续子数组，从左至右分别命名为 `left` ， `mid` ， `right` 。
- `left` 中元素和小于等于 `mid` 中元素和，`mid` 中元素和小于等于 `right` 中元素和。

给你一个 **非负** 整数数组 `nums` ，请你返回 **好的** 分割 `nums` 方案数目。由于答案可能会很大，请你将结果对 `109 + 7` 取余后返回。

 

**示例 1：**

```
输入：nums = [1,1,1]
输出：1
解释：唯一一种好的分割方案是将 nums 分成 [1] [1] [1] 。
```

**示例 2：**

```
输入：nums = [1,2,2,2,5,0]
输出：3
解释：nums 总共有 3 种好的分割方案：
[1] [2] [2,2,5,0]
[1] [2,2] [2,5,0]
[1,2] [2,2] [5,0]
```

**示例 3：**

```
输入：nums = [3,2,1]
输出：0
解释：没有好的分割方案。
```

 

**提示：**

- `3 <= nums.length <= 105`
- `0 <= nums[i] <= 104`

```java
class Solution {
    public int waysToSplit(int[] nums) {
        int n=nums.length;
        int psum[]=new int[n+1];
        for (int i=0; i<n; ++i) {
            psum[i+1]=psum[i]+nums[i];
        }
        int res=0;
        int a=1, b=1;
        for (int i=0; i<n-2; ++i) {
            a=Math.max(i+1,a);
            while (a<n-1&&psum[a+1]-psum[i+1]<psum[i+1]) {
                a++;
            }
            if (a==n-1) {
                break;
            }
            b=Math.max(b,a);
            while (b<n-1&&psum[n]-psum[b+1]>=psum[b+1]-psum[i+1]) {
                b++;
            }
            res=(res+(b-a))%1000000007;
        }
        return res;
    }
}
```

#### [826. 安排工作以达到最大收益](https://leetcode.cn/problems/most-profit-assigning-work/)

难度中等100收藏分享切换为英文接收动态反馈

你有 `n` 个工作和 `m` 个工人。给定三个数组： `difficulty`, `profit` 和 `worker` ，其中:

- `difficulty[i]` 表示第 `i` 个工作的难度，`profit[i]` 表示第 `i` 个工作的收益。
- `worker[i]` 是第 `i` 个工人的能力，即该工人只能完成难度小于等于 `worker[i]` 的工作。

每个工人 **最多** 只能安排 **一个** 工作，但是一个工作可以 **完成多次** 。

- 举个例子，如果 3 个工人都尝试完成一份报酬为 `$1` 的同样工作，那么总收益为 `$3` 。如果一个工人不能完成任何工作，他的收益为 `$0` 。

返回 *在把工人分配到工作岗位后，我们所能获得的最大利润* 。

 

**示例 1：**

```
输入: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
输出: 100 
解释: 工人被分配的工作难度是 [4,4,6,6] ，分别获得 [20,20,30,30] 的收益。
```

**示例 2:**

```
输入: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
输出: 0
```

 

**提示:**

- `n == difficulty.length`
- `n == profit.length`
- `m == worker.length`
- `1 <= n, m <= 104`
- `1 <= difficulty[i], profit[i], worker[i] <= 105`

**贪心**

```java
class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        ArrayList<Integer> list = new ArrayList<>();
        int result = 0;
        for (int i = 0; i < worker.length; i++) {
            int maxSale = 0;
            for (int j = 0; j < difficulty.length; j++) {
                if (worker[i] >= difficulty[j] && profit[j] >= maxSale) {
                    maxSale = profit[j];
                }
            }
            list.add(maxSale);
        }
        for (int k = 0; k < list.size(); k++) {
            result += list.get(k);
        }
        return result;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/7b17e048af624274b2d2859666e34a3a.png)

#### [162. 寻找峰值](https://leetcode.cn/problems/find-peak-element/)

峰值元素是指其值严格大于左右相邻值的元素。

给你一个整数数组 `nums`，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 **任何一个峰值** 所在位置即可。

你可以假设 `nums[-1] = nums[n] = -∞` 。

你必须实现时间复杂度为 `O(log n)` 的算法来解决此问题。

 

**示例 1：**

```
输入：nums = [1,2,3,1]
输出：2
解释：3 是峰值元素，你的函数应该返回其索引 2。
```

**示例 2：**

```
输入：nums = [1,2,1,3,5,6,4]
输出：1 或 5 
解释：你的函数可以返回索引 1，其峰值元素为 2；
     或者返回索引 5， 其峰值元素为 6。
```

 

**提示：**

- `1 <= nums.length <= 1000`
- `-231 <= nums[i] <= 231 - 1`
- 对于所有有效的 `i` 都有 `nums[i] != nums[i + 1]`

```java
class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

**二分**

```java
class Solution {
    public boolean search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return nums[0] == target;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[l] == nums[mid] && nums[mid] == nums[r]) {
                ++l;
                --r;
            } else if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return false;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/08b54b7ac71d41d4a91bc2f8a8e5df12.png)

#### [1488. 避免洪水泛滥](https://leetcode.cn/problems/avoid-flood-in-the-city/)

你的国家有无数个湖泊，所有湖泊一开始都是空的。当第 `n` 个湖泊下雨前是空的，那么它就会装满水。如果第 `n` 个湖泊下雨前是 **满的** ，这个湖泊会发生 **洪水** 。你的目标是避免任意一个湖泊发生洪水。

给你一个整数数组 `rains` ，其中：

- `rains[i] > 0` 表示第 `i` 天时，第 `rains[i]` 个湖泊会下雨。
- `rains[i] == 0` 表示第 `i` 天没有湖泊会下雨，你可以选择 **一个** 湖泊并 **抽干** 这个湖泊的水。

请返回一个数组 `ans` ，满足：

- `ans.length == rains.length`
- 如果 `rains[i] > 0` ，那么`ans[i] == -1` 。
- 如果 `rains[i] == 0` ，`ans[i]` 是你第 `i` 天选择抽干的湖泊。

如果有多种可行解，请返回它们中的 **任意一个** 。如果没办法阻止洪水，请返回一个 **空的数组** 。

请注意，如果你选择抽干一个装满水的湖泊，它会变成一个空的湖泊。但如果你选择抽干一个空的湖泊，那么将无事发生。

 

**示例 1：**

```
输入：rains = [1,2,3,4]
输出：[-1,-1,-1,-1]
解释：第一天后，装满水的湖泊包括 [1]
第二天后，装满水的湖泊包括 [1,2]
第三天后，装满水的湖泊包括 [1,2,3]
第四天后，装满水的湖泊包括 [1,2,3,4]
没有哪一天你可以抽干任何湖泊的水，也没有湖泊会发生洪水。
```

**示例 2：**

```
输入：rains = [1,2,0,0,2,1]
输出：[-1,-1,2,1,-1,-1]
解释：第一天后，装满水的湖泊包括 [1]
第二天后，装满水的湖泊包括 [1,2]
第三天后，我们抽干湖泊 2 。所以剩下装满水的湖泊包括 [1]
第四天后，我们抽干湖泊 1 。所以暂时没有装满水的湖泊了。
第五天后，装满水的湖泊包括 [2]。
第六天后，装满水的湖泊包括 [1,2]。
可以看出，这个方案下不会有洪水发生。同时， [-1,-1,1,2,-1,-1] 也是另一个可行的没有洪水的方案。
```

**示例 3：**

```
输入：rains = [1,2,0,1,2]
输出：[]
解释：第二天后，装满水的湖泊包括 [1,2]。我们可以在第三天抽干一个湖泊的水。
但第三天后，湖泊 1 和 2 都会再次下雨，所以不管我们第三天抽干哪个湖泊的水，另一个湖泊都会发生洪水。
```

 

**提示：**

- `1 <= rains.length <= 105`
- `0 <= rains[i] <= 109`

**TreeSet**

```java
class Solution {
    public int[] avoidFlood(int[] rains) {
        int[] ans = new int[rains.length];
        Arrays.fill(ans, 1);
        // 雨水
        HashMap<Integer, Integer> waterMap = new HashMap<>();
        // 晴天
        TreeSet<Integer> zero = new TreeSet<>();
        for (int i = 0; i < rains.length; i++) {

            int rain = rains[i];
            if (rain == 0) {
                // 晴天 存下标
                zero.add(i);
                continue;
            }
            if (waterMap.containsKey(rain)) {
                // 下雨 之前这个湖泊下过雨
                // 找到之前最近的下雨的下标
                Integer higher = zero.higher(waterMap.get(rain));
                if (higher == null) {
                    return new int[]{};
                }
                // 清空下标
                ans[higher] = rain;
                // 移除使用过的晴天
                zero.remove(higher);
            }
            // 存放下雨的湖泊
            waterMap.put(rain, i);
            // 下雨不操作
            ans[i] = -1;
        }
        return ans;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/e9181e255fd044739b95d9edd4808e62.png)

#### [1562. 查找大小为 M 的最新分组](https://leetcode.cn/problems/find-latest-group-of-size-m/)

给你一个数组 `arr` ，该数组表示一个从 `1` 到 `n` 的数字排列。有一个长度为 `n` 的二进制字符串，该字符串上的所有位最初都设置为 `0` 。

在从 `1` 到 `n` 的每个步骤 `i` 中（假设二进制字符串和 `arr` 都是从 `1` 开始索引的情况下），二进制字符串上位于位置 `arr[i]` 的位将会设为 `1` 。

给你一个整数 `m` ，请你找出二进制字符串上存在长度为 `m` 的一组 `1` 的最后步骤。一组 `1` 是一个连续的、由 `1` 组成的子串，且左右两边不再有可以延伸的 `1` 。

返回存在长度 **恰好** 为 `m` 的 **一组 `1`** 的最后步骤。如果不存在这样的步骤，请返回 `-1` 。

 

**示例 1：**

```
输入：arr = [3,5,1,2,4], m = 1
输出：4
解释：
步骤 1："00100"，由 1 构成的组：["1"]
步骤 2："00101"，由 1 构成的组：["1", "1"]
步骤 3："10101"，由 1 构成的组：["1", "1", "1"]
步骤 4："11101"，由 1 构成的组：["111", "1"]
步骤 5："11111"，由 1 构成的组：["11111"]
存在长度为 1 的一组 1 的最后步骤是步骤 4 。
```

**示例 2：**

```
输入：arr = [3,1,5,4,2], m = 2
输出：-1
解释：
步骤 1："00100"，由 1 构成的组：["1"]
步骤 2："10100"，由 1 构成的组：["1", "1"]
步骤 3："10101"，由 1 构成的组：["1", "1", "1"]
步骤 4："10111"，由 1 构成的组：["1", "111"]
步骤 5："11111"，由 1 构成的组：["11111"]
不管是哪一步骤都无法形成长度为 2 的一组 1 。
```

**示例 3：**

```
输入：arr = [1], m = 1
输出：1
```

**示例 4：**

```
输入：arr = [2,1], m = 2
输出：2
```

 

**提示：**

- `n == arr.length`
- `1 <= n <= 10^5`
- `1 <= arr[i] <= n`
- `arr` 中的所有整数 **互不相同**
- `1 <= m <= arr.length`

**TreeSet**

```java
class Solution {
    public int findLatestStep(int[] arr, int m) {
        TreeSet<Integer> set=new TreeSet<>();
        set.add(0);
        set.add(arr.length+1);
        if(arr.length==m) return arr.length;
        int n=arr.length;
        for (int i = n-1; i >=0; i--) {
            int index=arr[i];
            int a=set.lower(index);
            int b=set.higher(index);
            if(index-a-1==m||b-index-1==m){
                return i;
            }
            set.add(index);
        }
        return -1;
    }
}
```


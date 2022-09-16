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

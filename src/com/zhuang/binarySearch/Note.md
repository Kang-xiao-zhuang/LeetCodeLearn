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

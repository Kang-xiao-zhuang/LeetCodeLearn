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

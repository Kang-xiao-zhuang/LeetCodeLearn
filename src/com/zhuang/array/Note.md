#### [26. 删除有序数组中的重复项](https://leetcode.cn/problems/remove-duplicates-from-sorted-array/)

给你一个 **升序排列** 的数组 `nums` ，请你**[ 原地](http://baike.baidu.com/item/原地算法)** 删除重复出现的元素，使每个元素 **只出现一次** ，返回删除后数组的新长度。元素的 **相对顺序** 应该保持 **一致** 。

由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 `k` 个元素，那么 `nums` 的前 `k` 个元素应该保存最终结果。

将最终结果插入 `nums` 的前 `k` 个位置后返回 `k` 。

不要使用额外的空间，你必须在 **[原地 ](https://baike.baidu.com/item/原地算法)修改输入数组** 并在使用 O(1) 额外空间的条件下完成。

**判题标准:**

系统会用下面的代码来测试你的题解:

```
int[] nums = [...]; // 输入数组
int[] expectedNums = [...]; // 长度正确的期望答案

int k = removeDuplicates(nums); // 调用

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
```

如果所有断言都通过，那么您的题解将被 **通过**。

 

**示例 1：**

```
输入：nums = [1,1,2]
输出：2, nums = [1,2,_]
解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
```

**示例 2：**

```
输入：nums = [0,0,1,1,1,2,2,3,3,4]
输出：5, nums = [0,1,2,3,4]
解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
```

 

**提示：**

- `1 <= nums.length <= 3 * 104`
- `-104 <= nums[i] <= 104`
- `nums` 已按 **升序** 排列



定义索引，找不到继续，返回数组的长度+1即可

```java
class Solution {
    public int removeDuplicates(int[] nums) {
         // 定义索引
        int index=0;
        for (int num : nums) {
            if (nums[index]!=num){
                index++;
                nums[index]=num;
            }
        }
        return index+1;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/143a6330691b48d6a9e38acfd0818845.png)



双指针法

定义两个指针fast和slow分别为快指针和慢指针，快指针表示遍历数组到达的下标位置，慢指针表示下一个不同元素要填入的下标位置，初始时两个都指向下标1。

数组长度为n，将快指针遍历依次从1到n-1的位置，对于每个位置，如果num[fast]!=nums[fast - 1]，说明nums[fast]和之前的元素都不同，因此将nums[fast]的值复制到nums[slow]，然后将slow的值++，即指向下一个位置。

遍历结束后，从nums[0] 到nums[slow−1] 的每个元素都不相同且包含原数组中的每个不同的元素，因此新的长度即为slow，返回 slow 即可。

```java
class Solution {
    public int removeDuplicates(int[] nums) {
         int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int slow = 1, fast = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/bcffe56ec9b44cbe9e78275c254c34f7.png)

#### [122. 买卖股票的最佳时机 II](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/)

给你一个整数数组 `prices` ，其中 `prices[i]` 表示某支股票第 `i` 天的价格。

在每一天，你可以决定是否购买和/或出售股票。你在任何时候 **最多** 只能持有 **一股** 股票。你也可以先购买，然后在 **同一天** 出售。

返回 *你能获得的 **最大** 利润* 。

 

**示例 1：**

```
输入：prices = [7,1,5,3,6,4]
输出：7
解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
     总利润为 4 + 3 = 7 。
```

**示例 2：**

```
输入：prices = [1,2,3,4,5]
输出：4
解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
     总利润为 4 。
```

**示例 3：**

```
输入：prices = [7,6,4,3,1]
输出：0
解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。
```

 

**提示：**

- `1 <= prices.length <= 3 * 104`
- `0 <= prices[i] <= 104`



```java
class Solution {
    public int maxProfit(int[] prices) {
        int sum=0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]>prices[i-1]){
                sum+=prices[i]-prices[i-1];
            }
        }
        return sum;
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/5d57d5d4d40c4c8289254e25f0592c52.png)

#### [189. 轮转数组](https://leetcode.cn/problems/rotate-array/)

给你一个数组，将数组中的元素向右轮转 `k` 个位置，其中 `k` 是非负数。

 

**示例 1:**

```
输入: nums = [1,2,3,4,5,6,7], k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右轮转 1 步: [7,1,2,3,4,5,6]
向右轮转 2 步: [6,7,1,2,3,4,5]
向右轮转 3 步: [5,6,7,1,2,3,4]
```

**示例 2:**

```
输入：nums = [-1,-100,3,99], k = 2
输出：[3,99,-1,-100]
解释: 
向右轮转 1 步: [99,-1,-100,3]
向右轮转 2 步: [3,99,-1,-100]
```

 

**提示：**

- `1 <= nums.length <= 105`
- `-231 <= nums[i] <= 231 - 1`
- `0 <= k <= 105`

 

**进阶：**

- 尽可能想出更多的解决方案，至少有 **三种** 不同的方法可以解决这个问题。
- 你可以使用空间复杂度为 `O(1)` 的 **原地** 算法解决这个问题吗？



使用额外的数组存放每个元素的位置，将原数组下标为 i 的元素放至新数组下标为` (i+k)%n`的位置，最后将新数组拷贝至原数组即可

```java
class Solution {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        int[] temp = new int[len];
        for (int i = 0; i < len; i++) {
            temp[(i + k) % len] = nums[i];
        }
        System.arraycopy(temp,0,nums,0,len);
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/070c580a80d245448a34a063a684b0b5.png)



数组翻转

将数组的元素向右移动k次后，尾部`k%n`个元素会移动到数组头部，其余元素向后移动`k%n`.

先将所有的元素翻转，尾部`k%n`个元素会移动到数组头部，然后翻转[0,k%n-1]区间的元素和[k%n,n-1]区间的元素就能得到最后的答案。

| 操作                          | 结果          |
| ----------------------------- | ------------- |
| 原始数组                      | 1 2 3 4 5 6 7 |
| 翻转所有元素                  | 7 6 5 4 3 2 1 |
| 翻转 [0, k% n - 1] 区间的元素 | 5 6 7 4 3 2 1 |
| 翻转 [k%n,n-1]区间的元素      | 5 6 7 1 2 3 4 |



```java
class Solution {
    public void rotate(int[] nums, int k) {
         k%=nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }
    public void reverse(int[] nums,int start,int end){
        while (start<end){
            int temp=nums[start];
            nums[start]=nums[end];
            nums[end]=temp;
            start+=1;
            end-=1;
        }
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/4c3e6cc33744475bbdae5ea8ec954872.png)
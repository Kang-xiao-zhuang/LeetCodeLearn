package com.zhuang.binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * description: Solution007
 * date: 2022/9/23 17:54
 * author: Zhuang
 * version: 1.0
 */
public class Solution007 {
    public static void main(String[] args) {
        int[] dist = {1, 3, 2};
        Solution007 solution007 = new Solution007();
        //solution007.minSpeedOnTime(dist, 2.7);
        String s = "abcbddddd";
        String p = "abcd";
        int[] removable = {3, 2, 1, 4, 5, 6};
        solution007.maximumRemovals(s, p, removable);
        int[] citations = {0, 1, 3, 5, 6};
        solution007.hIndex(citations);
        int[] nums = {1, 1, 2, 3, 3, 4, 4, 8, 8};
        solution007.singleNonDuplicate(nums);
        solution007.singleNonDuplicate2(nums);
        solution007.singleNonDuplicate3(nums);
    }


    /**
     * https://leetcode.cn/problems/minimum-speed-to-arrive-on-time/
     *
     * @param dist 整数数组
     * @param hour 浮点数
     * @return 能满足你准时到达办公室所要求全部列车的 最小正整数 时速
     */
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

    /**
     * https://leetcode.cn/problems/maximum-number-of-removable-characters/
     *
     * @param s         字符串
     * @param p         字符串
     * @param removable 整数数组
     * @return 找出的 最大 k ，满足在移除字符后 p 仍然是 s 的一个子序列
     */
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
        return j == str2.length;
    }

    /**
     * https://leetcode.cn/problems/minimum-number-of-days-to-make-m-bouquets/
     *
     * @param bloomDay 整数数组
     * @param m        整数
     * @param k        整数
     * @return 从花园中摘 m 束花需要等待的最少的天数
     */
    public int minDays(int[] bloomDay, int m, int k) {

        if (m > bloomDay.length / k) return -1; // 此时无论如何都制作不出来m束花

        // 只要 m <= bloomDay.length / k, 成立，无论如何都能制作出来
        // 调用两次stream()方法求最值的效率较低，可以使用常规方法替代！
        int low = Arrays.stream(bloomDay).min().getAsInt(); // 花开的最小天数
        int high = Arrays.stream(bloomDay).max().getAsInt(); // 花开的最大天数

        // 如果可以制作m束花，天数一定在low和high之间，因此使用二分查找
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (make(bloomDay, m, k, mid)) {
                high = mid;
            } else low = mid + 1;
        }
        return low;

    }

    public boolean make(int[] bloomDay, int m, int k, int days) {
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


    /**
     * https://leetcode.cn/problems/minimum-absolute-sum-difference/
     *
     * @param nums1 正整数数组
     * @param nums2 正整数数组
     * @return 最小绝对差值和
     */
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

    /**
     * https://leetcode.cn/problems/h-index-ii/
     *
     * @param citations 整数数组
     * @return 该研究者的 h 指数
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0;
        int right = n - 1;
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

    /**
     * https://leetcode.cn/problems/frequency-of-the-most-frequent-element/
     *
     * @param nums 整数数组
     * @param k    整数k
     * @return 数组中最高频元素的 最大可能频数
     */
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


    /**
     * https://leetcode.cn/problems/single-element-in-a-sorted-array/
     *
     * @param nums 数组
     * @return 整数
     */
    public int singleNonDuplicate(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans = ans ^ num;
        }
        return ans;
    }

    public int singleNonDuplicate2(int[] nums) {
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


    public int singleNonDuplicate3(int[] nums) {
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

    /**
     * https://leetcode.cn/problems/count-complete-tree-nodes/
     *
     * @param root 根节点
     * @return 节点个数
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        int low = 1 << level;
        int high = (1 << (level + 1)) - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }

    /**
     * https://leetcode.cn/problems/most-profit-assigning-work/
     *
     * @param difficulty 工作的难度
     * @param profit     工作的收益
     * @param worker     工人的能力
     * @return 最大利润
     */
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        ArrayList<Integer> list = new ArrayList<>();
        int result = 0;
        for (int k : worker) {
            int maxSale = 0;
            for (int j = 0; j < difficulty.length; j++) {
                if (k >= difficulty[j] && profit[j] >= maxSale) {
                    maxSale = profit[j];
                }
            }
            list.add(maxSale);
        }
        for (Integer integer : list) {
            result += integer;
        }
        return result;
    }

    /**
     * https://leetcode.cn/problems/search-in-rotated-sorted-array-ii/
     *
     * @param nums   整数数组
     * @param target 目标值
     * @return 布尔
     */
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

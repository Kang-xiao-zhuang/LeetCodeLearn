package com.zhuang.Daily.twotwo.January;

import java.util.*;

/**
 * @Classname Solution002
 * @Description 2022.1.12-2022.1.22 每日一题
 * @Date 2022/1/12 16:54
 * @Author by dell
 */
public class Solution002 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        increasingTriplet(nums);
    }

    /**
     * https://leetcode-cn.com/problems/increasing-triplet-subsequence/
     * 1.12
     *
     * @param nums 整数数组
     * @return 判断这个数组中是否存在长度为 3 的递增子序列
     */
    public static boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        int[] leftMin = new int[n];
        leftMin[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i - 1], nums[i]);
        }
        int[] rightMax = new int[n];
        rightMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > leftMin[i - 1] && nums[i] < rightMax[i + 1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * https://leetcode-cn.com/problems/largest-number-at-least-twice-of-others/
     * 1.13
     *
     * @param nums 整数数组
     * @return 返回 最大元素的下标
     */
    public int dominantIndex(int[] nums) {
        int m1 = -1, m2 = -1;
        int index = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > m1) {
                m2 = m1;
                m1 = nums[i];
                index = i;
            } else if (nums[i] > m2) {
                m2 = nums[i];
            }
        }
        return m1 >= m2 * 2 ? index : -1;
    }

    /**
     * https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/
     * 1.14
     *
     * @param nums1 整数数组
     * @param nums2 整数数组
     * @param k     整数
     * @return 最小的 k 个数对
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2) -> {
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i, 0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            if (idxPair[1] + 1 < n) {
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/calculate-money-in-leetcode-bank/
     * 1.15
     *
     * @param n 天数
     * @return 总价
     */
    public int totalMoney(int n) {
        int week = 1, day = 1;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += week + day - 1;
            ++day;
            if (day == 8) {
                day = 1;
                ++week;
            }
        }
        return res;
    }

    List<Integer> list;
    Random random;

    /**
     * https://leetcode-cn.com/problems/linked-list-random-node/
     * 1.16
     *
     * @param head 头节点
     */
    public Solution002(ListNode head) {
        list = new ArrayList<Integer>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        random = new Random();
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }


    /**
     * https://leetcode-cn.com/problems/count-vowels-permutation/
     * 1.17
     *
     * @param n 整数
     * @return 长度为N的字符串
     */
    public int countVowelPermutation(int n) {
        long mod = 1000000007;
        long[] dp = new long[5];
        long[] ndp = new long[5];
        for (int i = 0; i < 5; ++i) {
            dp[i] = 1;
        }
        for (int i = 2; i <= n; ++i) {
            /* a前面可以为e,u,i */
            ndp[0] = (dp[1] + dp[2] + dp[4]) % mod;
            /* e前面可以为a,i */
            ndp[1] = (dp[0] + dp[2]) % mod;
            /* i前面可以为e,o */
            ndp[2] = (dp[1] + dp[3]) % mod;
            /* o前面可以为i */
            ndp[3] = dp[2];
            /* u前面可以为i,o */
            ndp[4] = (dp[2] + dp[3]) % mod;
            System.arraycopy(ndp, 0, dp, 0, 5);
        }
        long ans = 0;
        for (int i = 0; i < 5; ++i) {
            ans = (ans + dp[i]) % mod;
        }
        return (int) ans;
    }

    /**
     * https://leetcode-cn.com/problems/minimum-time-difference/
     * 1.18
     *
     * @param timePoints 时间列表
     * @return 最小时间差
     */
    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size() * 2;
        int[] nums = new int[n];
        for (int i = 0, idx = 0; i < n / 2; i++, idx += 2) {
            String[] ss = timePoints.get(i).split(":");
            int h = Integer.parseInt(ss[0]), m = Integer.parseInt(ss[1]);
            nums[idx] = h * 60 + m;
            nums[idx + 1] = nums[idx] + 1440;
        }
        Arrays.sort(nums);
        int ans = nums[1] - nums[0];
        for (int i = 0; i < n - 1; i++) ans = Math.min(ans, nums[i + 1] - nums[i]);
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/contains-duplicate-ii/
     * 1.19
     *
     * @param nums 数组
     * @param k    目标值
     * @return 布尔值
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                Integer tmp = map.get(nums[i]);
                if (i - tmp <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }
}

package com.zhuang.binarySearch;

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
}

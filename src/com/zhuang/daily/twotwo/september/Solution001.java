package com.zhuang.daily.twotwo.september;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution001 {
    public static void main(String[] args) {
        Solution001 solution001 = new Solution001();
        String[] logs = {"d1/", "d2/", "../", "d21/", "./"};
        solution001.minOperations(logs);
        int[] nums = {0, 4, 3, 0, 4};
        // solution001.specialArray(nums);
        //solution001.specialArray2(nums);
        solution001.specialArray3(nums);
        solution001.maximumSwap(2736);
    }


    /**
     * https://leetcode.cn/problems/crawler-log-folder/
     * 9.9
     *
     * @param logs 字符串列表
     * @return 返回主文件夹所需的最小步数
     */
    public int minOperations(String[] logs) {
        int depth = 0;
        for (String log : logs) {
            if ("./".equals(log)) {
                continue;
            } else if ("../".equals(log)) {
                if (depth > 0) {
                    depth--;
                }
            } else {
                depth++;
            }
        }
        return depth;
    }

    /**
     * https://leetcode.cn/problems/trim-a-binary-search-tree/
     * 9.10
     *
     * @param root 根节点
     * @param low  最小边界
     * @param high 最大边界
     * @return TreeNode
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            return trimBST(root.left, low, high);
        } else {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }

    /**
     * https://leetcode.cn/problems/minimum-cost-to-hire-k-workers/
     * 9.11
     * CV官解
     *
     * @param quality 数组
     * @param wage    数组
     * @param k       k名工人
     * @return 组成满足上述条件的付费群体所需的最小金额
     */
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Integer[] h = new Integer[n];
        for (int i = 0; i < n; i++) {
            h[i] = i;
        }
        Arrays.sort(h, (a, b) -> {
            return quality[b] * wage[a] - quality[a] * wage[b];
        });
        double res = 1e9;
        double totalq = 0.0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        for (int i = 0; i < k - 1; i++) {
            totalq += quality[h[i]];
            pq.offer(quality[h[i]]);
        }
        for (int i = k - 1; i < n; i++) {
            int idx = h[i];
            totalq += quality[idx];
            pq.offer(quality[idx]);
            double totalc = ((double) wage[idx] / quality[idx]) * totalq;
            res = Math.min(res, totalc);
            totalq -= pq.poll();
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/special-array-with-x-elements-greater-than-or-equal-x/
     * 9.12
     *
     * @param nums 数组
     * @return 特征值
     */
    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        for (int i = 1; i <= n; ++i) {
            if (nums[i - 1] >= i && (i == n || nums[i] < i)) {
                return i;
            }
        }
        return -1;
    }

    public int specialArray2(int[] nums) {
        int[] cnts = new int[1010];
        for (int x : nums) {
            cnts[x]++;
        }
        for (int i = 1009, tot = 0; i >= 0; i--) {
            tot += cnts[i];
            if (i == tot) {
                return i;
            }
        }
        return -1;
    }

    public int specialArray3(int[] nums) {
        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            int cnt = 0;
            for (int num : nums) {
                if (num >= i) {
                    cnt++;
                }
            }
            if (cnt == i) {
                return i;
            }
        }
        return -1;
    }

    /**
     * https://leetcode.cn/problems/maximum-swap/
     * 9.13
     *
     * @param num 非负整数
     * @return 最大整数
     */
    public int maximumSwap(int num) {
        char[] charArray = String.valueOf(num).toCharArray();
        int n = charArray.length;
        int maxNum = num;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(charArray, i, j);
                maxNum = Math.max(maxNum, Integer.parseInt(new String(charArray)));
                swap(charArray, i, j);
            }
        }
        return maxNum;
    }

    public void swap(char[] charArray, int i, int j) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }

    /**
     * https://leetcode.cn/problems/mean-of-array-after-removing-some-elements/
     * 9.14
     *
     * @param arr 数组
     * @return 剩余数字的平均值
     */
    public double trimMean(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        int partialSum = 0;
        for (int i = n / 20; i < 19 * n / 20; i++) {
            partialSum += arr[i];
        }
        return partialSum / (n * 0.9);
    }


    /**
     * https://leetcode.cn/problems/bulb-switcher-ii/
     * 9.15
     * 题解:https://leetcode.cn/problems/bulb-switcher-ii/solution/dengp-by-capital-worker-51rb/
     *
     * @param n       灯泡数量
     * @param presses 按压开关次数
     * @return 不同可能状态
     */
    public int flipLights(int n, int presses) {
        //不按开关
        if (presses == 0) {
            return 1;
        }
        //特殊情况处理
        if (n == 1) {
            return 2;
        } else if (n == 2) {
            //特殊情况
            return presses == 1 ? 3 : 4;
        } else {
            //n >= 3
            return presses == 1 ? 4 : presses == 2 ? 7 : 8;
        }
    }
}

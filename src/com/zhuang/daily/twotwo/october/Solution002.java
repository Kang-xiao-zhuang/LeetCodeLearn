package com.zhuang.daily.twotwo.october;

import java.util.*;

/**
 * description: Solution002
 * date: 2022/10/10 8:30
 * author: Zhuang
 * version: 1.0
 */
public class Solution002 {
    public static void main(String[] args) {
        int[] target = {1, 2};
        Solution002 solution002 = new Solution002();
        solution002.buildArray(target, 4);
    }

    /**
     * https://leetcode.cn/problems/minimum-swaps-to-make-sequences-increasing/
     * 10.10
     *
     * @param nums1 整型数组
     * @param nums2 整型数组
     * @return 使 nums1 和 nums2 严格递增 所需操作的最小次数
     */
    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int a = 0, b = 1;
        for (int i = 1; i < n; i++) {
            int at = a, bt = b;
            a = b = n;
            if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
                a = Math.min(a, at);
                b = Math.min(b, bt + 1);
            }
            if (nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                a = Math.min(a, bt);
                b = Math.min(b, at + 1);
            }
        }
        return Math.min(a, b);
    }


    /**
     * https://leetcode.cn/problems/check-if-one-string-swap-can-make-strings-equal/
     * 2022.10.11
     *
     * @param s1 字符串
     * @param s2 字符串
     * @return 布尔
     */
    public boolean areAlmostEqual(String s1, String s2) {
        char[] c1 = new char[2];
        char[] c2 = new char[2];
        int k = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (k > 1) return false;
                c1[k] = s1.charAt(i);
                c2[k] = s2.charAt(i);
                k++;
            }
        }
        return c1[0] == c2[1] && c1[1] == c2[0];
    }

    public boolean areAlmostEqual2(String s1, String s2) {
        int n = s1.length();
        List<Integer> diff = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (diff.size() >= 2) {
                    return false;
                }
                diff.add(i);
            }
        }
        if (diff.isEmpty()) {
            return true;
        }
        if (diff.size() != 2) {
            return false;
        }
        return s1.charAt(diff.get(0)) == s2.charAt(diff.get(1)) && s1.charAt(diff.get(1)) == s2.charAt(diff.get(0));
    }

    /**
     * https://leetcode.cn/problems/linked-list-components/
     * 2022.10.12
     *
     * @param head 头节点
     * @param nums 列表
     * @return 列表 nums 中组件的个数
     */
    public int numComponents(ListNode head, int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        boolean inSet = false;
        int ans = 0;
        while (head != null) {
            if (set.contains(head.val)) {
                if (!inSet) {
                    inSet = true;
                    ans++;
                }
            } else {
                inSet = false;
            }
            head = head.next;
        }
        return ans;
    }


    public int numComponents2(ListNode head, int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 0;
        ListNode cur = new ListNode(-1, head);
        while (head != null) {
            if (set.contains(head.val) && !set.contains(cur.val)) {
                ans++;
            }
            cur = head;
            head = head.next;
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/max-chunks-to-make-sorted/
     * 2022.10.13
     *
     * @param arr 数组
     * @return 数组能分成的最多块数量
     */
    public int maxChunksToSorted(int[] arr) {
        int m = 0, res = 0;
        for (int i = 0; i < arr.length; i++) {
            m = Math.max(m, arr[i]);
            if (m == i) {
                res++;
            }
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/distinct-subsequences-ii/
     * 2022.10.14
     *
     * @param s 字符串
     * @return 计算 s 的 不同非空子序列 的个数
     */
    public int distinctSubseqII(String s) {
        final int MOD = 1000000007;
        int[] last = new int[26];
        Arrays.fill(last, -1);

        int n = s.length();
        int[] f = new int[n];
        Arrays.fill(f, 1);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 26; ++j) {
                if (last[j] != -1) {
                    f[i] = (f[i] + f[last[j]]) % MOD;
                }
            }
            last[s.charAt(i) - 'a'] = i;
        }

        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            if (last[i] != -1) {
                ans = (ans + f[last[i]]) % MOD;
            }
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/build-an-array-with-stack-operations/
     * 2022.10.15
     *
     * @param target 目标数组
     * @param n      包含 1 到 n 之间的数字
     * @return 构建目标数组所用的操作序列
     */
    public List<String> buildArray(int[] target, int n) {
        ArrayList<String> list = new ArrayList<>();
        int index = 1;
        for (int num : target) {
            while (num != index) {
                list.add("Push");
                list.add("Pop");
                index++;
            }
            list.add("Push");
            index++;
        }
        return list;
    }

    /**
     * https://leetcode.cn/problems/possible-bipartition/
     * 2022.10.16
     *
     * @param n        整数
     * @param dislikes 数组
     * @return 可以用这种方法将所有人分进两组时
     */
    public boolean possibleBipartition(int n, int[][] dislikes) {
        int[] color = new int[n + 1];
        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i <= n; ++i) {
            g[i] = new ArrayList<Integer>();
        }
        for (int[] p : dislikes) {
            g[p[0]].add(p[1]);
            g[p[1]].add(p[0]);
        }
        for (int i = 1; i <= n; ++i) {
            if (color[i] == 0) {
                Queue<Integer> queue = new ArrayDeque<>();
                queue.offer(i);
                color[i] = 1;
                while (!queue.isEmpty()) {
                    int t = queue.poll();
                    for (int next : g[t]) {
                        if (color[next] > 0 && color[next] == color[t]) {
                            return false;
                        }
                        if (color[next] == 0) {
                            color[next] = 3 ^ color[t];
                            queue.offer(next);
                        }
                    }
                }
            }
        }
        return true;
    }
}

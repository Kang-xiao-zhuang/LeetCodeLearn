package com.zhuang.Daily.December;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Classname Solution001
 * @Description 2021.12.1-2021.12.7每日一题
 * @Date 2021/12/1 20:26
 * @Author by dell
 */
public class Solution001 {
    public static void main(String[] args) {
        // maxPower("abbcccddddeeeeedcba");
        //int[] score = {5, 4, 3, 2, 1};
        //findRelativeRanks(score);

        truncateSentence2("Hello how are you Contestant", 4);
    }

    /**
     * https://leetcode-cn.com/problems/consecutive-characters/
     * 12.1
     *
     * @param s 字符串
     * @return 只包含一种字符的最长非空子字符串的长度
     */
    public static int maxPower(String s) {
        int ans = 1, cnt = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                cnt++;
                ans = Math.max(ans, cnt);
            } else {
                cnt = 1;
            }
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/relative-ranks/
     * 12.2
     *
     * @param score 分数
     * @return 数组
     */
    public static String[] findRelativeRanks(int[] score) {
        String[] ss = new String[]{"Gold Medal", "Silver Medal", "Bronze Medal"};
        int n = score.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = score[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            if (i >= 3) {
                ans[arr[i][1]] = Integer.toString(i + 1);
            } else {
                ans[arr[i][1]] = ss[i];
            }
        }
        System.out.println(Arrays.toString(ans));
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations/
     * 12.3
     *
     * @param nums 数组
     * @param k    整数k
     * @return 可能的最大和
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < k; i++) {
            Arrays.sort(nums);
            nums[0] = -nums[0];
        }
        for (int i : nums) {
            count += i;
        }
        return count;
    }

    public int largestSumAfterKNegations2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int sum = Arrays.stream(nums).sum();
        for (int i = -100; i < 0; ++i) {
            if (map.containsKey(i)) {
                int ops = Math.min(k, map.get(i));
                sum += (-i) * ops * 2;
                map.put(i, map.get(i) - ops);
                map.put(-i, map.getOrDefault(-i, 0) + ops);
                k -= ops;
                if (k == 0) {
                    break;
                }
            }
        }
        if (k > 0 && k % 2 == 1 && !map.containsKey(0)) {
            for (int i = 1; i <= 100; ++i) {
                if (map.containsKey(i)) {
                    sum -= i * 2;
                    break;
                }
            }
        }
        return sum;
    }

    public int largestSumAfterKNegations3(int[] nums, int k) {
        while (k > 0) {
            int index = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < nums[index]) {
                    index = i;
                }
            }
            nums[index] = -nums[index];
            k--;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    /**
     * https://leetcode-cn.com/problems/ransom-note/
     * int数组
     * 12.4
     *
     * @param ransomNote 数组1
     * @param magazine   数组2
     * @return 布尔值
     */
    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] arr = new int[26];
        // 记录杂志出现的字符串次数
        int temp;
        for (int i = 0; i < magazine.length(); i++) {
            temp = magazine.charAt(i) - 'a';
            arr[temp]++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            temp = ransomNote.charAt(i) - 'a';
            if (arr[temp] > 0) {
                arr[temp]--;
            } else {
                System.out.println(false);
                return false;
            }
        }
        System.out.println(true);
        return true;
    }

    /**
     * char数组
     *
     * @param ransomNote 字符串1
     * @param magazine   字符串2
     * @return 布尔值
     */
    public static boolean canConstruct2(String ransomNote, String magazine) {
        char[] chars = new char[26];
        for (char c : magazine.toCharArray()) {
            chars[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (chars[c - 'a']-- == 0) {
                System.out.println(false);
                return false;
            }
        }
        System.out.println(true);
        return true;
    }


    int MOD = 1337;

    /**
     * https://leetcode-cn.com/problems/super-pow/
     * 12.5
     *
     * @param a 正整数
     * @param b 非常大的正整数
     * @return 取模
     */
    public int superPow(int a, int[] b) {
        return dfs(a, b, b.length - 1);
    }

    public int dfs(int a, int[] b, int u) {
        if (u == -1) {
            return 1;
        }
        return qpow(dfs(a, b, u - 1), 10) * qpow(a, b[u]) % MOD;
    }

    public int qpow(int a, int b) {
        int ans = 1;
        a %= MOD;
        while (b != 0) {
            if ((b & 1) != 0) {
                ans = ans * a % MOD;
            }
            a = a * a % MOD;
            b >>= 1;
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/truncate-sentence/
     * 12.6
     *
     * @param s 字符串
     * @param k 整数
     * @return 截断后的句子
     */
    public static String truncateSentence(String s, int k) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                count++;
                if (count == k) {
                    return s.substring(0, i);
                }
            }
        }
        return s;
    }

    public static String truncateSentence2(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < s.length() && count < k; i++) {
            if (s.charAt(i) == ' ') {
                count++;
            }
            if (count < k) {
                sb.append(s.charAt(i));
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}

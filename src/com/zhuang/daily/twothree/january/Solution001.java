package com.zhuang.daily.twothree.january;

import java.util.*;

/**
 * description: Solution001
 * date: 2023/1/1 8:30
 * author: Zhuang
 * version: 1.0
 */
public class Solution001 {

    public static void main(String[] args) {
        Solution001 solution001 = new Solution001();
        solution001.repeatedCharacter("abcdd");
    }

    /**
     * https://leetcode.cn/problems/first-letter-to-appear-twice/
     * 2023.1.1
     *
     * @param s 字符串
     * @return 找出并返回第一个出现 两次 的字母
     */
    public char repeatedCharacter(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char res = 0;
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) == 2) {
                res = c;
                break;
            }
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/number-of-orders-in-the-backlog/
     * 2023.1.2
     *
     * @param orders 二维整数数组
     * @return 积压订单中的 订单总数 。由于数字可能很大，所以需要返回对 109 + 7 取余的结果
     */
    public int getNumberOfBacklogOrders(int[][] orders) {
        final int MOD = 1000000007;
        PriorityQueue<int[]> buyOrders = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        PriorityQueue<int[]> sellOrders = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int[] order : orders) {
            int price = order[0], amount = order[1], orderType = order[2];
            if (orderType == 0) {
                while (amount > 0 && !sellOrders.isEmpty() && sellOrders.peek()[0] <= price) {
                    int[] sellOrder = sellOrders.poll();
                    int sellAmount = Math.min(amount, sellOrder[1]);
                    amount -= sellAmount;
                    sellOrder[1] -= sellAmount;
                    if (sellOrder[1] > 0) {
                        sellOrders.offer(sellOrder);
                    }
                }
                if (amount > 0) {
                    buyOrders.offer(new int[]{price, amount});
                }
            } else {
                while (amount > 0 && !buyOrders.isEmpty() && buyOrders.peek()[0] >= price) {
                    int[] buyOrder = buyOrders.poll();
                    int buyAmount = Math.min(amount, buyOrder[1]);
                    amount -= buyAmount;
                    buyOrder[1] -= buyAmount;
                    if (buyOrder[1] > 0) {
                        buyOrders.offer(buyOrder);
                    }
                }
                if (amount > 0) {
                    sellOrders.offer(new int[]{price, amount});
                }
            }
        }
        int total = 0;
        for (PriorityQueue<int[]> pq : Arrays.asList(buyOrders, sellOrders)) {
            while (!pq.isEmpty()) {
                int[] order = pq.poll();
                total = (total + order[1]) % MOD;
            }
        }
        return total;
    }

    /**
     * https://leetcode.cn/problems/check-if-numbers-are-ascending-in-a-sentence/
     * 2023.1.3
     *
     * @param s 字符串
     * @return 需要检查 s 中的 全部 数字是否从左到右严格递增
     */
    public boolean areNumbersAscending(String s) {
        int pre = 0, pos = 0;
        while (pos < s.length()) {
            if (Character.isDigit(s.charAt(pos))) {
                int cur = 0;
                while (pos < s.length() && Character.isDigit(s.charAt(pos))) {
                    cur = cur * 10 + s.charAt(pos) - '0';
                    pos++;
                }
                if (cur <= pre) {
                    return false;
                }
                pre = cur;
            } else {
                pos++;
            }
        }
        return true;
    }

    /**
     * https://leetcode.cn/problems/maximum-value-at-a-given-index-in-a-bounded-array/
     * 2023.1.4
     *
     * @param n      正整数
     * @param index  正整数
     * @param maxSum 正整数
     * @return 构造的数组中的 nums[index]
     */
    public int maxValue(int n, int index, int maxSum) {
        long l = 0, r = maxSum;
        //因为数组中所有的数均为正整数，所以减去n，剩余的表示可以填的数
        maxSum -= n;
        while (l < r) {
            //m表示index指向位置的高度
            long m = l + r + 1 >> 1;

            //计算当index的位置高度为m时，数组所有元素的和
            long count = m * m;
            //如果左边越界，就减去左边多的
            if (m > index) count -= (m - index - 1) * (m - index) / 2;
            //如果右边越界，就减去右边多的
            if (m > (n - index)) count -= (m - (n - index - 1) - 1) * (m - (n - index - 1)) / 2;

            //二分法判断
            if (count > maxSum) r = m - 1;
            else l = m;
        }
        return (int) l + 1;
    }

    /**
     * https://leetcode.cn/problems/count-integers-with-even-digit-sum/
     * 2023.1.6
     *
     * @param num 正整数
     * @return 返回 小于或等于 num 且各位数字之和为 偶数 的正整数的数目
     */
    public int countEven(int num) {
        int res = 0;
        for (int i = 1; i <= num; i++) {
            int x = i, sum = 0;
            while (x != 0) {
                sum += x % 10;
                x /= 10;
            }
            if (sum % 2 == 0) {
                res++;
            }
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/
     * 2023.1.7
     *
     * @param nums 整数数组
     * @param x    整数
     * @return 最小操作数
     */
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();

        if (sum < x) {
            return -1;
        }

        int right = 0;
        int lsum = 0, rsum = sum;
        int ans = n + 1;

        for (int left = -1; left < n; ++left) {
            if (left != -1) {
                lsum += nums[left];
            }
            while (right < n && lsum + rsum > x) {
                rsum -= nums[right];
                ++right;
            }
            if (lsum + rsum == x) {
                ans = Math.min(ans, (left + 1) + (n - right));
            }
        }

        return ans > n ? -1 : ans;
    }

    /**
     * https://leetcode.cn/problems/counting-words-with-a-given-prefix/
     * 2023.1.8
     *
     * @param words 字符串数组
     * @param pref  字符串
     * @return words 中以 pref 作为 前缀 的字符串的数目
     */
    public int prefixCount(String[] words, String pref) {
        int res = 0;
        for (String word : words) {
            if (word.startsWith(pref)) {
                res++;
            }
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/minimum-number-of-operations-to-reinitialize-a-permutation/
     * 2023.1.9
     *
     * @param n 偶数
     * @return 最小的 非零 操作步数
     */
    public int reinitializePermutation(int n) {
        int[] perm = new int[n];
        int[] target = new int[n];
        for (int i = 0; i < n; i++) {
            perm[i] = i;
            target[i] = i;
        }
        int step = 0;
        while (true) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                if ((i & 1) != 0) {
                    arr[i] = perm[n / 2 + (i - 1) / 2];
                } else {
                    arr[i] = perm[i / 2];
                }
            }
            perm = arr;
            step++;
            if (Arrays.equals(perm, target)) {
                break;
            }
        }
        return step;
    }

    /**
     * https://leetcode.cn/problems/check-if-number-has-equal-digit-count-and-digit-value/
     * 2023.1.11
     *
     * @param num 字符串
     * @return 满足数位 i 在 num 中出现了 num[i]次
     */
    public boolean digitCount(String num) {
        Map<Integer, Integer> h = new HashMap<>();
        int n = num.length();
        for (int i = 0; i < n; i++) {
            int digit = num.charAt(i) - '0';
            h.put(digit, h.getOrDefault(digit, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            int v = num.charAt(i) - '0';
            if (h.getOrDefault(i, 0) != v) {
                return false;
            }
        }
        return true;
    }

    /**
     * https://leetcode.cn/problems/evaluate-the-bracket-pairs-of-a-string/
     * 2023.1.13
     *
     * @param s         字符串
     * @param knowledge 二维字符串数组
     * @return 替换 所有 括号对后的结果字符串
     */
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> dict = new HashMap<>();
        for (List<String> kd : knowledge) {
            dict.put(kd.get(0), kd.get(1));
        }
        boolean addKey = false;
        StringBuilder key = new StringBuilder();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                addKey = true;
            } else if (c == ')') {
                if (dict.containsKey(key.toString())) {
                    res.append(dict.get(key.toString()));
                } else {
                    res.append('?');
                }
                addKey = false;
                key.setLength(0);
            } else {
                if (addKey) {
                    key.append(c);
                } else {
                    res.append(c);
                }
            }
        }
        return res.toString();
    }


    /**
     * https://leetcode.cn/problems/rearrange-characters-to-make-target-string/
     * 2023.1.13
     *
     * @param s      字符串
     * @param target 字符串
     * @return 可以形成 target 的 最大 副本数
     */
    public int rearrangeCharacters(String s, String target) {
        Map<Character, Integer> sCounts = new HashMap<>();
        Map<Character, Integer> targetCounts = new HashMap<>();
        int n = s.length(), m = target.length();
        for (int i = 0; i < m; i++) {
            char c = target.charAt(i);
            targetCounts.put(c, targetCounts.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (targetCounts.containsKey(c)) {
                sCounts.put(c, sCounts.getOrDefault(c, 0) + 1);
            }
        }
        int ans = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> entry : targetCounts.entrySet()) {
            char c = entry.getKey();
            int count = entry.getValue();
            int totalCount = sCounts.containsKey(c) ? sCounts.get(c) : 0;
            ans = Math.min(ans, totalCount / count);
            if (ans == 0) {
                return 0;
            }
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/number-of-different-subsequences-gcds/
     * 2023.1.14
     *
     * @param nums 正整数组成的数组
     * @return 返回 nums 的所有 非空 子序列中 不同 最大公约数的 数目
     */
    public int countDifferentSubsequenceGCDs(int[] nums) {
        int maxVal = Arrays.stream(nums).max().getAsInt();
        boolean[] occured = new boolean[maxVal + 1];
        for (int num : nums) {
            occured[num] = true;
        }
        int ans = 0;
        for (int i = 1; i <= maxVal; i++) {
            int subGcd = 0;
            for (int j = i; j <= maxVal; j += i) {
                if (occured[j]) {
                    if (subGcd == 0) {
                        subGcd = j;
                    } else {
                        subGcd = gcd(subGcd, j);
                    }
                    if (subGcd == i) {
                        ans++;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    public int gcd(int num1, int num2) {
        while (num2 != 0) {
            int temp = num1;
            num1 = num2;
            num2 = temp % num2;
        }
        return num1;
    }

    /**
     * https://leetcode.cn/problems/min-max-game/
     * 2023.1.15
     *
     * @param nums 整数数组
     * @return nums 中剩下的那个数字
     */
    public int minMaxGame(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] newNums = new int[n / 2];
        for (int i = 0; i < newNums.length; i++) {
            if (i % 2 == 0) {
                newNums[i] = Math.min(nums[2 * i], nums[2 * i + 1]);
            } else {
                newNums[i] = Math.max(nums[2 * i], nums[2 * i + 1]);
            }
        }
        return minMaxGame(newNums);
    }

    /**
     * https://leetcode.cn/problems/sentence-similarity-iii/
     * 2023.1.16
     *
     * @param sentence1 包含大写和小写英文字母
     * @param sentence2 包含大写和小写英文字母
     * @return 如果 sentence1 和 sentence2 是相似的，请你返回 true ，否则返回 false
     */
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] words1 = sentence1.split(" ");
        String[] words2 = sentence2.split(" ");
        int i = 0, j = 0;
        while (i < words1.length && i < words2.length && words1[i].equals(words2[i])) {
            i++;
        }
        while (j < words1.length - i && j < words2.length - i && words1[words1.length - j - 1].equals(words2[words2.length - j - 1])) {
            j++;
        }
        return i + j == Math.min(words1.length, words2.length);
    }

    /**
     * https://leetcode.cn/problems/count-nice-pairs-in-an-array/
     * 2023.2.14
     *
     * @param nums 数组
     * @return 返回好下标对的数目
     */
    public int countNicePairs(int[] nums) {
        final int MOD = 1000000007;
        int res = 0;
        Map<Integer, Integer> h = new HashMap<>();
        for (int i : nums) {
            int temp = i, j = 0;
            while (temp > 0) {
                j = j * 10 + temp % 10;
                temp /= 10;
            }
            res = (res + h.getOrDefault(i - j, 0)) % MOD;
            h.put(i - j, h.getOrDefault(i - j, 0) + 1);
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/strong-password-checker-ii/
     * 2023.1.19
     *
     * @param password 字符串
     * @return 如果它是一个 强 密码，返回 true，否则返回 false
     */
    public boolean strongPasswordCheckerII(String password) {
        if (password.length() < 8) {
            return false;
        }

        Set<Character> specials = new HashSet<Character>() {{
            add('!');
            add('@');
            add('#');
            add('$');
            add('%');
            add('^');
            add('&');
            add('*');
            add('(');
            add(')');
            add('-');
            add('+');
        }};
        int n = password.length();
        boolean hasLower = false, hasUpper = false, hasDigit = false, hasSpecial = false;
        for (int i = 0; i < n; ++i) {
            if (i != n - 1 && password.charAt(i) == password.charAt(i + 1)) {
                return false;
            }

            char ch = password.charAt(i);
            if (Character.isLowerCase(ch)) {
                hasLower = true;
            } else if (Character.isUpperCase(ch)) {
                hasUpper = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (specials.contains(ch)) {
                hasSpecial = true;
            }
        }

        return hasLower && hasUpper && hasDigit && hasSpecial;
    }

    /**
     * https://leetcode.cn/problems/finding-the-users-active-minutes/
     * 2023.1.20
     *
     * @param logs 二维整数数组
     * @param k    整数
     * @return 上面描述的答案数组 answer
     */
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        Map<Integer, Set<Integer>> activeMinutes = new HashMap<>();
        for (int[] log : logs) {
            int id = log[0], time = log[1];
            activeMinutes.putIfAbsent(id, new HashSet<>());
            activeMinutes.get(id).add(time);
        }
        int[] answer = new int[k];
        for (Set<Integer> minutes : activeMinutes.values()) {
            int activeCount = minutes.size();
            answer[activeCount - 1]++;
        }
        return answer;
    }
}

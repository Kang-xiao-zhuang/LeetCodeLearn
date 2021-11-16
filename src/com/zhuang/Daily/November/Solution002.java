package com.zhuang.Daily.November;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname Solution002
 * @Description 2021.11.8-2021.11.15每日一题
 * @Date 2021/11/8 20:26
 * @Author by dell
 */

public class Solution002 {
    public static void main(String[] args) {
        //String secret = "1807";
        //String guess = "7810";
        //getHint(secret, guess);

        //int[] timeSeries = {1, 4};
        //findPoisonedDuration(timeSeries, 2);

        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        mapSum.sum("ap");
        mapSum.insert("app", 2);
        mapSum.sum("ap");
    }

    /**
     * https://leetcode-cn.com/problems/bulls-and-cows/
     * 11.8
     *
     * @param secret 秘密数字
     * @param guess  猜测数字
     * @return 猜测的提示
     */
    public static String getHint(String secret, String guess) {
        int a = 0, b = 0;
        int[] arr = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                a++;
            } else {
                // 小于0说明之前guess中国出现过相同的字符
                if (arr[secret.charAt(i) - '0']++ < 0) {
                    b++;
                }
                // 大于0说之前secret中出现过相同的字符
                if (arr[guess.charAt(i) - '0']-- > 0) {
                    b++;
                }
            }
        }
        System.out.println(new StringBuilder().append(a).append('A').append(b).append('B').toString());
        return new StringBuilder().append(a).append('A').append(b).append('B').toString();
    }

    int INF = 0x3f3f3f3f;
    String b;
    int m;
    Map<String, Integer> map = new HashMap<>();

    /**
     * https://leetcode-cn.com/problems/zuma-game/
     * 11.9
     *
     * @param board 字符串
     * @param hand  字符串
     * @return 所需的 最少 球数
     */
    public int findMinStep(String board, String hand) {
        b = hand;
        m = b.length();
        int ans = dfs(board, 1 << m);
        return ans == INF ? -1 : ans;
    }

    public int dfs(String a, int cur) {
        if (a.length() == 0) {
            return 0;
        }
        if (map.containsKey(a)) {
            return map.get(a);
        }
        int ans = INF;
        int n = a.length();
        for (int i = 0; i < m; i++) {
            if (((cur >> i) & 1) == 1) {
                continue;
            }
            int next = (1 << i) | cur;
            for (int j = 0; j <= n; j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(a.substring(0, j)).append(b.substring(i, i + 1));
                if (j != n) {
                    sb.append(a.substring(j));
                }
                int k = j;
                while (0 <= k && k < sb.length()) {
                    char c = sb.charAt(k);
                    int l = k, r = k;
                    while (l >= 0 && sb.charAt(l) == c) {
                        l--;
                    }
                    while (r < sb.length() && sb.charAt(r) == c) {
                        r++;
                    }
                    if (r - l - 1 >= 3) {
                        sb.delete(l + 1, r);
                        k = l >= 0 ? l : r;
                    } else {
                        break;
                    }
                }
                ans = Math.min(ans, dfs(sb.toString(), next) + 1);
            }
        }
        map.put(a, ans);
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/teemo-attacking/
     * 11.10
     *
     * @param timeSeries 整数数组
     * @param duration   中毒持续时间的整数
     * @return 处于中毒状态的总秒数
     */
    public static int findPoisonedDuration(int[] timeSeries, int duration) {
        int ans = 0;
        // 未中毒的起始时间
        int expired = 0;
        for (int i = 0; i < timeSeries.length; i++) {
            if (timeSeries[i] >= expired) {
                ans += duration;
            } else {
                ans += timeSeries[i] + duration - expired;
            }
            expired = timeSeries[i] + duration;
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/k-inverse-pairs-array/
     * 11.11
     *
     * @param n 整数
     * @param k 整数
     * @return 返回逆序对的个数
     */
    public int kInversePairs(int n, int k) {
        final int MOD = 1000000007;
        int[][] f = new int[2][k + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                int cur = i & 1, prev = cur ^ 1;
                f[cur][j] = (j - 1 >= 0 ? f[cur][j - 1] : 0) - (j - i >= 0 ? f[prev][j - i] : 0) + f[prev][j];
                if (f[cur][j] >= MOD) {
                    f[cur][j] -= MOD;
                } else if (f[cur][j] < 0) {
                    f[cur][j] += MOD;
                }
            }
        }
        return f[n & 1][k];
    }

    /**
     * https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/
     * 11.12
     *
     * @param n 数字
     * @return 确保获胜的最小现金数
     */
    public int getMoneyAmount(int n) {
        int[][] f = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                int minCost = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = k + Math.max(f[i][k - 1], f[k + 1][j]);
                    minCost = Math.min(minCost, cost);
                }
                f[i][j] = minCost;
            }
        }
        return f[1][n];
    }

    /**
     * https://leetcode-cn.com/problems/detect-capital/
     * 11.13
     *
     * @param word 字母
     * @return 单词大写用法是否正确
     */
    public boolean detectCapitalUse(String word) {
        // 若第1个字母为小写，则需额外判断第2个字母是否为小写
        if (word.length() >= 2 && Character.isLowerCase(word.charAt(0)) && Character.isUpperCase(word.charAt(1))) {
            return false;
        }
        // 无论第1个字母是否大写，其他字母必须与第2个字母的大小写相同
        for (int i = 2; i < word.length(); i++) {
            if (Character.isLowerCase(word.charAt(i)) ^ Character.isLowerCase(word.charAt(1))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 正则表达式
     *
     * @param word 字母
     * @return 单词大写用法是否正确
     */
    public boolean detectCapitalUse2(String word) {
        return word.matches("[A-Z][a-z]*|[A-Z]*|[a-z]*");
    }


    // 11.14 笔记里有

    /**
     * https://leetcode-cn.com/problems/map-sum-pairs/
     */
    static class MapSum {
        private Map<String, Integer> map;
        private Map<String, Integer> prefixmap;

        public MapSum() {
            map = new HashMap<String, Integer>();
            prefixmap = new HashMap<String, Integer>();
        }

        public void insert(String key, int val) {
            int diff = val - map.getOrDefault(key, 0);
            map.put(key, val);
            for (int i = 1; i <= key.length(); i++) {
                String currprefix = key.substring(0, i);
                prefixmap.put(currprefix, prefixmap.getOrDefault(currprefix, 0) + diff);
            }
        }

        public int sum(String prefix) {
            return prefixmap.getOrDefault(prefix, 0);
        }
    }

    /**
     * https://leetcode-cn.com/problems/bulb-switcher/
     * 11.16
     *
     * @param n n 个灯泡
     * @return n 轮后有多少个亮着的灯泡
     */
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}

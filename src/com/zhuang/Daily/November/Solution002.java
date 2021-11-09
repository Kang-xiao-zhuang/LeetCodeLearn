package com.zhuang.Daily.November;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname Solution002
 * @Description 2021.11.8-2021.11.15每日一题
 * @Date 2021/11/1 20:26
 * @Author by dell
 */

public class Solution002 {
    public static void main(String[] args) {
        String secret = "1807";
        String guess = "7810";
        getHint(secret, guess);
    }


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
}

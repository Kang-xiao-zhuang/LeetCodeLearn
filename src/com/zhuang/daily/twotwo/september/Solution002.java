package com.zhuang.daily.twotwo.september;

import javafx.util.Pair;

import java.util.*;

/**
 * description: Solution002
 * date: 2022/9/16 7:55
 * author: Zhuang
 * version: 1.0
 */
public class Solution002 {

    public static void main(String[] args) {
        Solution002 solution002 = new Solution002();
//        solution002.maxLengthBetweenEqualCharacters("abca");
        int[] code = {2, 4, 9, 3};
        solution002.decrypt(code, -2);
        solution002.rotatedDigits(10);
    }


    /**
     * https://leetcode.cn/problems/rectangle-area-ii/
     * 9.16
     *
     * @param rectangles 二维矩形列表
     * @return 总面积
     */
    public int rectangleArea(int[][] rectangles) {
        final int MOD = 1000000007;
        int n = rectangles.length;
        Set<Integer> set = new HashSet<>();
        for (int[] rect : rectangles) {
            // 下边界
            set.add(rect[1]);
            // 上边界
            set.add(rect[3]);
        }
        List<Integer> hbound = new ArrayList<>(set);
        Collections.sort(hbound);
        int m = hbound.size();
        // 「思路与算法部分」的 length 数组并不需要显式地存储下来
        // length[i] 可以通过 hbound[i+1] - hbound[i] 得到
        int[] seg = new int[m - 1];

        List<int[]> sweep = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            // 左边界
            sweep.add(new int[]{rectangles[i][0], i, 1});
            // 右边界
            sweep.add(new int[]{rectangles[i][2], i, -1});
        }
        Collections.sort(sweep, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else if (a[1] != b[1]) {
                return a[1] - b[1];
            } else {
                return a[2] - b[2];
            }
        });

        long ans = 0;
        for (int i = 0; i < sweep.size(); ++i) {
            int j = i;
            while (j + 1 < sweep.size() && sweep.get(i)[0] == sweep.get(j + 1)[0]) {
                ++j;
            }
            if (j + 1 == sweep.size()) {
                break;
            }
            // 一次性地处理掉一批横坐标相同的左右边界
            for (int k = i; k <= j; ++k) {
                int[] arr = sweep.get(k);
                int idx = arr[1], diff = arr[2];
                int left = rectangles[idx][1], right = rectangles[idx][3];
                for (int x = 0; x < m - 1; ++x) {
                    if (left <= hbound.get(x) && hbound.get(x + 1) <= right) {
                        seg[x] += diff;
                    }
                }
            }
            int cover = 0;
            for (int k = 0; k < m - 1; ++k) {
                if (seg[k] > 0) {
                    cover += (hbound.get(k + 1) - hbound.get(k));
                }
            }
            ans += (long) cover * (sweep.get(j + 1)[0] - sweep.get(j)[0]);
            i = j;
        }
        return (int) (ans % MOD);
    }

    /**
     * https://leetcode.cn/problems/largest-substring-between-two-equal-characters/
     * 9.17
     *
     * @param s 字符串
     * @return 两个相同字符之间的最长子字符串的长度
     */
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] firestIndex = new int[26];
        Arrays.fill(firestIndex, -1);
        int maxLen = -1;
        for (int i = 0; i < s.length(); i++) {
            if (firestIndex[s.charAt(i) - 'a'] < 0) {
                firestIndex[s.charAt(i) - 'a'] = i;
            } else {
                maxLen = Math.max(maxLen, i - firestIndex[s.charAt(i) - 'a'] - 1);
            }
        }
        return maxLen;
    }

    static int[] d = {0, -1, 0, 1, 0};

    /**
     * @param grid 二进制矩阵
     * @return 最大的岛屿面积
     */
    public int largestIsland(int[][] grid) {
        int n = grid.length, res = 0;
        int[][] tag = new int[n][n];
        Map<Integer, Integer> area = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && tag[i][j] == 0) {
                    int t = i * n + j + 1;
                    area.put(t, dfs(grid, i, j, tag, t));
                    res = Math.max(res, area.get(t));
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int z = 1;
                    Set<Integer> connected = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int x = i + d[k], y = j + d[k + 1];
                        if (!valid(n, x, y) || tag[x][y] == 0 || connected.contains(tag[x][y])) {
                            continue;
                        }
                        z += area.get(tag[x][y]);
                        connected.add(tag[x][y]);
                    }
                    res = Math.max(res, z);
                }
            }
        }
        return res;
    }

    public int dfs(int[][] grid, int x, int y, int[][] tag, int t) {
        int n = grid.length, res = 1;
        tag[x][y] = t;
        for (int i = 0; i < 4; i++) {
            int x1 = x + d[i], y1 = y + d[i + 1];
            if (valid(n, x1, y1) && grid[x1][y1] == 1 && tag[x1][y1] == 0) {
                res += dfs(grid, x1, y1, tag, t);
            }
        }
        return res;
    }

    public boolean valid(int n, int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }


    /**
     * https://leetcode.cn/problems/sort-array-by-increasing-frequency/
     * 9.19
     *
     * @param nums 整数数组
     * @return 返回排序后的数组
     */
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        list.sort((a, b) -> {
            int cnt1 = cnt.get(a), cnt2 = cnt.get(b);
            return cnt1 != cnt2 ? cnt1 - cnt2 : b - a;
        });
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            nums[i] = list.get(i);
        }
        return nums;
    }

    /**
     * https://leetcode.cn/problems/partition-to-k-equal-sum-subsets/
     * 9.20
     *
     * @param nums 数组
     * @param k    正整数
     * @return 是否有可能把这个数组分成 k 个非空子集，其总和都相等
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length;
        if (n < k) return false;
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) return false;
        Arrays.sort(nums);
        int[] dp = new int[1 << n], curSum = new int[1 << n];
        dp[0] = 1;
        for (int i = 0; i < 1 << n; i++) {
            if (dp[i] == 0) continue;
            for (int j = 0; j < n; j++) {
                if (curSum[i] + nums[j] > sum / k) break;
                if (((i >> j) & 1) == 0 && dp[i | (1 << j)] == 0) {
                    curSum[i | (1 << j)] = (curSum[i] + nums[j]) % (sum / k);
                    dp[i | (1 << j)] = 1;
                }
            }
        }
        return dp[(1 << n) - 1] == 1;
    }

    /**
     * https://leetcode.cn/problems/k-similar-strings/
     * 9.21
     *
     * @param s1 非负整数
     * @param s2 字母异位词
     * @return 返回 s1 和 s2 的相似度 k 的最小值
     */
    public int kSimilarity(String s1, String s2) {
        int n = s1.length();
        Queue<Pair<String, Integer>> queue = new ArrayDeque<>();
        Set<String> visit = new HashSet<>();
        queue.offer(new Pair<>(s1, 0));
        visit.add(s1);
        int step = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                Pair<String, Integer> pair = queue.poll();
                String cur = pair.getKey();
                int pos = pair.getValue();
                if (cur.equals(s2)) {
                    return step;
                }
                while (pos < n && cur.charAt(pos) == s2.charAt(pos)) {
                    pos++;
                }
                for (int j = pos + 1; j < n; j++) {
                    if (s2.charAt(j) == cur.charAt(j)) {
                        continue;
                    }
                    if (s2.charAt(pos) == cur.charAt(j)) {
                        String next = swap(cur, pos, j);
                        if (!visit.contains(next)) {
                            visit.add(next);
                            queue.offer(new Pair<>(next, pos + 1));
                        }
                    }
                }
            }
            step++;
        }
        return step;
    }

    public String swap(String cur, int i, int j) {
        char[] arr = cur.toCharArray();
        char c = arr[i];
        arr[i] = arr[j];
        arr[j] = c;
        return new String(arr);
    }

    /**
     * https://leetcode.cn/problems/check-array-formation-through-concatenation/
     * 9.22
     *
     * @param arr    整数数组
     * @param pieces 整数数组
     * @return 布尔
     */
    public boolean canFormArray(int[] arr, int[][] pieces) {
        int[] map = new int[101];
        for (int i = 0; i < pieces.length; i++) {
            map[pieces[i][0]] = i;
        }
        int p = 0;
        while (p < arr.length) {
            int num = arr[p];
            int[] piece = pieces[map[num]];
            int j = 0;
            while (j < piece.length) {
                if (piece[j] != arr[p]) {
                    return false;
                }
                j++;
                p++;
            }
        }
        return true;
    }


    /**
     * https://leetcode.cn/problems/defuse-the-bomb/
     *
     * @param code 数组
     * @param k    密钥
     * @return 解密后的结果
     */
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] ans = new int[n];
        if (k == 0) {
            return ans;
        }
        if (k > 0) {
            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= k; j++) {
                    int index = (i + j) % n;
                    ans[i] += code[index];
                }
            }
        }
        if (k < 0) {
            for (int i = 0; i < n; i++) {
                for (int j = -1; j >= k; j--) {
                    int index = (i + j + n) % n;
                    ans[i] += code[index];
                }
            }
        }
        return ans;
    }

    static int[] check = {0, 0, 1, -1, -1, 1, 1, -1, 0, 1};

    /**
     * https://leetcode.cn/problems/rotated-digits/
     * 数组中没有出现 3 4 7
     * 数组至少出现一次 2 5 6 9
     * 对于0 1 8 没有要求
     *
     * @param n 整数
     * @return 从 1 到 N 中有多少个数 X 是好数
     */
    public int rotatedDigits(int n) {
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            String s = String.valueOf(i);
            boolean valid = true;
            boolean diff = false;
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (check[c - '0'] == -1) {
                    valid = false;
                } else if (check[c - '0'] == 1) {
                    diff = true;
                }
            }
            if (valid && diff) {
                ++ans;
            }
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/missing-two-lcci/
     * 9.26
     *
     * @param nums 数字
     * @return 返回消失的数字
     */
    public int[] missingTwo(int[] nums) {
        int xorsum = 0;
        int n = nums.length + 2;
        for (int num : nums) {
            xorsum ^= num;
        }
        for (int i = 1; i <= n; i++) {
            xorsum ^= i;
        }
        // 防止溢出
        int lsb = (xorsum == Integer.MIN_VALUE ? xorsum : xorsum & (-xorsum));
        int type1 = 0;
        int type2 = 0;
        for (int num : nums) {
            if ((num & lsb) != 0) {
                type1 ^= num;
            } else {
                type2 ^= num;
            }
        }
        for (int i = 1; i <= n; i++) {
            if ((i & lsb) != 0) {
                type1 ^= i;
            } else {
                type2 ^= i;
            }
        }
        return new int[]{type1, type2};
    }

    /**
     * https://leetcode.cn/problems/check-permutation-lcci/
     * 9.27
     *
     * @param s1 字符串
     * @param s2 字符串
     * @return 布尔
     */
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();
        Arrays.sort(char1);
        Arrays.sort(char2);
        return Arrays.equals(char1, char2);
    }

    public boolean CheckPermutation2(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] cnts = new int[256];
        int total = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (++cnts[s1.charAt(i)] == 1) {
                total++;
            }
            if (--cnts[s2.charAt(i)] == 0) {
                total--;
            }
        }
        return total == 0;
    }

    /**
     * https://leetcode.cn/problems/get-kth-magic-number-lcci/
     * 9.28
     *
     * @param k 整数
     * @return 第 k 个数
     */
    public int getKthMagicNumber(int k) {
        int[] nums = {3, 5, 7};
        // 是否访问 相同算一次
        HashSet<Long> visited = new HashSet<>();
        PriorityQueue<Long> queue = new PriorityQueue<>();
        // 初始值为1
        visited.add(1L);
        queue.offer(1L);
        // 第k个元素
        int target = 0;
        for (int i = 0; i < k; i++) {
            // 弹出堆顶元素
            long poll = queue.poll();
            target = (int) poll;
            for (int num : nums) {
                long cur = poll * num;
                if (!visited.contains(cur)) {
                    visited.add(cur);
                    queue.offer(cur);
                }
            }
        }
        return target;
    }

    /**
     * https://leetcode.cn/problems/string-rotation-lcci/
     *
     * @param s1 字符串
     * @param s2 字符串
     * @return 布尔
     */
    public boolean isFlipedString(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m != n) {
            return false;
        }
        if (n == 0) {
            return true;
        }
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (s1.charAt((i + j) % n) != s2.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

    public boolean isFlipedString2(String s1, String s2) {
        return s1.length() == s2.length() && (s1 + s1).contains(s2);
    }

    /**
     * https://leetcode.cn/problems/zero-matrix-lcci/
     * 9.30
     *
     * @param matrix 二维数组
     */
    public void setZeroes(int[][] matrix) {
        //用2个标记数组 记录每一行和每一列是否有0出现 出现就标记数组的位置为true
        int m = matrix.length, n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        // 最后更新原数组
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}

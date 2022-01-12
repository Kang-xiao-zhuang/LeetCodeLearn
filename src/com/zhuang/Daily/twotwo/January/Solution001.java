package com.zhuang.Daily.twotwo.January;

import java.util.*;

/**
 * @Classname Solution001
 * @Description 2022.1.1-2022.1.11 每日一题
 * @Date 2022/1/1 6:54
 * @Author by dell
 */

public class Solution001 {
    public static void main(String[] args) {
        Solution001 solution001 = new Solution001();
        int[] original = {1, 2, 3, 4};
        solution001.construct2DArray(original, 2, 2);
    }

    /**
     * https://leetcode-cn.com/problems/convert-1d-array-into-2d-array/
     * 1.1
     *
     * @param original 一维整数数组
     * @param m        整数
     * @param n        整数
     * @return 二维数组
     */
    public int[][] construct2DArray(int[] original, int m, int n) {
        if (original.length != n * m) {
            return new int[][]{};
        }
        int[][] ans = new int[m][n];
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = original[index++];
            }
        }
        System.out.println(Arrays.deepToString(ans));
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/elimination-game/
     *
     * @param n 整数
     * @return 最后剩下的数字
     */
    public int lastRemaining(int n) {
        return n == 1 ? 1 : 2 * (n / 2 + 1 - lastRemaining(n / 2));
    }

    /**
     * https://leetcode-cn.com/problems/day-of-the-week/
     *
     * @param day   整数
     * @param month 整数
     * @param year  整数
     * @return 对应一周中的哪一天
     */
    public String dayOfTheWeek(int day, int month, int year) {
        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        /* 输入年份之前的年份的天数贡献 */
        int days = 365 * (year - 1971) + (year - 1969) / 4;
        /* 输入年份中，输入月份之前的月份的天数贡献 */
        for (int i = 0; i < month - 1; ++i) {
            days += monthDays[i];
        }
        if ((year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) && month >= 3) {
            days += 1;
        }
        /* 输入月份中的天数贡献 */
        days += day;
        return week[(days + 3) % 7];
    }

    /**
     * https://leetcode-cn.com/problems/cat-and-mouse/
     * 1.4
     *
     * @param graph 图
     * @return 获胜的代号
     */
    static int N = 55;
    static int[][][] f = new int[2 * N * N][N][N];
    int[][] g;
    int n;

    public int catMouseGame(int[][] graph) {
        g = graph;
        n = g.length;
        for (int k = 0; k < 2 * n * n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    f[k][i][j] = -1;
                }
            }
        }
        return dfs(0, 1, 2);
    }

    // 0:draw / 1:mouse / 2:cat
    int dfs(int k, int a, int b) {
        int ans = f[k][a][b];
        if (a == 0) ans = 1;
        else if (a == b) ans = 2;
        else if (k >= 2 * n * n) ans = 0;
        else if (ans == -1) {
            if (k % 2 == 0) { // mouse
                boolean win = false, draw = false;
                for (int ne : g[a]) {
                    int t = dfs(k + 1, ne, b);
                    if (t == 1) win = true;
                    else if (t == 0) draw = true;
                    if (win) break;
                }
                if (win) ans = 1;
                else if (draw) ans = 0;
                else ans = 2;
            } else { // cat
                boolean win = false, draw = false;
                for (int ne : g[b]) {
                    if (ne == 0) continue;
                    int t = dfs(k + 1, a, ne);
                    if (t == 2) win = true;
                    else if (t == 0) draw = true;
                    if (win) break;
                }
                if (win) ans = 2;
                else if (draw) ans = 0;
                else ans = 1;
            }
        }
        f[k][a][b] = ans;
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/
     * 1.5每日一题
     *
     * @param s 英文字母
     * @return 最终的字符串不包含任何 连续重复 的字符
     */
    public String modifyString(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (arr[i] == '?') {
                for (char ch = 'a'; ch <= 'c'; ch++) {
                    if ((i > 0 && arr[i - 1] == ch) || (i < n - 1 && arr[i + 1] == ch)) {
                        continue;
                    }
                    arr[i] = ch;
                    break;
                }
            }
        }
        return new String(arr);
    }

    /**
     * https://leetcode-cn.com/problems/simplify-path/
     * 1.6
     *
     * @param path 字符串
     * @return 规范路径
     */
    public String simplifyPath(String path) {
        String[] names = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
        for (String name : names) {
            if ("..".equals(name)) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (name.length() > 0 && !".".equals(name)) {
                stack.offerLast(name);
            }
        }
        StringBuilder ans = new StringBuilder();
        if (stack.isEmpty()) {
            ans.append('/');
        } else {
            while (!stack.isEmpty()) {
                ans.append('/');
                ans.append(stack.pollFirst());
            }
        }
        return ans.toString();
    }

    /**
     * https://leetcode-cn.com/problems/maximum-nesting-depth-of-the-parentheses/
     * 1.7
     *
     * @param s 字符串
     * @return 嵌套深度
     */
    public int maxDepth(String s) {
        int ans = 0, size = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                size++;
                ans = Math.max(ans, size);
            } else if (c == ')') {
                --size;
            }
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/gray-code/
     * 1.8
     *
     * @param n 格雷码
     * @return n位格雷码序列
     */
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        while (n-- > 0) {
            int m = ans.size();
            for (int i = m - 1; i >= 0; i--) {
                ans.set(i, ans.get(i) << 1);
                ans.add(ans.get(i) + 1);
            }
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/slowest-key/
     * 1.9
     *
     * @param releaseTimes 升序排列的列表
     * @param keysPressed  字符串
     * @return 按字母顺序排列最大
     */
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        int n = releaseTimes.length;
        char ans = keysPressed.charAt(0);
        int maxTime = releaseTimes[0];
        for (int i = 1; i < n; i++) {
            char key = keysPressed.charAt(i);
            int time = releaseTimes[i] - releaseTimes[i - 1];
            if (time > maxTime || (time == maxTime && key > ans)) {
                ans = key;
                maxTime = time;
            }
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/additive-number/
     * 1.10
     *
     * @param num 字符串
     * @return 布尔
     */
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int secondStart = 1; secondStart < n - 1; ++secondStart) {
            if (num.charAt(0) == '0' && secondStart != 1) {
                break;
            }
            for (int secondEnd = secondStart; secondEnd < n - 1; ++secondEnd) {
                if (num.charAt(secondStart) == '0' && secondStart != secondEnd) {
                    break;
                }
                if (valid(secondStart, secondEnd, num)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param secondStart 第二个开头
     * @param secondEnd   第二个末尾
     * @param num         字符串
     * @return 布尔
     */
    public boolean valid(int secondStart, int secondEnd, String num) {
        int n = num.length();
        int firstStart = 0, firstEnd = secondStart - 1;
        while (secondEnd <= n - 1) {
            String third = stringAdd(num, firstStart, firstEnd, secondStart, secondEnd);
            int thirdStart = secondEnd + 1;
            int thirdEnd = secondEnd + third.length();
            if (thirdEnd >= n || !num.substring(thirdStart, thirdEnd + 1).equals(third)) {
                break;
            }
            if (thirdEnd == n - 1) {
                return true;
            }
            firstStart = secondStart;
            firstEnd = secondEnd;
            secondStart = thirdStart;
            secondEnd = thirdEnd;
        }
        return false;
    }

    /**
     * @param s           字符串
     * @param firstStart  第一个开头
     * @param firstEnd    第一个末尾
     * @param secondStart 第二个开头
     * @param secondEnd   第二个末尾
     * @return 字符串
     */
    public String stringAdd(String s, int firstStart, int firstEnd, int secondStart, int secondEnd) {
        StringBuilder third = new StringBuilder();
        int carry = 0, cur = 0;
        while (firstEnd >= firstStart || secondEnd >= secondStart || carry != 0) {
            cur = carry;
            if (firstEnd >= firstStart) {
                cur += s.charAt(firstEnd) - '0';
                --firstEnd;
            }
            if (secondEnd >= secondStart) {
                cur += s.charAt(secondEnd) - '0';
                --secondEnd;
            }
            carry = cur / 10;
            cur %= 10;
            third.append((char) (cur + '0'));
        }
        third.reverse();
        return third.toString();
    }

    // 在包围圈中
    static final int BLOCKED = -1;
    // 不在包围圈中
    static final int VALID = 0;
    // 无论在不在包围圈中，但在 n(n-1)/2 步搜索的过程中经过了 target
    static final int FOUND = 1;

    static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static final int BOUNDARY = 1000000;

    /**
     * https://leetcode-cn.com/problems/escape-a-large-maze/
     * 1.11
     * @param blocked 封锁列
     * @param source  源方格
     * @param target  目标方格
     * @return 布尔
     */
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        if (blocked.length < 2) {
            return true;
        }

        Set<Pair> hashBlocked = new HashSet<Pair>();
        for (int[] pos : blocked) {
            hashBlocked.add(new Pair(pos[0], pos[1]));
        }

        int result = check(blocked, source, target, hashBlocked);
        if (result == FOUND) {
            return true;
        } else if (result == BLOCKED) {
            return false;
        } else {
            result = check(blocked, target, source, hashBlocked);
            return result != BLOCKED;
        }
    }

    public int check(int[][] blocked, int[] start, int[] finish, Set<Pair> hashBlocked) {
        int sx = start[0], sy = start[1];
        int fx = finish[0], fy = finish[1];
        int countdown = blocked.length * (blocked.length - 1) / 2;
        Pair startPair = new Pair(sx, sy);
        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(startPair);
        Set<Pair> visited = new HashSet<>();
        visited.add(startPair);
        while (!queue.isEmpty() && countdown > 0) {
            Pair pair = queue.poll();
            int x = pair.x, y = pair.y;
            for (int d = 0; d < 4; ++d) {
                int nx = x + dirs[d][0], ny = y + dirs[d][1];
                Pair newPair = new Pair(nx, ny);
                if (nx >= 0 && nx < BOUNDARY && ny >= 0 && ny < BOUNDARY && !hashBlocked.contains(newPair) && !visited.contains(newPair)) {
                    if (nx == fx && ny == fy) {
                        return FOUND;
                    }
                    --countdown;
                    queue.offer(newPair);
                    visited.add(newPair);
                }
            }
        }
        if (countdown > 0) {
            return BLOCKED;
        }
        return VALID;
    }
}

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return (int) ((long) x << 20 | y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pair) {
            Pair pair2 = (Pair) obj;
            return x == pair2.x && y == pair2.y;
        }
        return false;
    }
}

package com.zhuang.daily.twozero;

import java.util.*;

/**
 * description: Solution001
 * date: 2022/10/9 9:26
 * author: Zhuang
 * version: 1.0
 */
public class Solution001 {

    public static void main(String[] args) {
        Solution001 solution001 = new Solution001();
        //int[][] boards = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        //solution001.gameOfLife(boards);
        //solution001.gameOfLife2(boards);
        //solution001.movingCount(16, 16, 3);
        //solution001.generateParenthesis(3);
        //solution001.generateParenthesis2(3);

        //solution001.reverseWords("a good   example");
        //solution001.reverseWords2("a good   example");
        //solution001.reverseWords3("a good   example");
    }

    /**
     * https://leetcode.cn/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/
     * 2020.4.1
     *
     * @param seq 有效括号字符串
     * @return 将其分成两个不相交的有效括号字符串，A 和 B，并使这两个字符串的深度最小
     */
    public int[] maxDepthAfterSplit(String seq) {
        // 定义深度
        int depth = 0;
        int len = seq.length();
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            if (seq.charAt(i) == '(') {
                depth++;
                ans[i] = depth % 2;
            } else {
                ans[i] = depth % 2;
                depth--;
            }
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/game-of-life/
     * 2020.4.2
     *
     * @param board 包含 m × n 个格子的面板
     */
    public void gameOfLife(int[][] board) {
        int[] neighbors = {0, 1, -1};

        int rows = board.length;
        int cols = board[0].length;

        // 创建复制数组 copyBoard
        int[][] copyBoard = new int[rows][cols];

        // 从原数组复制一份到 copyBoard 中
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                copyBoard[row][col] = board[row][col];
            }
        }

        // 遍历面板每一个格子里的细胞
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                // 对于每一个细胞统计其八个相邻位置里的活细胞数量
                int liveNeighbors = 0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            int r = (row + neighbors[i]);
                            int c = (col + neighbors[j]);

                            // 查看相邻的细胞是否是活细胞
                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && (copyBoard[r][c] == 1)) {
                                liveNeighbors += 1;
                            }
                        }
                    }
                }

                // 规则 1 或规则 3
                if ((copyBoard[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[row][col] = 0;
                }
                // 规则 4
                if (copyBoard[row][col] == 0 && liveNeighbors == 3) {
                    board[row][col] = 1;
                }
            }
        }
    }

    public void gameOfLife2(int[][] board) {
        int[] neighbors = {0, 1, -1};

        int rows = board.length;
        int cols = board[0].length;

        // 遍历面板每一个格子里的细胞
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                // 对于每一个细胞统计其八个相邻位置里的活细胞数量
                int liveNeighbors = 0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            // 相邻位置的坐标
                            int r = (row + neighbors[i]);
                            int c = (col + neighbors[j]);

                            // 查看相邻的细胞是否是活细胞
                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && (Math.abs(board[r][c]) == 1)) {
                                liveNeighbors += 1;
                            }
                        }
                    }
                }

                // 规则 1 或规则 3
                if ((board[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    // -1 代表这个细胞过去是活的现在死了
                    board[row][col] = -1;
                }
                // 规则 4
                if (board[row][col] == 0 && liveNeighbors == 3) {
                    // 2 代表这个细胞过去是死的现在活了
                    board[row][col] = 2;
                }
            }
        }

        // 遍历 board 得到一次更新后的状态
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] > 0) {
                    board[row][col] = 1;
                } else {
                    board[row][col] = 0;
                }
            }
        }
    }

    /**
     * https://leetcode.cn/problems/string-to-integer-atoi/
     * 2020.4.3
     *
     * @param s 字符串
     * @return 整数
     */
    public int myAtoi(String s) {
        Automaton automaton = new Automaton();
        for (int i = 0; i < s.length(); i++) {
            automaton.get(s.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }


    public int myAtoi2(String str) {
        if ((str == null) || (str.length() <= 0)) {
            return 0;
        }
        //正负数的最大最小值
        int MAX = Integer.MAX_VALUE;
        //正负数的最大最小值
        int MIN = Integer.MIN_VALUE;
        int res = 0;
        int index = 0;
        //过滤空格
        while ((index < str.length()) && (str.charAt(index) == ' ')) {
            index++;
        }
        if (index == str.length()) return 0;
        //取正负号
        char firstChar = str.charAt(index);
        boolean positive = true;
        if (!isDigit(firstChar)) {
            if ((firstChar != '+') && (firstChar != '-')) {
                return 0;
            }
            index++;
            positive = firstChar != '-';
        }
        //用负数保存正负数的边界，这样不会溢出
        //正数 -2147483647
        //负数 -2147483648
        int limit = positive ? (-MAX) : MIN;
        //过滤0
        while ((index < str.length()) && (str.charAt(index) == '0')) {
            index++;
        }
        //取每一位,在非字符截止
        while ((index < str.length()) && isDigit(str.charAt(index))) {
            int digit = str.charAt(index++) - '0';
            if (res < ((limit + digit) / 10)) {
                return positive ? MAX : MIN;
            }
            //这里的res>=limit
            res = (res * 10) - digit; //用减法
        }
        return positive ? (-res) : res;
    }

    public boolean isDigit(char c) {
        return (c >= '0') && (c <= '9');
    }


    /**
     * https://leetcode.cn/problems/trapping-rain-water/
     * 2020.4.3
     *
     * @param height 高度
     * @return 接多少雨水
     */
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/edit-distance/
     * 2020.4.6
     *
     * @param word1 字符串
     * @param word2 字符串
     * @return int
     */
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

    /**
     * https://leetcode.cn/problems/rotate-matrix-lcci/
     * 2020.4.7
     *
     * @param matrix 二维数组
     */
    public void rotate(int[][] matrix) {
        //获取数组长度
        int n = matrix.length;
        //定义新的数组
        int[][] matrix_new = new int[n][n];
        //两次循环
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //翻转后的元素的规律是 假如元素是第二列第一个，翻转后会是第二行最后一个！
                /*
                1 2 3    7 4 1
                4 5 6 -> 8 5 2
                7 8 9    9 6 3

             以 2 为例
             翻转前 位置 [0][1]
             翻转后 位置 [1][2]
                */
                matrix_new[j][n - i - 1] = matrix[i][j];
            }
        }
        //遍历完成之后，再将matrix_new中的结果复制到原数组中即可
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = matrix_new[i][j];
            }
        }
    }

    /**
     * https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
     * 2020.4.8
     *
     * @param m m行
     * @param n n列
     * @param k int
     * @return 机器人能够到达多少个格子
     */
    public int movingCount(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        Queue<int[]> queue = new LinkedList<>();
        // 向右和向下的方向数组
        int[] dx = {0, 1};
        int[] dy = {1, 0};
        boolean[][] vis = new boolean[m][n];
        queue.offer(new int[]{0, 0});
        vis[0][0] = true;
        int ans = 1;
        while (!queue.isEmpty()) {
            // 上一次已访问过的位置
            int[] cell = queue.poll();
            // 横坐标
            int x = cell[0];
            // 纵坐标
            int y = cell[1];
            for (int i = 0; i < 2; ++i) {
                // 向右走
                int tx = dx[i] + x;
                // 向下走
                int ty = dy[i] + y;
                if (tx < 0 || tx >= m || ty < 0 || ty >= n || vis[tx][ty] || get(tx) + get(ty) > k) {
                    continue;
                }
                // 入队列
                queue.offer(new int[]{tx, ty});
                // 此位置设为 访问过
                vis[tx][ty] = true;
                ans++;
            }
        }
        return ans;
    }

    private int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/generate-parentheses/
     * 2020.4.9
     *
     * @param n 生成括号的对数
     * @return 用于能够生成所有可能的并且 有效的 括号组合
     */
    public List<String> generateParenthesis(int n) {
        ArrayList<String> list = new ArrayList<>();
        // 特值判定
        if (n == 0) {
            return list;
        }
        dfs("", n, n, list);
        System.out.println(list.toString());
        return list;
    }

    /**
     * @param str   递归得到的拼接字符串
     * @param left  左括号的数量
     * @param right 右括号的数量
     * @param list  结果list
     */
    private void dfs(String str, int left, int right, ArrayList<String> list) {
        if (left == 0 && right == 0) {
            list.add(str);
            return;
        }
        // 剪枝（如图，左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
        if (left > right) {
            return;
        }

        // 左括号-1
        if (left > 0) {
            dfs(str + "(", left - 1, right, list);
        }
        // 右括号-1
        if (right > 0) {
            dfs(str + ")", left, right - 1, list);
        }
    }

    public List<String> generateParenthesis2(int n) {
        ArrayList<String> list = new ArrayList<>();
        // 特值判定
        if (n == 0) {
            return list;
        }
        dfs2("", 0, 0, n, list);
        System.out.println(list.toString());
        return list;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号已经用了几个
     * @param right  右括号已经用了几个
     * @param n      左括号、右括号一共得用几个
     * @param res    结果集
     */
    private void dfs2(String curStr, int left, int right, int n, List<String> res) {
        if (left == n && right == n) {
            res.add(curStr);
            return;
        }

        // 剪枝
        if (left < right) {
            return;
        }

        if (left < n) {
            dfs2(curStr + "(", left + 1, right, n, res);
        }
        if (right < n) {
            dfs2(curStr + ")", left, right + 1, n, res);
        }
    }

    /**
     * https://leetcode.cn/problems/reverse-words-in-a-string/
     * 2020.4.10
     *
     * @param s 字符串
     * @return 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串
     */
    public String reverseWords(String s) {
        // 去除空格
        String trim = s.trim();
        String[] s1 = trim.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = s1.length - 1; i >= 0; i--) {
            if (!s1[i].equals("")) {
                sb.append(s1[i]).append(" ");
            }
        }
        System.out.println(sb.toString().trim());
        return sb.toString().trim();
    }

    public String reverseWords2(String s) {
        // 思路先用栈的方法
        Deque<String> stack = new ArrayDeque<>();
        // 去除空格
        String trim = s.trim();
        String[] s1 = trim.split(" ");

        for (String num : s1) {
            if (!num.equals("")) {
                stack.push(num);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        return sb.toString().trim();
    }

    public String reverseWords3(String s) {
        char[] charArray = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        // 清楚左右两边的空格
        while (charArray[left] == ' ') {
            left++;
        }
        while (charArray[right] == ' ') {
            right--;
        }
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            // 开始遍历拼接单词
            int index = right;
            while (index >= left && charArray[index] != ' ') {
                // 左移索引
                index--;
            }
            // 现在index已经找到第一个空格， index+1后移到出现字符串的位置
            for (int i = index + 1; i <= right; i++) {
                sb.append(charArray[i]);
            }
            // 如果不是最后一个单词,就添加空格
            if (index > left) {
                sb.append(' ');
            }
            // 跳过可能出现的空格
            while (index >= left && charArray[index] == ' ') {
                index--;
            }
            right = index;
        }
        System.out.println(sb.toString());
        return sb.toString();
    }


    /**
     * https://leetcode.cn/problems/add-two-numbers-ii/
     * 2020.4.14
     *
     * @param l1 非空链表
     * @param l2 非空链表
     * @return ListNode
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        ListNode ans = null;
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int a = stack1.isEmpty() ? 0 : stack1.pop();
            int b = stack2.isEmpty() ? 0 : stack2.pop();
            int cur = a + b + carry;
            carry = cur / 10;
            cur %= 10;
            ListNode node = new ListNode(cur);
            node.next = ans;
            ans = node;
        }
        return ans;
    }

    /**
     * https://leetcode.cn/problems/01-matrix/
     * 2020.10.15
     *
     * @param mat 矩阵
     * @return 二维矩阵
     */
    public int[][] updateMatrix(int[][] mat) {
        Queue<int[]> queue = new LinkedList<>();
        int row = mat.length;
        int col = mat[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    // 设置为访问的 -1
                    mat[i][j] = -1;
                }
            }
        }
        // 遍历x的方向
        int[] dx = {-1, 1, 0, 0};
        // 遍历y的方向
        int[] dy = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            // 出列
            int[] point = queue.poll();
            int x = point[0];
            int y = point[1];
            for (int i = 0; i < 4; i++) {
                // 邻居坐标
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < row && ny < col && nx >= 0 && ny >= 0 && mat[nx][ny] == -1) {
                    // 邻居最短距离+1
                    mat[nx][ny] = mat[x][y] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return mat;
    }

    /**
     * https://leetcode-cn.com/problems/merge-intervals/
     * 2020.4.16
     *
     * @param intervals 若干区间的集合
     * @return 一个不重叠的区间数组
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        System.out.println(Arrays.deepToString(merged.toArray(new int[merged.size()][])));
        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * https://leetcode.cn/problems/jump-game/
     * 2020.4.17
     *
     * @param nums 数组
     * @return 布尔
     */
    public boolean canJump(int[] nums) {
        int reach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reach) {
                return false;
            }
            reach = Math.max(i + nums[i], reach);
        }
        return true;
    }
}

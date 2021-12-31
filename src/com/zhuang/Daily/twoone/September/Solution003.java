package com.zhuang.Daily.twoone.September;

import java.util.*;

/**
 * @Classname Solution003
 * @Description 2021.9.15-2021.9.24每日一题
 * @Date 2021/9/15 8:45
 * @Author by Zhuang
 */
public class Solution003 {
    public static void main(String[] args) {
        //int[] nums = {1, 2, 3, 1};
        //findPeakElement(nums);

        //char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        //String[] words = {"oath", "pea", "eat", "rain"};
        //findWords(board, words);

        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        //isValidSudoku2(board);

        minSteps(3);
    }

    /**
     * https://leetcode-cn.com/problems/find-peak-element/
     * 9.15
     *
     * @param nums 整数数组
     * @return 任何一个峰值的所在位置
     */
    public static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
        return left;
    }

    /**
     * https://leetcode-cn.com/problems/word-search-ii/
     * 9.16
     *
     * @param board 二维字符网格
     * @param words 列表
     * @return 所有同时在网格和字典中出现的单词
     */
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String str = words[i];
            if (exist(board, str)) {
                ans.add(str);
            }
        }
        System.out.println(ans.toString());
        return ans;
    }

    /**
     * @param board 二维字符网格
     * @param words 列表
     * @return 布尔
     */
    public static boolean exist(char[][] board, String words) {
        int[] dics = new int[128];
        char[] wordc = words.toCharArray();
        char head = wordc[0];
        Queue<Integer[]> heads = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dics[board[i][j]]++;
                if (board[i][j] == head) {
                    heads.add(new Integer[]{i, j});
                }
            }
        }
        for (int i = 0; i < wordc.length; i++) {
            if (--dics[wordc[i]] < 0) {
                return false;
            }
        }
        while (!heads.isEmpty()) {
            Integer[] pos = heads.poll();
            boolean has = exist(pos[0], pos[1], board, wordc, 0);
            if (has) {
                return true;
            }
        }
        return false;
    }

    private static boolean exist(Integer x, Integer y, char[][] board, char[] wordc, int i) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }
        // 以 x,y为起点，在board，上有以i为起点 wordc后续的字符串吗？
        if (board[x][y] != wordc[i]) {
            return false;
        }
        if (i == wordc.length - 1) {
            return true;
        }
        char temp = board[x][y];
        board[x][y] = '!';
        if (exist(x + 1, y, board, wordc, i + 1)) {
            board[x][y] = temp;
            return true;
        }
        if (exist(x - 1, y, board, wordc, i + 1)) {
            board[x][y] = temp;
            return true;
        }
        if (exist(x, y + 1, board, wordc, i + 1)) {
            board[x][y] = temp;
            return true;
        }
        if (exist(x, y - 1, board, wordc, i + 1)) {
            board[x][y] = temp;
            return true;
        }
        board[x][y] = temp;
        return false;
    }

    /**
     * https://leetcode-cn.com/problems/valid-sudoku/
     * 9.17
     * 模拟
     *
     * @param board 二维数独数组
     * @return 布尔
     */
    public static boolean isValidSudoku(char[][] board) {
        // 定义三个二维数组
        int[][] rows = new int[9][9];
        int[][] cols = new int[9][9];
        int[][] boxes = new int[9][9];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int index_box = (i / 3) * 3 + j / 3;
                    if (rows[i][num] == 1) {
                        return false;
                    } else {
                        rows[i][num] = 1;
                    }
                    if (cols[j][num] == 1) {
                        return false;
                    } else {
                        cols[j][num] = 1;
                    }
                    if (boxes[index_box][num] == 1) {
                        return false;
                    } else {
                        boxes[index_box][num] = 1;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 哈希表
     *
     * @param board 二维数独数组
     * @return 布尔
     */
    public static boolean isValidSudoku2(char[][] board) {
        HashMap<Integer, Set<Integer>> rows = new HashMap<>();
        HashMap<Integer, Set<Integer>> cols = new HashMap<>();
        HashMap<Integer, Set<Integer>> boxes = new HashMap<>();

        for (int i = 0; i < 9; i++) {
            rows.put(i, new HashSet<>());
            cols.put(i, new HashSet<>());
            boxes.put(i, new HashSet<>());
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                int u = c - '0';
                int index = (i / 3) * 3 + j / 3;
                if (rows.get(i).contains(u) || cols.get(j).contains(u) || boxes.get(index).contains(u)) {
                    System.out.println(false);
                    return false;
                }
                rows.get(i).add(u);
                cols.get(j).add(u);
                boxes.get(index).add(u);
            }
        }
        System.out.println(true);
        return true;
    }

    /**
     * https://leetcode-cn.com/problems/nim-game/
     * 9.18
     *
     * @param n 石头数量
     * @return 是否可以赢得游戏
     */
    public static boolean canWinNim(int n) {
        return n % 4 != 0;
    }

    /**
     * https://leetcode-cn.com/problems/2-keys-keyboard/
     * 9.19
     *
     * @param n 数字n
     * @return 对A的最少操作数
     */
    public static int minSteps(int n) {
        int ans = 0;
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                n /= i;
                ans += i;
            }
        }
        if (n > 1) {
            ans += n;
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/
     * 9.20
     *
     * @param nums 整数数组
     * @return 最长递增子序列的个数
     */
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length, maxLen = 0, ans = 0;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        for (int i = 0; i < n; ++i) {
            dp[i] = 1;
            cnt[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j]; // 重置计数
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                ans = cnt[i]; // 重置计数
            } else if (dp[i] == maxLen) {
                ans += cnt[i];
            }
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/length-of-last-word/
     * 9.21
     *
     * @param s 字符串
     * @return 字符串中最后一个单词的长度
     */
    public int lengthOfLastWord(String s) {
        int len = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                len++;
            } else if (len != 0) {
                return len;
            }
        }
        return len;
    }

    /**
     * https://leetcode-cn.com/problems/split-linked-list-in-parts/
     * 9.22
     *
     * @param head 头节点
     * @param k    整数k
     * @return 分隔k个连续的部分
     */
    public ListNode[] splitListToParts(ListNode head, int k) {
        int n = 0;
        ListNode temp = head;
        while (temp != null) {
            n++;
            temp = temp.next;
        }
        int quotient = n / k, remainder = n % k;

        ListNode[] parts = new ListNode[k];
        ListNode curr = head;
        for (int i = 0; i < k && curr != null; i++) {
            parts[i] = curr;
            int partSize = quotient + (i < remainder ? 1 : 0);
            for (int j = 1; j < partSize; j++) {
                curr = curr.next;
            }
            ListNode next = curr.next;
            curr.next = null;
            curr = next;
        }
        return parts;
    }

    /**
     * https://leetcode-cn.com/problems/power-of-three/
     * 9.23
     *
     * @param n 整数n
     * @return 是否是3的幂次方
     */
    public static boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    /**
     * 试除法
     *
     * @param n 整数n
     * @return 是否是3的幂次方
     */
    public static boolean isPowerOfThree2(int n) {
        while (n != 0 && n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    /**
     * https://leetcode-cn.com/problems/flatten-a-multilevel-doubly-linked-list/
     * 9.24
     * 递归解法
     *
     * @param head 头节点
     * @return Node节点
     */
    public static Node flatten(Node head) {
        Node dummy = new Node(0);
        dummy.next = head;
        while (head != null) {
            // head没有child节点，指针直接向后移动
            if (head.child == null) {
                head = head.next;
                // head没有child节点
            } else {
                Node tmp = head.next;
                Node chead = flatten(head.child);
                head.next = chead;
                chead.prev = head;
                head.child = null;
                while (head.next != null) {
                    head = head.next;
                }
                head.next = tmp;
                if (tmp != null) {
                    tmp.prev = head;
                }
                head = tmp;
            }
        }
        return dummy.next;
    }

    /**
     * 迭代解法
     *
     * @param head 头节点
     * @return Node节点
     */
    public static Node flatten2(Node head) {
        Node dummy = new Node(0);
        dummy.next = head;
        while (head != null) {
            // head没有child节点，指针直接向后移动
            if (head.child == null) {
                head = head.next;
                // head没有child节点
            } else {
                Node tmp = head.next;
                Node child = head.child;
                head.next = child;
                child.prev = head;
                head.child = null;
                Node last = head;
                while (last.next != null) {
                    last = last.next;
                }
                last.next = tmp;
                if (tmp != null) {
                    tmp.prev = last;
                }
                head = head.next;
            }
        }
        return dummy.next;
    }

    /**
     * 深度优先搜索
     *
     * @param head 头节点
     * @return Node节点
     */
    public static Node flatten3(Node head) {
        dfs(head);
        return head;
    }

    /**
     * 深度优先搜索
     *
     * @param node 节点
     * @return Node节点
     */
    public static Node dfs(Node node) {
        Node cur = node;
        // 记录链表的最后一个节点
        Node last = null;
        while (cur != null) {
            Node next = cur.next;
            // 如果有子节点，先处理子节点
            if (cur.child != null) {
                Node childLast = dfs(cur.child);
                next = cur.next;
                // node 和 child相连
                cur.next = cur.child;
                cur.child.prev = cur;
                // 如果next不为空，就将last与next相连
                if (next != null) {
                    childLast.next = next;
                    next.prev = childLast;
                }
                // 将child置为空
                cur.child = null;
                last = childLast;
            } else {
                last = cur;
            }
            cur = next;
        }
        return last;
    }
}

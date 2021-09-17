package com.zhuang.Daily.September;

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
        isValidSudoku2(board);
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
}

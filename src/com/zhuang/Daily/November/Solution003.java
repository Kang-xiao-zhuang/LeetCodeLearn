package com.zhuang.Daily.November;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @Classname Solution003
 * @Description 2021.11.16-2021.11.24每日一题
 * @Date 2021/11/16 20:26
 * @Author by dell
 */
public class Solution003 {
    public static void main(String[] args) {
        //int[][] rectangles = {{1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4}, {1, 3, 2, 4}, {2, 3, 3, 4}};
        //isRectangleCover(rectangles);

        //String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        //maxProduct(words);
        int[] nums = {1, 3, 2, 2, 5, 2, 3, 7};
        //findLHS(nums);
        //findLHS2(nums);

        originalDigits("owoztneoer");
    }

    /**
     * https://leetcode-cn.com/problems/perfect-rectangle/
     * 11.16
     *
     * @param rectangles 数组
     * @return 是否精确覆盖了某个矩形区域
     */
    public static boolean isRectangleCover(int[][] rectangles) {
        int n = rectangles.length;
        int[][] rs = new int[n * 2][4];
        for (int i = 0, idx = 0; i < n; i++) {
            int[] re = rectangles[i];
            rs[idx++] = new int[]{re[0], re[1], re[3], 1};
            rs[idx++] = new int[]{re[2], re[1], re[3], -1};
        }
        Arrays.sort(rs, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        n *= 2;
        // 分别存储相同的横坐标下「左边的线段」和「右边的线段」 (y1, y2)
        List<int[]> l1 = new ArrayList<>(), l2 = new ArrayList<>();
        for (int l = 0; l < n; ) {
            int r = l;
            l1.clear();
            l2.clear();
            // 找到横坐标相同部分
            while (r < n && rs[r][0] == rs[l][0]) {
                r++;
            }
            for (int i = l; i < r; i++) {
                int[] e = rs[i];
                int[] cur = new int[]{e[1], e[2]};
                List<int[]> list = e[3] == 1 ? l1 : l2;
                if (list.isEmpty()) {
                    list.add(cur);
                } else {
                    int[] prev = list.get(list.size() - 1);
                    if (cur[0] < prev[1]) {
                        return false; // 存在重叠
                    } else if (cur[0] == prev[1]) {
                        prev[1] = cur[1]; // 首尾相连
                    } else {
                        list.add(cur);
                    }
                }
            }
            if (l > 0 && r < n) {
                // 若不是完美矩形的边缘竖边，检查是否成对出现
                if (l1.size() != l2.size()) {
                    return false;
                }
                for (int i = 0; i < l1.size(); i++) {
                    if (l1.get(i)[0] == l2.get(i)[0] && l1.get(i)[1] == l2.get(i)[1]) {
                        continue;
                    }
                    return false;
                }
            } else {
                // 若是完美矩形的边缘竖边，检查是否形成完整一段
                if (l1.size() + l2.size() != 1) {
                    return false;
                }
            }
            l = r;
        }
        return true;
    }

    /**
     * https://leetcode-cn.com/problems/maximum-product-of-word-lengths/
     * 11.17
     *
     * @param words 字符串数组
     * @return
     */
    public static int maxProduct(String[] words) {
        int length = words.length;
        int[] masks = new int[length];
        for (int i = 0; i < length; i++) {
            String word = words[i];
            int wordLength = word.length();
            for (int j = 0; j < wordLength; j++) {
                masks[i] |= 1 << (word.charAt(j) - 'a');
            }
        }
        int maxProd = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    maxProd = Math.max(maxProd, words[i].length() * words[j].length());
                }
            }
        }
        System.out.println(maxProd);
        return maxProd;
    }

    /**
     * https://leetcode-cn.com/problems/binary-tree-tilt/
     * 11.18
     *
     * @param root 根节点
     * @return 二叉树坡度
     */
    int ans = 0;

    public int findTilt(TreeNode root) {
        dfs(root);
        return ans;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = dfs(root.left), r = dfs(root.right);
        ans += Math.abs(l - r);
        return l + r + root.val;
    }

    /**
     * https://leetcode-cn.com/problems/integer-replacement/
     * 11.19
     *
     * @param n 正整数
     * @return 最小替换次数
     */
    public int integerReplacement(int n) {
        if (n == 1) {
            return 0;
        }
        if (n % 2 == 0) {
            return 1 + integerReplacement(n / 2);
        }
        return 2 + Math.min(integerReplacement((n / 2)), integerReplacement((n / 2) + 1));
    }


    /**
     * https://leetcode-cn.com/problems/longest-harmonious-subsequence/
     * 11.20
     *
     * @param nums 数组
     * @return 最长的和谐子序列
     */
    public static int findLHS(int[] nums) {
        Arrays.sort(nums);
        int begin = 0;
        int res = 0;
        for (int end = 0; end < nums.length; end++) {
            while (nums[end] - nums[begin] > 1) {
                begin++;
            }
            if (nums[end] - nums[begin] == 1) {
                res = Math.max(res, end - begin + 1);
            }
        }
        System.out.println(res);
        return res;
    }

    public static int findLHS2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Integer key : map.keySet()) {
            if (map.containsKey(key + 1)) {
                res = Math.max(res, map.get(key) + map.get(key + 1));
            }
        }
        System.out.println(res);
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/
     * 11.21
     * 深度优先搜索
     *
     * @param root 根节点
     * @return 最大深度
     */
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int maxChildDepth = 0;
        List<Node> children = root.children;
        for (Node child : children) {
            int childDepth = maxDepth(child);
            maxChildDepth = Math.max(maxChildDepth, childDepth);
        }
        return maxChildDepth + 1;
    }

    /**
     * 广度优先搜索
     *
     * @param root 根节点
     * @return 最大深度
     */
    public int maxDepth2(Node root) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        ArrayDeque<Node> deque = new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            while (size > 0) {
                Node node = deque.pollFirst();
                for (Node child : node.children) {
                    deque.addLast(child);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }

    class Solution {
        int[] nums;
        int[] original;

        public Solution(int[] nums) {
            this.nums = nums;
            this.original = new int[nums.length];
            System.arraycopy(nums, 0, original, 0, nums.length);
        }

        public int[] reset() {
            System.arraycopy(original, 0, nums, 0, nums.length);
            return nums;
        }

        public int[] shuffle() {
            int[] shuffled = new int[nums.length];
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < nums.length; ++i) {
                list.add(nums[i]);
            }
            Random random = new Random();
            for (int i = 0; i < nums.length; ++i) {
                int j = random.nextInt(list.size());
                shuffled[i] = list.remove(j);
            }
            System.arraycopy(shuffled, 0, nums, 0, nums.length);
            return nums;
        }
    }

    /**
     * https://leetcode-cn.com/problems/buddy-strings/
     * 11.23
     *
     * @param s    字符串
     * @param goal 字符串
     * @return 是否为亲密字符串
     */
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        if (s.equals(goal)) {
            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
                if (count[s.charAt(i) - 'a'] > 1) {
                    return true;
                }
            }
            return false;
        } else {
            int first = -1, second = -1;
            for (int i = 0; i < goal.length(); i++) {
                if (s.charAt(i) != goal.charAt(i)) {
                    if (first == -1) {
                        first = i;
                    } else if (second == -1) {
                        second = i;
                    } else {
                        return false;
                    }
                }
            }
            return (second != -1 && s.charAt(first) == goal.charAt(second) && s.charAt(second) == goal.charAt(first));
        }
    }

    /**
     * https://leetcode-cn.com/problems/reconstruct-original-digits-from-english/
     * 11.24
     *
     * @param s 字符串
     * @return 升序返回原始的数字
     */
    public static String originalDigits(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int[] cnt = new int[10];
        cnt[0] = map.getOrDefault('z', 0);
        cnt[2] = map.getOrDefault('w', 0);
        cnt[4] = map.getOrDefault('u', 0);
        cnt[6] = map.getOrDefault('x', 0);
        cnt[8] = map.getOrDefault('g', 0);

        cnt[3] = map.getOrDefault('h', 0) - cnt[8];
        cnt[5] = map.getOrDefault('f', 0) - cnt[4];
        cnt[7] = map.getOrDefault('s', 0) - cnt[6];

        cnt[1] = map.getOrDefault('o', 0) - cnt[0] - cnt[2] - cnt[4];
        cnt[9] = map.getOrDefault('i', 0) - cnt[5] - cnt[6] - cnt[8];

        StringBuffer ans = new StringBuffer();
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < cnt[i]; ++j) {
                ans.append((char) (i + '0'));
            }
        }
        System.out.println(ans.toString());
        return ans.toString();
    }
}

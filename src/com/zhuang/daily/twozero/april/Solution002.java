package com.zhuang.daily.twozero.april;

import java.util.*;

/**
 * description: Solution002
 * date: 2022/10/17 15:14
 * author: Zhuang
 * version: 1.0
 */
public class Solution002 {

    public static void main(String[] args) {
        Solution002 solution002 = new Solution002();
        int[] nums = {1, 2, 3};
        //solution002.permute(nums);
        int[] nums2 = {4,1,4,6};
        solution002.singleNumbers(nums2);
    }

    /**
     * https://leetcode.cn/problems/binary-tree-right-side-view/
     * 2020.4.22
     *
     * @param root 根节点
     * @return 集合
     */
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {
                    // 将当层的最后一个节点放入到结果列表
                    list.add(node.val);
                }
            }
        }
        return list;
    }


    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(res, root, 0);
        return res;
    }

    private void dfs(List<Integer> res, TreeNode node, int level) {
        if (node != null) {
            if (res.size() == level) {
                res.add(node.val);
            }
            dfs(res, node.right, level + 1);
            dfs(res, node.left, level + 1);
        }
    }

    /**
     * https://leetcode.cn/problems/permutations/
     * 2020.4.25
     *
     * @param nums 数组
     * @return 全排列
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack(res, list, nums);
        System.out.println(res);
        return res;
    }

    public void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int num : nums) {
            if (!list.contains(num)) {
                list.add(num);
                backtrack(res, list, nums);
                list.remove(list.size() - 1);
            }
        }
    }

    /**
     * https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/
     * 2020.4.28
     *
     * @param nums 整型数组
     * @return 这两个只出现一次的数字
     */
    public int[] singleNumbers(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[2];
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int index=0;
        while (index<2){
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 1) {
                    ans[index] = entry.getKey();
                    index++;
                }
            }
        }
        System.out.println(Arrays.toString(ans));
        return ans;
    }
}

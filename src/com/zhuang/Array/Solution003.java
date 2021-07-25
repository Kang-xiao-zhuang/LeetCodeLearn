package com.zhuang.Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @Classname Solution003
 * @Description 数组刷题学习
 * @Date 2021/7/24 16:45
 * @Author Zhuang
 */

public class Solution003 {
    public static void main(String[] args) {
      //  int[]nums1 = {4,9,5};
      //  int[]nums2 = {9,4,9,8,4};
     //   intersect(nums1,nums2);
     //   intersect2(nums1,nums2);

     //   int[] prices={7,1,5,3,6,4};
     //   maxProfit(prices);
     //   maxProfit2(prices);

    //    int[][]  arr ={{1,2},{3,4,}};
    //    matrixReshape(arr,1,4);

    //    int[] nums={2,2,1};
   //     singleNumber(nums);


   //     int[] nums={5,1,5,2,5,3,5,4};
   //     repeatedNTimes(nums);

        int[] arr={-3,0,1,-3,1,1,1,-3,10,0};
        uniqueOccurrences(arr);
    }

    /**
     * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
     * @param nums1 数组1
     * @param nums2 数组2
     * @return int数组
     *
     * 双指针法
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        // 新建数组的大小为两个数组中较小的容量
        int[] intersection=new int[Math.min(nums1.length,nums2.length)];
        // 定义指针
        int index1=0;
        int index2 = 0;
        int index=0;
        while (index1< nums1.length&& index2< nums2.length){
            if (nums1[index1] <nums2[index2]){
                index1++;
            }else if (nums1[index1] >nums2[index2]) {
                index2++;
            }else {
                intersection[index]=nums1[index1];
                index1++;
                index2++;
                index++;
            }
        }
        System.out.println(Arrays.toString(Arrays.copyOfRange(intersection,0,index)));
        return Arrays.copyOfRange(intersection,0,index);
    }

    /**
     * 哈希表法
     * @param nums1 数组1
     * @param nums2 数组2
     * @return int数组
     */
    public static int[] intersect2(int[] nums1, int[] nums2) {
        // 这步骤是为了节省空间
        if(nums1.length> nums2.length){
            return  intersect2(nums2, nums1);
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums1) {
            int count=map.getOrDefault(num, 0)+1;
            map.put(num, count);
        }
        int[] intersection=new int[nums1.length];
        int index = 0;
        for (int num : nums2) {
            int count=map.getOrDefault(num, 0);
            if (count > 0){
                intersection[index++]=num;
                count--;
                map.put(num, count);
            }else {
                map.remove(num);
            }
        }
        System.out.println(Arrays.toString(Arrays.copyOfRange(intersection,0,index)));
        return Arrays.copyOfRange(intersection,0,index);
    }

    /**
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
     * @param prices 数组
     * @return int
     */
    public static int maxProfit(int[] prices) {
        int maxProfit=0;
        for (int i = 0; i < prices.length-1; i++) {
            for (int j = i+1; j < prices.length; j++) {
                int profit=prices[j]-prices[i];
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        System.out.println(maxProfit);
        return maxProfit;
    }

    /**
     * 第一种方法会超时，参考了下别人的代码
     * @param prices 数组
     * @return int
     */
    public static int maxProfit2(int[] prices) {
        //表示当前股票的最低价格
        int min = prices[0];
        //表示当前股票收益最大值
        int maxProfit= 0;
        for(int i = 1; i <= prices.length - 1; i++){
            min = Math.min(min, prices[i]);
            maxProfit = Math.max(prices[i] - min, maxProfit);
        }
        System.out.println(maxProfit);
        return maxProfit;
    }

    /**
     * https://leetcode-cn.com/problems/reshape-the-matrix/
     *
     * @param mat 数组
     * @param r 值1
     * @param c 值2
     * @return 二维数组
     */
    public static int[][] matrixReshape(int[][] mat, int r, int c) {
        int a=mat.length;
        int b=mat[0].length;
        if (a*b!=r*c){
            return mat;
        }
        int[][] res=new int[r][c];
        for (int i=0;i<a*b;i++){
            res[i/c][i%c]=mat[i/b][i % b];
        }
        System.out.println(Arrays.deepToString(res));
        return  res;
    }

    /**
     * https://leetcode-cn.com/problems/single-number/
     *
     * @param nums 数组
     * @return int
     */
    public static int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        System.out.println(single);
        return single;
    }

    /** https://leetcode-cn.com/problems/n-repeated-element-in-size-2n-array/
     *
     * @param nums 数组
     * @return int
     */
    public static int repeatedNTimes(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0)+1);
        }
        for (Integer integer : map.keySet()) {
            if (map.get(integer) >1){
                System.out.println(integer);
                return integer;
            }
        }
        return 0;
    }

    /**
     * https://leetcode-cn.com/problems/unique-number-of-occurrences/
     * @param arr 数组
     * @return 布尔
     */
    public static boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        HashSet<Integer> set = new HashSet<>();
        for (Map.Entry<Integer, Integer> integerEntry : map.entrySet()) {
            set.add(integerEntry.getValue());
        }
        System.out.println(map.size()==set.size());
        return map.size()==set.size();
    }
}

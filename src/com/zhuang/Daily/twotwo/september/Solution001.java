package com.zhuang.Daily.twotwo.september;

public class Solution001 {
    public static void main(String[] args) {

    }


    /**
     * https://leetcode.cn/problems/crawler-log-folder/
     * 9.9
     *
     * @param logs 字符串列表
     * @return 返回主文件夹所需的最小步数
     */
    public int minOperations(String[] logs) {
        int depth=0;
        for (String log : logs) {
            if ("./".equals(log)){
                continue;
            }else if ("../".equals(log)){
                if (depth>0){
                    depth--;
                }
            }
            else {
                depth++;
            }
        }
        return depth;
    }
}

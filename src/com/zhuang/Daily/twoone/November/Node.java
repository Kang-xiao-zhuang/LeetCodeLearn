package com.zhuang.Daily.twoone.November;

import java.util.List;

/**
 * @Classname Node
 * @Description 用一句话描述类的作用
 * @Date 2021/11/21 12:24
 * @Author by dell
 */

public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}

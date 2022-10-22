package com.zhuang.tree;

import java.util.List;

/**
 * description: Node
 * date: 2022/10/22 14:59
 * author: Zhuang
 * version: 1.0
 */
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}

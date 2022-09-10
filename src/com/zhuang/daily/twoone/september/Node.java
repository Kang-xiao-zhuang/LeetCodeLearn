package com.zhuang.daily.twoone.september;

/**
 * @Classname Node
 * @Description Definition for a Node.
 * @Date 2021/9/24 9:28
 * @Author by Zhuang
 */
public class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node(int val) {
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", prev=" + prev +
                ", next=" + next +
                ", child=" + child +
                '}';
    }
}
